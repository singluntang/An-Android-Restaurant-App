-- phpMyAdmin SQL Dump
-- version 2.8.0.1
-- http://www.phpmyadmin.net
-- 
-- Host: custsql-ipg44.eigbox.net
-- Generation Time: Aug 21, 2019 at 12:17 PM
-- Server version: 5.6.41
-- PHP Version: 4.4.9
-- 
-- Database: `drinkshop`
-- 
DROP DATABASE `drinkshop`;
CREATE DATABASE `drinkshop` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `drinkshop`;

-- --------------------------------------------------------

-- 
-- Table structure for table `banner`
-- 

DROP TABLE IF EXISTS `banner`;
CREATE TABLE IF NOT EXISTS `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `link` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- 
-- Dumping data for table `banner`
-- 

INSERT INTO `banner` (`id`, `name`, `link`) VALUES (1, 'Signatures Series', 'https://image.ibb.co/j1EYkJ/1.jpg');
INSERT INTO `banner` (`id`, `name`, `link`) VALUES (2, 'Ice Summer', 'https://image.ibb.co/mDBDkJ/2.jpg');
INSERT INTO `banner` (`id`, `name`, `link`) VALUES (3, 'Special Series', 'https://image.ibb.co/jJD4sy/31.jpg');

-- --------------------------------------------------------

-- 
-- Table structure for table `drink`
-- 

DROP TABLE IF EXISTS `drink`;
CREATE TABLE IF NOT EXISTS `drink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `link` text NOT NULL,
  `price` float NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menuid` (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1 AUTO_INCREMENT=50 ;

-- 
-- Dumping data for table `drink`
-- 

INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (1, 'Squash tea\r\n', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-Milkfoam-1.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (3, 'Oolong tea\r\n', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Oolong-Milkfoam-2.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (4, 'Alisan tea\r\n', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Alisan-Milkfoam-1.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (5, 'Earl tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Earl-Grey-Milkfoam-1.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (6, 'Black tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-%C4%90en-Milkfoam-1.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (7, 'Green tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Xanh-Milkfoam-1.png', 3.75, 10);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (8, 'Milk Tea Red Bean', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Matcha-%C4%91%E1%BA%ADu-%C4%91%E1%BB%8F-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (9, 'Milk Tea Oolong 3J', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Oolong-3J-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (10, 'Milk Tea Pudding Read Bean', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Pudding-%C4%91%E1%BA%ADu-%C4%91%E1%BB%8F-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (11, 'Milk Tea Chocolate', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Chocolate-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (12, 'Milk Tea Caramel', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Caramel-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (13, 'Milk Tea Black Pearl', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Caramel-1.png', 3.75, 9);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (14, 'Squash Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-Alisan-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (15, 'Squash Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (16, 'Earl Grey Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Early-Grey-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (17, 'Oolong Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Oolong-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (18, 'Black Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-%C4%90en-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (19, 'Green Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-Xanh-1.png', 3.75, 8);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (20, 'QQ Green Tea ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/QQ-Tr%C3%A0-xanh-chanh-d%C3%A2y-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (21, 'Lemon Ai-yu and White Pearl ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Chanh-Aiyu-tr%C3%A2n-ch%C3%A2u-tr%E1%BA%AFng-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (22, 'Pink Peach ', 'http://gongcha.com.vn/wp-content/uploads/2018/02/imgpsh_fullsize.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (23, 'Fig Oolong Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Oolong-v%E1%BA%A3i-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (24, 'Fig Alisan Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Alisan-v%E1%BA%A3i-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (25, 'Mango Alisan Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Alisan-xo%C3%A0i-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (26, 'Lemon Green Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Xanh-%C4%91%C3%A0o-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (27, 'Lemon Alisan Green Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Xanh-%C4%91%C3%A0o-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (28, 'Peach Black Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/%C4%90en-%C4%91%C3%A0o-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (29, 'Peach Green Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Xanh-%C4%91%C3%A0o-1.png', 3.75, 5);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (30, 'Chocolate Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Chocolate-%C4%91%C3%A1-xay-1.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (31, 'Taro Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Khoai-m%C3%B4n-%C4%91%C3%A1-xay-1.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (32, 'Matcha Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Matcha-%C4%91%C3%A1-xay-1.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (33, 'Yakult Peach Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Yakult-%C4%91%C3%A1-xay-1.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (34, 'Mango Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Xo%C3%A0i-%C4%91%C3%A1-xay-1.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (35, 'Peach Tea Grind', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-%C4%91%C3%A0o-%C4%91%C3%A1-tuy%E1%BA%BFt-fix.png', 3.75, 6);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (36, 'Milk Cream', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Kem-S%E1%BB%AFa.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (37, 'Vera', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Nha-%C4%90am.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (38, 'Seeds', 'http://gongcha.com.vn/wp-content/uploads/2018/03/H%E1%BA%A1t-%C3%89.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (39, 'Black Pearl', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Tr%C3%A2n-Ch%C3%A2u-%C4%90en.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (40, 'White Pearl', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Tr%C3%A2n-Ch%C3%A2u-Tr%E1%BA%AFng.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (41, 'Red Bean', 'http://gongcha.com.vn/wp-content/uploads/2018/03/%C4%90%E1%BA%ADu-%C4%90%E1%BB%8F.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (42, 'Jelly Black', 'http://gongcha.com.vn/wp-content/uploads/2018/03/S%C6%B0%C6%A1ng-s%C3%A1o.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (43, 'Jelly Brown', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Th%E1%BA%A1ch-N%C3%A2u.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (44, 'Jelly Fruits', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Th%E1%BA%A1ch-tr%C3%A1i-c%C3%A2y.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (45, 'Jelly Ai-yu', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Th%E1%BA%A1ch-Ai-yu.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (46, 'Jelly Coconut', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Th%E1%BA%A1ch-D%E1%BB%ABa.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (47, 'Pudding', 'http://gongcha.com.vn/wp-content/uploads/2018/03/%E5%B8%83%E4%B8%81-pudding.png', 2, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (48, 'Combo 2', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Combo2loaihat-2.png', 2.5, 7);
INSERT INTO `drink` (`id`, `name`, `link`, `price`, `menuid`) VALUES (49, 'Combo 3', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Combo-3-lo%E1%BA%A1i-h%E1%BA%A1t.png', 2.5, 7);

-- --------------------------------------------------------

-- 
-- Table structure for table `menu`
-- 

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` text NOT NULL,
  `Link` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- 
