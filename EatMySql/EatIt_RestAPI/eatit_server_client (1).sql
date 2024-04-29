-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2018 at 02:05 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eatit_server_client`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_category`
--

CREATE TABLE `tbl_category` (
  `id` varchar(255) NOT NULL,
  `name` text NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_category`
--

INSERT INTO `tbl_category` (`id`, `name`, `image`) VALUES
('03', 'EASTERN SOUPS', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-easternsoup.jpg'),
('04', 'SANDWICHES', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-sandwich.jpg'),
('05', 'PIZZA', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-pizza.jpg'),
('06', 'PASTA', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-pasta1.jpg'),
('07', 'CHICKEN', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-chicken.jpg'),
('08', 'FISH', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-fish.jpg'),
('09', 'CHINESE VEGETARIAN', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-vegetarian.jpg'),
('10', 'MEDIFOODS DELIGHTS', 'http://medifoods.my/wp-content/uploads/2015/03/cover-menu-delights.jpg'),
('11', 'McDonald', 'http://192.168.86.24/EatIt/pictures/cd4ea7d0-e1f7-446b-bd63-f93b6422cdf2.JPG'),
('12', 'Figure', 'http://192.168.86.24/EatIt/pictures/92bc187c-7d75-45b0-b5fe-e6433647dc89.JPG');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_food`
--

CREATE TABLE `tbl_food` (
  `id` varchar(256) NOT NULL,
  `name` text NOT NULL,
  `image` text NOT NULL,
  `description` text NOT NULL,
  `price` text NOT NULL,
  `discount` text NOT NULL,
  `menuid` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_food`
--

INSERT INTO `tbl_food` (`id`, `name`, `image`, `description`, `price`, `discount`, `menuid`) VALUES
('01', 'THAI STYLE SALAD', 'http://medifoods.my/images/menu/a1_thai_style_salad.jpg', 'Bamboo fungus in spicy thai style sauce', '1', '0', '01'),
('02', 'BLACK SESAME BALL', 'http://medifoods.my/images/menu/a2_sesame_ball_peanut.jpg', 'Glutinous rice ball with black sesame paste filling coated with minced peanuts', '1', '0', '01'),
('03', 'SPINACH POTATO BALL', 'http://medifoods.my/images/menu/a3_potato_ball.jpg', 'Golden fried spinach potato ball', '1', '0', '01'),
('12', 'EGG MAYO SANDWICH', 'http://medifoods.my/images/menu/22_egg_mayo.jpg', '2 organic eggs with only 1 yolk served with fresh organic vegetables and low fat mayonnaise on panini bread', '1', '0', '04'),
('13', 'HUMMUS PLATTER', 'http://medifoods.my/images/menu/23_hummus_platter.jpg', 'A refreshing dip made with chickpeas, tahini (sesame paste), and served with crackers, pita bread, fruit and an assortment of salads', '1', '0', '04'),
('14', 'MUSHROOM PIZZA', 'http://medifoods.my/images/menu/21_mushroom_pizza.jpg', 'Mushrooms and onion', '1', '0', '05'),
('15', 'MUSHROOM PIZZA', 'http://medifoods.my/images/menu/20_margherita_pizza.jpg', 'Tomatoes, capsicum and pesto', '1', '0', '05'),
('16', 'HAWAIIAN PIZZA', 'http://medifoods.my/images/menu/19_hawaiian_pizza.jpg', 'Pineapple, beancurd sheet and onion', '1', '0', '05'),
('17', 'GARDEN AGLIO OLIO', 'http://medifoods.my/images/menu/15_garden_aglio_olio.jpg', 'Pasta tossed with capsicum, tofu, soybean chunks and special herb-infused olive oil', '1', '0', '06'),
('18', 'SPICY AGLIO OLIO', 'http://medifoods.my/images/menu/17_spicy_aglio_olio.jpg', 'Pasta tossed with traditional Asian spices and served with pan fried diced vegetables', '1', '0', '06'),
('19', 'BOLOGNESE AGLIO OLIO', 'http://medifoods.my/images/menu/18_spaghetti_bolognese.jpg', 'Bolognese spaghetti with chunky vegetables', '1', '0', '06'),
('20', 'CAULIFLOWER FETTUCCINE', 'http://medifoods.my/images/menu/14_cauliflower_fettucine.jpg', 'Creamy fettuccine served with green peas, cauliflower and long beans', '1', '0', '06'),
('21', 'ROASTED QUARTER CHICKEN', 'http://medifoods.my/images/menu/4_roasted_quarter_chicken_with_special_sauces.jpg', 'Served with mushroom gravy, cranberry & mint sauces', '1', '0', '07'),
('22', 'CURRY CHICKEN', 'http://medifoods.my/images/menu/2_curry_chic.jpg', 'Chicken served in curry that is made from more than 10 spices to bring out the authentic traditional taste. Served with rice and 3 side dishes', '1', '0', '07'),
('23', 'RENDANG CHICKEN', 'http://medifoods.my/images/menu/2_curry_chic.jpg', 'Chicken served in curry that is made from more than 10 spices to bring out the authentic traditional taste. Served with rice and 3 side dishes', '1', '0', '07'),
('24', 'HERBAL STEAMED CHICKEN', 'http://medifoods.my/images/menu/3_herbal_steamed_chic.jpg', 'Steamed chicken with red dates and mushroom. Served with rice and 3 side dishes', '1', '0', '07'),
('25', 'ASAM FISH HEAD', 'http://medifoods.my/images/menu/9_asam_salmon_fish_head.jpg', 'Half a salmon fish head cooked in an asam broth. Served with rice and 3 side dishes', '1', '0', '08'),
('26', 'DUO SEASON SALMON PUFFS', 'http://medifoods.my/images/menu/10_duo_season_salmon_puff.jpg', 'Golden fried salmon stuffed bread puffs. Served with mashed potato & salad', '1', '0', '08'),
('27', 'TOMAN IN PUMPKIN SAUCE', 'http://medifoods.my/images/menu/11_pumpkin_sauce_fish.jpg', 'Breaded fried toman fish slices in pumpkin sauce. Served with rice and 3 side dishes', '1', '0', '08'),
('28', 'GINGER SOY FISH CAKE', 'http://medifoods.my/images/menu/5_ginger_vegetarian_fish.jpg', 'Breaded fried toman fish slices in pumpkin sauce. Served with rice and 3 side dishes', '1', '0', '09'),
('29', 'GONG BAO MUSHROOM TOFU', 'http://medifoods.my/images/menu/6_kung_po_mushroom_tofu.jpg', 'Fried mushroom served in a sweet and savoury sauce', '1', '0', '09'),
('30', 'PUMPKIN LUO HAN ZHAI', 'http://medifoods.my/images/menu/7_pumpkin_luo_han_zhai.jpg', 'An assortment of mushrooms, vegetables and white silken tofu in pumpkin sauce', '1', '0', '09'),
('31', 'SWEET & SOUR MONKEY HEAD MUSHROOM', 'http://medifoods.my/images/menu/8_sweet_sour_cranberry_monkey_head_mushroom.jpg', 'Fried breaded monkey head mushroom on a bed of tofu topped with homemade sweet and sour cranberry sauce', '1', '0', '09'),
('32', 'MEDIFOODS NASI LEMAK', 'http://medifoods.my/images/menu/34_nasi_lemak_veg.jpg', 'Enjoy the healthy version of the classic malaysian delight we all love', '1', '0', '10'),
('33', 'LEI CHA', 'http://medifoods.my/images/menu/13_lui_cha.jpg', 'Assortment of vegetables & nut served with rice and our signature basil blended soup', '1', '0', '10'),
('34', 'BLACK PEPPER UDON', 'http://medifoods.my/images/menu/26_black_pepper_udon.jpg', 'Udon noodle stir fried with blackpepper, soy sauce and mixed vegetables', '1', '0', '10'),
('35', 'CANTONESE STYLE MEE SUA', 'http://medifoods.my/images/menu/27_cantonese_style_mee_sua.jpg', 'Rice vermicelli cooked in vegetable soup infused with organic egg', '1', '0', '10'),
('36', 'MF SPECIAL FRIED RICE', 'http://medifoods.my/images/menu/29_mf_special_fried_rice.jpg', 'Fried rice with egg, textured soy protein, capsicum and our own lui cha sauce', '1', '0', '10'),
('37', 'BITTERGOURD MEE HOON', 'http://medifoods.my/images/menu/30_bittergourd_meehun.jpg', 'Bittergourd braised in vegatble soup along with tofu, bamboo fungus and rice vermicilli', '1', '0', '10'),
('38', 'JAPANESE RAMEN', 'http://medifoods.my/images/menu/31_japanese_ramen.jpg', 'Udon noodles served in a light miso soup with egg, japanese tofu, oyster mushroom and seaweed', '1', '0', '10'),
('39', 'MEDIFOODS PORRIDGE', 'http://medifoods.my/images/menu/33_porridge_nonveg.jpg', 'Boiled calrose rice with pumpkin, textured soy protein, tofu, toman fish and topped with fried meehoon', '1', '0', '10'),
('4', 'DOUBLE DELIGHT', 'http://medifoods.my/images/menu/a4_caramelised_sunflower_seeds.jpg', 'Apple in yoghurt sauce with walnut & sunflower seeds coated with cane sugar and sesame seeds', '1', '0', '01'),
('40', 'HOKKIEN VEGETARIAN FRIED MEE HOON', 'http://medifoods.my/images/menu/36_mf_hokkien_vegetarian_fried_meehoon.jpg', 'Fried rice vermicili with cloud ear, carrot and topped with vegetarian fish', '1', '0', '10'),
('41', 'CURRY MEE HOON', 'http://medifoods.my/images/menu/12_curry_mee.jpg', 'Rice vermicilli served in santan-free curry with chicken, long beans , tau pok and enoki mushrooms', '1', '0', '10'),
('42', 'MEDIFOODS RICE', 'http://medifoods.my/images/menu/38_mf_rice.jpg', 'Long grain Basmati rice with lentil and millets', '1', '0', '10'),
('43', 'Luch Set A', 'http://192.168.86.24/EatIt/Foods/7ca06de5-d09a-4686-a5f9-e2096aae5ccc.JPG', 'Promotion Set A', '15', '0', '11'),
('44', 'Lunch Set B', 'http://192.168.86.24/EatIt/Foods/8ebe0e2a-2a48-4fdb-83ad-06947b16e6ac.JPG', 'Promotional Set B', '77', '0', '11'),
('45', 'French Fries', 'http://192.168.86.24/EatIt/Foods/1ab17810-993e-49f0-b8b0-3df53603d2ac.JPG', 'Spicy and Hot', '11', '0', '11'),
('5', 'SOUR & SPICY TOFU', 'http://medifoods.my/images/menu/a5_sour_and_spicy_tofu.jpg', 'Tofu in spicy thai style sauce', '1', '0', '01'),
('6', 'LOTUS ROOT', 'http://medifoods.my/images/menu/s5_lotus_chinese_soup.jpg', 'Lotus root, ground nuts and kombu', '1', '0', '03'),
('7', 'OLD CUCUMBER', 'http://medifoods.my/images/menu/s6_old_cucumber_chinese_soup.jpg', 'Old cucumber and longan', '1', '0', '03'),
('8', 'BURDOCK', 'http://medifoods.my/images/menu/s7_bur_dock_chinese_soup.jpg', 'Burdock, fig, longan and goji berry', '1', '0', '03'),
('9', 'ABC', 'http://medifoods.my/images/menu/s8_abc_chinese_soup.jpg', 'Carrot, potato, onion, and tomato', '1', '0', '03');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating`
--

CREATE TABLE `tbl_rating` (
  `phone` varchar(256) NOT NULL,
  `ratevalue` text NOT NULL,
  `foodid` text NOT NULL,
  `comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_request`
--

CREATE TABLE `tbl_request` (
  `order_id` varchar(255) NOT NULL,
  `phone` text NOT NULL,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `total` text NOT NULL,
  `status` text NOT NULL,
  `Comment` text NOT NULL,
  `paymentState` text NOT NULL,
  `foods` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `phone` text NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `staff` text NOT NULL,
  `securecode` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`phone`, `name`, `password`, `staff`, `securecode`) VALUES
('66966379', 'Sing Lun', 'zukayno', 'true', 'abc'),
('64960648', 'Lai Fun', '1234', 'false', 'abc1234');

-- --------------------------------------------------------

--
-- Table structure for table `testquote`
--

CREATE TABLE `testquote` (
  `strquote` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testquote`
--

INSERT INTO `testquote` (`strquote`) VALUES
('\\\"'),
('\"\"ABC\"\"');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_food`
--
ALTER TABLE `tbl_food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_rating`
--
ALTER TABLE `tbl_rating`
  ADD PRIMARY KEY (`phone`);

--
-- Indexes for table `tbl_request`
--
ALTER TABLE `tbl_request`
  ADD PRIMARY KEY (`order_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
