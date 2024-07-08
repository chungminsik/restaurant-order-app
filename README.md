# 데이터베이스 설정
```
use restaurant;

create table menu(
	menu_id int auto_increment primary key,
    menu_name varchar(30) not null,
    menu_price decimal(10, 2)
);

CREATE TABLE seat (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    seat_name VARCHAR(30) NOT NULL,
    seat_price_amount INT
);

CREATE TABLE orders (
    orders_id INT AUTO_INCREMENT PRIMARY KEY,
    orders_menu_name VARCHAR(30) NOT NULL,
    orders_amount INT DEFAULT 1,
    orders_price_amount INT,
    orders_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    orders_seat_id INT,
    FOREIGN KEY (orders_seat_id) REFERENCES seat(seat_id)
);
```
