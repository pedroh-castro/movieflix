package com.phc.movieflix.service;

import com.phc.movieflix.dtos.request.StreamingRequest;
import com.phc.movieflix.dtos.request.StreamingRequestUpdate;
import com.phc.movieflix.dtos.response.StreamingResponse;
import com.phc.movieflix.entity.Streaming;
import com.phc.movieflix.exceptions.ResourceNotFoundException;
import com.phc.movieflix.mapper.StreamingMapper;
import com.phc.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public StreamingService(StreamingRepository streamingRepository, StreamingMapper streamingMapper) {
        this.streamingRepository = streamingRepository;
        this.streamingMapper = streamingMapper;
    }


    @Transactional
    public StreamingResponse createStreaming(StreamingRequest dto) {
        Streaming streaming = streamingRepository.save(streamingMapper.toEntity(dto));
        return streamingMapper.toResponse(streaming);
    }

    @Transactional(readOnly = true)
    public List<StreamingResponse> findAllStreaming() {
        List<Streaming> streamings = streamingRepository.findAll();
        return streamings.stream()
                .map(streaming -> streamingMapper.toResponse(streaming))
                .toList();
    }

    @Transactional(readOnly = true)
    public StreamingResponse findStreamingById(Long id) {
        Streaming streaming= streamingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming not found"));

        return streamingMapper.toResponse(streaming);
    }

    @Transactional
    public void deleteStreamingById(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming not found"));
        streamingRepository.delete(streaming);
    }

    @Transactional
    public StreamingResponse updateStreaming(Long id, StreamingRequestUpdate request) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming not found"));

        streamingMapper.updateEntityFromRequest(request, streaming);
        streamingRepository.save(streaming);

        return streamingMapper.toResponse(streaming);
    }
}
