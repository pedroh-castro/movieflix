package com.phc.movieflix.mapper;

import com.phc.movieflix.dtos.request.MovieRequest;
import com.phc.movieflix.dtos.request.MovieRequestUpdate;
import com.phc.movieflix.dtos.response.MovieResponse;
import com.phc.movieflix.entity.Movie;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, StreamingMapper.class})
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "streamings", ignore = true)
    Movie toEntity(MovieRequest dto);

    MovieResponse toResponse(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "streamings", ignore = true)
    void updateEntityFromRequest(MovieRequestUpdate request, @MappingTarget Movie movie);
}