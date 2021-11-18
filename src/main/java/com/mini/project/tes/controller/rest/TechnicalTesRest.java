package com.mini.project.tes.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.project.tes.controller.rest.util.HeaderUtil;
import com.mini.project.tes.controller.rest.util.ResponseUtil;
import com.mini.project.tes.model.dto.RajaOngkir;
import com.mini.project.tes.model.dto.RootRajaOngkir;
import com.mini.project.tes.model.entity.ApiResponse;
import com.mini.project.tes.util.ObjectMapperUtil;
import net.bull.javamelody.internal.common.LOG;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import scala.util.parsing.json.JSONObject;

import javax.persistence.Cacheable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TechnicalTesRest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get-external")
    public ResponseEntity<HashMap<String, Object> >MyGETRequest() throws IOException, URISyntaxException {
        log.info("REST Request to MyGETRequest");
        OkHttpClient client = new OkHttpClient();

        try {

            Request request = new Request.Builder()
                    .url("https://api.rajaongkir.com/starter/province?id=12")
                    .get()
                    .addHeader("key", "0c51eb4a7d645f7bf73e0a41329c7926")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println("JSON String Result " + response.body().string());
            return new ResponseEntity<HashMap<String, Object>>((MultiValueMap<String, String>) response.body(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error in MyGETRequest");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest").initCause(e);
        }
    }

    @GetMapping(value = "/get-external/request/{id}")
    public ResponseEntity<Object> MyGETRequestGetByid(@PathVariable("id") Long id) throws IOException, URISyntaxException {
        log.info("REST Request to MyGETRequest2");
        URL urlForGetRequest = new URL("https://api.rajaongkir.com/starter/province?id="+id);
        String readLine = null;
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
//        conection.setRequestProperty("id", "12");
        conection.setRequestProperty("key", "0c51eb4a7d645f7bf73e0a41329c7926");
        int responseCode = conection.getResponseCode();
        try {

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));

                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                System.out.println("JSON String Result " + response.toString());
                //GetAndPost.POSTRequest(response.toString());
            } else {
                System.out.println("GET NOT WORKED");
            }

            CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
            return ResponseEntity.ok()
                    .cacheControl(cacheControl)
                    .body(response.toString());
        }catch (Exception e){
            log.error("Error in MyGETRequest2");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest2").initCause(e);
        }

    }

    @GetMapping(value = "/get-external/request")
    public ResponseEntity<Object> MyGETRequestGetAll() throws IOException, URISyntaxException {
        log.info("REST Request to MyGETRequestGetAll");
        URL urlForGetRequest = new URL("https://api.rajaongkir.com/starter/province");
        String readLine = null;
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
//        conection.setRequestProperty("id", "12");
        conection.setRequestProperty("key", "0c51eb4a7d645f7bf73e0a41329c7926");
        int responseCode = conection.getResponseCode();
        try {

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));

                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                System.out.println("JSON String Result " + response.toString());
                //GetAndPost.POSTRequest(response.toString());
                CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
                return ResponseEntity.ok()
                        .cacheControl(cacheControl)
                        .body(response.toString());
            } else {
                return new ResponseEntity(new ApiResponse(false, "Connection Failed"),
                        HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error in MyGETRequest2");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest2").initCause(e);
        }

    }

}
