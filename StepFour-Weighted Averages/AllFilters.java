import java.util.ArrayList;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE-------------------------
 *                Now use the AllFilters class to combine asking questions
 *                about average ratings by genre and films on or after a
 *                particular year.
 *
 ****************************************************************/

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    @Override
    public boolean satisfies(String id) {
        for (Filter f : filters) {
            if (!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
    
}
