package ya.java_course.sprints.fourteen;

import java.util.Collections;
import java.util.List;

public class BookService {
    private BookDao bookDao;
    private final DateService dateService = new DateService();
    private AuthorService authorService;

    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void findPublicationYear(int bookId) {
        String creationDate = bookDao.findPublicationDate(bookId);
        int publicationYear = dateService.getYear(creationDate);
        int leastExpectedYear = 1700;
        if (publicationYear < leastExpectedYear) {
            throw new IllegalArgumentException("Год слишком маленький: где-то произошла ошибка");
        }
    }

    public String createAuthorListDescription(List<Integer> bookIdList) {
        List<String> authorList = authorService.getAuthorsList(bookIdList);

        StringBuilder result = new StringBuilder("Эти ")
                .append(bookIdList.size())
                .append(" книги были написаны ")
                .append(authorList.size())
                .append(" авторами: ");

        for (String author : authorList) {
            result.append(author).append(" ");
        }

        return result.toString();
    }

    public String createBookDescription(String bookName, int creationYear, int authorId, String authorName) {

        StringBuilder description = new StringBuilder();
        description.append(bookName).append(", ");
        description.append(creationYear);

        String authorDescription = authorService.getAuthorDescription(authorId);
        if (authorDescription != null) {
            description.append(" автор ");
            description.append(authorName).append(", ");
            description.append(authorDescription);
        } else {
            throw new DataNotAvailableException("Ошибка при доступе к базе");
        }

        return description.toString();
    }
}

class AuthorService {
    public List<String> getAuthorsList(List<Integer> bookIdList) {
        //найти в базе данных список уникальных авторов для заданных книг

        return Collections.emptyList();
    }


    public String getAuthorDescription(int id) {
        return "знаменитый русский писатель";
    }
}

class BookDao {

    public String findPublicationDate(int bookId) {
        //логика для поиска даты публикации в базе данных
        return "1998-11-24";
    }
}

class DateService {
    public int getYear(String dateString) {
        String[] dateArray = dateString.split("-");
        return Integer.parseInt(dateArray[0]);
    }
}

class DataNotAvailableException extends RuntimeException {

    DataNotAvailableException(String message) {
        super(message);
    }
}

