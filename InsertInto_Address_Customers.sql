 -- Insertions dans la table `address`
INSERT INTO address (id, street, city, state, postal_code, country)
VALUES
    ('ADR1', '10 Main St', 'Montreal', 'QC', 'H1A 2B3', 'Canada'),
    ('ADR2', '20 Elm St', 'Toronto', 'ON', 'M4B 1B4', 'Canada'),
    ('ADR3', '30 Maple Ave', 'Vancouver', 'BC', 'V5K 3Z4', 'Canada'),
    ('ADR4', '40 Oak Blvd', 'Calgary', 'AB', 'T2P 5C4', 'Canada'),
    ('ADR5', '50 Birch Rd', 'Montreal', 'QC', 'H2X 1Y7', 'Canada'),
    ('ADR6', '60 Cedar Ln', 'Toronto', 'ON', 'M5A 4E2', 'Canada'),
    ('ADR7', '70 Pine Ct', 'Vancouver', 'BC', 'V6B 5T1', 'Canada'),
    ('ADR8', '80 Walnut Dr', 'Ottawa', 'ON', 'K1A 0B1', 'Canada'),
    ('ADR9', '90 Poplar St', 'Quebec City', 'QC', 'G1A 1C5', 'Canada'),
    ('ADR10', '100 Cherry Pl', 'Montreal', 'QC', 'H3A 2R7', 'Canada'),
    ('ADR11', '110 Palm Way', 'Toronto', 'ON', 'M5J 2N2', 'Canada'),
    ('ADR12', '120 Fir St', 'Vancouver', 'BC', 'V6J 4A3', 'Canada'),
    ('ADR13', '130 Spruce Blvd', 'Calgary', 'AB', 'T3A 5R6', 'Canada'),
    ('ADR14', '140 Chestnut Rd', 'Montreal', 'QC', 'H3B 2V9', 'Canada'),
    ('ADR15', '150 Aspen Dr', 'Toronto', 'ON', 'M4C 1Z2', 'Canada');

-- Insertions dans la table `customer`
INSERT INTO customer (id, email, password, first_name, last_name, phone, role, is_deleted, address_id)
VALUES
    ('USE1000', 'alice_s@gmail.com', 'pass1', 'Alice', 'Smith', '1234567891', 'customer', 0, 'ADR1'),
    ('USE1037', 'bob_j@gmail.com', 'pass2', 'Bob', 'Johnson', '2234567892', 'customer', 0, 'ADR2'),
    ('USE1074', 'charlie_b@outlook.ca', 'pass3', 'Charlie', 'Brown', '3234567893', 'customer', 0, 'ADR3'),
    ('USE1111', 'david_t@gmail.com', 'pass4', 'David', 'Taylor', '4234567894', 'customer', 0, 'ADR4'),
    ('USE1148', 'eva_d@outlook.ca', 'pass5', 'Eva', 'Davis', '5234567895', 'customer', 0, 'ADR5'),
    ('USE1185', 'frank_g@outlook.ca', 'pass6', 'Frank', 'Garcia', '6234567896', 'customer', 0, 'ADR6'),
    ('USE1222', 'grace_m@gmail.com', 'pass7', 'Grace', 'Martinez', '7234567897', 'customer', 0, 'ADR7'),
    ('USE1259', 'hannah_w@gmail.com', 'pass8', 'Hannah', 'Wilson', '8234567898', 'customer', 0, 'ADR8'),
    ('USE1296', 'isaac_l@gmail.com', 'pass9', 'Isaac', 'Lopez', '9234567899', 'customer', 0, 'ADR9'),
    ('USE1333', 'julia_c@outlook.ca', 'pass10', 'Julia', 'Clark', '1034567890', 'customer', 0, 'ADR10'),
    ('USE1370', 'kyle_l@yahoo.ca', 'pass11', 'Kyle', 'Lewis', '1134567891', 'customer', 0, 'ADR11'),
    ('USE1407', 'laura_w@outlook.ca', 'pass12', 'Laura', 'Walker', '1234567892', 'customer', 0, 'ADR12'),
    ('USE1444', 'michael_a@outlook.ca', 'pass13', 'Michael', 'Allen', '1334567893', 'customer', 0, 'ADR13'),
    ('USE1481', 'nina_y@gmail.com', 'pass14', 'Nina', 'Young', '1434567894', 'saleagent', 0, 'ADR14'),
    ('USE1518', 'oscar_h@yahoo.ca', 'pass15', 'Oscar', 'Hernandez', '1534567895', 'administrator', 0, 'ADR15');

-- Insertions dans la table `admin`
 INSERT INTO admin (id, email, password, first_name, last_name, phone, role, is_deleted, address_id)
 VALUES
     ('ADM1', 'admin@jxhifi.ca', 'adminWord1', 'Jonathan-Lion', 'Haineault-Couturier', '1234567890', 'Administrator', 0, 'ADR15')