package com.msuploadarquivos.components;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator {
    public boolean imageValidator(MultipartFile[] images) throws Exception {
        MultipartFile image = images[0]; // Validando tamanho da imagem (1   MB máximo)
        if (image.getSize() > 1048576) throw new MaxUploadSizeExceededException(1048576);
        // Validando tipo mime da imagem

        String mimeType = new Tika().detect(image.getBytes());
        if (!mimeType.startsWith("image/")) throw new MultipartException("Tipo de arquivo não permitido!");


        return true;
    }
}
