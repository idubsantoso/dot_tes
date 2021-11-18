package com.mini.project.tes.assembler;

import com.mini.project.tes.model.dto.MovieList;
import com.mini.project.tes.model.entity.MovieListEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieListAssembler {
    private ModelMapper modelMapper;

    public MovieList convertToDto(MovieListEntity entity){
        MovieList dto = modelMapper.map(entity, MovieList.class);

        return dto;
    }

    public List<MovieList> convertToDtos(List<MovieListEntity> entities){
        return entities.stream()
                .map(entity -> convertToDto(entity))
                .collect(Collectors.toList());
    }

    public MovieListEntity convertToEntity(MovieList dto){
        MovieListEntity entity = modelMapper.map(dto, MovieListEntity.class);
        return entity;
    }
}
