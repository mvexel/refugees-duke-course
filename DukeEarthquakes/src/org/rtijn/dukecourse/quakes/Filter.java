package org.rtijn.dukecourse.quakes;

/**
 * Write a description of interface org.rtijn.dukecourse.quakes.Filter here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Filter {
	boolean satisfies(QuakeEntry qe);


	String getName();
}
