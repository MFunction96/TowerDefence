package Algorithm;

import BaseClass.Location;

/**
 * Created by MFunction on 2017/4/15.
 * This Class is used to finding the path from start to end.
 * @author MFunction
 *
 */
public class PathSearch {
    private final static Location DP[]= {new Location(1, 0), new Location(1, 1), new Location(0, 1), new Location(-1, 0), new Location(0, -1), new Location(1, -1)};
    public PathSearch() {

    }
}
