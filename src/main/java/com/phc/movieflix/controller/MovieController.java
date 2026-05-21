package com.phc.movieflix.controller;

import com.phc.movieflix.controller.docs.MovieDocs;
import com.phc.movieflix.dtos.request.MovieRequest;
import com.phc.movieflix.dtos.request.MovieRequestUpdate;
import com.phc.movieflix.dtos.response.MovieResponse;
import com.phc.movieflix.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movieflix/movies")
public class MovieController implements MovieDocs {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody @Valid MovieRequest movieRequest) {
        MovieResponse result = movieService.save(movieRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MovieRequestUpdate movieRequest) {
        return ResponseEntity.ok(movieService.updateMovieById(id, movieRequest));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(movieService.findMoviesByCategoryId(category));
    }

    @Override
    @GetMapping("/rating")
    public ResponseEntity<List<MovieResponse>> findByRating() {
        return ResponseEntity.ok(movieService.findTop5ByOrderByRatingDesc());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
