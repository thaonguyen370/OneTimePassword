-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 05, 2018 lúc 07:26 AM
-- Phiên bản máy phục vụ: 10.1.28-MariaDB
-- Phiên bản PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `otp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `hoTen` varchar(100) NOT NULL,
  `ngaySinh` varchar(100) NOT NULL,
  `user` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `lai_password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `soDT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`hoTen`, `ngaySinh`, `user`, `password`, `lai_password`, `email`, `soDT`) VALUES
('aa', 'aa', 'aa', 'aa', 'aa', 'nguyenvanthao8370@gmal.com', 234568),
('bb', 'bb', 'bb', 'bb', 'bb', 'nguyenvanthao8370@gmail.com', 234568),
('cc', 'cc', 'cc', 'cc', 'cc', 'nguyenvanthao8370@gmail.com', 234568),
('ddd', '10/11/1996', 'ddd', '111', '111', 'n14dcat134@student.ptithcm.edu.vn', 23456790),
('ngothiphuongtrang', '6/12/1996', 'phuongTrang', '12345', '12345', 'nguyenvanthao8370@gmail.com', 23456790),
('nguyen van thao', '5/10/1996', 'thaonguyen', 'thaonguyen37', 'thaonguyen37', 'nguyenvanthao8370@gmail.com', 23456790);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
