package com.phc.movieflix.service;

import com.phc.movieflix.dtos.request.MovieRequest;
import com.phc.movieflix.dtos.request.MovieRequestUpdate;
import com.phc.movieflix.dtos.response.MovieResponse;
import com.phc.movieflix.entity.Category;
import com.phc.movieflix.entity.Movie;
import com.phc.movieflix.entity.Streaming;
import com.phc.movieflix.exceptions.ResourceNotFoundException;
import com.phc.movieflix.mapper.MovieMapper;
import com.phc.movieflix.repository.CategoryRepository;
import com.phc.movieflix.repository.MovieRepository;
import com.phc.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }


    @Transactional
    public MovieResponse save(MovieRequest request) {
        Set<Category> foundCategories = findCategoriesOrThrow(request.categories());
        Set<Streaming> foundStreamings = findStreamingsOrThrow(request.streamings());

        Movie movie = movieMapper.toEntity(request);
        movie.setCategories(foundCategories);
        movie.setStreamings(foundStreamings);

        movie = movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies
                .stream()
                .map(movie -> movieMapper.toResponse(movie))
                .toList();
    }

    @Transactional(readOnly = true)
    public MovieResponse findMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID filme não encontrado "+ id));

        return movieMapper.toResponse(movie);
    }

    @Transactional
    public MovieResponse updateMovieById(Long id, MovieRequestUpdate request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID filme não encontrado"+ id));
        movieMapper.updateEntityFromRequest(request, movie);

        if (request.categories() != null && !request.categories().isEmpty()) {
            movie.setCategories(findCategoriesOrThrow(request.categories()));
        }

        if (request.streamings() != null && !request.streamings().isEmpty()) {
            movie.setStreamings(findStreamingsOrThrow(request.streamings()));
        }

        movie = movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findMoviesByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + categoryId));

        return movieRepository.findByCategoriesId(categoryId)
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findTop5ByOrderByRatingDesc() {
        List<Movie> movies = movieRepository.findTop5ByOrderByRatingDesc();
        return movies.stream().map(movieMapper::toResponse).toList();
    }

    @Transactional
    public void deleteMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado: " + id));

        movieRepository.delete(movie);
    }


    private Set<Category> findCategoriesOrThrow(Set<Long> ids) {
        List<Category> found = categoryRepository.findAllById(ids);

        if (found.size() != ids.size()) {
            Set<Long> missingIds = ids.stream()
                    .filter(id -> found.stream().noneMatch(c -> c.getId().equals(id)))
                    .collect(Collectors.toSet());

            throw new ResourceNotFoundException("Categories not found: " + missingIds);
        }

        return new HashSet<>(found);
    }

    private Set<Streaming> findStreamingsOrThrow(Set<Long> ids) {
        List<Streaming> found = streamingRepository.findAllById(ids);

        if (found.size() != ids.size()) {
            Set<Long> missingIds = ids.stream()
                    .filter(id -> found.stream().noneMatch(s -> s.getId().equals(id)))
                    .collect(Collectors.toSet());

            throw new ResourceNotFoundException("Streamings not found: " + missingIds);
        }

        return new HashSet<>(found);
    }
}
