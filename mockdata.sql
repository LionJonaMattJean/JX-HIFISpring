INSERT INTO category (id, name, description) VALUES
                                                 ('CAT222', 'Desktop', 'Pre-built computer for your daily work from home or office.'),
                                                 ('CAT333', 'Laptop', 'Powerful computer for your remote life.'),
                                                 ('CAT444', 'Phone', 'Flagship from the biggest company in the world.'),
                                                 ('CAT555', 'Monitor', 'Monitor to suit all your needs. Gaming, creating, working, we have it.'),
                                                 ('CAT666', 'Headphone', 'Classic headphones or in-ear for the best sound.');


INSERT INTO product (id, brand, cost_price, description, name, on_sale, sell_price, special_price, stock, category) VALUES
                                                                                                                        ('PRO1', 'Dell', 800.00, 'High-performance desktop computer', 'Dell Inspiron', 1, 1200.00, 1000.00, 10, 'CAT222'),
                                                                                                                        ('PRO2', 'HP', 700.00, 'Compact desktop with great features', 'HP Pavilion', 0, 900.00, 850.00, 15, 'CAT222'),
                                                                                                                        ('PRO3', 'Apple', 1200.00, 'MacBook Air with M1 chip', 'MacBook Air', 1, 1500.00, 1400.00, 8, 'CAT333'),
                                                                                                                        ('PRO4', 'Lenovo', 850.00, 'Affordable laptop for students', 'Lenovo IdeaPad', 0, 1000.00, 950.00, 20, 'CAT333'),
                                                                                                                        ('PRO5', 'Samsung', 1000.00, 'Galaxy S22 Ultra with top-tier camera', 'Galaxy S22 Ultra', 1, 1300.00, 1200.00, 12, 'CAT444'),
                                                                                                                        ('PRO6', 'Apple', 1100.00, 'iPhone 14 Pro with A16 Bionic', 'iPhone 14 Pro', 0, 1400.00, 1300.00, 6, 'CAT444'),
                                                                                                                        ('PRO7', 'Dell', 200.00, '27-inch Full HD monitor', 'Dell UltraSharp', 1, 300.00, 250.00, 25, 'CAT555'),
                                                                                                                        ('PRO8', 'LG', 250.00, '32-inch 4K monitor', 'LG UltraFine', 0, 400.00, 350.00, 18, 'CAT555'),
                                                                                                                        ('PRO9', 'Sony', 100.00, 'Wireless noise-canceling headphones', 'Sony WH-1000XM4', 1, 300.00, 200.00, 30, 'CAT666'),
                                                                                                                        ('PRO10', 'Bose', 150.00, 'Comfortable over-ear headphones', 'Bose QuietComfort 45', 0, 400.00, 350.00, 15, 'CAT666');

INSERT INTO specification_details (id, description, title, product_id) VALUES
-- PRO1: Dell Inspiron
('SPE1', 'Intel i5 processor, 16GB RAM, 512GB SSD', 'Processor and Memory', 'PRO1'),
('SPE2', '1TB HDD, Windows 11', 'Storage and OS', 'PRO1'),
('SPE3', 'Integrated Intel UHD Graphics', 'Graphics', 'PRO1'),
('SPE4', 'Wireless keyboard and mouse included', 'Accessories', 'PRO1'),
('SPE5', 'Supports dual monitors', 'Connectivity', 'PRO1'),
('SPE6', 'Compact tower design', 'Design', 'PRO1'),
('SPE7', 'Energy Star certified for efficiency', 'Certifications', 'PRO1'),
('SPE8', '3-year limited warranty', 'Warranty', 'PRO1'),

-- PRO2: HP Pavilion
('SPE9', 'AMD Ryzen 5, 8GB RAM, 256GB SSD', 'Processor and Memory', 'PRO2'),
('SPE10', '500GB HDD, Windows 10 Pro', 'Storage and OS', 'PRO2'),
('SPE11', 'AMD Radeon Vega Graphics', 'Graphics', 'PRO2'),
('SPE12', 'Built-in DVD drive', 'Features', 'PRO2'),
('SPE13', 'Pre-installed HP support tools', 'Software', 'PRO2'),
('SPE14', 'Minimalist black design', 'Design', 'PRO2'),
('SPE15', 'Low-noise cooling fan', 'Thermal', 'PRO2'),
('SPE16', '2-year warranty included', 'Warranty', 'PRO2'),

