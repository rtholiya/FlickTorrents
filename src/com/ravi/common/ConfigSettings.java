package com.ravi.common;

import java.io.Serializable;
import java.util.List;

public class ConfigSettings implements Serializable{
	private static final long serialVersionUID = 1L;
	int genere;
	int year;
	int ratings;
	public static List<Integer> years;
	
	public ConfigSettings(int genere, int year, int ratings) {
		super();
		this.genere = genere;
		this.year = year;
		this.ratings = ratings;
	}
	public int getGenere() {
		return genere;
	}
	public void setGenere(int genere) {
		this.genere = genere;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "ConfigSettings [genere=" + genere + ", year=" + year
				+ ", ratings=" + ratings + "]";
	}
	
	public static List<Integer> getYears() {
		return years;
	}

	public static void setYears(List<Integer> years) {
		ConfigSettings.years = years;
	}
	
}
