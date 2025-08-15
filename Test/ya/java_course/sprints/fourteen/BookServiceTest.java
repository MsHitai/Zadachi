package ya.java_course.sprints.fourteen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

class BookServiceTest {
    @Test
    void testCreateAuthorListDescription() {
        BookService bookService = new BookService();
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
        bookService.setAuthorService(mockAuthorService);

        Mockito
                .when(mockAuthorService.getAuthorsList(Arrays.asList(3, 12, 7, 4)))
                .thenReturn(List.of("Шекспир", "Байрон"));
        String description = bookService.createAuthorListDescription(Arrays.asList(3, 12, 7, 4));

        Assertions.assertEquals("Эти 4 книги были написаны 2 авторами: Шекспир Байрон ", description);
    }

    @Test
    void testCreateBookDescriptionComplexLogic() {
        BookService bookService = new BookService();
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
        bookService.setAuthorService(mockAuthorService);

        Mockito
                .when(mockAuthorService.getAuthorDescription(anyInt()))
                .thenAnswer(invocationOnMock -> {
                    int authorId = invocationOnMock.getArgument(0, Integer.class);
                    if (authorId % 2 == 0) {
                        //чётные id у русских авторов
                        return "великий русский писатель";
                    } else {
                        //нечётные id у английских авторов
                        return "великий английский писатель";
                    }
                });

        String bookDescriptionEnglish = bookService
                .createBookDescription("Гамлет", 1599, 11, "Уильям Шекспир");
        Assertions
                .assertEquals("Гамлет, 1599 автор Уильям Шекспир, великий английский писатель",
                        bookDescriptionEnglish);

        String bookDescriptionRussian = bookService
                .createBookDescription("Война и мир", 1898, 6, "Л.Н.Толстой");
        Assertions
                .assertEquals("Война и мир, 1898 автор Л.Н.Толстой, великий русский писатель",
                        bookDescriptionRussian);
    }

    @Test
    void testCreateBookDescriptionWhenException() {
        BookService bookService = new BookService();
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
        bookService.setAuthorService(mockAuthorService);

        Mockito
                .when(mockAuthorService.getAuthorDescription(Mockito.anyInt()))
                .thenThrow(new DataNotAvailableException("Ошибка при доступе к базе"));

        final DataNotAvailableException exception = Assertions.assertThrows(
                DataNotAvailableException.class,
                () -> bookService.createBookDescription("Война и мир", 1898, 5, "Л.Н.Толстой"));

        Assertions.assertEquals("Ошибка при доступе к базе", exception.getMessage());
    }

    @Test
    public void testFindPublicationYearWithDaoException() {
        BookService bookService = new BookService();
        BookDao mockBookDao = Mockito.mock(BookDao.class);
        bookService.setBookDao(mockBookDao);

        Mockito
                .when(mockBookDao.findPublicationDate(5))
                .thenThrow(new DataNotAvailableException("Данные не найдены"));

        final DataNotAvailableException exception = Assertions.assertThrows(
                DataNotAvailableException.class,
                () -> bookService.findPublicationYear(5));

        Assertions.assertEquals("Данные не найдены", exception.getMessage());
    }


}