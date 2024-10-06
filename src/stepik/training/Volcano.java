package stepik.training;

import lombok.Setter;

@Setter
public class Volcano {

    private Integer north;
    private Integer east;
    private Integer west;
    private Integer south;

    public Integer getNorth() {
        if (north == null) {
            return -1;
        }
        return north;
    }

    public Integer getEast() {
        if (east == null) {
            return -1;
        }
        return east;
    }

    public Integer getWest() {
        if (west == null) {
            return -1;
        }
        return west;
    }

    public Integer getSouth() {
        if (south == null) {
            return -1;
        }
        return south;
    }
}
