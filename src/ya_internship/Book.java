package ya_internship;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    String title;
    int publicationYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publicationYear);
    }

}
