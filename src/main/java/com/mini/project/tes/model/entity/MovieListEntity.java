package com.mini.project.tes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "MOVIE_LIST")
public class MovieListEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=true)
    private Long id;
    @Column(name = "TITLE", nullable = true)
    private String title;
    @Column(name = "CATEGORY", nullable = true)
    private String category;
    @Column(name = "RATE", nullable = true)
    private Integer rate;
    @Lob
    @Column(name = "DETAILS", nullable = true)
    private String details;



}
