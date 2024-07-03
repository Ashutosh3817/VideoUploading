package com.vidupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vidupload.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    
}