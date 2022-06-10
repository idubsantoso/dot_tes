package com.mini.project.tes.persistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.domain.MovieListEntity;

import java.util.List;
import java.util.stream.Stream;

public interface MovieListService {
    public MovieListEntity findById (Long id);
    public List<MovieListEntity> findAll();
    public MovieListEntity save(MovieListEntity movieList) throws JsonProcessingException;
    public MovieListEntity update(MovieListEntity movieList) throws JsonProcessingException;
    public void delete(MovieListEntity movieList) throws JsonProcessingException;
    public MovieListEntity getFile(Long id);
    public Stream<MovieListEntity> getAllFiles();
    List<MovieListEntity> findByCategory(String category);
    void jobDelete();
//    public MovieListEntity storeFile(MultipartFile file,MovieListEntity movieListEntity) throws FileStorageException, IOException;
}
