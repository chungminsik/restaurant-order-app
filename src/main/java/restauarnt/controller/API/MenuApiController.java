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
import restauarnt.domain.Menu;
import restauarnt.service.MenuService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuApiController {
	
	private final MenuService menuService;
	
	@PostMapping("/menu/create-api")
	public ResponseEntity<Menu> menuCreate(@RequestBody Menu menu) {
		if(menu.getMenuName().equals("") || menu.getMenuName().isEmpty()) {
			return new ResponseEntity<Menu>(menu, HttpStatus.BAD_REQUEST);
		}
		
		Menu insertedMenu = menuService.createMenu(menu);
		
		return new ResponseEntity<>(insertedMenu, HttpStatus.OK);
	}
	
	@GetMapping("/menu/findAll-api")
	public ResponseEntity<List<Menu>> menuFindAll(){
		List<Menu> menuList = menuService.findAllMenu();
		
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}
	
	@GetMapping("/menu/findById-api/{menuId}")
	public ResponseEntity<Menu> menuFindById(@PathVariable("menuId") int menuId){
		Menu menu = menuService.findByIdMenu(menuId);
		if(menu == null) {
			return new ResponseEntity<>(new Menu(-1L, "unexist id", -1), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(menu, HttpStatus.OK);
	}
	
}
