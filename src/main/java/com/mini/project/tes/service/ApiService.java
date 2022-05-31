package com.mini.project.tes.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ApiService {
    ResponseEntity<String> schedule() throws IOException, URISyntaxException;
}
