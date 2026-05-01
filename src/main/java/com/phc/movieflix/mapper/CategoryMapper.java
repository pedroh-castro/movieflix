package com.phc.movieflix.mapper;

import com.phc.movieflix.dtos.request.CategoryRequest;
import com.phc.movieflix.dtos.response.CategoryResponse;
import com.phc.movieflix.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest dto);

    CategoryResponse toResponse(Category category);
}
