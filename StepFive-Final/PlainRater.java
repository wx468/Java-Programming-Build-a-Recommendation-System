import java.util.ArrayList;

/***************************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP Three--------
 *                Change the name of the class Rater.java to PlainRater.java.
 *                Be sure to compile it to make sure your newly named class
 *                works—that you've made the changes necessary for the class
 *                to function with the name PlainRater.
 *
 ***************************************************************************/
public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;
    
    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
    
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item, rating));//item is string id?
    }
    
    public boolean hasRating(String item) {
        for (int k = 0; k < myRatings.size(); k++) {
            if (myRatings.get(k).getItem().equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String getID() {
        return myID;
    }
    
    public double getRating(String item) {
        for (int k = 0; k < myRatings.size(); k++) {
            if (myRatings.get(k).getItem().equals(item)) {
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }
    
    public int numRatings() {
        return myRatings.size();
    }
    
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (int k = 0; k < myRatings.size(); k++) {
            list.add(myRatings.get(k).getItem());
        }
        
        return list;//arrayList of item;
    }
}
