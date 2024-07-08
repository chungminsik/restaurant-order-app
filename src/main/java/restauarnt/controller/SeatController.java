package restauarnt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Seat;
import restauarnt.service.SeatService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SeatController {
	
	private final SeatService seatService;
	
	@GetMapping("/seat")
	public String seatBoard(Model model) {
		
		List<Seat> seats = seatService.findAllSeat();
		model.addAttribute("seats", seats);
		
		return "seat/seatList";
	}
	
	@PostMapping("/seat/create")
	public String seatCreate(@ModelAttribute Seat seat) {
		
		seatService.createSeat(seat);
		
		return "redirect:/seat";
	}
	
	@PostMapping("/seat/update")
	public String seatUpdateName(@ModelAttribute Seat seat) {
		log.info("{}, {}, {}", seat.getSeatId(), seat.getSeatName(), seat.getSeatPriceAmount());
		//기존에 있있던 총 가격 정보를 이전
		Seat findSeat = seatService.findByIdSeat(seat.getSeatId().intValue());
		seat.setSeatPriceAmount(findSeat.getSeatPriceAmount());
		
		seatService.updateSeat(seat);
		
		return "redirect:/seat";
	}
	
	@GetMapping("/{seatId}/seat/clear")
	public String seatClear(@PathVariable("seatId") int seatId) {
		
		seatService.clearSeat(seatId);
		
		return "redirect:/seat";
	}
	
	@GetMapping("/{seatId}/seat/delete")
	public String seatDelete(@PathVariable("seatId") int seatId) {
		seatService.deleteSeat(seatId);
		
		return "redirect:/seat";
	}
	
}
