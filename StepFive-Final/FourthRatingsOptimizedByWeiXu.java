import java.util.ArrayList;
import java.util.Collections;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 15th, 2019
 *
 *  Description:  -------------------STEP FOUR Optimized by Wei Xu--------
 *                 Compare to FourthRating, instead of iterate all movie in the
 *                 MovieDataBase, i use the movie list that only the topSimilarRater's movie.
 *                 Greatly reduce the time.
 *
 *  * IMPORTANT NOTICE:
 *      * Weighted average algorithm optimized by WEI XU
 *      * Instead of  "sum of (similar rating(i) *rating of the movie(i))/count of the raters"
 *      * !!!!!I use  "sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)", will achieve better results.
 *
 ****************************************************************/

public class FourthRatingsOptimizedByWeiXu {
    private double dotProduct(Rater me, Rater r) {
        double dp = 0;
        ArrayList<String> memovieid = me.getItemsRated();
        for (String id : memovieid) {
            if (r.getItemsRated().contains(id)) {
                dp += (me.getRating(id) - 5) * (r.getRating(id) - 5);
            }
        }
        return dp;
    }
    
    /**
     * Write the private method named getSimilarities, which has one String parameter
     * named id—this method computes a similarity rating for each rater in the
     * RaterDatabase (except the rater with the ID given by the parameter) to see
     * how similar they are to the Rater whose ID is the parameter to getSimilarities.
     * <p>
     * This method returns an ArrayList of type Rating sorted by ratings from highest to
     * lowest rating with the highest rating first and only including those raters who have a
     * positive similarity rating since those with negative values are not similar in any way.
     * Note that in each Rating object the "item field is a rater’s ID, and the value field
     * is the dot product" comparison between that rater and the rater whose ID is the
     * parameter to getSimilarities.
     * <p>
     * Be sure not to use the dotProduct method with parameter id and itself!
     */////////////////////String id is the target user id;
    private ArrayList<Rating> getSimilarities(String raterId) {
        ArrayList<Rating> simiList = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(raterId)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0) {
                    simiList.add(new Rating(r.getID(), dotProduct));
                    // System.out.println("rater id = " + r.getID() + " dotProduct " + dotProduct);
                }
            }
        }
        Collections.sort(simiList);
        Collections.reverse(simiList);
        /**
         * 1	68646	10
         * 1	113277	10
         * 2	1798709	10
         * 2	790636	7
         * 2	68646	9
         * 3	1798709	9
         * 4	68646	8
         * 4	1798709	6
         * 5	68646	9
         * 5	1798709	8
         *
         * id = 2;
         * rater id = 1; ((10-5)*(9-5) = 20
         * rater id = 5; 4*4+5*3 = 31
         * ////output
         * rater id = 5 dotProduct 31.0
         * rater id = 3 dotProduct 20.0
         * rater id = 1 dotProduct 20.0
         * rater id = 4 dotProduct 17.0
         * */
        //
        //        for (Rating i : simiList) {
        //            System.out.println("rater id = " + i.getItem() + " dotProduct " + i.getValue());
        //
        //        }
        //
        return simiList;
    }
    
    /**
     * This method should return an ArrayList of type Rating, of movies and
     * their weighted average ratings using only the top numSimilarRaters with
     * positive ratings and including only those movies that have at least minimalRaters
     * ratings from those most similar raters (not just minimalRaters ratings overall).
     * <p>
     * For example, if minimalRaters is 3 and a movie has 4 ratings but only 2 of
     * those ratings were made by raters in the top numSimilarRaters, that movie
     * should not be included.
     * <p>
     * For each movie, calculate a weighted average movie rating based on:
     * Use only the top (largest) numSimilarRaters raters.
     * For each of these raters, multiply their similarity rating by the rating
     * they gave that movie. This will emphasize those raters who are closer to the rater id,
     * since they have greater weights. The weighted average movie rating for a
     * particular movie is the sum of these weighted average rating
     * (for each rater multiply their similarity rating by their rating for the movie), divided by the total number of such ratings.
     * <p>
     * IMPORTANT NOTICE:
     * Weighted average algorithm optimized by WEI XU
     * Instead of  "sum of (similar rating(i) *rating of the movie(i))/count of the raters"
     * !!!!!I use  "sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i), will get more good results.
     * <p>
     * For example: in short movie and short rating csv
     * RaterID = 2;
     * //     * rater id = 5 dotProduct 31.0
     * //     * rater id = 3 dotProduct 20.0
     * //     * rater id = 1 dotProduct 20.0
     * //     * rater id = 4 dotProduct 17.0
     * Movie id = 0068646 id = 5 rating 9.0 ave 279.0
     * Movie id = 0068646 id = 1 rating 10.0 ave 479.0
     * Movie id = 0068646 count 2 :  WEI XU Algorithm 9.392156862745098 DUKE ALGORITHM: 239.5
     * (9*31+10*20)/50=279+200=479/51=9.39
     * (9*31+10*20)/2 = 479/2 = 239.5
     */
    // combine getAverageByID and getAverageRatings;
    // HashMap<MovieID, ArrayList<Rater>> from top numSimilarRaters.
    //or
    // Arraylist<movieID> from top numSimilarRaters.
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        // get Arraylist<movieID> from top numSimilarRaters.
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        //System.out.println("Total number of similar raters : " + simiList1.size());
        int num = simiList1.size();
        if (numSimilarRaters > num) {
            numSimilarRaters = num;
        }
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                    //System.out.println("Movie id = " + movieID + " Rater id = " + raterID1);
                }
            }
        }
        //System.out.println("MoviebyTopSimilar Size = " + movidIDByTopSimilar.size());
        //for (String i:)
        
        //rating for movies in the movieIDByTopSimilar list;
        //Filter trueFilter = new TrueFilter();
        for (String j : movidIDByTopSimilar) {
            // rating for one movie
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;
            // System.out.println("total");
            for (int i = 0; i < numSimilarRaters; i++) {
                //   System.out.println("i=" + i);
                
                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                if (rating != -1) {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                    //System.out.println("Movie id = " + j + " count " + count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                    
                }
            }
            if (count >= minimalRaters)
                ave = total / simiweighttotal;
            //ave = total / count;
            //System.out.println("Movie id = " + j + " count " + count + " : " + " rating " + ave + " total/count " + total / count);
            //(9*31+10*20)/50=279+200=479/50=9.58
            //(9*31+10*20)/2 = 479/2 = 239.5
            // rating for one movie end
            if (ave > 0)
                ratingList.add(new Rating(j, ave));
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        
        return ratingList;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //rating for all movie
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }
        
        for (String j : movidIDByTopSimilar) {
            if (f.satisfies(j)) {
                // rating for one movie
                double ave = 0;
                ArrayList<Rating> simiList = getSimilarities(raterID);
                // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
                int count = 0;
                double total = 0;
                double simiweighttotal = 0;
                for (int i = 0; i < numSimilarRaters; i++) {
                    double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                    if (rating != -1) {
                        count++;
                        total += rating * simiList.get(i).getValue();
                        simiweighttotal += simiList.get(i).getValue();
                        
                        //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                        
                    }
                }
                if (count >= minimalRaters)
                    ave = total / simiweighttotal;
                //ave = total / count;
                
                // rating for one movie end
                if (ave > 0)
                    ratingList.add(new Rating(j, ave));
            }
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        
        return ratingList;
    }
    
    public static void main(String[] args) {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        FourthRatingsOptimizedByWeiXu sr = new FourthRatingsOptimizedByWeiXu();
        System.out.println("---------------test-------------");
        System.out.println(sr.getSimilarRatings("2", 3, 0));
        
    }
}
