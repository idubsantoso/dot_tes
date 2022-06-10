package com.mini.project.tes.controller.rest;

import com.mini.project.tes.assembler.MovieListAssembler;
import com.mini.project.tes.controller.rest.error.BadRequestAlertException;
import com.mini.project.tes.controller.rest.util.HeaderUtil;
import com.mini.project.tes.controller.rest.util.ResponseUtil;
import com.mini.project.tes.model.entity.DetailsMovieEntity;
import com.mini.project.tes.model.entity.MovieListEntity;
import com.mini.project.tes.persistence.service.DetailsMovieService;
import com.mini.project.tes.persistence.service.MovieListService;
import com.mini.project.tes.utility.LogUtil;
import com.mini.project.tes.utility.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MovieListRest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ENTITY_NAME = "movie-lists";

    @Autowired
    private MovieListService service;
    @Autowired
    private DetailsMovieService detailsMovieService;

    @Autowired
    private LogUtil logUtil;
    @Autowired
    private MovieListAssembler assembler;

    ServletContext servletContext;

    @Value("${upload.path}")
    String uploadDir;


    //Get All Menu
    @GetMapping(value = "/movie-lists")
    public ResponseEntity<Object> getAllMovieList() throws URISyntaxException {
        log.info("REST Request to get All MovieList");
        List<MovieListEntity> movieListEntities  = null;
        try {
            movieListEntities  = service.findAll();
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [get]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error getAll MovieList").initCause(e);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movieListEntities));
    }

    @GetMapping(value = "/movie-lists-category/{category}")
    public ResponseEntity<Object> getAllMovieListByCategory(@PathVariable("category") String category) throws URISyntaxException {
        log.info("REST Request to getAllMovieListByCategory");
        List<MovieListEntity> movieListEntities  = null;
        try {
            movieListEntities  = service.findByCategory(category);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [get]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error getAllMovieListByCategory").initCause(e);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movieListEntities));
    }

    //Get One
    @GetMapping(value = "/movie-lists/{id}")
    public ResponseEntity<Object> getMovieListById(@PathVariable("id") Long id) throws URISyntaxException {
        log.info("REST Request to get one MovieList " + id);
        MovieListEntity movieListEntity = null;
        try {
            movieListEntity = service.findById(id);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [get]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error getMovieListById").initCause(e);
        }
        if (movieListEntity == null) {
            return ResponseEntity.notFound().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "204", "entity doesnt exist !")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movieListEntity));
    }

    //Add
    @PostMapping(value = "/movie-lists")
    public ResponseEntity<Object> addMovieList(@RequestBody @Valid MovieListEntity movieListEntity) throws URISyntaxException {
        log.info("REST Request to add MovieList " + movieListEntity.toString());
        if (movieListEntity.getId() != null) {
            throw new BadRequestAlertException("A new MovieList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieListEntity entityResult=null;
        try {
            entityResult = service.save(movieListEntity);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [post]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error post identities").initCause(e);
        }
        return ResponseEntity.created(new URI("/api/identities/" + entityResult.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,String.valueOf(entityResult.getId()))).body(entityResult);
    }


    @RequestMapping(value = "/get-image",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageDetails(@RequestParam Long idDetails) throws IOException {
        DetailsMovieEntity detailsMovieEntity=detailsMovieService.findById(idDetails);
        RandomAccessFile f = new RandomAccessFile(detailsMovieEntity.getUrl(),"r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/movie-lists/upload-file", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> uploadFile(@RequestPart("file") MultipartFile file,@RequestPart("movieList") String movieListJson ) throws Exception {

        MovieListEntity result=new MovieListEntity();
        MovieListEntity movieListEntity= ObjectMapperUtil.toObject(movieListJson, MovieListEntity.class);
        try {
            result=detailsMovieService.uploadFile(uploadDir,file,movieListEntity);

        } catch (Exception e){
            logUtil.error(this.getClass().getName() + " [post]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error uploadMovieList").initCause(e);
            //throw new BadRequestAlertException("Failed upload", ENTITY_NAME, fileStorageExeption.getMessage());
        }

        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }

    //Update
    @PutMapping(value = "/movie-lists")
    public ResponseEntity<Object> updateMovieList(@RequestBody @Valid MovieListEntity movieListEntity) throws URISyntaxException {
        log.info("REST Request to update identities " + movieListEntity.toString());
        if (service.findById(movieListEntity.getId()) == null) {
            return ResponseEntity.notFound().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "204", "entity doesnt exist !")).build();
        }
        MovieListEntity entityResult=null;
        try {
            entityResult = service.update(movieListEntity);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [update]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error update identities").initCause(e);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityResult.getId().toString())).body(entityResult);
    }

    //Delete
    @DeleteMapping(value = "/movie-lists/{id}")
    public ResponseEntity<Object> deleteMovieListById(@PathVariable("id") Long id) throws URISyntaxException {
        log.info("REST Request to delete identities " + id);
        MovieListEntity movieListEntity = service.findById(id);
        if (movieListEntity == null) {
            return ResponseEntity.notFound().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "204", "entity doesnt exist !")).build();
        }
        try {
            service.delete(movieListEntity);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [delete]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error delete identities").initCause(e);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
