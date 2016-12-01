package day1;

import java.util.Objects;
import com.google.common.collect.ComparisonChain;

public class Point implements Comparable<Point> {
    final int x;
    final int y;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    Point(final int x, final int y) {
        this.x = x;
        this.y = y;

    }


    @Override
    public int compareTo(final Point o) {
        return ComparisonChain.start().compare(x, o.x).compare(y, o.y).result();
    }
}
