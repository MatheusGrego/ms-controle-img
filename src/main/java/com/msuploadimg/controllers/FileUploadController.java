package com.msuploadimg.controllers;

import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

    @PostMapping("/upload-img")
    public ResponseEntity<String> uploadImage(@RequestPart("image") MultipartFile[] images) {
        if (images.length != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Envie somente uma imagem!");
        }

        try {
            // Validando tamanho da imagem (1   MB máximo)
            MultipartFile image = images[0];
            if (image.getSize() > 1048576) {
                throw new MaxUploadSizeExceededException(1048576);
            }

            // Validando tipo mime da imagem
            String mimeType = new Tika().detect(image.getBytes());
            if (!mimeType.startsWith("image/")) {
                throw new MultipartException("Teste");
            }

            return ResponseEntity.ok("Imagem enviada!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível processar a imagem...");
        }


    }
}
