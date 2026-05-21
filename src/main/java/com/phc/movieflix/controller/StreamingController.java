package com.phc.movieflix.controller;

import com.phc.movieflix.controller.docs.StreamingDocs;
import com.phc.movieflix.dtos.request.StreamingRequest;
import com.phc.movieflix.dtos.request.StreamingRequestUpdate;
import com.phc.movieflix.dtos.response.StreamingResponse;
import com.phc.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController implements StreamingDocs {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @Override
    @PostMapping
    public ResponseEntity<StreamingResponse> addStreaming(@RequestBody @Valid StreamingRequest dto) {
        StreamingResponse result = streamingService.createStreaming(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        return ResponseEntity.ok(streamingService.findAllStreaming());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        return ResponseEntity.ok(streamingService.findStreamingById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id) {
        streamingService.deleteStreamingById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateStreaming(
            @PathVariable Long id,
            @RequestBody @Valid StreamingRequestUpdate request) {
        return ResponseEntity.ok(streamingService.updateStreaming(id, request));
    }
}
