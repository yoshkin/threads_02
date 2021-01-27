import java.util.Arrays;
import java.util.Set;

public class GoField {

    static final int FIELD_SIZE = 3;

    final Figure[][] figures =  new Figure[FIELD_SIZE][FIELD_SIZE];

    GoField(){} // Standard constructor

    // BEGIN (write your solution here) Maybe you want to write a custom field constructor?

    // END

    @Override//Необходимо для работы Set.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoField goField = (GoField) o;

        return Arrays.deepEquals(figures, goField.figures);

    }

    @Override //Необходимо для работы Set.
    public int hashCode() {
        return Arrays.deepHashCode(figures);
    }

    @Override//Может поможет в отлове багов.
    public String toString() {
        return "GoField{figures=" + Arrays.deepToString(figures) + '}';
    }
}