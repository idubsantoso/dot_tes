package com.mini.project.tes.persistence.service.impl;

import com.mini.project.tes.domain.MovieListEntity;
import com.mini.project.tes.persistence.service.MovieListServiceImpl;
import com.mini.project.tes.persistence.repository.MovieListRepository;
import com.mini.project.tes.utility.BaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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