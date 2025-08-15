package ya.java_course.sprints.fourteen;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;

class OrderServiceTest {

    @Test
    void testSaveOrder() {
        OrderService orderService = new OrderService();

        CustomerService customerService = Mockito.mock(CustomerService.class);
        BookServiceDuplicate bookService = Mockito.mock(BookServiceDuplicate.class);
        OrderDao orderDao = Mockito.mock(OrderDao.class);

        orderService.setCustomerService(customerService);
        orderService.setBookService(bookService);
        orderService.setOrderDao(orderDao);

        Mockito.when(customerService.customerIsBlocked(anyInt())).thenReturn(false);
        Mockito.when(customerService.getCustomerAddress(anyInt())).thenReturn("адрес");

        LocalDate orderDate = LocalDate.of(2022, 4, 12);
        LocalDate deliveryDate = LocalDate.of(2022, 4, 14);
        orderService.saveOrder(2, 5, 1, orderDate);

        Mockito.verify(orderDao, Mockito.times(1))
                .saveOrder(2, "адрес", 5, 1, deliveryDate);
        Mockito.verify(bookService, Mockito.times(1))
                .decreaseBookAvailableAmount(5, 1);
        Mockito.verify(customerService, Mockito.atLeast(1))
                .customerIsBlocked(anyInt());
        Mockito.verify(customerService, Mockito.times(1))
                .getCustomerAddress(2);

        Mockito.verifyNoMoreInteractions(customerService, bookService, orderDao);

    }
}