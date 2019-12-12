import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 9th, 2019
 *
 *  Description:  -------------------STEP ONE--------
 *                 In this exercise, you will use the provided classes
 *                 Movie.java, Rating.java, and Rater.java and read in
 *                 and store information about movies and ratings of movies
 *                 by different movie raters to answer simple questions
 *                 about both movies and ratings.
 *
 ****************************************************************/
public class FirstRatings {
    //This method should process every record from the CSV file
    // whose name is filename, a file of movie information,
    // and return an ArrayList of type Movie with all of the
    // movie data from the file.
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String id = i.get("id");
            String title = i.get("title");
            String year = i.get("year");
            String country = i.get("country");
            String genre = i.get("genre");
            String director = i.get("director");
            int minutes = Integer.parseInt(i.get("minutes"));
            String poster = i.get("poster");
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }
    
    public void testLoadMovies() {
        
        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        for (Movie i : movies) {
            //System.out.println(" ++ " + i);
        }
        System.out.println("The size of movie list is = " + movies.size());
        
        //        for (Movie i : movies) {
        //            if (i.getGenres().contains("Comedy")) {
        //                System.out.println("Comedy = : " + i);
        //            }
        //        }
        //        for (Movie i : movies) {
        //            if (i.getMinutes() >= 150) {
        //                System.out.println("MIN >= 150 : " + i);
        //            }
        //        }
        
        //Add code to determine the maximum number of movies by any director,
        // and who the directors are that directed that many movies.
        // Remember that some movies may have more than one director.
        HashMap<String, ArrayList<Movie>> mapDirector = new HashMap<>();
        for (Movie i : movies) {
            //One movie may have many directors.
            //            J.D. Chakravarthi, Manish Gupta, Sajid Khan, Jijy Philip, Prawaal Raman, Vivek Shah, Ram Gopal Varma
            //            movie 0476649 : J.D. Chakravarthi
            //            movie 0476649 : Manish Gupta
            //            movie 0476649 : Sajid Khan
            //            movie 0476649 : Jijy Philip
            //            movie 0476649 : Prawaal Raman
            //            movie 0476649 : Vivek Shah
            //            movie 0476649 : Ram Gopal Varma
            String director = i.getDirector();
            //make a list of directos' name split into a string array
            String[] directors = director.split(", ");
            
            for (String j : directors) {
                //System.out.println("movie " + i.getID() + " : " + j);
                if (!mapDirector.containsKey(j)) {
                    ArrayList<Movie> a = new ArrayList<>();
                    a.add(i);
                    mapDirector.put(j, a);
                } else {
                    ArrayList<Movie> a = mapDirector.get(j);
                    a.add(i);
                    mapDirector.put(j, a);
                }
            }
        }
        //        System.out.println("Print HashMap--directorList");
        //        for (String i : directorList.keySet()) {
        //            System.out.println(i + " : " + directorList.get(i).size() + " : " + directorList.get(i));
        //        }
        
        int maxNumMovieByDirector = 0;
        String maxDirector = "";
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() > maxNumMovieByDirector) {
                maxNumMovieByDirector = mapDirector.get(key).size();
                //maxDirector = key;
            }
        }
        //System.out.println("The director who produce the most movie is : " + maxDirector + " : " + maxNumMovieByDirector);
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() == maxNumMovieByDirector) {
                System.out.println("The director who produce the most movie is : " + maxNumMovieByDirector + " : " + key);
            }
        }
    }
    
    //rater 1:(rater id 1, movielist 1)
    //rater 1:(rater id 1, movielist 1)
    //.........
    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> raters = new ArrayList<>();// why no need <PlainRater>?
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String rater_id = i.get("rater_id");
            String movie_id = i.get("movie_id");
            double rating = Double.parseDouble(i.get("rating"));
            //-------TODO: CAN WE simplify line 129-146 ???------------
            int count = 0;
            for (Rater j : raters) {
                if (j.getID().contains(rater_id)) {
                    //Rater m = new Rater(rater_id);
                    j.addRating(movie_id, rating);
                    count++;
                    //System.out.println("count = " + count);
                    //System.out.println("Existing rater updated! " + j.getID() + "\t" + movie_id + "\t" + rating);
                    //raters.add(m);
                    break;
                }
            }
            if (count == 0) {
                // HERE IS THE ONLY CHANGE I MADE FOR THE INTERFACE
                // WHY NO NEED CHANGE OTHERS SUCH AS LINE 120.
                EfficientRater m = new EfficientRater(rater_id);
                m.addRating(movie_id, rating);
                raters.add(m);// here is not correct when i did not write implement the rater interface in EfficientRater
                //System.out.println("New rater added!        " + rater_id + "\t" + movie_id + "\t" + rating);
            }
        }
        return raters;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        System.out.println("The size of rater list is = " + raters.size());
        //Add code to find the number of ratings for a particular rater
        // you specify in your code. For example, if you run this code
        // on the rater whose rater_id is 2
        for (Rater i : raters) {
            if (i.getID().equals("2")) {
                System.out.println("USER # " + i.getID() + " : " + i.numRatings() + " ratings");
                //             System.out.println(i.getItemsRated());
                //            System.out.println(i.getRating());
                
                ArrayList<String> rating = i.getItemsRated();
                for (String j : rating) {
                    System.out.print("movie_id: " + j + " ");
                    System.out.println(i.getRating(j) + " rating");
                }
            }
        }
        //Add code to find the maximum number of ratings by any rater.
        // Determine how many raters have this maximum number of ratings
        // and who those raters are.If you run this code on the file ratings_short.csv,
        // you will see rater 2 has three ratings, the maximum number of ratings of
        // all the raters,and that there is only one rater with three ratings.
        int max = 0;
        for (Rater i : raters) {
            if (i.numRatings() > max) {
                max = i.numRatings();
            }
        }
        for (Rater i : raters) {
            if (i.numRatings() == max) {
                System.out.println("The maximum rate is from USER # " + i.getID() + " : " + i.numRatings() + " ratings");
            }
        }
        //- Add code to find the number of ratings a particular movie has.
        // If you run this code on the file ratings_short.csv for the movie “1798709”,
        // you will see it was rated by four raters.
        int count = 0;
        String movie_id = "1798709";
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            if (rating.contains(movie_id)) {
                count++;
                System.out.println(count + " : " + "id = " + i.getID() + rating);
            }
        }
        System.out.println("The total # of " + movie_id + " that been rated is " + count);
        //- Add code to determine how many different movies have been rated
        // by all these raters. If you run this code on the file ratings_short.csv,
        // you will see there were four movies
        ArrayList<String> differentMovie = new ArrayList<>();
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            for (String j : rating) {
                if (!differentMovie.contains(j)) {
                    differentMovie.add(j);
                }
            }
        }
        System.out.println("The total # of movie is " + differentMovie.size());
    }
    
    public static void main(String[] args) {
        FirstRatings a = new FirstRatings();
        System.out.println("-------------------MOVIES-------------------");
        a.testLoadMovies();
        System.out.println();
        System.out.println();
        System.out.println("-------------------RATERS-------------------");
        a.testLoadRaters();
    }
}
