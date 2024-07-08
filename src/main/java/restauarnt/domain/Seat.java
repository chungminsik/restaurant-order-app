package restauarnt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
	private Long seatId;
	private String seatName;
	private int seatPriceAmount;
}
