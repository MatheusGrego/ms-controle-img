package com.msuploadimg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class FileUploadExceptionController {

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handlerTamanhoMaximo(MaxUploadSizeExceededException e) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Permitimos arquivos até " + e.getMaxUploadSize() + " MB!");
    }

}
