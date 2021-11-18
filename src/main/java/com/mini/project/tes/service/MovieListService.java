package com.mini.project.tes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.exception.FileStorageException;
import com.mini.project.tes.model.entity.MovieListEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
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
//    public MovieListEntity storeFile(MultipartFile file,MovieListEntity movieListEntity) throws FileStorageException, IOException;
}