-- PRO3: MacBook Air
('SPE17', 'Apple M1, 8GB RAM, 256GB SSD', 'Processor and Memory', 'PRO3'),
('SPE18', 'macOS Ventura', 'Operating System', 'PRO3'),
('SPE19', 'Retina display with True Tone', 'Display', 'PRO3'),
('SPE20', 'Touch ID for secure login', 'Security', 'PRO3'),
('SPE21', 'Up to 18 hours of battery life', 'Battery', 'PRO3'),
('SPE22', 'Compact and lightweight design', 'Design', 'PRO3'),
('SPE23', 'Backlit Magic Keyboard', 'Keyboard', 'PRO3'),
('SPE24', 'Thunderbolt and USB-C ports', 'Ports', 'PRO3'),

-- PRO4: Lenovo IdeaPad
('SPE25', 'Intel i7, 16GB RAM, 1TB SSD', 'Processor and Memory', 'PRO4'),
('SPE26', 'Windows 11 Home', 'Operating System', 'PRO4'),
('SPE27', 'Integrated Intel Iris Xe Graphics', 'Graphics', 'PRO4'),
('SPE28', 'Anti-glare Full HD screen', 'Display', 'PRO4'),
('SPE29', 'Rapid charge support', 'Battery', 'PRO4'),
('SPE30', 'Durable hinge for frequent use', 'Durability', 'PRO4'),
('SPE31', 'Supports Wi-Fi 6', 'Connectivity', 'PRO4'),
('SPE32', '2-year international warranty', 'Warranty', 'PRO4'),

-- PRO5: Galaxy S22 Ultra
('SPE33', '108MP main camera, 12MP ultra-wide', 'Camera', 'PRO5'),
('SPE34', '8K video recording', 'Video', 'PRO5'),
('SPE35', '5000mAh battery with fast charging', 'Battery', 'PRO5'),
('SPE36', 'Dynamic AMOLED 2X display', 'Display', 'PRO5'),
('SPE37', '5G support for fast browsing', 'Connectivity', 'PRO5'),
('SPE38', 'IP68 water and dust resistance', 'Durability', 'PRO5'),
('SPE39', 'One UI 5.0 based on Android', 'Software', 'PRO5'),
('SPE40', 'Stylus with enhanced features', 'Accessories', 'PRO5'),

-- PRO6: iPhone 14 Pro
('SPE41', '48MP main camera, A16 Bionic chip', 'Camera and Performance', 'PRO6'),
('SPE42', 'iOS 16', 'Operating System', 'PRO6'),
('SPE43', 'Super Retina XDR display', 'Display', 'PRO6'),
('SPE44', 'Ceramic Shield for durability', 'Durability', 'PRO6'),
('SPE45', 'Crash detection and emergency SOS', 'Safety Features', 'PRO6'),
('SPE46', 'Face ID for secure login', 'Security', 'PRO6'),
('SPE47', 'MagSafe wireless charging', 'Battery', 'PRO6'),
('SPE48', '5G capable with eSIM support', 'Connectivity', 'PRO6'),

-- PRO7: Dell UltraSharp
('SPE49', '1920x1080 resolution, 60Hz refresh rate', 'Display', 'PRO7'),
('SPE50', 'IPS panel for accurate colors', 'Panel Technology', 'PRO7'),
('SPE51', 'Height adjustable stand', 'Ergonomics', 'PRO7'),
('SPE52', 'HDMI and DisplayPort support', 'Connectivity', 'PRO7'),
('SPE53', '3-year premium panel warranty', 'Warranty', 'PRO7'),
('SPE54', 'Edge-to-edge display design', 'Design', 'PRO7'),
('SPE55', 'Flicker-free technology', 'Comfort Features', 'PRO7'),
('SPE56', 'Anti-glare coating', 'Display Features', 'PRO7'),

