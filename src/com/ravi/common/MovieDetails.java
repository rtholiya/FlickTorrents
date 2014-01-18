package com.ravi.common;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class MovieDetails implements Parcelable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String genres;
	String runtime;
	String country;
	String titile;
	int ratingCount;
	String imdbUrl;
	String knownAs;
	String imdbId;
	String releaseDate;
	String plot;
	String poster;
	String year;
	String actors;
	String writers;
	String filmingLocations;
	String language;
	float ratings;
	String directors;
	String rated;
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getImdbUrl() {
		return imdbUrl;
	}
	public void setImdbUrl(String imdbUrl) {
		this.imdbUrl = imdbUrl;
	}
	public String getKnownAs() {
		return knownAs;
	}
	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		if(null != poster)
		{
			this.poster = poster;
		}
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getWriters() {
		return writers;
	}
	public void setWriters(String writers) {
		this.writers = writers;
	}
	public String getFilmingLocations() {
		return filmingLocations;
	}
	public void setFilmingLocations(String filmingLocations) {
		this.filmingLocations = filmingLocations;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public float getRatings() {
		return ratings;
	}
	public void setRatings(float ratings) {
		this.ratings = ratings;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	@Override
	public String toString() {
		return "MovieDetails [genres=" + genres + ", runtime=" + runtime
				+ ", country=" + country + ", titile=" + titile
				+ ", ratingCount=" + ratingCount + ", imdbUrl=" + imdbUrl
				+ ", knownAs=" + knownAs + ", imdbId=" + imdbId
				+ ", releaseDate=" + releaseDate + ", plot=" + plot
				+ ", poster=" + poster + ", year=" + year + ", actors="
				+ actors + ", writers=" + writers + ", filmingLocations="
				+ filmingLocations + ", language=" + language + ", ratings="
				+ ratings + ", directors=" + directors + ", rated=" + rated
				+ "]";
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		/*
		 * String genres;
	String runtime;
	String country;
	String titile;
	int ratingCount;
	String imdbUrl;
	String knownAs;
	String imdbId;
	String releaseDate;
	String plot;
	String poster;
	String year;
	String actors;
	String writers;
	String filmingLocations;
	String language;
	float ratings;
	String directors;
	String rated;
		 */
		dest.writeString(genres);
		dest.writeString(runtime);
		dest.writeString(country);
		dest.writeString(titile);
		dest.writeInt(ratingCount);
		dest.writeString(imdbUrl);
		dest.writeString(knownAs);
		dest.writeString(imdbId);
		dest.writeString(releaseDate);
		dest.writeString(plot);
		dest.writeString(poster);
		dest.writeString(year);
		dest.writeString(actors);
		dest.writeString(writers);
		dest.writeString(filmingLocations);
		dest.writeString(language);
		dest.writeFloat(ratings);
		dest.writeString(directors);
		dest.writeString(rated);
		
	}
	

	
}
