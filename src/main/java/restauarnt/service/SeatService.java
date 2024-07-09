package restauarnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Seat;
import restauarnt.repository.OrdersRepository;
import restauarnt.repository.SeatRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatService {

	private final SeatRepository seatRepository;
	private final OrdersRepository ordersRepository;
	
	public List<Seat> findAllSeat() {
		List<Seat> list = seatRepository.findAllSeat();
		
		return list;
	}
	
	public Seat findByIdSeat(int seatId) {
		return seatRepository.findByIdSeat(seatId);
	}

	public Seat createSeat(Seat seat) {
		return seatRepository.createSeat(seat);
	}

	public void updateSeat(Seat seat) {
		Seat findSeat = findByIdSeat(seat.getSeatId().intValue());
		findSeat.setSeatPriceAmount(seat.getSeatPriceAmount());
		findSeat.setSeatName(seat.getSeatName());
		
		seatRepository.updateSeat(findSeat);
	}

	public void clearSeat(int seatId) {
		seatRepository.deleteSeatOrder(seatId);
		Seat clearSeat = findByIdSeat(seatId);
		clearSeat.setSeatPriceAmount(0);
		
		log.info("{}, {}", clearSeat.getSeatName(), clearSeat.getSeatPriceAmount());
		
		updateSeat(clearSeat);
	}
	
	public void deleteSeat(int seatId) {
		if(!ordersRepository.findAll(seatId).isEmpty()) {
			seatRepository.deleteSeatOrder(seatId);
			seatRepository.deleteSeat(seatId);
		}
		seatRepository.deleteSeat(seatId);
	}
}
