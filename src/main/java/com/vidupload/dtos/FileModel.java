package com.vidupload.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class FileModel {

	private String videoFileName;

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public FileModel(String videoFileName) {
		super();
		this.videoFileName = videoFileName;
	}

	public FileModel() {
		super();
// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FileModel [videoFileName=" + videoFileName + "]";
	}


}

