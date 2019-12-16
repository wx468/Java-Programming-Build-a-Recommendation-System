/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE--------
 *                 The interface Filter has only one signature for
 *                 the method satisfies. Any filters that implement
 *                 this interface must also have this method.
 *                 The method satisfies has one String parameter
 *                 named id representing a movie ID. This method returns
 *                 true if the movie satisfies the criteria in the method
 *                 and returns false otherwise.
 *
 ****************************************************************/

public interface Filter {
    public boolean satisfies(String id);
}
