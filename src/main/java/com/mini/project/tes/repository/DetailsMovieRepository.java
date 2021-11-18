package com.mini.project.tes.repository;

import com.mini.project.tes.model.entity.DetailsMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface DetailsMovieRepository extends JpaRepository<DetailsMovieEntity, Long> {
}
