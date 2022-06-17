package com.mini.project.tes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mini.project.tes.domain.ApiResponse;
import com.mini.project.tes.domain.TheMovieDbEntity;
import com.mini.project.tes.persistence.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TechnicalTesRest {
    @Autowired
    private ApiService service;
    @Value("${key.api}")
    private String apikey;
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
//                RajaOngkir rajaOngkir= ObjectMapperUtil.toObject(response.toString(),RajaOngkir.class);
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

    @GetMapping(value = "/get-external/request-themoviedb")
    public ResponseEntity<Object> getMovieExternalAPI() throws IOException, URISyntaxException {
        log.info("REST Request to getMovieExternalAPI");
        URL urlForGetRequest = new URL("https://api.themoviedb.org/3/movie/550?api_key="+apikey);
        String readLine = null;
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
//        conection.setRequestProperty("id", "12");
//        conection.setRequestProperty("api_key", "95467e28a39b346de61f7c8f8f3f6cea");
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
//                String tes=response.toString().replaceAll("]","");
//                tes.replaceAll("[^0-9]","");
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(response.toString(), Map.class);

                Gson g = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject object = (JsonObject) parser.parse(response.toString());
                object.remove("genres");
                object.remove("production_companies");
                object.remove("production_countries");
                object.remove("spoken_languages");
                TheMovieDbEntity s = g.fromJson(object, TheMovieDbEntity.class);
                service.save(s);
                //GetAndPost.POSTRequest(response.toString());
//                RajaOngkir rajaOngkir= ObjectMapperUtil.toObject(response.toString(),RajaOngkir.class);
                CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
                return ResponseEntity.ok()
                        .cacheControl(cacheControl)
                        .body(response.toString());
            } else {
                return new ResponseEntity(new ApiResponse(false, "Connection Failed"),
                        HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error in getMovieExternalAPI");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest2").initCause(e);
        }

    }

    @GetMapping(value = "/get-external/request-themoviedb/{id}/{api_key}")
    public ResponseEntity<Object> getMovieExternalAPIByIdAndApiKey(@PathVariable("id") String id,@PathVariable("api_key") String api_key) throws IOException, URISyntaxException {
        log.info("REST Request to getMovieExternalAPI");
        String build = "https://api.themoviedb.org/3/movie/" + id +
                "?api_key=" + api_key;
        URL urlForGetRequest = new URL(build);
        String readLine = null;
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
//        conection.setRequestProperty("id", "12");
//        conection.setRequestProperty("api_key", "95467e28a39b346de61f7c8f8f3f6cea");
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
//                String tes=response.toString().replaceAll("]","");
//                tes.replaceAll("[^0-9]","");
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(response.toString(), Map.class);

                Gson g = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject object = (JsonObject) parser.parse(response.toString());
                object.remove("genres");
                object.remove("production_companies");
                object.remove("production_countries");
                object.remove("spoken_languages");
                TheMovieDbEntity s = g.fromJson(object, TheMovieDbEntity.class);
                service.save(s);
                //GetAndPost.POSTRequest(response.toString());
//                RajaOngkir rajaOngkir= ObjectMapperUtil.toObject(response.toString(),RajaOngkir.class);
                CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
                return ResponseEntity.ok()
                        .cacheControl(cacheControl)
                        .body(response.toString());
            } else {
                return new ResponseEntity(new ApiResponse(false, "Connection Failed"),
                        HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error in getMovieExternalAPI");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest2").initCause(e);
        }

    }

}
