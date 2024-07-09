package restauarnt.controller.API;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Seat;
import restauarnt.service.SeatService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SeatApiContorller {

	private final SeatService seatService;
	
	@GetMapping("/seat-api")
	public ResponseEntity<List<Seat>> seatFindAll(){
		List<Seat> seatList = seatService.findAllSeat();
		
		return new ResponseEntity<List<Seat>>(seatList, HttpStatus.OK);
	}
	
	@GetMapping("/seat/{seatId}/findById-api")
	public ResponseEntity<Seat> seatfindById(@PathVariable("seatId") int seatId){
		Seat seat = seatService.findByIdSeat(seatId);
		
		if(seat == null) {
			return new ResponseEntity<Seat>(new Seat(-1L, "unexist id", -1), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Seat>(seat, HttpStatus.OK);
	}
	
	@PostMapping("/seat/create-api")
	public ResponseEntity<Seat> seatCreate(@RequestBody Seat seat) {
		Seat insertedSeat = seatService.createSeat(seat);
		
		return new ResponseEntity<Seat>(insertedSeat, HttpStatus.OK);
	}
}
