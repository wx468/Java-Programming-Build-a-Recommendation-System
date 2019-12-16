import java.util.ArrayList;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE--------
 *                 The class MovieDatabase—This class is an efficient way to get
 *                 information about movies. It stores movie information in a HashMap
 *                 for fast lookup of movie information given a movie ID.
 *                 The class also allows filtering movies based on queries. All methods
 *                 and fields in the class are static.
 *                 This means you'll be able to access methods in MovieDatabase
 *                 without using new to create objects, but by calling methods like
 *                 MovieDatabase.getMovie("0120915").
 *
 ****************************************************************/

public class ThirdRatings {
    /**
     * Movies will now be stored in the MovieDatabase instead of
     * in the instance variable myMovies,
     * so you will want to remove the private variable myMovies
     **/
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    //---------------todo: why only above won't compile???-------------
    
    public ThirdRatings(String ratingFile) {
        FirstRatings a = new FirstRatings();
        myRaters = a.loadRaters(ratingFile);
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
        System.out.printf("Movie ID : Count : Total : Rating = %-10s%-5d%-7.2f%-7.2f%n", movieID, count, total, total / count);
        if (count >= minimalRaters) return total / count;
        return 0.0;
    }
    
    /**
     * Note that myMovies no longer exists. Instead, you’ll need to get all the movies
     * from the MovieDatabase class and store them in an ArrayList of movie IDs.
     * Thus, you will need to modify getAverageRatings to call MovieDatabase
     * with a filter, and in this case you can use the TrueFilter to get every movie.
     **/
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //must initialize the filter first.
        Filter trueFilter = new TrueFilter();
        for (String i : MovieDatabase.filterBy(trueFilter)) {
            double ave = getAverageByID(i, minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i, ave));//item is string id?
        }
        return ratingList;
    }
    
    /**
     * write a public helper method named getAverageRatingsByFilter that has
     * two parameters, an int named minimalRaters for the minimum number of
     * ratings a movie must have and a Filter named filterCriteria. This method
     * should create and return an ArrayList of type Rating of all the movies
     * that have at least minimalRaters ratings and satisfies the filter criteria.
     * This method will need to create the ArrayList of type String of movie IDs
     * from the MovieDatabase using the filterBy method before calculating those averages.
     */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //must initialize the filter first.
        Filter trueFilter = new TrueFilter();
        ArrayList<String> movieID = MovieDatabase.filterBy(trueFilter);
        
        //        for (String i : movieID) {
        //            double ave = getAverageByID(i, minimalRaters);
        //            if (ave > 0)
        //                ratingList.add(new Rating(i, ave));
        //        }
        for (String i : movieID) {
            if (f.satisfies(i)) {
                double ave = getAverageByID(i, minimalRaters);
                if (ave > 0)
                    ratingList.add(new Rating(i, ave));
            }
        }
        return ratingList;
    }
    
    //
    //    public String getTitle(String movieID) {
    //        for (Movie i : myMovies) {
    //            if (i.getID().equals(movieID)) {
    //                return i.getTitle();
    //            }
    //        }
    //        return "The Movie ID was not found!";
    //    }
    //
    //    public String getID(String title) {
    //        for (Movie i : myMovies) {
    //            if (i.getTitle().equals(title)) {
    //                return i.getID();
    //            }
    //        }
    //        return "NO SUCH TITLE";
    //    }
    //
    public static void main(String[] args) {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0790636", 2));
        System.out.println("---------------test-------------");
        System.out.println(sr.getAverageRatings(2));
        // System.out.println(a);
        /** movie from "full.csv", raters from "short.csv"
         * ................
         * Movie ID = 0430922 : 0 : 0.0 : NaN
         * Movie ID = 0060176 : 0 : 0.0 : NaN
         * Movie ID = 1270262 : 0 : 0.0 : NaN
         * Movie ID = 3007512 : 0 : 0.0 : NaN
         * Movie ID = 0043618 : 0 : 0.0 : NaN
         * Movie ID = 1621039 : 0 : 0.0 : NaN
         * Movie ID = 2713180 : 0 : 0.0 : NaN
         * [[1798709, 8.25], [0068646, 9.0]]
         * */
    }
    
}
