package com.mini.project.tes.service;

import com.mini.project.tes.model.entity.DetailsMovieEntity;

import java.util.List;

public interface DetailsMovieService {
    DetailsMovieEntity save(DetailsMovieEntity detailsMovieEntity);
    DetailsMovieEntity findById(Long id);
    List<DetailsMovieEntity> findAll();
}
