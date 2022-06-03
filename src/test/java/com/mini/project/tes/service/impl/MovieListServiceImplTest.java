package com.mini.project.tes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.model.entity.SamUserEntity;
import com.mini.project.tes.repository.MovieListRepository;
import com.mini.project.tes.service.MovieListService;
import com.mini.project.tes.util.BaseHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovieListServiceImplTest {
    @Mock private MovieListRepository repo;

    private MovieListServiceImpl underTest;

    BaseHelper baseHelper=new BaseHelper();

    @BeforeEach
    void setUp() {
        underTest= new MovieListServiceImpl(repo);
    }


    @Test
    @Disabled
    void findById() {
    }

    @Test
    void canGetAllMovies() {
        //when
        underTest.findAll();
        //then
        verify(repo).findAll();

    }

    @Test
    @Disabled
    void findByCategory() {
    }

    @Test
    @Disabled
    void canAddUser() throws Exception {
        //given
        MovieListEntity movie=new MovieListEntity(
                Long.parseLong("2"),
                "Spiderman The Movie",
                "Hero",9,"aku belajar"
        );
//        movie.setId(Long.parseLong("1"));
//        movie.setDetails("user");
//        movie.setTitle("Spiderman The Movie");
//        movie.setCategory("Hero");
        //when
        underTest.save(movie);

        //then
        ArgumentCaptor<MovieListEntity> movieListEntityArgumentCaptor =
                ArgumentCaptor.forClass(MovieListEntity.class);
        verify(repo)
                .save(movieListEntityArgumentCaptor.capture());
        MovieListEntity captureMovie=movieListEntityArgumentCaptor.getValue();
        assertThat(captureMovie).isEqualTo(movie);


    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void delete() {
    }
}