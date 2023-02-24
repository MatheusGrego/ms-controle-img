package com.msuploadarquivos.services;

import com.msuploadarquivos.model.Image;
import com.msuploadarquivos.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public void saveImage(MultipartFile[] file) throws Exception {
        Image image = new Image();

        image.setName(file[0].getOriginalFilename());
        image.setContent(file[0].getBytes());

        imageRepository.save(image);
    }
}
