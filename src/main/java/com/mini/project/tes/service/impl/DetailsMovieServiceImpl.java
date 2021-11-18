package com.mini.project.tes.service.impl;

import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.repository.DetailsMovieRepository;
import com.mini.project.tes.service.DetailsMovieService;
import com.mini.project.tes.service.error.ServiceException;
import com.mini.project.tes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DetailsMovieServiceImpl implements DetailsMovieService {
    @Autowired
    private DetailsMovieRepository repo;
    @Autowired
    private LogUtil logUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public DetailsMovieEntity findById(Long id) {
        try {
            DetailsMovieEntity entity = repo.findById(id).get();
            return entity;
        } catch (Exception e) {
            log.error("Error find by id - "+ id.toString() + " in " + this.getClass().getName() + " : " + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public List<DetailsMovieEntity> findAll() {
        try {
            List<DetailsMovieEntity> entities = repo.findAll();
            return entities;
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public DetailsMovieEntity save(DetailsMovieEntity detailsMovieEntity) {
        try {
            DetailsMovieEntity entity = repo.saveAndFlush(detailsMovieEntity);
            logUtil.success(this.getClass().getName() +" [Save]", "id: " + detailsMovieEntity.getId());

            return entity;
        } catch (Exception e) {
            log.error("Error save in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
