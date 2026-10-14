-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 14, 2026 at 12:25 PM
-- Server version: 8.0.45
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klub_sepakbola`
--

-- --------------------------------------------------------

--
-- Table structure for table `pelatih`
--

CREATE TABLE `pelatih` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `spesialisasi` varchar(50) NOT NULL,
  `pengalaman` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pelatih`
--

INSERT INTO `pelatih` (`id`, `nama`, `spesialisasi`, `pengalaman`) VALUES
(1, 'Agus Salim', 'Pelatih Kepala', 10),
(2, 'Bambang Hartono', 'Pelatih Kiper', 6),
(3, 'Emil Audero', 'Pelatih Kiper', 2),
(4, 'Bambang', 'Pelatih Kepala', 12);

-- --------------------------------------------------------

--
-- Table structure for table `pemain`
--

CREATE TABLE `pemain` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `posisi` varchar(5) NOT NULL,
  `nomor_punggung` int NOT NULL,
  `usia` int NOT NULL,
  `status_kesehatan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pemain`
--

INSERT INTO `pemain` (`id`, `nama`, `posisi`, `nomor_punggung`, `usia`, `status_kesehatan`) VALUES
(1, 'Rizky Pratama', 'GK', 1, 24, 'Cedera'),
(2, 'Fadli Ramadhan', 'LB', 4, 22, 'Tersedia'),
(3, 'Andika Saputra', 'CB', 5, 26, 'Tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `pertandingan`
--

CREATE TABLE `pertandingan` (
  `id` int NOT NULL,
  `lawan` varchar(100) NOT NULL,
  `tanggal` varchar(10) NOT NULL,
  `kompetisi` varchar(100) NOT NULL,
  `skor_klub` int NOT NULL,
  `skor_lawan` int NOT NULL,
  `lokasi` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pertandingan`
--

INSERT INTO `pertandingan` (`id`, `lawan`, `tanggal`, `kompetisi`, `skor_klub`, `skor_lawan`, `lokasi`) VALUES
(1, 'Persija FC', '12-08-2026', 'Liga 1', 3, 1, 'Kandang'),
(2, 'Bali United', '19-08-2026', 'Liga 1', 1, 1, 'Tandang'),
(3, 'FC Barcelona', '14-10-26', 'Champions League', 3, 1, 'Kandang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pelatih`
--
ALTER TABLE `pelatih`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pemain`
--
ALTER TABLE `pemain`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pertandingan`
--
ALTER TABLE `pertandingan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pelatih`
--
ALTER TABLE `pelatih`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pemain`
--
ALTER TABLE `pemain`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pertandingan`
--
ALTER TABLE `pertandingan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
