package com.mini.project.tes.persistence.repository;

import com.mini.project.tes.config.security.entity.SamUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
public interface SamUserRepository extends JpaRepository<SamUserEntity, Long>, JpaSpecificationExecutor<SamUserEntity> {
	@Query(value = "select * from sam_user e where e.id_row =:id_row order by create_date desc ",nativeQuery = true)
    SamUserEntity findByIdEntity(@Param("id_row") int id_row);

    public SamUserEntity getById(Long id);
    public SamUserEntity findByUsername(String username);

}