package org.rtijn.dukecourse.ratings;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        //	ArrayList<Movie> list = new ArrayList<Movie>();
        FirstRatings fr = new FirstRatings();

//		list = fr.loadMovies();
//		System.out.println(list.size());
//
//		System.out.println("Comedy " + fr.GenresMovie(list, "Comedy"));
//		System.out.println(" longer than 150 is" + fr.greaterThan(list, 150));
//		System.out.println("maximum number of films directed by one director");
//		fr.maxMoviesDirector(list);
//		

        ArrayList<Rater> reat = fr.loadRaters("data/ratings_short.csv");
        for (Rater r : reat) {
            System.out.println(r);
        }

    }

}
