-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2015 at 04:03 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `form_builder`
--

-- --------------------------------------------------------

--
-- Table structure for table `acl_class`
--

CREATE TABLE IF NOT EXISTS `acl_class` (
`id` bigint(20) NOT NULL,
  `class` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Table structure for table `acl_entry`
--

CREATE TABLE IF NOT EXISTS `acl_entry` (
`id` bigint(20) NOT NULL,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=965 ;

-- --------------------------------------------------------

--
-- Table structure for table `acl_object_identity`
--

CREATE TABLE IF NOT EXISTS `acl_object_identity` (
`id` bigint(20) NOT NULL,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) NOT NULL DEFAULT '4',
  `entries_inheriting` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=197 ;

-- --------------------------------------------------------

--
-- Table structure for table `acl_sid`
--

CREATE TABLE IF NOT EXISTS `acl_sid` (
`id` bigint(20) NOT NULL,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
`id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Table structure for table `forms`
--

CREATE TABLE IF NOT EXISTS `forms` (
`id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT 'UNTITLED',
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

-- --------------------------------------------------------

--
-- Table structure for table `form_responses`
--

CREATE TABLE IF NOT EXISTS `form_responses` (
`id` int(11) NOT NULL,
  `form_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `latest_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_complete` tinyint(1) NOT NULL DEFAULT '0',
  `document_folder` varchar(64) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=117 ;

-- --------------------------------------------------------

--
-- Table structure for table `form_response_entries`
--

CREATE TABLE IF NOT EXISTS `form_response_entries` (
`entry_id` int(11) NOT NULL,
  `form_response_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `label` varchar(128) NOT NULL DEFAULT '',
  `value` varchar(3000) NOT NULL DEFAULT ''
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2343 ;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
`id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
`question_id` int(11) NOT NULL,
  `form_id` int(11) NOT NULL,
  `component` varchar(32) NOT NULL,
  `editable` tinyint(1) NOT NULL DEFAULT '1',
  `form_index` int(11) NOT NULL,
  `label` varchar(128) NOT NULL DEFAULT 'Label Your Question Here',
  `description` varchar(10000) DEFAULT NULL,
  `placeholder` varchar(128) DEFAULT 'Your text here...',
  `required` tinyint(1) NOT NULL DEFAULT '0',
  `validation` varchar(16) NOT NULL DEFAULT 'NONE',
  `options` varchar(256) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=305 ;

-- --------------------------------------------------------

--
-- Table structure for table `sample_object`
--

CREATE TABLE IF NOT EXISTS `sample_object` (
`id` int(11) NOT NULL,
  `document_folder` varchar(128) DEFAULT NULL,
  `basic_field_sample` varchar(64) DEFAULT NULL,
  `time_stamp_sample` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

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
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

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
-- Indexes for table `forms`
--
ALTER TABLE `forms`
 ADD PRIMARY KEY (`id`), ADD KEY `name` (`name`);

--
-- Indexes for table `form_responses`
--
ALTER TABLE `form_responses`
 ADD PRIMARY KEY (`id`), ADD KEY `owner_id` (`owner_id`), ADD KEY `form_id` (`form_id`);

--
-- Indexes for table `form_response_entries`
--
ALTER TABLE `form_response_entries`
 ADD PRIMARY KEY (`entry_id`), ADD KEY `form_response_id` (`form_response_id`), ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
 ADD PRIMARY KEY (`question_id`), ADD KEY `form_id` (`form_id`);

--
-- Indexes for table `sample_object`
--
ALTER TABLE `sample_object`
 ADD PRIMARY KEY (`id`), ADD KEY `time_stamp_sample` (`time_stamp_sample`);

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
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `acl_entry`
--
ALTER TABLE `acl_entry`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=965;
--
-- AUTO_INCREMENT for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=197;
--
-- AUTO_INCREMENT for table `acl_sid`
--
ALTER TABLE `acl_sid`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `authorities`
--
ALTER TABLE `authorities`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `forms`
--
ALTER TABLE `forms`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `form_responses`
--
ALTER TABLE `form_responses`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=117;
--
-- AUTO_INCREMENT for table `form_response_entries`
--
ALTER TABLE `form_response_entries`
MODIFY `entry_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2343;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=305;
--
-- AUTO_INCREMENT for table `sample_object`
--
ALTER TABLE `sample_object`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user_data`
--
ALTER TABLE `user_data`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
