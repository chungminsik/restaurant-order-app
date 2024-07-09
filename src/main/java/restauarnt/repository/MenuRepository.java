package restauarnt.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import restauarnt.domain.Menu;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
	
	private final SqlSessionTemplate sql;

	public Menu create(Menu menu) {
		sql.insert("Menu.create", menu);
		
		return menu;
	}
	
	public List<Menu> selectAll() {
		return sql.selectList("Menu.selectAll");
	}
	
	public Menu selectById(int menuId) {
		return sql.selectOne("Menu.selectById", menuId);
	}

	public void update(Menu menu) {
		sql.update("Menu.update", menu);
	}

	public void delete(int menuId) {
		sql.delete("Menu.delete", menuId);
	}

}
