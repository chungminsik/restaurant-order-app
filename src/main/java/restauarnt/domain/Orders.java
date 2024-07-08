package restauarnt.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {
	private Long ordersId;
	private String ordersMenuName;
	private int ordersAmount;
	private int ordersPriceAmount;
	private Timestamp ordersDatetime;
	private int ordersSeatId;
	
	
}
