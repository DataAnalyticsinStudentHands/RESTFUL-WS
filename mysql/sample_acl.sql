-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2015 at 09:51 PM
-- Server version: 5.6.20-log
-- PHP Version: 5.5.15

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sampleacldb`
--
CREATE DATABASE IF NOT EXISTS `sampleacldb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sampleacldb`;

-- --------------------------------------------------------

--
-- Table structure for table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE IF NOT EXISTS `acl_class` (
`id` bigint(20) NOT NULL,
  `class` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Truncate table before insert `acl_class`
--

TRUNCATE TABLE `acl_class`;
--
-- Dumping data for table `acl_class`
--

INSERT INTO `acl_class` (`id`, `class`) VALUES
(7, 'dash.pojo.Group'),
(9, 'dash.pojo.Post'),
(8, 'dash.pojo.Task'),
(4, 'dash.pojo.User');

-- --------------------------------------------------------

--
-- Table structure for table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE IF NOT EXISTS `acl_entry` (
`id` bigint(20) NOT NULL,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=215 ;

--
-- Truncate table before insert `acl_entry`
--

TRUNCATE TABLE `acl_entry`;
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
(136, 57, 2, 39, 8, 1, 0, 0),
(147, 60, 0, 41, 1, 1, 0, 0),
(148, 60, 1, 41, 2, 1, 0, 0),
(149, 60, 2, 41, 8, 1, 0, 0),
(195, 64, 0, 42, 1, 1, 0, 0),
(196, 64, 1, 42, 2, 1, 0, 0),
(197, 64, 2, 42, 8, 1, 0, 0),
(198, 59, 0, 35, 128, 1, 0, 0),
(199, 59, 1, 41, 64, 1, 0, 0),
(200, 59, 2, 42, 64, 1, 0, 0),
(207, 63, 0, 41, 128, 1, 0, 0),
(208, 63, 1, 42, 64, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `acl_object_identity`
--

DROP TABLE IF EXISTS `acl_object_identity`;
CREATE TABLE IF NOT EXISTS `acl_object_identity` (
`id` bigint(20) NOT NULL,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) NOT NULL DEFAULT '4',
  `entries_inheriting` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=66 ;

--
-- Truncate table before insert `acl_object_identity`
--

TRUNCATE TABLE `acl_object_identity`;
--
-- Dumping data for table `acl_object_identity`
--

INSERT INTO `acl_object_identity` (`id`, `object_id_class`, `object_id_identity`, `parent_object`, `owner_sid`, `entries_inheriting`) VALUES
(10, 4, 1, NULL, 4, 0),
(45, 4, 7, NULL, 4, 1),
(46, 4, 8, NULL, 4, 1),
(57, 4, 12, NULL, 4, 1),
(59, 7, 16, NULL, 4, 1),
(60, 4, 14, NULL, 4, 1),
(63, 8, 5, NULL, 4, 1),
(64, 4, 15, NULL, 4, 1),
(65, 4, 18, NULL, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `acl_sid`
--

DROP TABLE IF EXISTS `acl_sid`;
CREATE TABLE IF NOT EXISTS `acl_sid` (
`id` bigint(20) NOT NULL,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Truncate table before insert `acl_sid`
--

TRUNCATE TABLE `acl_sid`;
--
-- Dumping data for table `acl_sid`
--

INSERT INTO `acl_sid` (`id`, `principal`, `sid`) VALUES
(39, 1, 'Admin'),
(4, 1, 'Root'),
(41, 1, 'TaskManagerDemo'),
(42, 1, 'taskUser'),
(35, 1, 'User'),
(34, 1, 'Visitor');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
`id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Truncate table before insert `authorities`
--

TRUNCATE TABLE `authorities`;
--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`id`, `username`, `authority`) VALUES
(1, 'Admin', 'ROLE_ADMIN'),
(2, 'Root', 'ROLE_ROOT'),
(3, 'TaskManagerDemo', 'ROLE_USER'),
(4, 'taskUser', 'ROLE_USER'),
(5, 'User', 'ROLE_USER'),
(6, 'Visitor', 'ROLE_VISITOR');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
`id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Truncate table before insert `login`
--

TRUNCATE TABLE `login`;
--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `enabled`, `id`) VALUES
('Root', 'test', 1, 1),
('Visitor', 'test', 1, 7),
('User', 'test', 1, 8),
('Admin', 'test', 1, 12),
('TaskManagerDemo', 'test', 1, 14),
('taskUser', 'test', 1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
CREATE TABLE IF NOT EXISTS `user_data` (
  `username` varchar(50) NOT NULL,
`id` int(11) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `homePhone` varchar(10) DEFAULT NULL,
  `cellPhone` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_email_verified` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Truncate table before insert `user_data`
