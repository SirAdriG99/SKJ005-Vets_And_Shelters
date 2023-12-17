-- phpMyAdmin SQL Dump
-- version 
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Дек 17 2023 г., 20:06
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
-- Структура таблицы `TABLE 7`
--

CREATE TABLE `TABLE 7` (
  `id` int(2) DEFAULT NULL,
  `is_exotic` int(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `space_need` int(2) DEFAULT NULL,
  `activity_need` varchar(22) DEFAULT NULL,
  `alimentation_need` varchar(41) DEFAULT NULL,
  `dangerous_race` int(1) DEFAULT NULL,
  `time_dedication_need` decimal(2,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `TABLE 7`
--

INSERT INTO `TABLE 7` (`id`, `is_exotic`, `name`, `space_need`, `activity_need`, `alimentation_need`, `dangerous_race`, `time_dedication_need`) VALUES
(1, 1, 'Afghan Hound', 15, 'Energetic', 'Dignified, Profoundly Loyal, Aristocratic', 0, '0.2'),
(2, 0, 'French Bulldog', 8, 'Calm', 'Adaptable, Playful, Smart', 1, '0.6'),
(3, 0, 'Akita', 24, 'Energetic', 'Courageous, Dignified, Profoundly Loyal', 1, '0.6'),
(4, 0, 'Alaskan Malamute', 21, 'Energetic', 'Affectionate, Loyal, Playful', 0, '0.8'),
(5, 0, 'American Staffordshire Terrier', 14, 'Regular Exercise', 'Confident, Smart, Good-Natured', 1, '0.6'),
(6, 0, 'Chihuahua', 2, 'Regular Exercise', 'Charming, Graceful, Sassy', 1, '0.6'),
(7, 0, 'Dogo Argentino', 25, 'Needs Lots of Activity', 'Friendly, Cheerful, Humble', 1, '1.6'),
(8, 0, 'Australian Shepherd', 13, 'Energetic', 'Smart, Work-Oriented, Exuberant', 1, '0.6'),
(9, 0, 'Basset Hound', 13, 'Couch Potato', 'Charming, Patient, Low-Key', 0, '0.8'),
(10, 0, 'Beagle', 5, 'Energetic', 'Friendly, Curious, Merry', 0, '0.8'),
(11, 0, 'Poodle (Toy)', 1, 'Regular Exercise', 'Agile, Intelligent, Self-Confident', 0, '0.8'),
(12, 0, 'Siberian Husky', 12, 'Energetic', 'Loyal, Mischievous, Outgoing', 0, '0.8'),
(13, 0, 'Standard Schnauzer', 10, 'Regular Exercise', 'Fearless, Smart, Spirited', 0, '0.8'),
(14, 0, 'Yorkshire Terrier', 2, 'Regular Exercise', 'Affectionate, Sprightly, Tomboyish', 0, '0.8'),
(15, 0, 'Belgian Malinois', 15, 'Energetic', 'Confident, Smart, Hardworking', 0, '0.4'),
(16, 0, 'Bichon Frise', 4, 'Regular Exercise', 'Playful, Curious, Peppy', 0, '1.0'),
(17, 0, 'Border Collie', 10, 'Needs Lots of Activity', 'Affectionate, Smart, Energetic', 0, '0.4'),
(18, 0, 'Boston Terrier', 4, 'Regular Exercise', 'Friendly, Bright, Amusing', 0, '1.0'),
(19, 0, 'Boxer', 10, 'Energetic', 'Bright, Fun-Loving, Active', 0, '1.0'),
(20, 0, 'Bulldog', 12, 'Regular Exercise', 'Friendly, Courageous, Calm', 0, '0.8'),
(21, 0, 'Labrador Retriever', 17, 'Needs Lots of Activity', 'Friendly, Active, Outgoing', 0, '1.0'),
(22, 0, 'Cocker Spaniel', 6, 'Regular Exercise', 'Gentle, Smart, Happy', 0, '1.0'),
(23, 0, 'Dachshund', 5, 'Regular Exercise', 'Friendly, Curious, Spunky', 1, '0.6'),
(24, 0, 'Doberman Pinscher', 20, 'Needs Lots of Activity', 'Loyal, Fearless, Alert', 1, '0.6'),
(25, 0, 'English Cocker Spaniel', 8, 'Regular Exercise', 'Energetic, Merry, Responsive', 0, '0.8'),
(27, 0, 'Dutch Shepherd', 14, 'Needs Lots of Activity', 'Intelligent, Lively, Athletic', 1, '0.6'),
(28, 0, 'Golden Retriever', 17, 'Needs Lots of Activity', 'Friendly, Intelligent, Devoted', 0, '0.8'),
(28, 0, 'Golden Retriever', 17, 'Needs Lots of Activity', 'Friendly, Intelligent, Devoted', 0, '0.8'),
(29, 0, 'Great Dane', 36, 'Energetic', 'Friendly, Patient, Dependable', 1, '0.6'),
(30, 0, 'Russell Terrier', 3, 'Needs Lots of Activity', 'Alert, Inquisitive, Lively', 0, '0.8'),
(31, 0, 'Pekingese', 4, 'Calm', 'Affectionate, Loyal, Regal in Manner', 0, '0.4'),
(32, 0, 'Maltese', 2, 'Regular Exercise', 'Gentle, Playful, Charming', 0, '1.0'),
(33, 0, 'Miniature Schnauzer', 4, 'Regular Exercise', 'Friendly, Smart, Obedient', 1, '0.6'),
(34, 0, 'Shih Tzu', 3, 'Regular Exercise', 'Affectionate, Playful, Outgoing', 0, '0.8'),
(35, 0, 'Neapolitan Mastiff', 34, 'Regular Exercise', 'Loyal, Dignified, Watchful', 0, '0.2'),
(36, 0, 'Newfoundland', 32, 'Regular Exercise', 'Sweet, Patient, Devoted', 0, '0.8'),
(37, 0, 'Old English Sheepdog', 20, 'Regular Exercise', 'Adaptable, Gentle, Smart', 1, '0.6'),
(38, 0, 'Miniature Bull Terrier', 6, 'Energetic', 'Upbeat, Mischievous, Comical', 1, '0.6'),
(39, 0, 'Pug', 4, 'Regular Exercise', 'Charming, Mischievous, Loving', 0, '0.8'),
(40, 0, 'Parson Russell Terrier', 4, 'Needs Lots of Activity', 'Friendly, Clever, Athletic', 0, '0.8'),
(41, 0, 'Miniature Pinscher', 2, 'Regular Exercise', 'Fearless, Fun-Loving, Proud', 1, '0.6'),
(42, 0, 'Pomeranian', 1, 'Regular Exercise', 'Inquisitive, Bold, Lively', 1, '0.6'),
(43, 0, 'Rottweiler', 27, 'Regular Exercise', 'Loyal, Loving, Confident Guardian', 0, '0.4'),
(44, 0, 'Collie', 16, 'Regular Exercise', 'Devoted, Graceful, Proud', 0, '0.8'),
(45, 0, 'Samoyed', 12, 'Energetic', 'Adaptable, Friendly, Gentle', 0, '0.8'),
(46, 0, 'Scottish Terrier', 5, 'Regular Exercise', 'Confident, Independent, Spirited', 1, '0.6'),
(47, 0, 'Chinese Shar-Pei', 14, 'Regular Exercise', 'Loyal, Independent, Calm', 0, '0.4'),
(48, 0, 'Saint Bernard', 38, 'Regular Exercise', 'Playful, Charming, Inquisitive', 0, '0.8'),
(49, 0, 'West Highland White Terrier', 5, 'Regular Exercise', 'Loyal, Happy, Entertaining', 0, '0.8'),
(50, 0, 'Bull Terrier', 15, 'Energetic', 'Playful, Charming, Mischievous', 0, '0.8'),
(51, 0, 'Bullmastiff', 30, 'Energetic', 'Affectionate, Loyal, Brave', 1, '0.6'),
(52, 0, 'Cane Corso', 0, 'Energetic', 'Affectionate, Intelligent, Majestic', 1, '0.6'),
(53, 0, 'Chow Chow', 15, 'Regular Exercise', 'Dignified, Bright, Serious-Minded', 0, '0.4'),
(54, 0, 'Bernese Mountain Dog', 23, 'Energetic', 'Good-Natured, Calm, Strong', 0, '0.4'),
(55, 0, 'Central Asian Shepherd Dog', 26, 'Calm', 'Independent, Courageous, Self-Confident', 0, '0.2');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
