-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th3 25, 2018 lúc 08:38 PM
-- Phiên bản máy phục vụ: 5.7.21-0ubuntu0.16.04.1
-- Phiên bản PHP: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `BooksData`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `Book`
--

CREATE TABLE `Book` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `availableNumber` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `Book`
--

INSERT INTO `Book` (`id`, `title`, `author`, `availableNumber`, `description`) VALUES
(1, 'Crippled America: How to Make America Great Again ', 'Donald Trump', 10, 'MAGA'),
(2, 'Trump: The art of the deal', 'Donald Trump', 1, 'MAGA'),
(3, 'Time To Get Tough: Make America Great Again', 'Donald Trump', 10, 'MAGA'),
(4, 'Trump 101', 'Donald Trump', 10, 'MAGA'),
(5, 'Trump: The Art Of The Comeback', 'Donald Trump', 10, 'MAGA'),
(6, 'How To Get Rich', 'Donald Trump', 3, 'MAGA');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `Book`
--
ALTER TABLE `Book`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `Book`
--
ALTER TABLE `Book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
