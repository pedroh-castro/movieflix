package com.phc.movieflix.mapper;

import com.phc.movieflix.dtos.request.StreamingRequest;
import com.phc.movieflix.dtos.request.StreamingRequestUpdate;
import com.phc.movieflix.dtos.response.StreamingResponse;
import com.phc.movieflix.entity.Streaming;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StreamingMapper {

    @Mapping(target = "id", ignore = true)
    Streaming toEntity(StreamingRequest dto);

    StreamingResponse toResponse(Streaming streaming);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(StreamingRequestUpdate request, @MappingTarget Streaming streaming);
}
