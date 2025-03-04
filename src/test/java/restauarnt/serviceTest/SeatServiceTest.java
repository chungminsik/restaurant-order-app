package restauarnt.serviceTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restauarnt.domain.Seat;
import restauarnt.repository.OrdersRepository;
import restauarnt.repository.SeatRepository;
import restauarnt.service.SeatService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private SeatService seatService;

    private Seat sampleSeat;

    @BeforeEach
    void setUp() {
        sampleSeat = new Seat(1L, "Table 1", 100);
    }

    @Test
    void testFindAllSeat() {
        List<Seat> seats = Arrays.asList(sampleSeat, new Seat(2L, "Table 2", 200));
        when(seatRepository.findAllSeat()).thenReturn(seats);

        List<Seat> result = seatService.findAllSeat();

        assertEquals(2, result.size());
        verify(seatRepository, times(1)).findAllSeat();
    }

    @Test
    void testFindByIdSeat() {
        when(seatRepository.findByIdSeat(1)).thenReturn(sampleSeat);

        Seat result = seatService.findByIdSeat(1);

        assertNotNull(result);
        assertEquals(1L, result.getSeatId());
        verify(seatRepository, times(1)).findByIdSeat(1);
    }

    @Test
    void testCreateSeat() {
        when(seatRepository.createSeat(any(Seat.class))).thenReturn(sampleSeat);

        Seat result = seatService.createSeat(sampleSeat);

        assertNotNull(result);
        assertEquals("Table 1", result.getSeatName());
        verify(seatRepository, times(1)).createSeat(sampleSeat);
    }

    @Test
    void testUpdateSeat() {
        when(seatRepository.findByIdSeat(1)).thenReturn(sampleSeat);
        doNothing().when(seatRepository).updateSeat(any(Seat.class));

        Seat updatedSeat = new Seat(1L, "Table 1 Updated", 150);
        seatService.updateSeat(updatedSeat);

        verify(seatRepository, times(1)).updateSeat(any(Seat.class));
    }

    @Test
    void testClearSeat() {
        when(seatRepository.findByIdSeat(1)).thenReturn(sampleSeat);
        doNothing().when(seatRepository).deleteSeatOrder(1);
        doNothing().when(seatRepository).updateSeat(any(Seat.class));

        seatService.clearSeat(1);

        verify(seatRepository, times(1)).deleteSeatOrder(1);
        verify(seatRepository, times(1)).updateSeat(any(Seat.class));
    }

    @Test
    void testDeleteSeat() {
        when(ordersRepository.findAll(1)).thenReturn(Arrays.asList());
        doNothing().when(seatRepository).deleteSeat(1);
        doNothing().when(seatRepository).deleteSeatOrder(1);

        seatService.deleteSeat(1);

        verify(seatRepository, times(1)).deleteSeat(1);
    }
}
