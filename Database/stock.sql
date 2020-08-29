DROP DATABASE IF EXISTS stock;
CREATE DATABASE stock;
USE stock;
CREATE TABLE company (
  company_id int NOT NULL,
  company_name varchar(100) NOT NULL,
  company_reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO company (company_id, company_name, company_reg_date, status) VALUES
(1, 'HCL Tech', '2029-08-19 18:30:00', 1),
(2, 'IBM Tech', '2029-08-19 18:30:00', 1),
(3, 'HDFC Bank', '2029-08-19 18:30:00', 1),
(4, 'RIL', '2029-08-19 18:30:00', 1),
(5, 'ICICI Bank', '2029-08-19 18:30:00', 1),
(6, 'TANSOL', '2029-08-19 18:30:00', 1),
(7, 'HUL', '2029-08-19 18:30:00', 1),
(8, 'INFOSYS', '2029-08-19 18:30:00', 1),
(9, 'WIPRO', '2029-08-19 18:30:00', 1),
(10, 'GOOGLE', '2029-08-19 18:30:00', 1);

CREATE TABLE stock_quote (
  stock_id int NOT NULL,
  stock_company_id int NOT NULL,
  stock_name varchar(50) NOT NULL,
  stock_timestamp bigint NOT NULL,
  stock_date_time datetime NOT NULL,
  stock_price decimal(10,2) NOT NULL DEFAULT '0.00',
  stock_currency enum('INR','USD') NOT NULL,
  stock_availability tinyint(1) NOT NULL DEFAULT '1',
  status tinyint(1) NOT NULL,
  stock_last_activity timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO stock_quote (stock_id, stock_company_id, stock_name, stock_timestamp, stock_date_time, stock_price, stock_currency, stock_availability, status) VALUES
(1, 1, 'HCL', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 1, 1),
(2, 2, 'IBM', 1586629800000, '2012-04-20 00:00:00', '400.20', 'INR', 0, 1),
(3, 3, 'HDFC', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 1, 1),
(4, 4, 'RIL', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 1, 1),
(5, 5, 'ICICI', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 0, 1),
(6, 6, 'TANSOL', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 0, 1),
(7, 7, 'HUL', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 1, 1),
(8, 8, 'INFOSYS', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 1, 1),
(9, 9, 'WIPRO', 1586629800000, '2012-04-20 00:00:00', '200.20', 'INR', 0, 1),
(10, 10, 'GOOGLE', 1586629800000, '2012-04-20 00:00:00', '800.20', 'INR', 1, 1);

ALTER TABLE company
  ADD PRIMARY KEY (company_id);

ALTER TABLE stock_quote
  ADD PRIMARY KEY (stock_id),
  ADD KEY stock_company_id (stock_company_id);

ALTER TABLE company
  MODIFY company_id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE stock_quote
  MODIFY stock_id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
ALTER TABLE stock_quote
  ADD CONSTRAINT stock_quote_ibfk_1 FOREIGN KEY (stock_company_id) REFERENCES company (company_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

