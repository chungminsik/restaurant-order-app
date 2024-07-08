package restauarnt.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import restauarnt.domain.Seat;

@Repository
@RequiredArgsConstructor
public class SeatRepository {
	
	private final SqlSessionTemplate sql;

	public List<Seat> findAllSeat() {
		return sql.selectList("Seat.findAll");
	}
	
	public Seat findByIdSeat(int seatId) {
		return sql.selectOne("Seat.findById", seatId);
	}

	public void createSeat(Seat seat) {
		sql.insert("Seat.create", seat);
	}

	public void updateSeat(Seat seat) {
		sql.update("Seat.update", seat);
	}

	public void deleteSeatOrder(int seatId) {
		sql.delete("Seat.deleteSeatOrder", seatId);
	}
	
	public void deleteSeat(int seatId) {
		sql.delete("Seat.deleteSeat", seatId);
	}
}
