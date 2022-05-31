package com.mini.project.tes.service.impl;

import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.repository.DetailsMovieRepository;
import com.mini.project.tes.service.DetailsMovieService;
import com.mini.project.tes.service.MovieListService;
import com.mini.project.tes.service.error.ServiceException;
import com.mini.project.tes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Transactional
@Service
public class DetailsMovieServiceImpl implements DetailsMovieService {
    @Autowired
    private DetailsMovieRepository repo;
    @Autowired
    private MovieListService movieListService;
    @Autowired
    private LogUtil logUtil;
    private Thread thread;

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

    @Override
    public MovieListEntity uploadFile(String uploadDir, MultipartFile file,MovieListEntity result) throws IOException {
        result = movieListService.save(result);
            MovieListEntity finalResult=result;
            thread = new Thread(() -> {
//                System.out.println("Thread Running");
                try {
                    thread(uploadDir,file,finalResult);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("Error upload in "  + this.getClass().getName() + " :" + e.getMessage());
                    throw new ServiceException(e.getMessage());
                }
            });

            thread.start();


            logUtil.success(this.getClass().getName() +" [Save - upload]", "id: " + result.getId());
            return result;
    }
    private void thread(String uploadDir, MultipartFile file,MovieListEntity result) throws IOException {
        DetailsMovieEntity detailsMovieEntity=new DetailsMovieEntity();
        StringBuilder fileNames=new StringBuilder();
        try {
            Path fileNamePath = Paths.get(uploadDir, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNamePath, file.getBytes());
//            movieListEntity=assembler.convertToEntity(movieList);
            detailsMovieEntity.setUrl(fileNamePath.toString());
            detailsMovieEntity.setBytes(file.getBytes());
            detailsMovieEntity.setFileName(file.getOriginalFilename());
//            detailsMovieEntity.getType(file.getContentType());


            detailsMovieEntity.setMovieList(result);
            detailsMovieEntity.setCreatedDate(result.getCreatedDate());
            save(detailsMovieEntity);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error upload in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
