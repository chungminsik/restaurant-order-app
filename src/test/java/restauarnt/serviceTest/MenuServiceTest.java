package restauarnt.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restauarnt.domain.Menu;
import restauarnt.repository.MenuRepository;
import restauarnt.service.MenuService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    private Menu sampleMenu;

    @BeforeEach
    void setUp() {
        sampleMenu = new Menu(1L, "Pasta", 12);
    }

    @Test
    void testCreateMenu() {
        when(menuRepository.create(any(Menu.class))).thenReturn(sampleMenu);

        Menu savedMenu = menuService.createMenu(sampleMenu);

        assertNotNull(savedMenu);
        assertEquals("Pasta", savedMenu.getMenuName());
        verify(menuRepository, times(1)).create(sampleMenu);
    }

    @Test
    void testSelectById() {
        when(menuRepository.selectById(1)).thenReturn(sampleMenu);

        Menu menu = menuService.findByIdMenu(1);

        assertNotNull(menu);
        assertEquals(1, menu.getMenuId());
        verify(menuRepository, times(1)).selectById(1);
    }

    @Test
    void testSelectAll() {
        List<Menu> menuList = Arrays.asList(sampleMenu, new Menu(2L, "Pizza", 15));
        when(menuRepository.selectAll()).thenReturn(menuList);

        List<Menu> result = menuService.findAllMenu();

        assertEquals(2, result.size());
        verify(menuRepository, times(1)).selectAll();
    }

    @Test
    void testUpdateMenu() {
        doNothing().when(menuRepository).update(sampleMenu);

        menuService.updateMenu(sampleMenu);

        verify(menuRepository, times(1)).update(sampleMenu);
    }

    @Test
    void testDeleteMenu() {
        doNothing().when(menuRepository).delete(1);

        menuService.deleteMenu(1);

        verify(menuRepository, times(1)).delete(1);
    }
}
