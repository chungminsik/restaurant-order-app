package restauarnt.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import restauarnt.domain.Orders;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {
	
	private final SqlSessionTemplate sql;

	public List<Orders> findAll(int seatId) {
		return sql.selectList("Orders.findAllSeatOrders", seatId);
	}

	public void create(Orders orders) {
		sql.insert("Orders.create", orders);
		
	}

    public void delete(int orderId) {
		sql.delete("Orders.delete", orderId);
    }
}
