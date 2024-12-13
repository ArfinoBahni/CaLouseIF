-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2024 at 02:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `calouseif`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_category` varchar(50) NOT NULL,
  `item_size` varchar(20) NOT NULL,
  `item_price` int(11) NOT NULL CHECK (`item_price` > 0),
  `status` enum('pending','approved','declined') DEFAULT 'pending',
  `item_offer_status` enum('none','pending','accepted','declined') DEFAULT 'none',
  `offer_price` int(11) DEFAULT 0,
  `offering_user_id` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `seller_id`, `item_name`, `item_category`, `item_size`, `item_price`, `status`, `item_offer_status`, `offer_price`, `offering_user_id`, `reason`) VALUES
(2, 3, 'Tshirt', 'Top', 'S', 15000, 'declined', 'none', 0, NULL, NULL),
(3, 3, 'Celana', 'Bottom', 'L', 25000, 'declined', 'none', 0, NULL, NULL),
(5, 3, 'Short pants', 'Bottom', 'L', 30000, 'declined', 'none', 0, NULL, NULL),
(7, 3, 'Long pants', 'Bottom', 'M', 30000, 'declined', 'accepted', 28000, 2, NULL),
(8, 3, 'Jacket', 'top', 'M', 53000, 'declined', 'none', 0, NULL, NULL),
(9, 3, 'Sweater', 'top', 'S', 40000, 'approved', 'pending', 32000, 4, NULL),
(10, 3, 'air jordang', 'shoes', '39', 80000, 'declined', 'none', 0, NULL, 'Deny request reason: Item in bad condition'),
(11, 3, 'erforswan', 'shoes', '40', 40000, 'declined', 'accepted', 35000, 0, NULL),
(12, 3, 'adadis', 'shoes', '40', 50000, 'declined', 'accepted', 45000, 0, 'Deny offer reason: too cheap');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `seller_id` (`seller_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `items_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