-- PRO8: LG UltraFine
('SPE57', '3840x2160 resolution, 144Hz refresh rate', 'Display', 'PRO8'),
('SPE58', 'Nano IPS technology for vibrant colors', 'Panel Technology', 'PRO8'),
('SPE59', 'HDR10 support for high contrast', 'HDR', 'PRO8'),
('SPE60', 'USB-C and HDMI ports', 'Connectivity', 'PRO8'),
('SPE61', 'Pivot and tilt adjustable stand', 'Ergonomics', 'PRO8'),
('SPE62', '5ms response time', 'Performance', 'PRO8'),
('SPE63', 'Factory calibrated for color accuracy', 'Calibration', 'PRO8'),
('SPE64', 'Sleek and slim design', 'Design', 'PRO8'),

-- PRO9: Sony WH-1000XM4
('SPE65', 'Wireless noise-canceling technology', 'Audio Features', 'PRO9'),
('SPE66', '30-hour battery life with quick charge', 'Battery', 'PRO9'),
('SPE67', 'Supports LDAC and AAC codecs', 'Connectivity', 'PRO9'),
('SPE68', 'Comfortable ear padding', 'Design', 'PRO9'),
('SPE69', 'Ambient Sound Control', 'Features', 'PRO9'),
('SPE70', 'Touch controls for music and calls', 'Controls', 'PRO9'),
('SPE71', 'Folds for portability', 'Design', 'PRO9'),
('SPE72', 'Multipoint Bluetooth connection', 'Connectivity', 'PRO9'),

-- PRO10: Bose QuietComfort 45
('SPE73', 'QuietComfort active noise canceling', 'Audio Features', 'PRO10'),
('SPE74', '24-hour battery life', 'Battery', 'PRO10'),
('SPE75', 'Soft ear cushions for all-day comfort', 'Comfort', 'PRO10'),
('SPE76', 'USB-C fast charging', 'Battery', 'PRO10'),
('SPE77', 'Folds flat for storage', 'Design', 'PRO10'),
('SPE78', 'Simple buttons for easy controls', 'Controls', 'PRO10'),
('SPE79', 'Supports Alexa and Google Assistant', 'Smart Features', 'PRO10'),
('SPE80', 'Durable build for frequent use', 'Durability', 'PRO10');

INSERT INTO short_specification (id, description, title, product_id) VALUES
-- Specifications for PRO1
('QSPE5', 'Powerful performance with Intel i5', 'Key Features', 'PRO1'),
('QSPE6', 'Compact design with easy setup', 'Design Features', 'PRO1'),
('QSPE7', 'Energy-efficient and durable', 'Environmental Features', 'PRO1'),
('QSPE8', 'Comes with pre-installed Windows 10', 'Software Features', 'PRO1'),

-- Specifications for PRO2
('QSPE9', 'AMD Ryzen processor for multitasking', 'Performance Features', 'PRO2'),
('QSPE10', 'Elegant and slim design', 'Design Features', 'PRO2'),
('QSPE11', 'Integrated graphics for casual gaming', 'Graphics Features', 'PRO2'),
('QSPE12', 'Pre-installed with essential software', 'Software Features', 'PRO2'),

-- Specifications for PRO3
('QSPE13', 'M1 chip optimized for macOS', 'Processor Features', 'PRO3'),
('QSPE14', 'Long-lasting battery with fast charging', 'Battery Features', 'PRO3'),
('QSPE15', 'High-resolution Retina display', 'Display Features', 'PRO3'),
('QSPE16', 'Supports latest Wi-Fi and Bluetooth versions', 'Connectivity Features', 'PRO3'),

-- Specifications for PRO4
('QSPE17', 'Affordable with premium features', 'Value Features', 'PRO4'),
('QSPE18', 'Durable chassis and sleek design', 'Build Features', 'PRO4'),
('QSPE19', 'Advanced cooling for longer sessions', 'Cooling Features', 'PRO4'),
('QSPE20', 'Pre-installed Office suite for students', 'Software Features', 'PRO4'),

-- Specifications for PRO5
('QSPE21', 'High-performance chipset for multitasking', 'Performance Features', 'PRO5'),
('QSPE22', 'Stylus support for creative tasks', 'Accessory Features', 'PRO5'),
('QSPE23', 'Supports 5G for ultra-fast connectivity', 'Connectivity Features', 'PRO5'),
('QSPE24', 'Dynamic AMOLED display for vivid colors', 'Display Features', 'PRO5'),

