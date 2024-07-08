package restauarnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Menu;
import restauarnt.domain.Orders;
import restauarnt.domain.Seat;
import restauarnt.repository.MenuRepository;
import restauarnt.repository.OrdersRepository;
import restauarnt.repository.SeatRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

	private final OrdersRepository ordersRepository;
	private final MenuRepository menuRepository;
	private final SeatRepository seatRepository;
	
	public List<Orders> findByIdOrders(int seatId) {
		return ordersRepository.findAll(seatId);
	}

	public void createOrders(int seatId, int menuId, Orders orders) {
		//주문된 Menu를 찾아서 menu의 내용을 통해 orders 객체 완성시키기
		Orders ConvertedOrders = makeOrdersToMenuInCreateOrder(seatId, menuId, orders);
		
		//repository에 저장
		ordersRepository.create(ConvertedOrders);
		
		//seat의 total값 갱신
		updateSeatPriceAmountForOrder(seatId, findByIdOrders(seatId));
	}
	
	private Orders makeOrdersToMenuInCreateOrder(int seatId, int menuId, Orders orders) {
		Menu menu = menuRepository.selectById(menuId);
		
		orders.setOrdersMenuName(menu.getMenuName());
		orders.setOrdersPriceAmount(orders.getOrdersAmount() * menu.getMenuPrice());
		orders.setOrdersSeatId(seatId);
		
		return orders;
	}
	
	private void updateSeatPriceAmountForOrder(int seatId, List<Orders> ordersList) {
		int total = 0;
		
		for(Orders seatOrder : ordersList) {
			total += seatOrder.getOrdersPriceAmount();
		}
	
		Seat totalAmountUpdatedSeat = seatRepository.findByIdSeat(seatId);
		totalAmountUpdatedSeat.setSeatPriceAmount(total);
		
		seatRepository.updateSeat(totalAmountUpdatedSeat);
	}
	
	
}
