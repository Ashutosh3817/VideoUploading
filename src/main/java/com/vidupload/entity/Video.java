package com.vidupload.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


	private Boolean isPremium;

	@Override
	public String toString() {
		return "Video{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", tags='" + tags + '\'' +
				", videoName='" + videoName + '\'' +
				", addedDate=" + addedDate +
				", isPremium=" + isPremium +
				'}';
	}
}
