package restauarnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import restauarnt.domain.Menu;
import restauarnt.repository.MenuRepository;

@Service
@RequiredArgsConstructor
public class MenuService {
	
	private final MenuRepository menuRepository;

	public List<Menu> findAllMenu() {
		return menuRepository.selectAll();
	}

	public Menu findByIdMenu(int menuId) {
		return menuRepository.selectById(menuId);
	}
	
	public Menu createMenu(Menu menu) {
		return menuRepository.create(menu);
	}

	public void updateMenu(Menu menu) {
		menuRepository.update(menu);
	}

	public void deleteMenu(int menuId) {
		menuRepository.delete(menuId);
		
		// TODO Auto-generated method stub
		
	}

}
