package com.msuploadarquivos.controllers;

import com.msuploadarquivos.components.FileValidator;
import com.msuploadarquivos.exceptions.ErrorResponse;
import com.msuploadarquivos.exceptions.TooManyFilesException;
import com.msuploadarquivos.model.response.SuccessResponse;
import com.msuploadarquivos.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

    FileValidator fileValidator = new FileValidator();
    @Autowired
    ImageService imageService;

    @PostMapping("/upload-img")
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile[] images) {
        if (images.length != 1) throw new TooManyFilesException();
        try {
            if (fileValidator.imageValidator(images) == true) {
                imageService.saveImage(images);
                SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED.value(), "Imagem enviada");
                return ResponseEntity.status(HttpStatus.CREATED).body((successResponse));
            }
            throw new Exception();
        } catch (MultipartException e) {
            throw new MultipartException("Tipo de arquivo não permitido!");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível processar a imagem...");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((errorResponse));
        }


    }


}
