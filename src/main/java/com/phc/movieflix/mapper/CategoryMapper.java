package com.phc.movieflix.mapper;

import com.phc.movieflix.dtos.request.CategoryRequest;
import com.phc.movieflix.dtos.request.CategoryRequestUpdate;
import com.phc.movieflix.dtos.response.CategoryResponse;
import com.phc.movieflix.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest dto);

    CategoryResponse toResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(CategoryRequestUpdate request, @MappingTarget Category category);
}
