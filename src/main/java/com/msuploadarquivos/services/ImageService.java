package com.msuploadarquivos.services;

import com.msuploadarquivos.exceptions.ImageNotFoundException;
import com.msuploadarquivos.model.Image;
import com.msuploadarquivos.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;


@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public String saveLocal(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        String path = "src/main/resources/static/" + name;

        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(file.getBytes());
        }


        return path;
    }

    public void saveImage(MultipartFile[] file) throws Exception {
        String path = saveLocal(file[0]);
        Image image = new Image();


        image.setName(file[0].getOriginalFilename());
        image.setPath(path);
        if (image.getPath() != null) imageRepository.save(image);
    }

    public Resource getImage(Long id) throws Exception {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
        String path = image.getPath();
        if (path == null || path.length() == 0) throw new ImageNotFoundException(id);

        File file = new File(path);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new ByteArrayResource(bytes);

    }
}
