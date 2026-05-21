package com.phc.movieflix.repository;

import com.phc.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @EntityGraph(attributePaths = {"categories", "streamings"})
    List<Movie> findByCategoriesId(Long categoryId);

    List<Movie> findTop5ByOrderByRatingDesc();
}
