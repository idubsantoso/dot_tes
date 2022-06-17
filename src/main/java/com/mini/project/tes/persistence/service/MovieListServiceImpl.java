package com.mini.project.tes.persistence.service;


import com.mini.project.tes.domain.MovieListEntity;
import com.mini.project.tes.persistence.service.error.ServiceException;
import com.mini.project.tes.persistence.repository.MovieListRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@AllArgsConstructor
@Transactional
@Service
public class MovieListServiceImpl implements MovieListService {

    @Autowired
    private final MovieListRepository repo;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public MovieListEntity findById(Long id) {
        try {
            return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found"));
        } catch (Exception e) {
            log.error("Error find by id - "+ id.toString() + " in " + this.getClass().getName() + " : " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public List<MovieListEntity> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public List<MovieListEntity> findByCategory(String category) {
        try {
            return repo.findByCategory(category);
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public MovieListEntity save(MovieListEntity movieListEntity) {
        try {
            return repo.save(movieListEntity);
        } catch (Exception e) {
            log.error("Error save in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public MovieListEntity update(MovieListEntity movieListEntity) {
        try {
            MovieListEntity entity = repo.findById(movieListEntity.getId()).orElseThrow(() -> new NoSuchElementException("Movie not found"));
            entity.setCategory(movieListEntity.getCategory());
            entity.setTitle(movieListEntity.getTitle());
            entity.setDetails(movieListEntity.getDetails());
            entity.setRate(movieListEntity.getRate());
            return repo.saveAndFlush(entity);
        } catch (Exception e) {
            log.error("Error update in " + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }


    @Override
    public void delete(MovieListEntity movieListEntity) {
        try {
            repo.deleteById(movieListEntity.getId());
        } catch (Exception e) {
            log.info("Error delete in " + this.getClass().getName() + " :" + e.getMessage());
        }
    }
    @Override
    public MovieListEntity getFile(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    @Override
    public Stream<MovieListEntity> getAllFiles() {
        return repo.findAll().stream();
    }

    @Override
    public void jobDelete(){
        List<MovieListEntity> entities = repo.findAll();
        if(entities.size()>0){
            for (MovieListEntity movieListEntity:entities){
                delete(movieListEntity);
            }
        }
    }

}
