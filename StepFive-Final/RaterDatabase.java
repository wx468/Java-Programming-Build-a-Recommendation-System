/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 13th, 2019
 *
 *  Description:  -------------------STEP FOUR--------
 *                 The class RaterDatabase—This class is an efficient way to get
 *                 information about raters.
 *
 ****************************************************************/

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;//key:rater id, value: rater object
    
    /**
     * A private initialize method with no parameters that initializes
     * the HashMap ourRaters if it does not exist.
     **/
    private static void initialize() {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
        }
    }
    
    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
            addRatings("data/" + filename);
        }
    }
    
    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for (CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id, item, Double.parseDouble(rating));
        }
    }
    
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);//this is the method in EfficientRater class
    }
    
    public static Rater getRater(String id) {
        initialize();
        
        return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
        
        return list;
    }
    
    public static int size() {
        return ourRaters.size();
    }
    
    
}
