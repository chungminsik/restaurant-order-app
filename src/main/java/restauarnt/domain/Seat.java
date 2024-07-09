package restauarnt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
	private Long seatId;
	private String seatName;
	private int seatPriceAmount;
	
	public Seat() {
		
	}
	
	public Seat(Long seatId, String seatName, int seatPriceAmount) {
		this.seatId = seatId;
		this.seatName = seatName;
		this.seatPriceAmount = seatPriceAmount;
	}
}
