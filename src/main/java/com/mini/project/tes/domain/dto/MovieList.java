package com.mini.project.tes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mini.project.tes.domain.DetailsMovieEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieList {
    private Long id;
    private String title;
    private String category;

    @JsonIgnoreProperties("movieList")
    private DetailsMovieEntity detailsMovie;
}
