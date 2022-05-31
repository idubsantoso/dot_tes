package com.mini.project.tes.service;

import com.mini.project.tes.model.entity.TheMovieDbEntity;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ApiService {
    void schedule() throws IOException, URISyntaxException;
    TheMovieDbEntity save(TheMovieDbEntity detailsMovieEntity);
}
