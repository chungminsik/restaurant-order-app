package restauarnt.serviceTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restauarnt.domain.Menu;
import restauarnt.domain.Orders;
import restauarnt.domain.Seat;
import restauarnt.repository.MenuRepository;
import restauarnt.repository.OrdersRepository;
import restauarnt.repository.SeatRepository;
import restauarnt.service.OrdersService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private OrdersService ordersService;

    private Orders sampleOrder;
    private Seat sampleSeat;
    private Menu sampleMenu;

    @BeforeEach
    void setUp() {
        sampleOrder = new Orders();
        sampleOrder.setOrdersId(1L);
        sampleOrder.setOrdersMenuName("Pasta");
        sampleOrder.setOrdersAmount(2);
        sampleOrder.setOrdersPriceAmount(24);
        sampleOrder.setOrdersDatetime(new Timestamp(System.currentTimeMillis()));
        sampleOrder.setOrdersSeatId(1);

        sampleSeat = new Seat(1L, "Table 1", 100);
        sampleMenu = new Menu(1L, "Pasta", 12);
    }

    @Test
    void testFindByIdOrders() {
        List<Orders> ordersList = Arrays.asList(sampleOrder);
        when(ordersRepository.findAll(1)).thenReturn(ordersList);

        List<Orders> result = ordersService.findByIdOrders(1);

        assertEquals(1, result.size());
        verify(ordersRepository, times(1)).findAll(1);
    }

    @Test
    void testCreateOrders() {
        when(menuRepository.selectById(1)).thenReturn(sampleMenu);
        when(seatRepository.findByIdSeat(1)).thenReturn(sampleSeat);
        doNothing().when(ordersRepository).create(any(Orders.class));
        doNothing().when(seatRepository).updateSeat(any(Seat.class));

        Orders newOrder = new Orders();
        newOrder.setOrdersAmount(2);
        ordersService.createOrders(1, 1, newOrder);

        verify(ordersRepository, times(1)).create(any(Orders.class));
        verify(seatRepository, times(1)).updateSeat(any(Seat.class));
    }

    @Test
    void testDeleteOrders() {
        when(seatRepository.findByIdSeat(1)).thenReturn(sampleSeat);
        doNothing().when(seatRepository).updateSeat(any(Seat.class));
        doNothing().when(ordersRepository).delete(1);

        ordersService.deleteOrders(1, 1, 24);

        verify(ordersRepository, times(1)).delete(1);
        verify(seatRepository, times(1)).updateSeat(any(Seat.class));
    }
}