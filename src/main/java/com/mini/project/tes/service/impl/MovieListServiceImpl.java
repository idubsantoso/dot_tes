package com.mini.project.tes.service.impl;


import com.mini.project.tes.config.file.FileStorageProperties;
import com.mini.project.tes.exception.FileStorageException;
import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.repository.DetailsMovieRepository;
import com.mini.project.tes.service.MovieListService;
import com.mini.project.tes.repository.MovieListRepository;
import com.mini.project.tes.service.error.ServiceException;
import com.mini.project.tes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service
public class MovieListServiceImpl implements MovieListService {
    private Path fileStorageLocation;

    @Autowired
    private MovieListRepository repo;

    @Autowired
    private DetailsMovieRepository detailsMovieRepository;

    @Autowired
    private LogUtil logUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


//    public MovieListServiceImpl(FileStorageProperties fileStorageProperties) throws FileStorageException {
//        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                .toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }

    @Override
    public MovieListEntity findById(Long id) {
        try {
            MovieListEntity entity = repo.findById(id).get();
            return entity;
        } catch (Exception e) {
            log.error("Error find by id - "+ id.toString() + " in " + this.getClass().getName() + " : " + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }


    @Override
    public List<MovieListEntity> findAll() {
        try {
            List<MovieListEntity> entities = repo.findAll();
            return entities;
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public List<MovieListEntity> findByCategory(String category) {
        try {
            List<MovieListEntity> entities = repo.findByCategory(category);
            return entities;
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public MovieListEntity save(MovieListEntity movieListEntity) {
        try {
            MovieListEntity entity = repo.saveAndFlush(movieListEntity);
            logUtil.success(this.getClass().getName() +" [Save]", "id: " + movieListEntity.getId());

            return entity;
        } catch (Exception e) {
            log.error("Error save in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public MovieListEntity update(MovieListEntity movieListEntity) {
        try {
            MovieListEntity entity = repo.findById(movieListEntity.getId()).get();
            entity.setCategory(movieListEntity.getCategory());
            entity.setTitle(movieListEntity.getTitle());
            entity.setDetails(movieListEntity.getDetails());
            entity.setRate(movieListEntity.getRate());

            MovieListEntity entityResult = repo.saveAndFlush(entity);
            logUtil.success(this.getClass().getName() +" [Update]", "id: " + entity.getId());
            return entityResult;
        } catch (Exception e) {
            log.error("Error update in " + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }


    @Override
    public void delete(MovieListEntity movieListEntity) {
        try {
            repo.deleteById(movieListEntity.getId());
            logUtil.success(this.getClass().getName() +" [Delete]", "id: " + movieListEntity.getId());
        } catch (Exception e) {
            log.info("Error delete in " + this.getClass().getName() + " :" + e.getMessage());
        }
    }
    @Override
    public MovieListEntity getFile(Long id) {
        return repo.findById(id).get();
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
