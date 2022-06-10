package com.mini.project.tes.persistence.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mini.project.tes.model.entity.TheMovieDbEntity;
import com.mini.project.tes.persistence.repository.TheMovieDbRepository;
import com.mini.project.tes.persistence.service.error.ServiceException;
import com.mini.project.tes.utility.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class ExternalApiServiceImpl implements ApiService {
    @Autowired
    private TheMovieDbRepository repo;
    @Autowired
    private LogUtil logUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void schedule() throws IOException, URISyntaxException {
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
                Gson g = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject object = (JsonObject) parser.parse(response.toString());
                object.remove("genres");
                object.remove("production_companies");
                object.remove("production_countries");
                object.remove("spoken_languages");
                TheMovieDbEntity s = g.fromJson(object, TheMovieDbEntity.class);
                repo.save(s);

                //GetAndPost.POSTRequest(response.toString());
//                RajaOngkir rajaOngkir= ObjectMapperUtil.toObject(response.toString(),RajaOngkir.class);
                CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            log.error("Error in getMovieExternalAPI");
            throw (URISyntaxException)new URISyntaxException(e.toString(),"Error MyGETRequest2").initCause(e);
        }
    }

    @Override
    public TheMovieDbEntity save(TheMovieDbEntity detailsMovieEntity) {
        try {
            TheMovieDbEntity entity = repo.saveAndFlush(detailsMovieEntity);
            logUtil.success(this.getClass().getName() +" [Save]", "id: " + detailsMovieEntity.getId());
            return entity;
        } catch (Exception e) {
            log.error("Error save in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

}
