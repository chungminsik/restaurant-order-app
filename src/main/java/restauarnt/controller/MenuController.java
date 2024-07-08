package restauarnt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restauarnt.domain.Menu;
import restauarnt.service.MenuService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MenuController {
	
	private final MenuService menuService;
	
	@GetMapping("/menu")
	public String menuBoard(Model model) {
		List<Menu> list = menuService.findAllMenu();
		log.info("list = {}", list);
		
		model.addAttribute("menus", list);
		return "menu/menuList";
	}
	
	@GetMapping("/menu/create")
	public String menuCreateForm(Model model) {
		model.addAttribute("menu", new Menu());
		
		return "menu/menuForm";
	}
	
	@PostMapping("/menu/create")
	public String menuCreate(@ModelAttribute Menu menu, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "menu/menuForm";
		}
		
		menuService.createMenu(menu);
		
		return "redirect:/menu";
	}
	
	@GetMapping("/menu/update/{menuId}")
	public String menuEditForm(@PathVariable("menuId") int menuId, Model model) {
		model.addAttribute("menu", menuService.findByIdMenu(menuId));
		return "menu/menuEdit";
	}
	
	@PostMapping("/menu/update")
	public String menuEdit(@ModelAttribute Menu menu, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/menu/update/" + menu.getMenuId();
		}
		
		menuService.updateMenu(menu);
		
		return "redirect:/menu";
	}

	@GetMapping("/menu/delete/{menuId}")
	public String menuDelete(@PathVariable("menuId") int menuId) {
		menuService.deleteMenu(menuId);
		
		return "redirect:/menu";
	}
	
}
