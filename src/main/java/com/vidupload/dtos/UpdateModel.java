package com.vidupload.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateModel {
    private Long id;
    private String title;
    private String tags;
    private String description;
}
