package com.ivl.movierating.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "movierating")
public class Rating implements Serializable{

	//private static final long serialVersionUID = -1364750357438286836L;


	@Id
	//@Column(name="movie_Id")
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	private String movieName;
	private int rating;

	public Rating() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	/*
	 * public Rating(Long id, String movieName, int rating) { super(); this.id = id;
	 * this.movieName = movieName; this.rating = rating; }
	 */

	@Override
	public String toString() {
		return "Rating [id=" + id + ", movieName=" + movieName + ", rating=" + rating + "]";
	}

}