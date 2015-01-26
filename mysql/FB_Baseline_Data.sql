-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2015 at 04:02 PM
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

--
-- Dumping data for table `acl_class`
--

INSERT INTO `acl_class` (`id`, `class`) VALUES
(11, 'dash.pojo.Form'),
(12, 'dash.pojo.FormResponse'),
(10, 'dash.pojo.SampleObject'),
(4, 'dash.pojo.User');

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
(212, 65, 0, 43, 1, 1, 0, 0),
(213, 65, 1, 43, 2, 1, 0, 0),
(214, 65, 2, 43, 8, 1, 0, 0),
(218, 66, 0, 35, 1, 1, 0, 0),
(219, 66, 1, 35, 2, 1, 0, 0),
(220, 66, 2, 35, 8, 1, 0, 0),
(224, 67, 0, 35, 1, 1, 0, 0),
(225, 67, 1, 35, 2, 1, 0, 0),
(226, 67, 2, 35, 8, 1, 0, 0),
(230, 68, 0, 35, 1, 1, 0, 0),
(231, 68, 1, 35, 2, 1, 0, 0),
(232, 68, 2, 35, 8, 1, 0, 0),
(236, 75, 0, 35, 1, 1, 0, 0),
(237, 75, 1, 35, 2, 1, 0, 0),
(238, 75, 2, 35, 8, 1, 0, 0),
(242, 76, 0, 35, 1, 1, 0, 0),
(243, 76, 1, 35, 2, 1, 0, 0),
(244, 76, 2, 35, 8, 1, 0, 0),
(248, 77, 0, 35, 1, 1, 0, 0),
(249, 77, 1, 35, 2, 1, 0, 0),
(250, 77, 2, 35, 8, 1, 0, 0),
(254, 78, 0, 35, 1, 1, 0, 0),
(255, 78, 1, 35, 2, 1, 0, 0),
(256, 78, 2, 35, 8, 1, 0, 0),
(260, 79, 0, 35, 1, 1, 0, 0),
(261, 79, 1, 35, 2, 1, 0, 0),
(262, 79, 2, 35, 8, 1, 0, 0),
(266, 80, 0, 35, 1, 1, 0, 0),
(267, 80, 1, 35, 2, 1, 0, 0),
(268, 80, 2, 35, 8, 1, 0, 0),
(272, 81, 0, 35, 1, 1, 0, 0),
(273, 81, 1, 35, 2, 1, 0, 0),
(274, 81, 2, 35, 8, 1, 0, 0),
(278, 82, 0, 35, 1, 1, 0, 0),
(279, 82, 1, 35, 2, 1, 0, 0),
(280, 82, 2, 35, 8, 1, 0, 0),
(284, 83, 0, 35, 1, 1, 0, 0),
(285, 83, 1, 35, 2, 1, 0, 0),
(286, 83, 2, 35, 8, 1, 0, 0),
(296, 85, 0, 35, 1, 1, 0, 0),
(297, 85, 1, 35, 2, 1, 0, 0),
(298, 85, 2, 35, 8, 1, 0, 0),
(302, 86, 0, 35, 1, 1, 0, 0),
(303, 86, 1, 35, 2, 1, 0, 0),
(304, 86, 2, 35, 8, 1, 0, 0),
(308, 87, 0, 35, 1, 1, 0, 0),
(309, 87, 1, 35, 2, 1, 0, 0),
(310, 87, 2, 35, 8, 1, 0, 0),
(314, 88, 0, 39, 1, 1, 0, 0),
(315, 88, 1, 39, 2, 1, 0, 0),
(316, 88, 2, 39, 8, 1, 0, 0),
(320, 89, 0, 39, 1, 1, 0, 0),
(321, 89, 1, 39, 2, 1, 0, 0),
(322, 89, 2, 39, 8, 1, 0, 0),
(326, 90, 0, 35, 1, 1, 0, 0),
(327, 90, 1, 35, 2, 1, 0, 0),
(328, 90, 2, 35, 8, 1, 0, 0),
(332, 91, 0, 35, 1, 1, 0, 0),
(333, 91, 1, 35, 2, 1, 0, 0),
(334, 91, 2, 35, 8, 1, 0, 0),
(338, 92, 0, 39, 1, 1, 0, 0),
(339, 92, 1, 39, 2, 1, 0, 0),
(340, 92, 2, 39, 8, 1, 0, 0),
(344, 93, 0, 39, 1, 1, 0, 0),
(345, 93, 1, 39, 2, 1, 0, 0),
(346, 93, 2, 39, 8, 1, 0, 0),
(350, 94, 0, 39, 1, 1, 0, 0),
(351, 94, 1, 39, 2, 1, 0, 0),
(352, 94, 2, 39, 8, 1, 0, 0),
(356, 95, 0, 44, 1, 1, 0, 0),
(357, 95, 1, 44, 2, 1, 0, 0),
(358, 95, 2, 44, 8, 1, 0, 0),
(362, 96, 0, 44, 1, 1, 0, 0),
(363, 96, 1, 44, 2, 1, 0, 0),
(364, 96, 2, 44, 8, 1, 0, 0),
(368, 97, 0, 44, 1, 1, 0, 0),
(369, 97, 1, 44, 2, 1, 0, 0),
(370, 97, 2, 44, 8, 1, 0, 0),
(374, 98, 0, 44, 1, 1, 0, 0),
(375, 98, 1, 44, 2, 1, 0, 0),
(376, 98, 2, 44, 8, 1, 0, 0),
(380, 99, 0, 44, 1, 1, 0, 0),
(381, 99, 1, 44, 2, 1, 0, 0),
(382, 99, 2, 44, 8, 1, 0, 0),
(386, 100, 0, 44, 1, 1, 0, 0),
(387, 100, 1, 44, 2, 1, 0, 0),
(388, 100, 2, 44, 8, 1, 0, 0),
(392, 101, 0, 44, 1, 1, 0, 0),
(393, 101, 1, 44, 2, 1, 0, 0),
(394, 101, 2, 44, 8, 1, 0, 0),
(398, 102, 0, 44, 1, 1, 0, 0),
(399, 102, 1, 44, 2, 1, 0, 0),
(400, 102, 2, 44, 8, 1, 0, 0),
(404, 103, 0, 35, 1, 1, 0, 0),
(405, 103, 1, 35, 2, 1, 0, 0),
(406, 103, 2, 35, 8, 1, 0, 0),
(410, 104, 0, 44, 1, 1, 0, 0),
(411, 104, 1, 44, 2, 1, 0, 0),
(412, 104, 2, 44, 8, 1, 0, 0),
(416, 105, 0, 44, 1, 1, 0, 0),
(417, 105, 1, 44, 2, 1, 0, 0),
(418, 105, 2, 44, 8, 1, 0, 0),
(422, 106, 0, 44, 1, 1, 0, 0),
(423, 106, 1, 44, 2, 1, 0, 0),
(424, 106, 2, 44, 8, 1, 0, 0),
(428, 107, 0, 44, 1, 1, 0, 0),
(429, 107, 1, 44, 2, 1, 0, 0),
(430, 107, 2, 44, 8, 1, 0, 0),
(434, 108, 0, 44, 1, 1, 0, 0),
(435, 108, 1, 44, 2, 1, 0, 0),
(436, 108, 2, 44, 8, 1, 0, 0),
(440, 109, 0, 44, 1, 1, 0, 0),
(441, 109, 1, 44, 2, 1, 0, 0),
(442, 109, 2, 44, 8, 1, 0, 0),
(446, 110, 0, 44, 1, 1, 0, 0),
(447, 110, 1, 44, 2, 1, 0, 0),
(448, 110, 2, 44, 8, 1, 0, 0),
(452, 111, 0, 44, 1, 1, 0, 0),
(453, 111, 1, 44, 2, 1, 0, 0),
(454, 111, 2, 44, 8, 1, 0, 0),
(458, 112, 0, 44, 1, 1, 0, 0),
(459, 112, 1, 44, 2, 1, 0, 0),
(460, 112, 2, 44, 8, 1, 0, 0),
(464, 113, 0, 44, 1, 1, 0, 0),
(465, 113, 1, 44, 2, 1, 0, 0),
(466, 113, 2, 44, 8, 1, 0, 0),
(470, 114, 0, 44, 1, 1, 0, 0),
(471, 114, 1, 44, 2, 1, 0, 0),
(472, 114, 2, 44, 8, 1, 0, 0),
(476, 115, 0, 44, 1, 1, 0, 0),
(477, 115, 1, 44, 2, 1, 0, 0),
(478, 115, 2, 44, 8, 1, 0, 0),
(482, 116, 0, 44, 1, 1, 0, 0),
(483, 116, 1, 44, 2, 1, 0, 0),
(484, 116, 2, 44, 8, 1, 0, 0),
(488, 117, 0, 44, 1, 1, 0, 0),
(489, 117, 1, 44, 2, 1, 0, 0),
(490, 117, 2, 44, 8, 1, 0, 0),
(494, 118, 0, 44, 1, 1, 0, 0),
(495, 118, 1, 44, 2, 1, 0, 0),
(496, 118, 2, 44, 8, 1, 0, 0),
(500, 119, 0, 44, 1, 1, 0, 0),
(501, 119, 1, 44, 2, 1, 0, 0),
(502, 119, 2, 44, 8, 1, 0, 0),
(506, 120, 0, 44, 1, 1, 0, 0),
(507, 120, 1, 44, 2, 1, 0, 0),
(508, 120, 2, 44, 8, 1, 0, 0),
(512, 121, 0, 44, 1, 1, 0, 0),
(513, 121, 1, 44, 2, 1, 0, 0),
(514, 121, 2, 44, 8, 1, 0, 0),
(518, 122, 0, 44, 1, 1, 0, 0),
(519, 122, 1, 44, 2, 1, 0, 0),
(520, 122, 2, 44, 8, 1, 0, 0),
(524, 123, 0, 44, 1, 1, 0, 0),
(525, 123, 1, 44, 2, 1, 0, 0),
(526, 123, 2, 44, 8, 1, 0, 0),
(530, 124, 0, 44, 1, 1, 0, 0),
(531, 124, 1, 44, 2, 1, 0, 0),
(532, 124, 2, 44, 8, 1, 0, 0),
(536, 125, 0, 44, 1, 1, 0, 0),
(537, 125, 1, 44, 2, 1, 0, 0),
(538, 125, 2, 44, 8, 1, 0, 0),
(542, 126, 0, 44, 1, 1, 0, 0),
(543, 126, 1, 44, 2, 1, 0, 0),
(544, 126, 2, 44, 8, 1, 0, 0),
(548, 127, 0, 44, 1, 1, 0, 0),
(549, 127, 1, 44, 2, 1, 0, 0),
(550, 127, 2, 44, 8, 1, 0, 0),
(554, 128, 0, 44, 1, 1, 0, 0),
(555, 128, 1, 44, 2, 1, 0, 0),
(556, 128, 2, 44, 8, 1, 0, 0),
(560, 129, 0, 44, 1, 1, 0, 0),
(561, 129, 1, 44, 2, 1, 0, 0),
(562, 129, 2, 44, 8, 1, 0, 0),
(566, 130, 0, 44, 1, 1, 0, 0),
(567, 130, 1, 44, 2, 1, 0, 0),
(568, 130, 2, 44, 8, 1, 0, 0),
(572, 131, 0, 44, 1, 1, 0, 0),
(573, 131, 1, 44, 2, 1, 0, 0),
(574, 131, 2, 44, 8, 1, 0, 0),
(578, 132, 0, 44, 1, 1, 0, 0),
(579, 132, 1, 44, 2, 1, 0, 0),
(580, 132, 2, 44, 8, 1, 0, 0),
(584, 133, 0, 44, 1, 1, 0, 0),
(585, 133, 1, 44, 2, 1, 0, 0),
(586, 133, 2, 44, 8, 1, 0, 0),
(590, 134, 0, 44, 1, 1, 0, 0),
(591, 134, 1, 44, 2, 1, 0, 0),
(592, 134, 2, 44, 8, 1, 0, 0),
(596, 135, 0, 44, 1, 1, 0, 0),
(597, 135, 1, 44, 2, 1, 0, 0),
(598, 135, 2, 44, 8, 1, 0, 0),
(602, 136, 0, 44, 1, 1, 0, 0),
(603, 136, 1, 44, 2, 1, 0, 0),
(604, 136, 2, 44, 8, 1, 0, 0),
(608, 137, 0, 44, 1, 1, 0, 0),
(609, 137, 1, 44, 2, 1, 0, 0),
(610, 137, 2, 44, 8, 1, 0, 0),
(614, 138, 0, 44, 1, 1, 0, 0),
(615, 138, 1, 44, 2, 1, 0, 0),
(616, 138, 2, 44, 8, 1, 0, 0),
(620, 139, 0, 44, 1, 1, 0, 0),
(621, 139, 1, 44, 2, 1, 0, 0),
(622, 139, 2, 44, 8, 1, 0, 0),
(626, 140, 0, 35, 1, 1, 0, 0),
(627, 140, 1, 35, 2, 1, 0, 0),
(628, 140, 2, 35, 8, 1, 0, 0),
(632, 141, 0, 35, 1, 1, 0, 0),
(633, 141, 1, 35, 2, 1, 0, 0),
(634, 141, 2, 35, 8, 1, 0, 0),
(638, 142, 0, 34, 1, 1, 0, 0),
(639, 142, 1, 34, 2, 1, 0, 0),
(640, 142, 2, 34, 8, 1, 0, 0),
(644, 143, 0, 34, 1, 1, 0, 0),
(645, 143, 1, 34, 2, 1, 0, 0),
(646, 143, 2, 34, 8, 1, 0, 0),
(650, 144, 0, 34, 1, 1, 0, 0),
(651, 144, 1, 34, 2, 1, 0, 0),
(652, 144, 2, 34, 8, 1, 0, 0),
(656, 145, 0, 34, 1, 1, 0, 0),
(657, 145, 1, 34, 2, 1, 0, 0),
(658, 145, 2, 34, 8, 1, 0, 0),
(662, 146, 0, 34, 1, 1, 0, 0),
(663, 146, 1, 34, 2, 1, 0, 0),
(664, 146, 2, 34, 8, 1, 0, 0),
(668, 147, 0, 34, 1, 1, 0, 0),
(669, 147, 1, 34, 2, 1, 0, 0),
(670, 147, 2, 34, 8, 1, 0, 0),
(674, 148, 0, 44, 1, 1, 0, 0),
(675, 148, 1, 44, 2, 1, 0, 0),
(676, 148, 2, 44, 8, 1, 0, 0),
(680, 149, 0, 39, 1, 1, 0, 0),
(681, 149, 1, 39, 2, 1, 0, 0),
(682, 149, 2, 39, 8, 1, 0, 0),
(686, 150, 0, 39, 1, 1, 0, 0),
(687, 150, 1, 39, 2, 1, 0, 0),
(688, 150, 2, 39, 8, 1, 0, 0),
(692, 151, 0, 39, 1, 1, 0, 0),
(693, 151, 1, 39, 2, 1, 0, 0),
(694, 151, 2, 39, 8, 1, 0, 0),
(698, 152, 0, 39, 1, 1, 0, 0),
(699, 152, 1, 39, 2, 1, 0, 0),
(700, 152, 2, 39, 8, 1, 0, 0),
(704, 153, 0, 39, 1, 1, 0, 0),
(705, 153, 1, 39, 2, 1, 0, 0),
(706, 153, 2, 39, 8, 1, 0, 0),
(710, 154, 0, 44, 1, 1, 0, 0),
(711, 154, 1, 44, 2, 1, 0, 0),
(712, 154, 2, 44, 8, 1, 0, 0),
(716, 155, 0, 44, 1, 1, 0, 0),
(717, 155, 1, 44, 2, 1, 0, 0),
(718, 155, 2, 44, 8, 1, 0, 0),
(722, 156, 0, 44, 1, 1, 0, 0),
(723, 156, 1, 44, 2, 1, 0, 0),
(724, 156, 2, 44, 8, 1, 0, 0),
(728, 157, 0, 44, 1, 1, 0, 0),
(729, 157, 1, 44, 2, 1, 0, 0),
(730, 157, 2, 44, 8, 1, 0, 0),
(734, 158, 0, 44, 1, 1, 0, 0),
(735, 158, 1, 44, 2, 1, 0, 0),
(736, 158, 2, 44, 8, 1, 0, 0),
(740, 159, 0, 44, 1, 1, 0, 0),
(741, 159, 1, 44, 2, 1, 0, 0),
(742, 159, 2, 44, 8, 1, 0, 0),
(746, 160, 0, 44, 1, 1, 0, 0),
(747, 160, 1, 44, 2, 1, 0, 0),
(748, 160, 2, 44, 8, 1, 0, 0),
(752, 161, 0, 44, 1, 1, 0, 0),
(753, 161, 1, 44, 2, 1, 0, 0),
(754, 161, 2, 44, 8, 1, 0, 0),
(758, 162, 0, 44, 1, 1, 0, 0),
(759, 162, 1, 44, 2, 1, 0, 0),
(760, 162, 2, 44, 8, 1, 0, 0),
(764, 163, 0, 44, 1, 1, 0, 0),
(765, 163, 1, 44, 2, 1, 0, 0),
(766, 163, 2, 44, 8, 1, 0, 0),
(770, 164, 0, 44, 1, 1, 0, 0),
(771, 164, 1, 44, 2, 1, 0, 0),
(772, 164, 2, 44, 8, 1, 0, 0),
(776, 165, 0, 44, 1, 1, 0, 0),
(777, 165, 1, 44, 2, 1, 0, 0),
(778, 165, 2, 44, 8, 1, 0, 0),
(782, 166, 0, 44, 1, 1, 0, 0),
(783, 166, 1, 44, 2, 1, 0, 0),
(784, 166, 2, 44, 8, 1, 0, 0),
(788, 167, 0, 44, 1, 1, 0, 0),
(789, 167, 1, 44, 2, 1, 0, 0),
(790, 167, 2, 44, 8, 1, 0, 0),
(794, 168, 0, 44, 1, 1, 0, 0),
(795, 168, 1, 44, 2, 1, 0, 0),
(796, 168, 2, 44, 8, 1, 0, 0),
(800, 169, 0, 44, 1, 1, 0, 0),
(801, 169, 1, 44, 2, 1, 0, 0),
(802, 169, 2, 44, 8, 1, 0, 0),
(806, 170, 0, 44, 1, 1, 0, 0),
(807, 170, 1, 44, 2, 1, 0, 0),
(808, 170, 2, 44, 8, 1, 0, 0),
(812, 171, 0, 44, 1, 1, 0, 0),
(813, 171, 1, 44, 2, 1, 0, 0),
(814, 171, 2, 44, 8, 1, 0, 0),
(818, 172, 0, 44, 1, 1, 0, 0),
(819, 172, 1, 44, 2, 1, 0, 0),
(820, 172, 2, 44, 8, 1, 0, 0),
(824, 173, 0, 44, 1, 1, 0, 0),
(825, 173, 1, 44, 2, 1, 0, 0),
(826, 173, 2, 44, 8, 1, 0, 0),
(830, 174, 0, 34, 1, 1, 0, 0),
(831, 174, 1, 34, 2, 1, 0, 0),
(832, 174, 2, 34, 8, 1, 0, 0),
(836, 175, 0, 34, 1, 1, 0, 0),
(837, 175, 1, 34, 2, 1, 0, 0),
(838, 175, 2, 34, 8, 1, 0, 0),
(842, 176, 0, 44, 1, 1, 0, 0),
(843, 176, 1, 44, 2, 1, 0, 0),
(844, 176, 2, 44, 8, 1, 0, 0),
(848, 177, 0, 44, 1, 1, 0, 0),
(849, 177, 1, 44, 2, 1, 0, 0),
(850, 177, 2, 44, 8, 1, 0, 0),
(854, 178, 0, 44, 1, 1, 0, 0),
(855, 178, 1, 44, 2, 1, 0, 0),
(856, 178, 2, 44, 8, 1, 0, 0),
(860, 179, 0, 34, 1, 1, 0, 0),
(861, 179, 1, 34, 2, 1, 0, 0),
(862, 179, 2, 34, 8, 1, 0, 0),
(866, 180, 0, 34, 1, 1, 0, 0),
(867, 180, 1, 34, 2, 1, 0, 0),
(868, 180, 2, 34, 8, 1, 0, 0),
(872, 181, 0, 34, 1, 1, 0, 0),
(873, 181, 1, 34, 2, 1, 0, 0),
(874, 181, 2, 34, 8, 1, 0, 0),
(878, 182, 0, 34, 1, 1, 0, 0),
(879, 182, 1, 34, 2, 1, 0, 0),
(880, 182, 2, 34, 8, 1, 0, 0),
(884, 183, 0, 34, 1, 1, 0, 0),
(885, 183, 1, 34, 2, 1, 0, 0),
(886, 183, 2, 34, 8, 1, 0, 0),
(890, 184, 0, 34, 1, 1, 0, 0),
(891, 184, 1, 34, 2, 1, 0, 0),
(892, 184, 2, 34, 8, 1, 0, 0),
(896, 185, 0, 34, 1, 1, 0, 0),
(897, 185, 1, 34, 2, 1, 0, 0),
(898, 185, 2, 34, 8, 1, 0, 0),
(902, 186, 0, 44, 1, 1, 0, 0),
(903, 186, 1, 44, 2, 1, 0, 0),
(904, 186, 2, 44, 8, 1, 0, 0),
(908, 187, 0, 44, 1, 1, 0, 0),
(909, 187, 1, 44, 2, 1, 0, 0),
(910, 187, 2, 44, 8, 1, 0, 0),
(914, 188, 0, 44, 1, 1, 0, 0),
(915, 188, 1, 44, 2, 1, 0, 0),
(916, 188, 2, 44, 8, 1, 0, 0),
(920, 189, 0, 44, 1, 1, 0, 0),
(921, 189, 1, 44, 2, 1, 0, 0),
(922, 189, 2, 44, 8, 1, 0, 0),
(926, 190, 0, 44, 1, 1, 0, 0),
(927, 190, 1, 44, 2, 1, 0, 0),
(928, 190, 2, 44, 8, 1, 0, 0),
(932, 191, 0, 44, 1, 1, 0, 0),
(933, 191, 1, 44, 2, 1, 0, 0),
(934, 191, 2, 44, 8, 1, 0, 0),
(938, 192, 0, 44, 1, 1, 0, 0),
(939, 192, 1, 44, 2, 1, 0, 0),
(940, 192, 2, 44, 8, 1, 0, 0),
(944, 193, 0, 44, 1, 1, 0, 0),
(945, 193, 1, 44, 2, 1, 0, 0),
(946, 193, 2, 44, 8, 1, 0, 0),
(950, 194, 0, 34, 1, 1, 0, 0),
(951, 194, 1, 34, 2, 1, 0, 0),
(952, 194, 2, 34, 8, 1, 0, 0),
(956, 195, 0, 44, 1, 1, 0, 0),
(957, 195, 1, 44, 2, 1, 0, 0),
(958, 195, 2, 44, 8, 1, 0, 0),
(962, 196, 0, 44, 1, 1, 0, 0),
(963, 196, 1, 44, 2, 1, 0, 0),
(964, 196, 2, 44, 8, 1, 0, 0);

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

