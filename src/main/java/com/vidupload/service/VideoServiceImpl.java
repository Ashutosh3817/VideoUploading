package com.vidupload.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidupload.entity.Video;
import com.vidupload.exception.ResourceNotFoundException;
import com.vidupload.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Override
	public Video createPost(Video video) {
		if (video.getTitle() == null || video.getTitle().isEmpty()) {
			throw new ResourceNotFoundException(false, "Video title cannot be null or empty");
		}
		video.setAddedDate(LocalDateTime.now());
		return videoRepository.save(video);
	}

	@Override
	public Video getById(Long id) {
		return videoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(false, "Video doesn't exist with given id"));
	}

	@Override
	public Video updatePost(Video video, Long id) {
		Video existingVideo = videoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(false, "Video doesn't exist with given id"));
		existingVideo.setAddedDate(LocalDateTime.now());
		existingVideo.setTitle(video.getTitle());
		existingVideo.setDescription(video.getDescription());
		existingVideo.setTags(video.getTags());
		existingVideo.setVideoName(video.getVideoName());
		existingVideo.setIsPremium(video.getIsPremium());
		return videoRepository.save(existingVideo);
	}

	@Override
	public void deleteVideos(Long id) {
		Video video = videoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(false, "Video doesn't exist with given id"));
		videoRepository.delete(video);
	}

	@Override
	public List<Video> getAllPost() {
		return videoRepository.findAll();
	}
}

