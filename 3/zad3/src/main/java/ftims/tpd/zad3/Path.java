package ftims.tpd.zad3;

import java.util.Arrays;

public class Path {

    public final int[] vertices;

    public final int dist;

    public Path(int[] vertices, int dist) {
        this.vertices = vertices;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(vertices).map(v -> v + 1).toArray()) + ", distance: " + dist;
    }
}
