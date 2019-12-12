/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE-------------------------
 *                Create a new class named DirectorsFilter that implements Filter.
 *                The constructor should have one parameter named directors
 *                representing a list of directors separated by commas.
 *
 *                (Example: "Charles Chaplin,Michael Mann,Spike Jonze",
 *                and its satisfies method should return true if a movie has
 *                at least one of these directors as one of its directors.
 *                Note that each movie may have several directors.
 *
 ****************************************************************/
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] dir = directors.split(",");
        //boolean exist = false;
        for (String i : dir) {
            //System.out.println(id + " : " + MovieDatabase.getDirector(id) + " : " + MovieDatabase.getTitle(id));
            if (MovieDatabase.getDirector(id).contains(i)) {
                return true;
                //break;
            }
        }
        return false;
    }
}
