package com.mini.project.tes.persistence.service;

import com.mini.project.tes.domain.DetailsMovieEntity;
import com.mini.project.tes.domain.MovieListEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DetailsMovieService {
    DetailsMovieEntity save(DetailsMovieEntity detailsMovieEntity);
    DetailsMovieEntity findById(Long id);
    List<DetailsMovieEntity> findAll();
    MovieListEntity uploadFile(String uploadDir, MultipartFile file, MovieListEntity result) throws IOException;
}
