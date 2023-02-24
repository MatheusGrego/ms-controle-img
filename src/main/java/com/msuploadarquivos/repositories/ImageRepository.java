package com.msuploadarquivos.repositories;

import com.msuploadarquivos.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
