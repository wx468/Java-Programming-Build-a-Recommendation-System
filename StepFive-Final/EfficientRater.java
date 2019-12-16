import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP Three----------------------------
 *                Create a new class named EfficientRater, and copy the
 *                PlainRater class into this class. You will make several
 *                changes to this class, including:
 *
 *                Change the ArrayList of type Rating private variable to a
 *                HashMap<String,Rating>. The key in the HashMap is a movie ID,
 *                and its value is a rating associated with this movie.
 *                You will need to change addRating to instead add a new Rating
 *                to the HashMap with the value associated with the movie ID
 *                String item as the key in the HashMap. The method hasRating
 *                should now be much shorter; it no longer needs a loop.
 *                More method need change.......
 *
 ***************************************************************************/
public class EfficientRater implements Rater {
    private String myID;
    /* Change the ArrayList of type Rating private variable to a HashMap<String,Rating>.
       The key in the HashMap is a movie ID, and
        its value is a rating associated with this movie.*/
    //private HashMap<String, ArrayList<Rating>> myRatings;//why not this one?
    private HashMap<String, Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }
    
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));//item is string id?
    }
    
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }
    
    public String getID() {
        return myID;
    }
    
    public double getRating(String item) {
        //        for (int k = 0; k < myRatings.size(); k++) {
        //            if (myRatings.get(k).getItem().equals(item)) {
        //                return myRatings.get(k).getValue();
        //            }
        //        }
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }
    
    public int numRatings() {
        return myRatings.size();
    }
    
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>(myRatings.keySet());
        //        for (int k = 0; k < myRatings.size(); k++) {
        //            list.add(myRatings.get(k).getItem());
        //        }
        //
        return list;//arrayList of item;
    }
}
