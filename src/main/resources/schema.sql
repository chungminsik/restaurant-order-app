-- 테이블: seat
CREATE TABLE IF NOT EXISTS seat (
  seat_id INT NOT NULL AUTO_INCREMENT,
  seat_name VARCHAR(30) NOT NULL,
  seat_price_amount INT DEFAULT NULL,
  PRIMARY KEY (seat_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블: menu
CREATE TABLE IF NOT EXISTS menu (
  menu_id INT NOT NULL AUTO_INCREMENT,
  menu_name VARCHAR(30) NOT NULL,
  menu_price DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블: orders
CREATE TABLE IF NOT EXISTS orders (
  orders_id INT NOT NULL AUTO_INCREMENT,
  orders_menu_name VARCHAR(30) NOT NULL,
  orders_amount INT DEFAULT 1,
  orders_price_amount INT DEFAULT NULL,
  orders_datetime TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  orders_seat_id INT DEFAULT NULL,
  PRIMARY KEY (orders_id),
  CONSTRAINT fk_orders_seat_id FOREIGN KEY (orders_seat_id) REFERENCES seat (seat_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
