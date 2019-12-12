/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE-------------------------
 *                 Create a new class named MinutesFilter that implements Filter.
 *                 Its satisfies method should return true if a movie’s running time
 *                 is at least min minutes and no more than max minutes.
 *
 ****************************************************************/
public class MinutesFilter implements Filter {
    private int min;
    private int max;
    
    
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
