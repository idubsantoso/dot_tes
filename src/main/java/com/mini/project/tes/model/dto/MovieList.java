package com.mini.project.tes.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mini.project.tes.model.entity.DetailsMovieEntity;


public class MovieList {
    private Long id;
    private String title;
    private String category;

    @JsonIgnoreProperties("movieList")
    private DetailsMovieEntity detailsMovie;

    public MovieList() {
    }

    public MovieList(Long id, String title, String fileName, String category, DetailsMovieEntity detailsMovie) {
        this.id = id;
        this.title = title;

        this.category = category;
        this.detailsMovie = detailsMovie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DetailsMovieEntity getDetailsMovie() {
        return detailsMovie;
    }

    public void setDetailsMovie(DetailsMovieEntity detailsMovie) {
        this.detailsMovie = detailsMovie;
    }
}
