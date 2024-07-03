package com.vidupload.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videoprocess")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for auto-increment in MySQL
	private Long id;

	@Column(name = "vid_title", nullable = false, length = 50)
	private String title;

	@Column(name = "description", length = 200) // Renamed from "desc" to avoid SQL keyword conflict
	private String description;

	private String tags;

	@Column(name = "vid_name")
	private String videoName;

	@Column(name = "added_date")
	private LocalDateTime addedDate;

	// Constructors and toString() method as shown in your code

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", description=" + description + ", tags=" + tags
				+ ", videoName=" + videoName + ", addedDate=" + addedDate + "]";
	}

	// Constructor with all fields except 'id'
	public Video(String title, String description, String tags, String videoName, LocalDateTime addedDate) {
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.videoName = videoName;
		this.addedDate = addedDate;
	}

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}


}
