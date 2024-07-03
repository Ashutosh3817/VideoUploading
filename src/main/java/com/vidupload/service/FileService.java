package com.vidupload.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.vidupload.dtos.FileModel;

public interface FileService {

//for uploading the file

	FileModel uploadVideo(String path,MultipartFile file) throws IOException;

	//convert incoming inputs to bytes
	InputStream getVideoFile(String path,String fileName,Long id) throws FileNotFoundException;

}
/*
A MultipartFile in Spring represents a file uploaded through a multipart request. When you're dealing with web applications that allow users to upload files (like images, videos, documents),
you often handle these files using multipart requests. Here's a breakdown of what MultipartFile is and how it's used:

What is MultipartFile?
Definition: MultipartFile is an interface in Spring Framework that provides access to uploaded file content.
It represents a file or set of files uploaded with a multipart/form-data request.

Purpose: It encapsulates the file attributes such as the file name, content type, and file content itself.
 This allows Spring applications to handle file uploads conveniently.

Usage: You typically use MultipartFile in Spring MVC controllers where you define methods to handle multipart requests.
 These methods receive MultipartFile parameters to access and process the uploaded files.
 */
