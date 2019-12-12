/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE-------------------------
 *                 Create a new class named GenreFilter that implements Filter.
 *                 The constructor should have one parameter named genre
 *                 representing one genre, and the satisfies method should
 *                 return true if a movie has this genre. Note that movies
 *                 may have several genres.
 *
 ****************************************************************/

public class GenreFilter implements Filter {
    private String genre;
    
    public GenreFilter(String genre) {
        this.genre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
