package com.mini.project.tes.persistence.repository;

import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.TheMovieDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface TheMovieDbRepository extends JpaRepository<TheMovieDbEntity, Integer> {
}
