package com.mini.project.tes.controller;

import com.mini.project.tes.controller.error.BadRequestAlertException;
import com.mini.project.tes.utility.HeaderUtil;
import com.mini.project.tes.utility.ResponseUtil;
import com.mini.project.tes.security.entity.SamUserEntity;
import com.mini.project.tes.persistence.service.UserService;
import com.mini.project.tes.utility.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserRest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogUtil logUtil;

    private static final String ENTITY_NAME = "users";
    @Autowired
    private UserService service;

    //Get All user
    @GetMapping(value = "/users")
    public ResponseEntity<Object> getAllUser() throws URISyntaxException {
        log.info("REST Request to get All MovieList");
        List<SamUserEntity> userEntities  = null;
        try {
            userEntities  = service.findAll();
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [get]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error getAll MovieList").initCause(e);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userEntities));
    }
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getByUsername(@PathVariable("username") String username) throws URISyntaxException {
        log.info("REST Request to get All MovieList");
        SamUserEntity userEntities  = null;
        try {
            userEntities  = service.findByUsername(username);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [get]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error getAll MovieList").initCause(e);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userEntities));
    }
    //Add
    @PostMapping(value = "/users")
    public ResponseEntity<Object> addUser(@RequestBody @Valid SamUserEntity userEntity) throws URISyntaxException {
        log.info("REST Request to add user" + userEntity.toString());
        if (userEntity.getId() != null) {
            throw new BadRequestAlertException("A new User cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SamUserEntity entityResult=null;
        if (service.findByUsername(userEntity.getUsername())!=null){
            throw new BadRequestAlertException("User already exist", ENTITY_NAME, "idexists");
        }
        try {
            entityResult = service.save(userEntity);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [post]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error post users").initCause(e);
        }
        return ResponseEntity.created(new URI("/api/users/" + entityResult.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,String.valueOf(entityResult.getId()))).body(entityResult);
    }
    //Update
    @PutMapping(value = "/users")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid SamUserEntity userEntity) throws URISyntaxException {
        log.info("REST Request to update users " + userEntity.toString());
        SamUserEntity old=service.findByUsername(userEntity.getUsername());
        if(!old.getUsername().equals(userEntity.getUsername())){
            if(service.findByUsername(userEntity.getUsername())!=null){
                throw new BadRequestAlertException("User already exist", ENTITY_NAME, "idexists");
            }
        }
        SamUserEntity entityResult=null;
        try {
            entityResult = service.update(userEntity);
        }catch (Exception e){
            logUtil.error(this.getClass().getName() + " [update]", e.getMessage());
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error update identities").initCause(e);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityResult.getId().toString())).body(entityResult);
    }
}
