package com.mini.project.tes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "DETAILS_MOVIE")
public class DetailsMovieEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Long id;
    @Column(name = "URL", nullable = true)
    private String url;
//    @Column(name = "TYPE", nullable = true)
//    private String type;
    @Column(name = "BYTE", nullable = true)
    private byte[] bytes;
    @Column(name = "FILE_NAME", nullable = true)
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "movie_list_id")
    private MovieListEntity movieList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MovieListEntity getMovieList() {
        return movieList;
    }

    public void setMovieList(MovieListEntity movieList) {
        this.movieList = movieList;
    }
}