--
-- Dumping data for table `acl_object_identity`
--

INSERT INTO `acl_object_identity` (`id`, `object_id_class`, `object_id_identity`, `parent_object`, `owner_sid`, `entries_inheriting`) VALUES
(10, 4, 1, NULL, 4, 0),
(45, 4, 7, NULL, 4, 1),
(46, 4, 8, NULL, 4, 1),
(57, 4, 12, NULL, 4, 1),
(60, 4, 14, NULL, 4, 1),
(64, 4, 15, NULL, 4, 1),
(65, 4, 18, NULL, 4, 1),
(66, 10, 1, NULL, 4, 1),
(67, 10, 2, NULL, 4, 1),
(68, 10, 3, NULL, 4, 1),
(69, 11, 1, NULL, 35, 1),
(70, 11, 5, NULL, 35, 1),
(71, 11, 6, NULL, 35, 1),
(72, 11, 7, NULL, 35, 1),
(73, 10, 4, NULL, 35, 1),
(74, 10, 5, NULL, 35, 1),
(75, 11, 8, NULL, 35, 1),
(76, 12, 1, NULL, 35, 1),
(77, 12, 3, NULL, 35, 1),
(78, 11, 10, NULL, 35, 1),
(79, 12, 4, NULL, 35, 1),
(80, 12, 6, NULL, 35, 1),
(81, 12, 7, NULL, 35, 1),
(82, 12, 8, NULL, 35, 1),
(83, 12, 9, NULL, 35, 1),
(85, 12, 12, NULL, 35, 1),
(86, 12, 13, NULL, 35, 1),
(87, 12, 14, NULL, 35, 1),
(88, 12, 16, NULL, 39, 1),
(89, 12, 18, NULL, 39, 1),
(90, 11, 12, NULL, 35, 1),
(91, 12, 22, NULL, 35, 1),
(92, 12, 24, NULL, 39, 1),
(93, 12, 26, NULL, 39, 1),
(94, 12, 27, NULL, 39, 1),
(95, 4, 19, NULL, 4, 1),
(96, 12, 28, NULL, 44, 1),
(97, 12, 29, NULL, 44, 1),
(98, 12, 30, NULL, 44, 1),
(99, 12, 32, NULL, 44, 1),
(100, 12, 34, NULL, 44, 1),
(101, 12, 35, NULL, 44, 1),
(102, 12, 37, NULL, 44, 1),
(103, 12, 38, NULL, 35, 1),
(104, 12, 39, NULL, 44, 1),
(105, 12, 40, NULL, 44, 1),
(106, 12, 41, NULL, 44, 1),
(107, 12, 42, NULL, 44, 1),
(108, 12, 43, NULL, 44, 1),
(109, 12, 44, NULL, 44, 1),
(110, 12, 45, NULL, 44, 1),
(111, 12, 47, NULL, 44, 1),
(112, 12, 48, NULL, 44, 1),
(113, 12, 49, NULL, 44, 1),
(114, 12, 50, NULL, 44, 1),
(115, 12, 51, NULL, 44, 1),
(116, 12, 52, NULL, 44, 1),
(117, 12, 53, NULL, 44, 1),
(118, 12, 54, NULL, 44, 1),
(119, 12, 55, NULL, 44, 1),
(120, 12, 56, NULL, 44, 1),
(121, 12, 57, NULL, 44, 1),
(122, 12, 58, NULL, 44, 1),
(123, 12, 59, NULL, 44, 1),
(124, 12, 60, NULL, 44, 1),
(125, 12, 61, NULL, 44, 1),
(126, 12, 62, NULL, 44, 1),
(127, 12, 63, NULL, 44, 1),
(128, 12, 64, NULL, 44, 1),
(129, 12, 65, NULL, 44, 1),
(130, 12, 66, NULL, 44, 1),
(131, 12, 67, NULL, 44, 1),
(132, 12, 68, NULL, 44, 1),
(133, 12, 69, NULL, 44, 1),
(134, 12, 70, NULL, 44, 1),
(135, 12, 71, NULL, 44, 1),
(136, 12, 72, NULL, 44, 1),
(137, 12, 73, NULL, 44, 1),
(138, 12, 74, NULL, 44, 1),
(139, 12, 75, NULL, 44, 1),
(140, 11, 13, NULL, 35, 1),
(141, 11, 14, NULL, 35, 1),
(142, 12, 77, NULL, 34, 1),
(143, 12, 78, NULL, 34, 1),
(144, 12, 79, NULL, 34, 1),
(145, 12, 80, NULL, 34, 1),
(146, 12, 81, NULL, 34, 1),
(147, 12, 82, NULL, 34, 1),
(148, 12, 84, NULL, 44, 1),
(149, 11, 15, NULL, 39, 1),
(150, 11, 16, NULL, 39, 1),
(151, 11, 17, NULL, 39, 1),
(152, 11, 18, NULL, 39, 1),
(153, 11, 19, NULL, 39, 1),
(154, 12, 85, NULL, 44, 1),
(155, 12, 86, NULL, 44, 1),
(156, 11, 20, NULL, 44, 1),
(157, 11, 21, NULL, 44, 1),
(158, 11, 22, NULL, 44, 1),
(159, 11, 23, NULL, 44, 1),
(160, 11, 24, NULL, 44, 1),
(161, 11, 25, NULL, 44, 1),
(162, 11, 26, NULL, 44, 1),
(163, 11, 27, NULL, 44, 1),
(164, 11, 28, NULL, 44, 1),
(165, 11, 29, NULL, 44, 1),
(166, 12, 99, NULL, 44, 1),
(167, 12, 100, NULL, 44, 1),
(168, 11, 30, NULL, 44, 1),
(169, 12, 101, NULL, 44, 1),
(170, 11, 31, NULL, 44, 1),
(171, 12, 102, NULL, 44, 1),
(172, 11, 32, NULL, 44, 1),
(173, 12, 103, NULL, 44, 1),
(174, 12, 104, NULL, 34, 1),
(175, 12, 105, NULL, 34, 1),
(176, 11, 37, NULL, 44, 1),
(177, 12, 106, NULL, 44, 1),
(178, 12, 107, NULL, 44, 1),
(179, 12, 108, NULL, 34, 1),
(180, 12, 109, NULL, 34, 1),
(181, 12, 110, NULL, 34, 1),
(182, 12, 111, NULL, 34, 1),
(183, 12, 112, NULL, 34, 1),
(184, 12, 113, NULL, 34, 1),
(185, 12, 114, NULL, 34, 1),
(186, 11, 38, NULL, 44, 1),
(187, 11, 39, NULL, 44, 1),
(188, 11, 40, NULL, 44, 1),
(189, 11, 41, NULL, 44, 1),
(190, 11, 42, NULL, 44, 1),
(191, 11, 43, NULL, 44, 1),
(192, 11, 44, NULL, 44, 1),
(193, 11, 45, NULL, 44, 1),
(194, 12, 115, NULL, 34, 1),
(195, 11, 46, NULL, 44, 1),
(196, 12, 116, NULL, 44, 1);