--

TRUNCATE TABLE `user_data`;
--
-- Dumping data for table `user_data`
--

INSERT INTO `user_data` (`username`, `id`, `firstName`, `lastName`, `city`, `homePhone`, `cellPhone`, `email`, `picture`, `insertion_date`, `is_email_verified`) VALUES
('Root', 1, '', '', NULL, NULL, NULL, NULL, NULL, '2014-07-09 16:41:51', 0),
('Visitor', 7, 'Client', 'Device', NULL, NULL, NULL, NULL, NULL, '2014-07-18 12:12:54', 0),
('User', 8, 'Demo', 'ofUser', NULL, NULL, NULL, 'tyler.swensen@gmail.com', NULL, '2014-07-18 12:14:26', 1),
('Admin', 12, 'Demo', 'ofAdmin', NULL, NULL, NULL, NULL, NULL, '2014-07-24 10:38:34', 0),
('TaskManagerDemo', 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2014-07-29 16:17:31', 0),
('taskUser', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2014-08-01 12:23:02', 0);

-- --------------------------------------------------------

--
-- Table structure for table `validation_tokens`
--

DROP TABLE IF EXISTS `validation_tokens`;
CREATE TABLE IF NOT EXISTS `validation_tokens` (
  `user_id` int(11) NOT NULL,
  `token` varchar(64) NOT NULL,
  `expiration_date` datetime NOT NULL,
  `token_type` enum('PASSWORD_RESET','EMAIL_ACTIVATION') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `validation_tokens`
--

TRUNCATE TABLE `validation_tokens`;
--
-- Dumping data for table `validation_tokens`
--

INSERT INTO `validation_tokens` (`user_id`, `token`, `expiration_date`, `token_type`) VALUES
(8, 'gdkAr1uXptJJ8BEmALjA7k7fgl5I4ZmcMAJCgMfNVj0rkrnOBJOA2ywWJZ67b4KN', '2015-02-11 09:50:38', 'PASSWORD_RESET'),
(8, '6XL2fc5x3qvle4fir7Ebmy76fbeWpJeXCEiWWGy2CxUz0hd943jG92EE5chMmm8i', '2015-02-28 14:00:44', 'PASSWORD_RESET'),
(8, '2VdrEwi5xmwOwdBfRVsm6KHBGDvQ8t8vWmVr0lemp6bLf2MjkQAFslNe2lhZ2rUg', '2015-02-28 14:47:54', 'PASSWORD_RESET'),
(8, 'AsYHarW7SgA0ylDhbRsh6kX16Z36SizuUVBr89zly9B1Eno57S1xFLwjLqhRgdyO', '2015-03-02 16:24:25', 'PASSWORD_RESET');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acl_class`
--
ALTER TABLE `acl_class`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unique_uk_2` (`class`);

--
-- Indexes for table `acl_entry`
--
ALTER TABLE `acl_entry`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`), ADD UNIQUE KEY `Permission` (`sid`,`acl_object_identity`,`mask`) COMMENT 'Prevents duplicate permissions', ADD KEY `foreign_fk_5` (`sid`);

--
-- Indexes for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`), ADD KEY `foreign_fk_1` (`parent_object`), ADD KEY `foreign_fk_3` (`owner_sid`);

--
-- Indexes for table `acl_sid`
--
ALTER TABLE `acl_sid`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unique_uk_1` (`sid`,`principal`);

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `ix_auth_username` (`username`,`authority`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_data`
--
ALTER TABLE `user_data`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acl_class`
--
ALTER TABLE `acl_class`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `acl_entry`
--
ALTER TABLE `acl_entry`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=215;
--
-- AUTO_INCREMENT for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=66;
--
-- AUTO_INCREMENT for table `acl_sid`
--
ALTER TABLE `acl_sid`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `authorities`
--
ALTER TABLE `authorities`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `user_data`
--
ALTER TABLE `user_data`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
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
ADD CONSTRAINT `object_id->acl_class.id` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
ADD CONSTRAINT `object_id->acl_sid.id` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `object_id->object_id.id` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`);

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
ADD CONSTRAINT `login->user_data (id)` FOREIGN KEY (`id`) REFERENCES `user_data` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `login->user_data (username)` FOREIGN KEY (`username`) REFERENCES `user_data` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
