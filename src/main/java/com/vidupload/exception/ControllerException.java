package com.vidupload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ControllerException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

}
