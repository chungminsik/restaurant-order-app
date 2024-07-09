package restauarnt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	
	private Long menuId;
	private String menuName;
	private int menuPrice;
	
	public Menu() {
		
	}
	
	public Menu(Long menuId, String menuName, int menuPrice) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
}
