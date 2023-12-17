-- phpMyAdmin SQL Dump
-- version 
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Дек 17 2023 г., 18:36
-- Версия сервера: 5.7.44-48-log
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `host1400746_uji`
--

-- --------------------------------------------------------

--
-- Структура таблицы `TABLE 5`
--

CREATE TABLE `TABLE 5` (
  `id` int(1) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `TABLE 5`
--

INSERT INTO `TABLE 5` (`id`, `name`) VALUES
(1, 'Good'),
(2, 'Sick'),
(3, 'At the vet'),
(4, 'Returned'),
(5, 'Adopt');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