-- Specifications for PRO6
('QSPE25', 'A16 Bionic chip for top performance', 'Processor Features', 'PRO6'),
('QSPE26', 'Durable ceramic shield', 'Build Features', 'PRO6'),
('QSPE27', '120Hz ProMotion display for smooth visuals', 'Display Features', 'PRO6'),
('QSPE28', 'Professional-grade camera system', 'Camera Features', 'PRO6'),

-- Specifications for PRO7
('QSPE29', 'Adjustable stand for comfort', 'Ergonomic Features', 'PRO7'),
('QSPE30', 'Anti-glare coating on the screen', 'Display Features', 'PRO7'),
('QSPE31', 'Energy-saving mode included', 'Power Features', 'PRO7'),
('QSPE32', 'Multiple ports for connectivity', 'Connectivity Features', 'PRO7'),

-- Specifications for PRO8
('QSPE33', 'High refresh rate for smooth visuals', 'Gaming Features', 'PRO8'),
('QSPE34', 'HDR support for vibrant colors', 'Display Features', 'PRO8'),
('QSPE35', 'Sleek and slim design', 'Design Features', 'PRO8'),
('QSPE36', 'Multiple input options available', 'Connectivity Features', 'PRO8'),

-- Specifications for PRO9
('QSPE37', 'Wireless design with quick pairing', 'Connectivity Features', 'PRO9'),
('QSPE38', '30-hour battery life with ANC enabled', 'Battery Features', 'PRO9'),
('QSPE39', 'Customizable sound profiles', 'Audio Features', 'PRO9'),
('QSPE40', 'Folds flat for easy storage', 'Portability Features', 'PRO9'),

-- Specifications for PRO10
('QSPE41', 'Superior sound quality for audiophiles', 'Audio Features', 'PRO10'),
('QSPE42', 'Long-lasting battery with quick charge', 'Battery Features', 'PRO10'),
('QSPE43', 'Elegant design with premium materials', 'Design Features', 'PRO10'),
('QSPE44', 'Advanced ANC for immersive sound', 'Noise-Canceling Features', 'PRO10');

INSERT INTO review (id, review, star, title, product_id) VALUES
-- Reviews for PRO1
('REV3', 'Fast and reliable for daily use.', 4, 'Great Value', 'PRO1'),

-- Reviews for PRO2
('REV4', 'Compact and perfect for small spaces.', 4, 'Good Design', 'PRO2'),
('REV5', 'Runs smoothly with no lags.', 5, 'Excellent Choice', 'PRO2'),

-- Reviews for PRO3
('REV6', 'The M1 chip is revolutionary!', 5, 'Amazing Performance', 'PRO3'),
('REV7', 'Lightweight and very portable.', 4, 'Perfect for Travel', 'PRO3'),

-- Reviews for PRO4
('REV8', 'Affordable yet powerful.', 5, 'Best for Students', 'PRO4'),
('REV9', 'Handles multitasking well.', 4, 'Solid Build', 'PRO4'),

-- Reviews for PRO5
('REV10', 'Excellent display and features.', 5, 'Highly Recommended', 'PRO5'),

-- Reviews for PRO6
('REV11', 'Camera quality is unmatched.', 5, 'Fantastic Smartphone', 'PRO6'),
('REV12', 'Expensive but worth it.', 4, 'Premium Feel', 'PRO6'),

-- Reviews for PRO7
('REV13', 'Crystal clear display for work.', 5, 'Best Monitor', 'PRO7'),
('REV14', 'Affordable and functional.', 4, 'Value for Money', 'PRO7'),

-- Reviews for PRO8
('REV15', 'Amazing 4K resolution.', 5, 'Top-Notch', 'PRO8'),
('REV16', 'Sleek and elegant design.', 4, 'Modern Aesthetic', 'PRO8'),

-- Reviews for PRO9
('REV17', 'Best noise-canceling headphones.', 5, 'Superb Audio', 'PRO9'),
('REV18', 'Comfortable for long hours.', 4, 'Great Comfort', 'PRO9'),

-- Reviews for PRO10
('REV19', 'Excellent sound and build.', 5, 'Top Quality', 'PRO10'),
('REV20', 'Long-lasting battery life.', 4, 'Very Convenient', 'PRO10');

