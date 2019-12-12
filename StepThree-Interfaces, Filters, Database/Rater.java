import java.util.ArrayList;

/***************************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP Three--------
 *                Create a new public interface named Rater. Add methods to
 *                this new interface by copying all the method signatures
 *                from the PlainRater class.
 *
 ***************************************************************************/

public interface Rater {
    public void addRating(String item, double rating);
    
    public boolean hasRating(String item);
    
    public String getID();
    
    public double getRating(String item);
    
    public int numRatings();
    
    public ArrayList<String> getItemsRated();
}
