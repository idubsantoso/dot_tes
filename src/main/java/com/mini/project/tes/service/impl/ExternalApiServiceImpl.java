package com.mini.project.tes.service.impl;

import com.mini.project.tes.model.entity.ApiResponse;
import com.mini.project.tes.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ExternalApiServiceImpl implements ApiService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public ResponseEntity<String> schedule() throws IOException, URISyntaxException {
        URL urlForGetRequest = new URL("https://api.themoviedb.org/3/movie/550?api_key=95467e28a39b346de61f7c8f8f3f6cea");
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
