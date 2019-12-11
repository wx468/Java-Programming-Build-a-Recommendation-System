import java.util.ArrayList;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 11th, 2019
 *
 *  Description:  -------------------STEP TWO-------------------------
 *                 You will build on this assignment by calculating
 *                 average ratings of movies.
 *                 In this assignment you will modify a new class named SecondRatings,
 *                 which has been started for you, to do many of the calculations
 *                 focusing on computing averages on movie ratings.
 *                 You will also create a second new class named MovieRunnerAverage,
 *                 which you will use to test the methods you created in SecondRatings
 *                 by creating a SecondRatings object in MovieRunnerAverage and
 *                 calling its methods.
 *
 ****************************************************************/
public class SecondRatings {
    
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    //---------------todo: why only above won't compile???-------------
    
    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings a = new FirstRatings();
        myMovies = a.loadMovies(movieFile);
        myRaters = a.loadRaters(ratingFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    //private helper method
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            // if (i.hasRating(movieID)) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
                // System.out.println(count + " : " + "id = " + i.getID() + " rating " + rating + " ave " + total);
            }
        }
        //System.out.println("Movie ID = " + movieID + " : " + count + " : " + total + " : " + total / count);
        if (count >= minimalRaters) return total / count;
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        for (Movie i : myMovies) {
            double ave = getAverageByID(i.getID(), minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i.getID(), ave));//item is string id?
        }
        return ratingList;
    }
    
    public String getTitle(String movieID) {
        for (Movie i : myMovies) {
            if (i.getID().equals(movieID)) {
                return i.getTitle();
            }
        }
        return "The Movie ID was not found!";
    }
    
    public String getID(String title) {
        for (Movie i : myMovies) {
            if (i.getTitle().equals(title)) {
                return i.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public static void main(String[] args) {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0790636", 2));
        System.out.println("---------------test-------------");
        System.out.println(sr.getAverageRatings(2));
        //[[6414, 0.0], [68646, 0.0], [113277, 0.0], [1798709, 8.25], [790636, 0.0]]
    }
    
}
