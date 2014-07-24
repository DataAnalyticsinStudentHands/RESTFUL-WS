-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2014 at 05:44 PM
-- Server version: 5.6.16-log
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `acl_class`
--

CREATE TABLE IF NOT EXISTS `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `acl_class`
--

INSERT INTO `acl_class` (`id`, `class`) VALUES
(7, 'dash.pojo.Group'),
(4, 'dash.pojo.User');

-- --------------------------------------------------------

--
-- Table structure for table `acl_entry`
--

CREATE TABLE IF NOT EXISTS `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=137 ;

--
-- Dumping data for table `acl_entry`
--

INSERT INTO `acl_entry` (`id`, `acl_object_identity`, `ace_order`, `sid`, `mask`, `granting`, `audit_success`, `audit_failure`) VALUES
(1, 10, 0, 4, 1, 1, 1, 1),
(2, 10, 1, 4, 2, 1, 0, 0),
(127, 46, 0, 35, 1, 1, 0, 0),
(128, 46, 1, 35, 2, 1, 0, 0),
(129, 46, 2, 35, 8, 1, 0, 0),
(130, 45, 0, 34, 1, 1, 0, 0),
(134, 57, 0, 39, 1, 1, 0, 0),
(135, 57, 1, 39, 2, 1, 0, 0),
(136, 57, 2, 39, 8, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `acl_object_identity`
--

CREATE TABLE IF NOT EXISTS `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Dumping data for table `acl_object_identity`
--

INSERT INTO `acl_object_identity` (`id`, `object_id_class`, `object_id_identity`, `parent_object`, `owner_sid`, `entries_inheriting`) VALUES
(10, 4, 1, NULL, 4, 0),
(45, 4, 7, NULL, 4, 1),
(46, 4, 8, NULL, 35, 1),
(56, 7, 15, NULL, 35, 1),
(57, 4, 12, NULL, 39, 1);

-- --------------------------------------------------------

--
-- Table structure for table `acl_sid`
--

CREATE TABLE IF NOT EXISTS `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `acl_sid`
--

INSERT INTO `acl_sid` (`id`, `principal`, `sid`) VALUES
(39, 1, 'Admin'),
(4, 1, 'Root'),
(35, 1, 'User'),
(34, 1, 'Visitor');

-- --------------------------------------------------------

--
-- Table structure for table `admin_post`
--

CREATE TABLE IF NOT EXISTS `admin_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `admin_post`
--

INSERT INTO `admin_post` (`id`, `date`, `message`) VALUES
(1, '2011-01-03 21:37:58', 'Custom post #1 from admin'),
(2, '2011-01-04 21:38:39', 'Custom post #2 from admin'),
(3, '2011-01-05 21:39:37', 'Custom post #3 from admin');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('Admin', 'ROLE_ADMIN'),
('Root', 'ROLE_ROOT'),
('User', 'ROLE_USER'),
('Visitor', 'ROLE_VISITOR');

-- --------------------------------------------------------

--
-- Table structure for table `group_data`
--

CREATE TABLE IF NOT EXISTS `group_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `creation_timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `group_data`
--

INSERT INTO `group_data` (`id`, `name`, `description`, `creation_timestamp`) VALUES
(15, 'TestGroup', 'This is the description, ''user'' is the group manager', '2014-07-24 10:29:42');

-- --------------------------------------------------------

--
-- Table structure for table `integrated plant database 2012`
--

CREATE TABLE IF NOT EXISTS `integrated plant database 2012` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Scientific Name` char(50) NOT NULL,
  `Common Name` varchar(50) NOT NULL,
  `Host Plant For` varchar(50) NOT NULL,
  `Website` varchar(500) NOT NULL,
  `Height (ft)` int(50) NOT NULL,
  `Width (ft)` int(50) NOT NULL,
  `Sun` int(5) NOT NULL,
  `Soil Conditions` int(5) NOT NULL,
  `Class` int(5) NOT NULL,
  `Annual` tinyint(1) NOT NULL,
  `Native` tinyint(1) NOT NULL,
  `Spring Color` tinyint(1) NOT NULL,
  `Summer Color` tinyint(1) NOT NULL,
  `Fall Color` tinyint(1) NOT NULL,
  `Summer Fruit` tinyint(1) NOT NULL,
  `Fall/Winter Fruit` tinyint(1) NOT NULL,
  `D4?` tinyint(1) NOT NULL,
  `Seeds and Nuts` tinyint(1) NOT NULL,
  `Deciduous Cover` tinyint(1) NOT NULL,
  `Evergreen Cover` tinyint(1) NOT NULL,
  `Bird Food` tinyint(1) NOT NULL,
  `Butterfly Nectar` tinyint(1) NOT NULL,
  `Hummingbird Nectar` tinyint(1) NOT NULL,
  `Larval Butterfly Host` tinyint(1) NOT NULL,
  `Understory Plant` tinyint(1) NOT NULL,
  `Screen/Hedge Plant` tinyint(1) NOT NULL,
  `Erosion Control` tinyint(1) NOT NULL,
  `Groundcover Plant` tinyint(1) NOT NULL,
  `Fast Growing` tinyint(1) NOT NULL,
  `Slow Growing` tinyint(1) NOT NULL,
  `Endorsed by Native Plant Society` tinyint(1) NOT NULL,
  `Naturalized, not Native` tinyint(1) NOT NULL,
  `Native to Texas, not Houston` tinyint(1) NOT NULL,
  `Cultivar of a Native` tinyint(1) NOT NULL,
  `Keep in a Container` tinyint(1) NOT NULL,
  `Endorsed by Cockrell Butterfly Center` tinyint(1) NOT NULL,
  `Endorsed by Jesse H. Jones Park & Nature Center` tinyint(1) NOT NULL,
  `pH` tinyint(10) NOT NULL,
  `Price` tinyint(5) NOT NULL,
  `Taste of Fruit` varchar(200) NOT NULL,
  `Edible Parts` varchar(100) NOT NULL,
  `Companion Plants` varchar(200) NOT NULL,
  `Root Depth` tinyint(50) NOT NULL,
  `Bloom Period` date NOT NULL,
  `Toxic Part` varchar(100) NOT NULL,
  `Sap` tinyint(1) NOT NULL,
  `Seed Timing` date NOT NULL,
  `Seed Type` tinyint(10) NOT NULL,
  `Disease Tolerance` tinyint(10) NOT NULL,
  `Pest Tolerance` tinyint(10) NOT NULL,
  `Hardy Scale` tinyint(10) NOT NULL,
  `Irrigated` tinyint(1) NOT NULL,
  `Allergenicity` tinyint(10) NOT NULL,
  `Detering Animals` tinyint(10) NOT NULL,
  `Cultivar Names` varchar(100) NOT NULL,
  `Distance between Plants` tinyint(5) NOT NULL,
  `Map` varchar(200) NOT NULL,
  `Air Purifying` tinyint(1) NOT NULL,
  `Soil Purifying` tinyint(1) NOT NULL,
  `Cold Tolerance` tinyint(10) NOT NULL,
  `Drought Tolerance` tinyint(10) NOT NULL,
  `Moisture Tolerance` tinyint(10) NOT NULL,
  `Time to Produce` date NOT NULL,
  `Data Source` varchar(50) NOT NULL,
  `Can we share the source` tinyint(1) NOT NULL,
  `HOA approved` tinyint(1) NOT NULL,
  `Tourist Spots` varchar(100) NOT NULL,
  `Ease of Planting` tinyint(10) NOT NULL,
  `Interesting Facts` varchar(100) NOT NULL,
  `Suggestions on How to Grow` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `enabled`, `id`) VALUES
