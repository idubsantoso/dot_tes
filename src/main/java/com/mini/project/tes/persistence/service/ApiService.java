package com.mini.project.tes.persistence.service;

import com.mini.project.tes.domain.TheMovieDbEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ApiService {
    void schedule() throws IOException, URISyntaxException;
    TheMovieDbEntity save(TheMovieDbEntity detailsMovieEntity);
}
