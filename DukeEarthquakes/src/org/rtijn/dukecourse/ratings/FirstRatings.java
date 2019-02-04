package org.rtijn.dukecourse.ratings;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

class FirstRatings {

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Movie> loadMovies() {

        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource();
        // System.out.println(fr.asString());
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            try {
                String id = record.get("id");
                String title = record.get("title");
                String year = record.get("year");
                String genres = record.get("genre");
                String director = record.get("director");
                String country = record.get("country");
                String poster = record.get("poster");
                int minutes = Integer.parseInt(record.get("minutes"));

                Movie m = new Movie(id, title, year, genres, director, country, poster, minutes);

                list.add(m);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////
    public int GenresMovie(ArrayList<Movie> list, String genres) {
        int count = 0;

        for (Movie s : list) {
            String st = s.getGenres();
            if (st.indexOf(genres) != -1)
                count++;
        }
        return count;
    }

    //////////////////////////////////////////////////////////////////////
    public int greaterThan(ArrayList<Movie> list, int max) {
        int count = 0;
        for (Movie s : list) {

            if (s.getMinutes() >= max)
                count++;
        }
        return count;
    }

    //////////////////////////////////////////////////////////////////////
    public void maxMoviesDirector(ArrayList<Movie> list) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        String maxName = "";
        int max = 0;

        for (Movie s : list) {

            String st = s.getDirector();
            String[] dir = st.split(", ");
            for (int i = 0; i < dir.length; i++) {

                if (map.containsKey(dir[i])) {
                    map.put(dir[i], map.get(dir[i]) + 1);
                } else
                    map.put(dir[i], 1);
            }

            for (String dirName : map.keySet()) {
                int temp = map.get(dirName);
                if (temp > max) {
                    maxName = dirName;
                    max = temp;
                }
            }
        }
        System.out.println(maxName + "  " + max);
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Rater> loadRaters(String faileName) {

        ArrayList<Rater> list = new ArrayList<Rater>();

        FileResource fr = new FileResource(faileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {

            String id = record.get("rater_id");
            String title = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));
//-------------------------------------------------------------//
            Rater myRatre = new Rater(id);
            Boolean hasRater = false;
            for (Rater R : list) {
                if (R.getID().equals(id)) {
                    hasRater = true;
                    myRatre = R;
                }
                myRatre.addRating(title, rating);
                if (!hasRater)
                    list.add(myRatre);
            }
        }
        return list;
    }
//-------------------------------------------------------------//
}
