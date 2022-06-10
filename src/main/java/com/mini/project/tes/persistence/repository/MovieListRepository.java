package com.mini.project.tes.persistence.repository;

import com.mini.project.tes.domain.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface MovieListRepository extends JpaRepository<MovieListEntity, Long> {
    List<MovieListEntity> findByCategory(String category);
}