-- --------------------------------------------------------

--
-- Table structure for table `acl_sid`
--

CREATE TABLE IF NOT EXISTS `acl_sid` (
`id` bigint(20) NOT NULL,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `acl_sid`
--

INSERT INTO `acl_sid` (`id`, `principal`, `sid`) VALUES
(39, 1, 'Admin'),
(43, 1, 'BrokenTest'),
(4, 1, 'Root'),
(41, 1, 'TaskManagerDemo'),
(42, 1, 'taskUser'),
(44, 1, 'test1'),
(35, 1, 'User'),
(34, 1, 'Visitor');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
`id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`id`, `username`, `authority`) VALUES
(1, 'Admin', 'ROLE_ADMIN'),
(7, 'BrokenTest', 'ROLE_USER'),
(2, 'Root', 'ROLE_ROOT'),
(3, 'TaskManagerDemo', 'ROLE_USER'),
(4, 'taskUser', 'ROLE_USER'),
(8, 'test1', 'ROLE_USER'),
(5, 'User', 'ROLE_USER'),
(6, 'Visitor', 'ROLE_VISITOR');

-- --------------------------------------------------------

--
-- Table structure for table `forms`
--

CREATE TABLE IF NOT EXISTS `forms` (
`id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT 'UNTITLED',
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `forms`
--

INSERT INTO `forms` (`id`, `name`, `insertion_date`) VALUES
(46, 'One Test Form', '2015-01-26 09:01:52');

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

--
-- Dumping data for table `form_responses`
--

INSERT INTO `form_responses` (`id`, `form_id`, `owner_id`, `insertion_date`, `latest_update`, `is_complete`, `document_folder`) VALUES
(116, 46, 1, '2015-01-26 09:02:13', '2015-01-26 09:02:13', 0, NULL);

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

--
-- Dumping data for table `form_response_entries`
--

INSERT INTO `form_response_entries` (`entry_id`, `form_response_id`, `question_id`, `label`, `value`) VALUES
(2341, 116, 304, 'Text Input', 'And a reponse!'),
(2342, 116, 304, 'Text Input', 'And a reponse!');

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

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `enabled`, `id`) VALUES
('Root', 'test', 1, 1),
('Visitor', 'test', 1, 7),
('User', 'test', 1, 8),
('Admin', 'test', 1, 12),
('TaskManagerDemo', 'test', 1, 14),
('taskUser', 'test', 1, 15),
('BrokenTest', 'test', 1, 18),
('test1', 'ca11abc8fa2994ab4a0bb40887afdf8b421542b45787d211269bd146cd229170d857d5c7c76856bafbc6e2e8debef019ad0cd8792ee1a481ff09a494ed709de4', 1, 19);

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

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `form_id`, `component`, `editable`, `form_index`, `label`, `description`, `placeholder`, `required`, `validation`, `options`) VALUES
(304, 46, 'textInput', 1, 0, 'Text Input', 'description', 'placeholder', 0, '0', '[]');

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
-- Dumping data for table `user_data`
--

INSERT INTO `user_data` (`username`, `id`, `firstName`, `lastName`, `city`, `homePhone`, `cellPhone`, `email`, `picture`, `insertion_date`) VALUES
('Root', 1, '', '', NULL, NULL, NULL, NULL, NULL, '2014-07-09 16:41:51'),
('Visitor', 7, 'Client', 'Device', NULL, NULL, NULL, NULL, NULL, '2014-07-18 12:12:54'),
('User', 8, 'Demo', 'ofUser', NULL, NULL, NULL, NULL, NULL, '2014-07-18 12:14:26'),
('Admin', 12, 'Demo', 'ofAdmin', NULL, NULL, NULL, NULL, NULL, '2014-07-24 10:38:34'),
('TaskManagerDemo', 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2014-07-29 16:17:31'),
('taskUser', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2014-08-01 12:23:02'),
('BrokenTest', 18, 'first', 'last', 'houston', '2818888208', '1234567890', 'test@gmail.com', NULL, '2014-10-22 21:26:16'),
('test1', 19, 'test', 'test', NULL, NULL, NULL, NULL, NULL, '2015-01-15 21:40:22');

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
