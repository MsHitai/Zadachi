package ya.java_course.sprints.fourteen;

import java.time.LocalDate;

public class OrderService {
    CustomerService customerService;
    BookServiceDuplicate bookService;
    OrderDao orderDao;

    public void saveOrder(int customerId, int bookId, int amount, LocalDate orderDate) {
        //orderDao.saveOrder(customerId, null, bookId, amount, null);

        if (customerService.customerIsBlocked(customerId)) {
            throw new CustomerIsBlockedException();
        }

        String customerAddress = customerService.getCustomerAddress(customerId);
        LocalDate deliveryDate = orderDate.plusDays(2);

        orderDao.saveOrder(customerId, customerAddress, bookId, amount, deliveryDate);
        bookService.decreaseBookAvailableAmount(bookId, amount);
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setBookService(BookServiceDuplicate bookService) {
        this.bookService = bookService;
    }

}

class BookServiceDuplicate {
    public void decreaseBookAvailableAmount(int bookId, int amount) {
        //уменьшаем доступное количество единиц в БД на 'amount'
    }
}

class CustomerService {

    public boolean customerIsBlocked(int customerId) {
        //проверяем, нет ли у пользователя неоплаченных заказов
        return false;
    }

    public String getCustomerAddress(int customerId) {
        //получаем адрес доставки из базы данных
        return "address";
    }
}

class OrderDao {
    public void saveOrder(int customerId, String deliveryAddress, int bookId, int amount, LocalDate deliveryDate) {
        //сохраняем заказ в базу данных

    }
}

class CustomerIsBlockedException extends RuntimeException {
}
