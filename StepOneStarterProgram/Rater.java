/**
 * Write a description of class Rater here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class Rater {
    private String myID;
    private ArrayList<Rating> myRatings;
    
    public Rater(String id) {
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