('Root', 'test', 1, 1),
('Visitor', 'test', 1, 7),
('User', 'test', 1, 8),
('Admin', 'test', 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `personal_post`
--

CREATE TABLE IF NOT EXISTS `personal_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `personal_post`
--

INSERT INTO `personal_post` (`id`, `date`, `message`) VALUES
(1, '2011-01-06 21:40:02', 'Custom post #1 from user'),
(2, '2011-01-07 21:40:13', 'Custom post #2 from user'),
(3, '2011-01-08 21:40:34', 'Custom post #3 from user');

-- --------------------------------------------------------

--
-- Table structure for table `plant_db_form_submissions`
--

CREATE TABLE IF NOT EXISTS `plant_db_form_submissions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Scientific Name` char(50) NOT NULL,
  `Common Name` varchar(50) NOT NULL,
  `Host Plant For` varchar(50) NOT NULL,
  `Website` varchar(500) NOT NULL,
  `Height (ft)` int(50) NOT NULL,
  `Width (ft)` int(50) NOT NULL,
  `Sun` int(5) NOT NULL,
  `Soil Conditions` int(5) NOT NULL,
  `Class` int(5) NOT NULL,
  `Annual` tinyint(1) NOT NULL,
  `Native` tinyint(1) NOT NULL,
  `Spring Color` tinyint(1) NOT NULL,
  `Summer Color` tinyint(1) NOT NULL,
  `Fall Color` tinyint(1) NOT NULL,
  `Summer Fruit` tinyint(1) NOT NULL,
  `Fall/Winter Fruit` tinyint(1) NOT NULL,
  `D4?` tinyint(1) NOT NULL,
  `Seeds and Nuts` tinyint(1) NOT NULL,
  `Deciduous Cover` tinyint(1) NOT NULL,
  `Evergreen Cover` tinyint(1) NOT NULL,
  `Bird Food` tinyint(1) NOT NULL,
  `Butterfly Nectar` tinyint(1) NOT NULL,
  `Hummingbird Nectar` tinyint(1) NOT NULL,
  `Larval Butterfly Host` tinyint(1) NOT NULL,
  `Understory Plant` tinyint(1) NOT NULL,
  `Screen/Hedge Plant` tinyint(1) NOT NULL,
  `Erosion Control` tinyint(1) NOT NULL,
  `Groundcover Plant` tinyint(1) NOT NULL,
  `Fast Growing` tinyint(1) NOT NULL,
  `Slow Growing` tinyint(1) NOT NULL,
  `Endorsed by Native Plant Society` tinyint(1) NOT NULL,
  `Naturalized, not Native` tinyint(1) NOT NULL,
  `Native to Texas, not Houston` tinyint(1) NOT NULL,
  `Cultivar of a Native` tinyint(1) NOT NULL,
  `Keep in a Container` tinyint(1) NOT NULL,
  `Endorsed by Cockrell Butterfly Center` tinyint(1) NOT NULL,
  `Endorsed by Jesse H. Jones Park & Nature Center` tinyint(1) NOT NULL,
  `pH` tinyint(10) NOT NULL,
  `Price` tinyint(5) NOT NULL,
  `Taste of Fruit` varchar(200) NOT NULL,
  `Edible Parts` varchar(100) NOT NULL,
  `Companion Plants` varchar(200) NOT NULL,
  `Root Depth` tinyint(50) NOT NULL,
  `Bloom Period` date NOT NULL,
  `Toxic Part` varchar(100) NOT NULL,
  `Sap` tinyint(1) NOT NULL,
  `Seed Timing` date NOT NULL,
  `Seed Type` tinyint(10) NOT NULL,
  `Disease Tolerance` tinyint(10) NOT NULL,
  `Pest Tolerance` tinyint(10) NOT NULL,
  `Hardy Scale` tinyint(10) NOT NULL,
  `Irrigated` tinyint(1) NOT NULL,
  `Allergenicity` tinyint(10) NOT NULL,
  `Detering Animals` tinyint(10) NOT NULL,
  `Cultivar Names` varchar(100) NOT NULL,
  `Distance between Plants` tinyint(5) NOT NULL,
  `Map` varchar(200) NOT NULL,
  `Air Purifying` tinyint(1) NOT NULL,
  `Soil Purifying` tinyint(1) NOT NULL,
  `Cold Tolerance` tinyint(10) NOT NULL,
  `Drought Tolerance` tinyint(10) NOT NULL,
  `Moisture Tolerance` tinyint(10) NOT NULL,
  `Time to Produce` date NOT NULL,
  `Data Source` varchar(50) NOT NULL,
  `Can we share the source` tinyint(1) NOT NULL,
  `HOA approved` tinyint(1) NOT NULL,
  `Tourist Spots` varchar(100) NOT NULL,
  `Ease of Planting` tinyint(10) NOT NULL,
  `Interesting Facts` varchar(100) NOT NULL,
  `Suggestions on How to Grow` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `public_post`
--

CREATE TABLE IF NOT EXISTS `public_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `public_post`
--

INSERT INTO `public_post` (`id`, `date`, `message`) VALUES
(1, '2011-01-10 21:40:44', 'Custom post #1 from public'),
(2, '2011-01-11 21:40:48', 'Custom post #2 from public'),
(3, '2011-01-12 21:41:08', 'Custom post #3 from public');

-- --------------------------------------------------------

--
-- Table structure for table `testexport`
--

CREATE TABLE IF NOT EXISTS `testexport` (
  `testExport_id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `last_login` varchar(255) DEFAULT NULL,
  `favorite_color` varchar(255) DEFAULT NULL,
  `logged_in` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`testExport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `creation_time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `creation_time_stamp`) VALUES
(1, 'tyler', 'test', '2014-06-19 22:13:58');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `city` varchar(20) DEFAULT NULL,
  `homePhone` varchar(10) DEFAULT NULL,
  `cellPhone` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`, `id`, `firstName`, `lastName`, `city`, `homePhone`, `cellPhone`, `email`, `picture`, `insertion_date`) VALUES
('tyler', 'test', 1, 1, 'bobby', 'joe', NULL, NULL, NULL, NULL, NULL, '2014-07-09 16:41:51');

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

CREATE TABLE IF NOT EXISTS `user_data` (
  `username` varchar(50) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `city` varchar(20) DEFAULT NULL,
  `homePhone` varchar(10) DEFAULT NULL,
  `cellPhone` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `user_data`
--

INSERT INTO `user_data` (`username`, `id`, `firstName`, `lastName`, `city`, `homePhone`, `cellPhone`, `email`, `picture`, `insertion_date`) VALUES
('Root', 1, '', '', NULL, NULL, NULL, NULL, NULL, '2014-07-09 16:41:51'),
('Visitor', 7, 'Client', 'Device', NULL, NULL, NULL, NULL, NULL, '2014-07-18 12:12:54'),
('User', 8, 'Demo', 'ofUser', NULL, NULL, NULL, NULL, NULL, '2014-07-18 12:14:26'),
('Admin', 12, 'Demo', 'ofAdmin', NULL, NULL, NULL, NULL, NULL, '2014-07-24 10:38:34');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `acl_entry`
--
ALTER TABLE `acl_entry`
  ADD CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
  ADD CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  ADD CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`);

--
-- Constraints for table `acl_sid`
--
ALTER TABLE `acl_sid`
  ADD CONSTRAINT `acl_sid_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `user_data` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `login` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login->user_data (username)` FOREIGN KEY (`username`) REFERENCES `user_data` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `login->user_data (id)` FOREIGN KEY (`id`) REFERENCES `user_data` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
