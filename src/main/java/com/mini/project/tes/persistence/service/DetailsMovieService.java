package com.mini.project.tes.persistence.service;

import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.MovieListEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DetailsMovieService {
    DetailsMovieEntity save(DetailsMovieEntity detailsMovieEntity);
    DetailsMovieEntity findById(Long id);
    List<DetailsMovieEntity> findAll();
    MovieListEntity uploadFile(String uploadDir, MultipartFile file, MovieListEntity result) throws IOException;
}
