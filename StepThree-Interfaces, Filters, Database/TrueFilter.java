/**
 * The class TrueFilter can be used to select every movie from
 * MovieDatabase. It’s satisfies method always returns true.
 */
public class TrueFilter implements Filter {
    @Override
    public boolean satisfies(String id) {
        return true;
    }
    
}
