package com.mini.project.tes.repository;

import com.mini.project.tes.model.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource
public interface MovieListRepository extends JpaRepository<MovieListEntity, Long> {
    List<MovieListEntity> findByCategory(String category);
}
