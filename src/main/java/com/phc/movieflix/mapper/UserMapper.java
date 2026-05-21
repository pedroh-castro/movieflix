package com.phc.movieflix.mapper;

import com.phc.movieflix.dtos.request.UserRequest;
import com.phc.movieflix.dtos.response.UserResponse;
import com.phc.movieflix.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request);

    UserResponse toResponse(User user);
}