INSERT INTO image (id, url, product_id) VALUES
-- Images for PRO1
('IMG1', 'https://example.com/images/dell_inspiron_front.jpg', 'PRO1'),
('IMG2', 'https://example.com/images/dell_inspiron_side.jpg', 'PRO1'),
('IMG3', 'https://example.com/images/dell_inspiron_back.jpg', 'PRO1'),

-- Images for PRO2
('IMG4', 'https://example.com/images/hp_pavilion_front.jpg', 'PRO2'),
('IMG5', 'https://example.com/images/hp_pavilion_side.jpg', 'PRO2'),
('IMG6', 'https://example.com/images/hp_pavilion_back.jpg', 'PRO2'),

-- Images for PRO3
('IMG7', 'https://example.com/images/macbook_air_front.jpg', 'PRO3'),
('IMG8', 'https://example.com/images/macbook_air_side.jpg', 'PRO3'),
('IMG9', 'https://example.com/images/macbook_air_back.jpg', 'PRO3'),

-- Images for PRO4
('IMG10', 'https://example.com/images/lenovo_ideapad_front.jpg', 'PRO4'),
('IMG11', 'https://example.com/images/lenovo_ideapad_side.jpg', 'PRO4'),
('IMG12', 'https://example.com/images/lenovo_ideapad_back.jpg', 'PRO4'),

-- Images for PRO5
('IMG13', 'https://example.com/images/galaxy_s22_front.jpg', 'PRO5'),
('IMG14', 'https://example.com/images/galaxy_s22_side.jpg', 'PRO5'),
('IMG15', 'https://example.com/images/galaxy_s22_back.jpg', 'PRO5'),

-- Images for PRO6
('IMG16', 'https://example.com/images/iphone_14_front.jpg', 'PRO6'),
('IMG17', 'https://example.com/images/iphone_14_side.jpg', 'PRO6'),
('IMG18', 'https://example.com/images/iphone_14_back.jpg', 'PRO6'),

-- Images for PRO7
('IMG19', 'https://example.com/images/dell_ultrasharp_front.jpg', 'PRO7'),
('IMG20', 'https://example.com/images/dell_ultrasharp_side.jpg', 'PRO7'),
('IMG21', 'https://example.com/images/dell_ultrasharp_back.jpg', 'PRO7'),

-- Images for PRO8
('IMG22', 'https://example.com/images/lg_ultrafine_front.jpg', 'PRO8'),
('IMG23', 'https://example.com/images/lg_ultrafine_side.jpg', 'PRO8'),
('IMG24', 'https://example.com/images/lg_ultrafine_back.jpg', 'PRO8'),

-- Images for PRO9
('IMG25', 'https://example.com/images/sony_wh1000xm4_front.jpg', 'PRO9'),
('IMG26', 'https://example.com/images/sony_wh1000xm4_side.jpg', 'PRO9'),
('IMG27', 'https://example.com/images/sony_wh1000xm4_back.jpg', 'PRO9'),

-- Images for PRO10
('IMG28', 'https://example.com/images/bose_quietcomfort_front.jpg', 'PRO10'),
('IMG29', 'https://example.com/images/bose_quietcomfort_side.jpg', 'PRO10'),
('IMG30', 'https://example.com/images/bose_quietcomfort_back.jpg', 'PRO10');

INSERT INTO product_colors (product_id, colors) VALUES
-- Colors for PRO1
('PRO1', 'Black'),
('PRO1', 'Silver'),

-- Colors for PRO2
('PRO2', 'White'),
('PRO2', 'Blue'),

-- Colors for PRO3
('PRO3', 'Space Gray'),
('PRO3', 'Gold'),

-- Colors for PRO4
('PRO4', 'Gray'),
('PRO4', 'Platinum'),

-- Colors for PRO5
('PRO5', 'Phantom Black'),
('PRO5', 'Phantom White'),

-- Colors for PRO6
('PRO6', 'Deep Purple'),
('PRO6', 'Starlight'),

-- Colors for PRO7
('PRO7', 'Black'),
('PRO7', 'Silver'),

-- Colors for PRO8
('PRO8', 'White'),
('PRO8', 'Black'),

-- Colors for PRO9
('PRO9', 'Black'),
('PRO9', 'Silver'),

-- Colors for PRO10
('PRO10', 'White'),
('PRO10', 'Black');





