-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2023 at 12:40 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbjavan`
--

-- --------------------------------------------------------

--
-- Table structure for table `table_tax_report`
--

CREATE TABLE `table_tax_report` (
  `id` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` text DEFAULT NULL,
  `last_modified_at` datetime DEFAULT NULL,
  `last_modified_by` text DEFAULT NULL,
  `number` text DEFAULT NULL,
  `status` text DEFAULT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_tax_report`
--

INSERT INTO `table_tax_report` (`id`, `created_at`, `created_by`, `last_modified_at`, `last_modified_by`, `number`, `status`, `version`) VALUES
('tax1', '2023-03-04 12:10:33', 'SYSTEM', '2023-03-04 11:32:50', NULL, '001', 'DRAFT', 0),
('tax2', '2023-03-04 12:10:33', 'SYSTEM', '2023-03-04 12:10:33', NULL, '002', 'APPROVED', 0),
('tax3', '2023-03-04 12:10:33', 'SYSTEM', '2023-03-04 12:10:33', NULL, '003', 'REJECTED', 0);

-- --------------------------------------------------------

--
-- Table structure for table `table_user`
--

CREATE TABLE `table_user` (
  `id` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` text DEFAULT NULL,
  `email` text DEFAULT NULL,
  `last_modified_at` datetime DEFAULT NULL,
  `last_modified_by` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `role` text DEFAULT NULL,
  `taxrole` text DEFAULT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_user`
--

INSERT INTO `table_user` (`id`, `created_at`, `created_by`, `email`, `last_modified_at`, `last_modified_by`, `name`, `password`, `role`, `taxrole`, `version`) VALUES
('ian1', '2023-03-04 11:54:04', 'SYSTEM', 'ian1@gmail.com', '2023-03-04 11:54:04', NULL, 'ian1', 'ian1', 'USER', 'MAKER', 0),
('ian2', '2023-03-04 11:54:04', 'SYSTEM', 'ian2@gmail.com', '2023-03-04 11:54:04', NULL, 'ian2', 'ian2', 'USER', 'CHECKER', 0),
('ian3', '2023-03-04 11:54:04', 'SYSTEM', 'ian3@gmail.com', '2023-03-04 11:54:04', NULL, 'ian3', 'ian3', 'USER', 'APPROVER', 0),
('ian4', '2023-03-04 11:54:04', 'SYSTEM', 'ian4@gmail.com', '2023-03-04 11:54:04', NULL, 'ian4', 'ian4', 'ADMIN', 'ADMIN', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `table_tax_report`
--
ALTER TABLE `table_tax_report`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_36fihu4camhq22lpergvsgv6a` (`number`) USING HASH;

--
-- Indexes for table `table_user`
--
ALTER TABLE `table_user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
