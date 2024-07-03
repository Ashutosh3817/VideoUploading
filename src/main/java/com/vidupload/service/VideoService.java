package com.vidupload.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vidupload.entity.Video;


public interface VideoService {
	
	public Video createPost(Video video);
	
	public Video getById(Long id);
	public Video updatePost(Video video,Long id);
	public void deleteVideos(Long id);
	public List<Video> getAllPost();
}
