-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2019 at 06:44 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studdybuddy`
--

-- --------------------------------------------------------

--
-- Table structure for table `requestdetails`
--

CREATE TABLE `requestdetails` (
  `id` int(11) NOT NULL,
  `emailfrom` varchar(30) NOT NULL,
  `emailfor` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requestdetails`
--

INSERT INTO `requestdetails` (`id`, `emailfrom`, `emailfor`) VALUES
(1, 'hinu', '3'),
(2, 'hinu', '1'),
(3, 'hinu', '4'),
(4, 'hinu', '3'),
(5, 'hinu', '1'),
(6, 'hinu', '4'),
(7, 'hinu', '2'),
(8, 'rinny', '2'),
(9, 'rinny', '4'),
(10, 'rinny', '4'),
(11, 'zimple@gmail.com', '2'),
(12, 'zimple@gmail.com', '3'),
(13, 'zimple@gmail.com', '4'),
(14, 'bhavana', '1');

-- --------------------------------------------------------

--
-- Table structure for table `usertable`
--

CREATE TABLE `usertable` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `university` varchar(50) NOT NULL,
  `major` varchar(50) NOT NULL,
  `level` varchar(30) NOT NULL,
  `phone` int(10) NOT NULL,
  `zipcode` int(5) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertable`
--

INSERT INTO `usertable` (`id`, `name`, `university`, `major`, `level`, `phone`, `zipcode`, `email`, `password`) VALUES
(1, 'zimple', 'bu', 'Accounting (BS)', 'Freshman', 1236547890, 13905, 'zimple@gmail.com', '1234'),
(2, 'Hinal', 'BU', 'Biomedical Engineering (BS)', 'Masters', 1236459781, 13905, 'hinu', '1234'),
(3, 'Rinny', 'BU', 'Chemistry (BA, BS)', 'PhD', 1236459781, 13905, 'rinny', '1234'),
(4, 'Bhavana', 'BU', 'Electrical Engineering (BS)', 'Senior', 1236459781, 13905, 'bhavana', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `requestdetails`
--
ALTER TABLE `requestdetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usertable`
--
ALTER TABLE `usertable`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `requestdetails`
--
ALTER TABLE `requestdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `usertable`
--
ALTER TABLE `usertable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
