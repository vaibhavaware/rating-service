package com.ivl.movierating.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ivl.movierating.model.Rating;
@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	List<Rating> findByMovieName(String movieName);
}
