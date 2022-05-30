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


}
