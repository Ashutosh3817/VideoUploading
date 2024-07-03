package com.vidupload.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vidupload.dtos.FileModel;

@Service
public class FileServiceImpl implements FileService{



	@Override

	public FileModel uploadVideo(String path, MultipartFile file) throws IOException {

		//how we generate the directory

		FileModel fileModel = new FileModel();

		//get the default filename

		String fileName = file.getOriginalFilename();

		//when we uplaod the video then we have to unique name of the video

		String randomId = UUID.randomUUID().toString();



		String finalName = randomId.concat(fileName).substring(fileName.indexOf("."));
		//generated the exact filename //every video filename should be unique
		String filePath= path + File.separator + finalName;

		//create directory where we save the file



		File f = new File(path);



		//if directory exist then we won't create the directory else we create the directory

		if(!f.exists()) {

			f.mkdir();

		}
		Files.copy(file.getInputStream(), Paths.get(filePath));

		fileModel.setVideoFileName(finalName);

		return fileModel;

	}



	@Override

	public InputStream getVideoFile(String path, String fileName, Long id) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;



	}



}