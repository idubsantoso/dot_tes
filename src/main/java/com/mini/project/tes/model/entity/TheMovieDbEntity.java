package com.mini.project.tes.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "the_movie_db")
public class TheMovieDbEntity {
    @Id
    private Integer id;
    private Boolean adult;
    private String backdrop_path;
    private String belongs_to_collection;
    private BigDecimal budget;
    private String genres;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    @Lob
    private String overview;
    private BigDecimal popularity;
    private String poster_path;
    private String production_companies;
    private String production_countries;
    private Date release_date;
    private Integer revenue;
    private Integer runtime;
    private String spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private String video;
    private BigDecimal vote_average;
    private Integer vote_count;

}
