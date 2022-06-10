package com.mini.project.tes.persistence.service;


import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.persistence.service.error.ServiceException;
import com.mini.project.tes.persistence.repository.MovieListRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Transactional
@Service
public class MovieListServiceImpl implements MovieListService {

    @Autowired
    private final MovieListRepository repo;

//    @Autowired
//    private LogUtil logUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


//    public MovieListServiceImpl(MovieListRepository repo) {
//    }


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
            throw new ServiceException(e.getMessage());
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
            MovieListEntity entity = repo.save(movieListEntity);
//            logUtil.success(this.getClass().getName() +" [Save]", "id: " + movieListEntity.getId());

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
//            logUtil.success(this.getClass().getName() +" [Update]", "id: " + entity.getId());
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
//            logUtil.success(this.getClass().getName() +" [Delete]", "id: " + movieListEntity.getId());
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
