package restauarnt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Menu;
import restauarnt.domain.Orders;
import restauarnt.service.MenuService;
import restauarnt.service.OrdersService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrdersContorller {
	
	private final OrdersService ordersService;
	private final MenuService menuService;
	
	@GetMapping("/{seatId}/orders")
	public String ordersBoard(@PathVariable("seatId") int seatId, Model model) {
		List<Orders> orders = ordersService.findByIdOrders(seatId);
		
		model.addAttribute("orders", orders);
		model.addAttribute("seatId", seatId);
		
		return "orders/ordersList";
	}
	
	@GetMapping("/{seatId}/orders/create")
	public String ordersCreateForm(@PathVariable("seatId") int seatId, Model model) {
		List<Menu> menus = menuService.findAllMenu();
		
		model.addAttribute("menus", menus);
		model.addAttribute("seatId", seatId);
		return "orders/ordersForm";
	}
	
	@PostMapping("/{seatId}/orders/create")
	public String ordersCreate(@PathVariable("seatId") int seatId, @RequestParam("menuId") int menuId, @ModelAttribute Orders orders) {
		log.info("객체 {}, {}", menuId, orders);
		log.info("{}", menuId);
		
		ordersService.createOrders(seatId, menuId, orders);
		
		return "redirect:/" + seatId + "/orders";
	}
	
	@GetMapping("/{seatId}/orders/update")
	public String ordersEditForm(@PathVariable("seatId") int seatId, Model model) {
		model.addAttribute("orders", ordersService.findByIdOrders(seatId));
		
		return "orders/ordersEdit";
	}


	@PostMapping("/{seatId}/orders/update")
	public String ordersEdit(@ModelAttribute Orders orders){
		//TODO
		return "";

	}

	@PostMapping("/{seatId}/orders/delete")
	public String ordersDelete(@RequestParam("ordersId") int ordersId, @RequestParam("ordersPriceAmount") int ordersPriceAmount, @PathVariable("seatId") int seatId){
		ordersService.deleteOrders(ordersId, seatId, ordersPriceAmount);

		return "redirect:/" + seatId + "/orders";

	}
}
