package com.vidupload.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.List;

import com.vidupload.dtos.UpdateModel;
import com.vidupload.exception.ControllerException;
import com.vidupload.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.vidupload.dtos.FileModel;
import com.vidupload.entity.Video;
import com.vidupload.repository.VideoRepository;
import com.vidupload.service.FileService;
import com.vidupload.service.VideoService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin("http://localhost:1234")
public class VideoController {

	//use path defined in application.properties for this we use spring expression language
	@Value("${project.video}")
	private String path;

	@Autowired
	private VideoService videoService;

	@Autowired
	private FileService fileService;

	@Autowired
	private VideoRepository videoRepository;

	@PostMapping("/save")
	public ResponseEntity<Video> saveVideo(@RequestBody Video video) {
		Video savedVideo = videoService.createPost(video);
		return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
	}

	@SuppressWarnings("unused")
	@GetMapping("/allVideos")
	public ResponseEntity<List<Video>> getAllVideos() {
		List<Video> allVideos = videoService.getAllPost();
		return new ResponseEntity<>(allVideos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
		Video video = videoService.getById(id);
		return new ResponseEntity<>(video, HttpStatus.OK);
	}

	//API for uploading the video after posting the titles and other video fields
	//for uploading we will accept the video from the backend
	//3. Posting Video api .
//	@PostMapping("/upload/{id}")
//	public ResponseEntity<Video> uploadVideo(@RequestParam("video") MultipartFile video, @PathVariable Long id)
//			throws IOException {
//		Video v = this.videoService.getById(id);
//		FileModel fileModel = this.fileService.uploadVideo(path, video);
//		v.setVideoName(fileModel.getVideoFileName());
//		Video uploadVideo = this.videoService.updatePost(v, id);
//		return new ResponseEntity<Video>(uploadVideo, HttpStatus.OK);
//	}

	//In form-date key section we write video as a key after selecting the video because in RequestParam we pass the video

	@PostMapping("/upload/{id}")
	public ResponseEntity<Video> uploadVideo(@RequestParam("video") MultipartFile video, @PathVariable Long id)
			throws IOException {
		if (video.isEmpty()) {
			throw new IllegalArgumentException("No video file uploaded");
			// Alternatively, you can customize the exception handling or return a specific ResponseEntity
			// return ResponseEntity.badRequest().body("No video file uploaded");
		}

		Video v = this.videoService.getById(id);
		FileModel fileModel = this.fileService.uploadVideo(path, video);
		v.setVideoName(fileModel.getVideoFileName());
		Video uploadVideo = this.videoService.updatePost(v, id);
		return new ResponseEntity<>(uploadVideo, HttpStatus.OK);
	}
	//API FOR SERVING THE VIDEO FILE ON THE UI
	//we want the return type because we want to play the video not anything else
	//we fetch the video in bytes format not in the format of text or string so fetch video with the help of input stream
	@GetMapping(value="/play/{id}",produces=MediaType.ALL_VALUE)
	public void playVideo(@PathVariable Long id,HttpServletResponse response) throws IOException{
		Optional<Video> video = videoRepository.findById(id);

		//we receive the video in the inputstream file
		InputStream resource = fileService.getVideoFile(path,video.get().getVideoName(), id);
		response.setContentType(MediaType.ALL_VALUE);

		org.springframework.util.StreamUtils.copy(resource,response.getOutputStream());

	}

	/*
In Spring Framework, org.springframework.util.StreamUtils.copy() is a utility method that provides a convenient way to copy data
from an InputStream to an OutputStream. This method is useful in various scenarios where you need to handle streaming data,
such as file uploads, downloading files, or any other operation involving data streams
.*/

	//Delete videos By the given id
	@DeleteMapping("{id}")
	public String deleteVideo(@PathVariable Long id){
		Optional<Video> vid = videoRepository.findById(id);
		Path exactPath = Paths.get(path + File.separator + vid.get().getVideoName());
        System.out.println(exactPath);

		try{
			Files.deleteIfExists(exactPath);
		}
		catch(Exception e1){
			e1.getMessage();
			System.out.println(e1.getMessage()+"video can't be deleted");
		}
		this.videoService.deleteVideos(id);

		return "Video deleted successfully...";
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Video> setVideoData(@RequestBody Video updateModel,@PathVariable Long id){
		try{
			videoService.updatePost(updateModel,id);
			return new ResponseEntity<Video>(updateModel,HttpStatus.OK);
		}
		catch(Exception e){
			throw new ControllerException("404","user id is not found");
		}
	}


	// Exception handler for MissingServletRequestPartException
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<String> handleMissingPartException(MissingServletRequestPartException ex) {
		String error = "Required part 'video' is not present. Please upload a video file.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}