-- Dumping data for table `menu`
-- 

INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (5, 'Mixed Drink', 'http://gongcha.com.vn/wp-content/uploads/2018/02/QQ-Tr%C3%A0-xanh-chanh-d%C3%A2y-1.png');
INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (6, 'Ice Drink', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Chocolate-%C4%91%C3%A1-xay-1.png');
INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (7, 'Topping', 'http://gongcha.com.vn/wp-content/uploads/2018/03/Tr%C3%A2n-Ch%C3%A2u-Tr%E1%BA%AFng.png');
INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (8, 'Original Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-Alisan-1.png');
INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (9, 'Milk Tea', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Hokkaido-1.png');
INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES (10, 'Special Drink', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-Milkfoam-1.png');

-- --------------------------------------------------------

-- 
-- Table structure for table `messages`
-- 

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_users_id` int(11) NOT NULL,
  `to_users_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `sentat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `messages_users_from` (`to_users_id`),
  KEY `messages_users_to` (`from_users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- 
-- Dumping data for table `messages`
-- 

INSERT INTO `messages` (`id`, `from_users_id`, `to_users_id`, `title`, `message`, `sentat`) VALUES (1, 2, 2, 'testing', 'hi thishhjÄµjjjj', '2018-06-14 10:07:36');

-- --------------------------------------------------------

-- 
-- Table structure for table `user`
-- 

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `phone` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `address` text NOT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `user`
-- 

INSERT INTO `user` (`phone`, `name`, `birthday`, `address`) VALUES ('+85257437759', 'Tang Sing Lun Admin', '1979-09-21', 'Rm 2409 Mui Yuen Hse');

-- --------------------------------------------------------

-- 
-- Table structure for table `users`
-- 

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `gender` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- 
-- Dumping data for table `users`
-- 

INSERT INTO `users` (`id`, `name`, `email`, `password`, `gender`) VALUES (2, 'tang sing', 'cokelight@gmail.com', '4a7d1ed414474e4033ac29ccb8653d9b', 'Male');
INSERT INTO `users` (`id`, `name`, `email`, `password`, `gender`) VALUES (3, 'Tang Sing Lun', 'songlun@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Male');

-- 
-- Constraints for dumped tables
-- 

-- 
-- Constraints for table `drink`
-- 
ALTER TABLE `drink`
  ADD CONSTRAINT `drink_ibfk_1` FOREIGN KEY (`menuid`) REFERENCES `menu` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Constraints for table `messages`
-- 
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_users_from` FOREIGN KEY (`to_users_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `messages_users_to` FOREIGN KEY (`from_users_id`) REFERENCES `users` (`id`);
-- 
-- Database: `eatit_server_client`
-- 
DROP DATABASE `eatit_server_client`;
CREATE DATABASE `eatit_server_client` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `eatit_server_client`;

-- --------------------------------------------------------

-- 
-- Table structure for table `tbl_category`
-- 

DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE IF NOT EXISTS `tbl_category` (
  `id` varchar(255) NOT NULL,
  `name` text NOT NULL,
  `image` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `tbl_category`
-- 

INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('03', 'EASTERN SOUPS', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-easternsoup.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('04', 'SANDWICHES', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-sandwich.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('05', 'PIZZA', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-pizza.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('06', 'PASTA', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-pasta1.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('07', 'CHICKEN', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-chicken.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('08', 'FISH', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-fish.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('09', 'CHINESE VEGETARIAN', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-vegetarian.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('10', 'MEDIFOODS DELIGHTS', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-delights.jpg');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('11', 'McDonald', 'http://programming.website/EatIt/pictures/7b3930f0-017c-4b20-8393-a126c3984ec5.JPG');
INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES ('12', 'figure', 'http://programming.website/EatIt/pictures/792b42fc-a7cb-4f15-badb-b8f22686765f.JPG');

-- --------------------------------------------------------

-- 
-- Table structure for table `tbl_food`
-- 

DROP TABLE IF EXISTS `tbl_food`;
CREATE TABLE IF NOT EXISTS `tbl_food` (
  `id` varchar(256) NOT NULL,
  `name` text NOT NULL,
  `image` text NOT NULL,
  `description` text NOT NULL,
  `price` text NOT NULL,
  `discount` text NOT NULL,
  `menuid` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `tbl_food`
-- 

INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('01', 'THAI STYLE SALAD', 'http://medifoods.my/images/menu/a1_thai_style_salad.jpg', 'Bamboo fungus in spicy thai style sauce', '1', '0', '01');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('02', 'BLACK SESAME BALL', 'http://medifoods.my/images/menu/a2_sesame_ball_peanut.jpg', 'Glutinous rice ball with black sesame paste filling coated with minced peanuts', '1', '0', '01');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('03', 'SPINACH POTATO BALL', 'http://medifoods.my/images/menu/a3_potato_ball.jpg', 'Golden fried spinach potato ball', '1', '0', '01');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('12', 'EGG MAYO SANDWICH', 'http://medifoods.my/images/menu/22_egg_mayo.jpg', '2 organic eggs with only 1 yolk served with fresh organic vegetables and low fat mayonnaise on panini bread', '1', '0', '04');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('13', 'HUMMUS PLATTER', 'http://medifoods.my/images/menu/23_hummus_platter.jpg', 'A refreshing dip made with chickpeas, tahini (sesame paste), and served with crackers, pita bread, fruit and an assortment of salads', '1', '0', '04');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('14', 'MUSHROOM PIZZA', 'http://medifoods.my/images/menu/21_mushroom_pizza.jpg', 'Mushrooms and onion', '1', '0', '05');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('15', 'MUSHROOM PIZZA', 'http://medifoods.my/images/menu/20_margherita_pizza.jpg', 'Tomatoes, capsicum and pesto', '1', '0', '05');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('16', 'HAWAIIAN PIZZA', 'http://medifoods.my/images/menu/19_hawaiian_pizza.jpg', 'Pineapple, beancurd sheet and onion', '1', '0', '05');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('17', 'GARDEN AGLIO OLIO', 'http://medifoods.my/images/menu/15_garden_aglio_olio.jpg', 'Pasta tossed with capsicum, tofu, soybean chunks and special herb-infused olive oil', '1', '0', '06');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('18', 'SPICY AGLIO OLIO', 'http://medifoods.my/images/menu/17_spicy_aglio_olio.jpg', 'Pasta tossed with traditional Asian spices and served with pan fried diced vegetables', '1', '0', '06');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('19', 'BOLOGNESE AGLIO OLIO', 'http://medifoods.my/images/menu/18_spaghetti_bolognese.jpg', 'Bolognese spaghetti with chunky vegetables', '1', '0', '06');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('20', 'CAULIFLOWER FETTUCCINE', 'http://medifoods.my/images/menu/14_cauliflower_fettucine.jpg', 'Creamy fettuccine served with green peas, cauliflower and long beans', '1', '0', '06');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('21', 'ROASTED QUARTER CHICKEN', 'http://medifoods.my/images/menu/4_roasted_quarter_chicken_with_special_sauces.jpg', 'Served with mushroom gravy, cranberry & mint sauces', '1', '0', '07');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('22', 'CURRY CHICKEN', 'http://medifoods.my/images/menu/2_curry_chic.jpg', 'Chicken served in curry that is made from more than 10 spices to bring out the authentic traditional taste. Served with rice and 3 side dishes', '1', '0', '07');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('23', 'RENDANG CHICKEN', 'http://medifoods.my/images/menu/2_curry_chic.jpg', 'Chicken served in curry that is made from more than 10 spices to bring out the authentic traditional taste. Served with rice and 3 side dishes', '1', '0', '07');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('24', 'HERBAL STEAMED CHICKEN', 'http://medifoods.my/images/menu/3_herbal_steamed_chic.jpg', 'Steamed chicken with red dates and mushroom. Served with rice and 3 side dishes', '1', '0', '07');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('25', 'ASAM FISH HEAD', 'http://medifoods.my/images/menu/9_asam_salmon_fish_head.jpg', 'Half a salmon fish head cooked in an asam broth. Served with rice and 3 side dishes', '1', '0', '08');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('26', 'DUO SEASON SALMON PUFFS', 'http://medifoods.my/images/menu/10_duo_season_salmon_puff.jpg', 'Golden fried salmon stuffed bread puffs. Served with mashed potato & salad', '1', '0', '08');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('27', 'TOMAN IN PUMPKIN SAUCE', 'http://medifoods.my/images/menu/11_pumpkin_sauce_fish.jpg', 'Breaded fried toman fish slices in pumpkin sauce. Served with rice and 3 side dishes', '1', '0', '08');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('28', 'GINGER SOY FISH CAKE', 'http://medifoods.my/images/menu/5_ginger_vegetarian_fish.jpg', 'Breaded fried toman fish slices in pumpkin sauce. Served with rice and 3 side dishes', '1', '0', '09');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('29', 'GONG BAO MUSHROOM TOFU', 'http://medifoods.my/images/menu/6_kung_po_mushroom_tofu.jpg', 'Fried mushroom served in a sweet and savoury sauce', '1', '0', '09');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('30', 'PUMPKIN LUO HAN ZHAI', 'http://medifoods.my/images/menu/7_pumpkin_luo_han_zhai.jpg', 'An assortment of mushrooms, vegetables and white silken tofu in pumpkin sauce', '1', '0', '09');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('31', 'SWEET & SOUR MONKEY HEAD MUSHROOM', 'http://medifoods.my/images/menu/8_sweet_sour_cranberry_monkey_head_mushroom.jpg', 'Fried breaded monkey head mushroom on a bed of tofu topped with homemade sweet and sour cranberry sauce', '1', '0', '09');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('32', 'MEDIFOODS NASI LEMAK', 'http://medifoods.my/images/menu/34_nasi_lemak_veg.jpg', 'Enjoy the healthy version of the classic malaysian delight we all love', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('33', 'LEI CHA', 'http://medifoods.my/images/menu/13_lui_cha.jpg', 'Assortment of vegetables & nut served with rice and our signature basil blended soup', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('34', 'BLACK PEPPER UDON', 'http://medifoods.my/images/menu/26_black_pepper_udon.jpg', 'Udon noodle stir fried with blackpepper, soy sauce and mixed vegetables', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('35', 'CANTONESE STYLE MEE SUA', 'http://medifoods.my/images/menu/27_cantonese_style_mee_sua.jpg', 'Rice vermicelli cooked in vegetable soup infused with organic egg', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('36', 'MF SPECIAL FRIED RICE', 'http://medifoods.my/images/menu/29_mf_special_fried_rice.jpg', 'Fried rice with egg, textured soy protein, capsicum and our own lui cha sauce', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('37', 'BITTERGOURD MEE HOON', 'http://medifoods.my/images/menu/30_bittergourd_meehun.jpg', 'Bittergourd braised in vegatble soup along with tofu, bamboo fungus and rice vermicilli', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('38', 'JAPANESE RAMEN', 'http://medifoods.my/images/menu/31_japanese_ramen.jpg', 'Udon noodles served in a light miso soup with egg, japanese tofu, oyster mushroom and seaweed', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('39', 'MEDIFOODS PORRIDGE', 'http://medifoods.my/images/menu/33_porridge_nonveg.jpg', 'Boiled calrose rice with pumpkin, textured soy protein, tofu, toman fish and topped with fried meehoon', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('4', 'DOUBLE DELIGHT', 'http://medifoods.my/images/menu/a4_caramelised_sunflower_seeds.jpg', 'Apple in yoghurt sauce with walnut & sunflower seeds coated with cane sugar and sesame seeds', '1', '0', '01');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('40', 'HOKKIEN VEGETARIAN FRIED MEE HOON', 'http://medifoods.my/images/menu/36_mf_hokkien_vegetarian_fried_meehoon.jpg', 'Fried rice vermicili with cloud ear, carrot and topped with vegetarian fish', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('41', 'CURRY MEE HOON', 'http://medifoods.my/images/menu/12_curry_mee.jpg', 'Rice vermicilli served in santan-free curry with chicken, long beans , tau pok and enoki mushrooms', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('42', 'MEDIFOODS RICE', 'http://medifoods.my/images/menu/38_mf_rice.jpg', 'Long grain Basmati rice with lentil and millets', '1', '0', '10');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('43', 'Burger', 'http://programming.website/EatIt/Foods/049370d3-9806-4b6c-9217-5c890f973b42.JPG', 'very delicious burger', '11', '0', '11');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('44', 'Lunch Set A', 'http://programming.website/EatIt/Foods/d90eeabe-0189-46d9-8fd0-fc513dd2ad03.JPG', 'This is the famous Lunch Set.', '78', '5', '11');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('45', 'xxx', 'http://programming.website/EatIt/Foods/683f97fb-9c53-441c-a22e-207fee78e879.JPG', 'ffff', '55', '0', '12');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('46', 'bbb', 'http://programming.website/EatIt/Foods/285bc64d-5304-488e-aefc-5b9147116cbd.JPG', 'vvvv', '36', '6', '12');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('5', 'SOUR & SPICY TOFU', 'http://medifoods.my/images/menu/a5_sour_and_spicy_tofu.jpg', 'Tofu in spicy thai style sauce', '1', '0', '01');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('6', 'LOTUS ROOT', 'http://medifoods.my/images/menu/s5_lotus_chinese_soup.jpg', 'Lotus root, ground nuts and kombu', '1', '0', '03');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('7', 'OLD CUCUMBER', 'http://medifoods.my/images/menu/s6_old_cucumber_chinese_soup.jpg', 'Old cucumber and longan', '1', '0', '03');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('8', 'BURDOCK', 'http://medifoods.my/images/menu/s7_bur_dock_chinese_soup.jpg', 'Burdock, fig, longan and goji berry', '1', '0', '03');
INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES ('9', 'ABC', 'http://medifoods.my/images/menu/s8_abc_chinese_soup.jpg', 'Carrot, potato, onion, and tomato', '1', '0', '03');

-- --------------------------------------------------------

-- 
-- Table structure for table `tbl_rating`
-- 

DROP TABLE IF EXISTS `tbl_rating`;
CREATE TABLE IF NOT EXISTS `tbl_rating` (
  `phone` varchar(256) NOT NULL,
  `ratevalue` text NOT NULL,
  `foodid` text NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `tbl_rating`
-- 

INSERT INTO `tbl_rating` (`phone`, `ratevalue`, `foodid`, `comment`) VALUES ('64960648', '3', '14', 'good food');
INSERT INTO `tbl_rating` (`phone`, `ratevalue`, `foodid`, `comment`) VALUES ('66966379', '3', '13', 'This is good and delicious');

-- --------------------------------------------------------

-- 
-- Table structure for table `tbl_request`
-- 

DROP TABLE IF EXISTS `tbl_request`;
CREATE TABLE IF NOT EXISTS `tbl_request` (
  `order_id` varchar(255) NOT NULL,
  `phone` text NOT NULL,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `total` text NOT NULL,
  `status` text NOT NULL,
  `Comment` text NOT NULL,
  `paymentState` text NOT NULL,
  `foods` text NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `tbl_request`
-- 

INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1522671757271', '64960648', 'Lai Fun', '2-8 Kwei Tei street , International industrial Center', '$89.00', '1', 'deliver after 11:00am', '', '{"server_response":[{"foods":[{"productId":"44","productName":"Lunch Set A","quantity":"1","price":"78","discount":"5"},{"productId":"43","productName":"Burger","quantity":"1","price":"11","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525699381985', '64960648', 'Lai Fun', 'Rm 2409 Mui Yuen Hse', '$1.00', '0', 'Testing', '', '{"server_response":[{"foods":[{"productId":"34","productName":"BLACK PEPPER UDON","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525700204256', '64960648', 'Lai Fun', 'Address', '$1.00', '0', 'Testing', '', '{"server_response":[{"foods":[{"productId":"17","productName":"GARDEN AGLIO OLIO","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525700490562', '64960648', 'Lai Fun', 'dasfg', '$1.00', '0', 'sdfg', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525700605078', '64960648', 'Lai Fun', 'rte', '$1.00', '0', 'ert', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525700785864', '64960648', 'Lai Fun', 'M', '$2.00', '0', 'M', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"},{"productId":"15","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525701115012', '64960648', 'Lai Fun', 'f', '$1.00', '0', 'f', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525702392127', '64960648', 'Lai Fun', 'd', '$1.00', '0', 'd', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525702646287', '64960648', 'Lai Fun', 'qqq', '$1.00', '0', 'qqq', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525703389213', '64960648', 'Lai Fun', 'p', '$1.00', '0', 'p', '', '{"server_response":[{"foods":[{"productId":"12","productName":"EGG MAYO SANDWICH","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525712952678', '64960648', 'Lai Fun', 'k', '$1.00', '0', 'k', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525713882415', '64960648', 'Lai Fun', 'l', '$1.00', '0', 'l', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525714040068', '64960648', 'Lai Fun', 'Rm 2409 Mui Yuen Hse', '$2.00', '0', 'Testing', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"},{"productId":"17","productName":"GARDEN AGLIO OLIO","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525714787723', '64960648', 'Lai Fun', 'a', '$1.00', '0', 'a', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525714889627', '64960648', 'Lai Fun', 'p', '$1.00', '0', 'p', '', '{"server_response":[{"foods":[{"productId":"13","productName":"HUMMUS PLATTER","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525715508786', '64960648', 'Lai Fun', 'sdfds', '$1.00', '0', 'sdfdsf', '', '{"server_response":[{"foods":[{"productId":"12","productName":"EGG MAYO SANDWICH","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525715587485', '64960648', 'Lai Fun', 'q', '$1.00', '0', 'q', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525715691623', '64960648', 'Lai Fun', 'a', '$1.00', '0', 'a', '', '{"server_response":[{"foods":[{"productId":"13","productName":"HUMMUS PLATTER","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525716217163', '64960648', 'Lai Fun', 'a', '$4.00', '0', 'a', '', '{"server_response":[{"foods":[{"productId":"14","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"},{"productId":"16","productName":"HAWAIIAN PIZZA","quantity":"1","price":"1","discount":"0"},{"productId":"13","productName":"HUMMUS PLATTER","quantity":"1","price":"1","discount":"0"},{"productId":"15","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525716334701', '64960648', 'Lai Fun', 'r', '$2.00', '0', 'r', '', '{"server_response":[{"foods":[{"productId":"6","productName":"LOTUS ROOT","quantity":"1","price":"1","discount":"0"},{"productId":"15","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525716884151', '64960648', 'Lai Fun', 'q', '$1.00', '0', 'q', '', '{"server_response":[{"foods":[{"productId":"15","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');
INSERT INTO `tbl_request` (`order_id`, `phone`, `name`, `address`, `total`, `status`, `Comment`, `paymentState`, `foods`) VALUES ('1525716961936', '64960648', 'Lai Fun', 'fd', '$1.00', '0', 'fd', '', '{"server_response":[{"foods":[{"productId":"15","productName":"MUSHROOM PIZZA","quantity":"1","price":"1","discount":"0"}]}]}');

-- --------------------------------------------------------

-- 
-- Table structure for table `tbl_users`
-- 

DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE IF NOT EXISTS `tbl_users` (
  `phone` text NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `staff` text NOT NULL,
  `securecode` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `tbl_users`
-- 

INSERT INTO `tbl_users` (`phone`, `name`, `password`, `staff`, `securecode`) VALUES ('66966379', 'Sing Lun', 'zukayno', 'true', 'abc');
INSERT INTO `tbl_users` (`phone`, `name`, `password`, `staff`, `securecode`) VALUES ('64960648', 'Lai Fun', '1234', 'false', 'abc1234');

-- --------------------------------------------------------

-- 
-- Table structure for table `testquote`
-- 

DROP TABLE IF EXISTS `testquote`;
CREATE TABLE IF NOT EXISTS `testquote` (
  `strquote` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Dumping data for table `testquote`
-- 

INSERT INTO `testquote` (`strquote`) VALUES ('\\"');
INSERT INTO `testquote` (`strquote`) VALUES ('""ABC""');
