package com.ravi.common;

import java.io.Serializable;
import java.util.Set;

public class Movie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movie;
	private Set<String> links;
    private MovieDetails movieDetails;	
	
	public Movie()
	{
		
	}
	
	public Movie(String movie, Set<String> links) {
		super();
		this.movie = movie;
		this.links = links;
	}
	
	
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		this.links = links;
	}
	
	
	
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	
	
	public MovieDetails getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(MovieDetails movieDetails) {
		this.movieDetails = movieDetails;
	}

	@Override
	public String toString() {
		return "Movie [movie=" + movie + ", links=" + links + ", movieDetails="
				+ movieDetails + "]";
	}

	
}
