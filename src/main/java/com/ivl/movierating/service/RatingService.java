package com.ivl.movierating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivl.movierating.model.Rating;


public interface RatingService {

	public List<Rating> getAllRatings();

	public List<Rating> findByName(String name);

	public Rating getById(Long id);

	public void deleteRating(Long RatingId);

	public boolean addRating(Rating Rating);

	public boolean updateRating(Rating rating);

}
