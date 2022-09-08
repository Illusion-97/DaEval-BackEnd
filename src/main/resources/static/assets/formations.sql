-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : jeu. 16 juin 2022 à 08:48
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formations`
--

-- --------------------------------------------------------

--
-- Structure de la table `bloc_competences`
--

DROP TABLE IF EXISTS `bloc_competences`;
CREATE TABLE IF NOT EXISTS `bloc_competences` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `titre_professionnel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdesf2t5voe73i0op0ub0l0h9h` (`titre_professionnel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bloc_competences`
--

INSERT INTO `bloc_competences` (`id`, `description`, `titre`, `version`, `titre_professionnel_id`) VALUES
(1, 'LEGO', 'MINECRAFT (Bloc 1)', 0, 1),
(2, 'LEGO LEGO', 'MINECRAFT (Bloc 2)', 0, 1),
(3, 'LEGO LEGO LEGO', 'MINECRAFT (Bloc 3)', 1, 3),
(4, 'MINECRAFT', 'MINECRAFT (Bloc 4)', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

DROP TABLE IF EXISTS `competence`;
CREATE TABLE IF NOT EXISTS `competence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `bloc_competences_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK19jbnhide0qnvv8u9bwiyan03` (`bloc_competences_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`id`, `description`, `titre`, `version`, `bloc_competences_id`) VALUES
(1, 'Scalp Zombies', 'Scalp Zombies', 0, 1),
(2, 'Grind Diamond', 'Grind Diamond', 0, 2),
(3, 'LEGO LEGO LEGO', 'Un Nouveau Bloc', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `epreuve`
--

DROP TABLE IF EXISTS `epreuve`;
CREATE TABLE IF NOT EXISTS `epreuve` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `bloc_competences_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKao3iv01ss5630y71def77tl62` (`bloc_competences_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `epreuve`
--

INSERT INTO `epreuve` (`id`, `description`, `titre`, `type`, `version`, `bloc_competences_id`) VALUES
(1, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 1),
(2, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 1),
(3, 'UNE', 'Une Nouvelle Epreuve', 1, 0, 1),
(4, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 2),
(5, 'UNE', 'Une Nouvelle Epreuve', 1, 0, 2),
(6, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 2),
(7, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 3),
(8, 'UNE', 'Une Nouvelle Epreuve', 1, 0, 3),
(9, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 3),
(10, 'UNE', 'Une Nouvelle Epreuve', 0, 0, 4),
(11, 'UNE', 'Une Nouvelle Epreuve', 1, 0, 4);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk1k5fhsjak7v0uk031i5geqj9` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `version`, `utilisateur_id`) VALUES
(2, 1, 6),
(3, 1, 7),
(11, 1, 15),
(12, 0, 17),
(13, 8, 19),
(14, 1, 20);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_promotions`
--

DROP TABLE IF EXISTS `etudiant_promotions`;
CREATE TABLE IF NOT EXISTS `etudiant_promotions` (
  `etudiants_id` bigint(20) NOT NULL,
  `promotions_id` bigint(20) NOT NULL,
  KEY `FKfyy22nh9lj0rwxa85ltiq3es0` (`promotions_id`),
  KEY `FKg5vs91jnxuqc9c395lhekes89` (`etudiants_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant_promotions`
--

INSERT INTO `etudiant_promotions` (`etudiants_id`, `promotions_id`) VALUES
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(11, 1),
(11, 2),
(13, 1),
(13, 2),
(14, 1),
(14, 2);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `note` double NOT NULL,
  `version` int(11) NOT NULL,
  `epreuve_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhurd0ycs98i7fc3jmk76rjsvy` (`epreuve_id`),
  KEY `FKplr8nstfplr2tfglvcb8kyelc` (`etudiant_id`),
  KEY `FKmqlqxgh4i7owgulrjvida40xo` (`promotion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id`, `note`, `version`, `epreuve_id`, `etudiant_id`, `promotion_id`) VALUES
(221, 4.29, 0, 1, 2, 2),
(222, 3.49, 0, 1, 3, 2),
(223, 1.12, 0, 1, 11, 1),
(224, 14.57, 0, 1, 13, 1),
(225, 7.4, 0, 1, 14, 2),
(226, 7.14, 0, 1, 2, 2),
(227, 15.5, 0, 1, 3, 1),
(228, 5.38, 0, 1, 11, 1),
(229, 8.03, 0, 1, 13, 1),
(230, 13.41, 0, 1, 14, 1),
(231, 14.97, 0, 1, 2, 2),
(232, 0.39, 0, 1, 3, 1),
(233, 9.02, 0, 1, 11, 1),
(234, 1.05, 0, 1, 13, 1),
(235, 11, 0, 1, 14, 1),
(236, 16.02, 0, 1, 2, 2),
(237, 11.28, 0, 1, 3, 1),
(238, 12.86, 0, 1, 11, 1),
(239, 5.42, 0, 1, 13, 1),
(240, 19.46, 0, 1, 14, 1),
(241, 13.51, 0, 2, 2, 1),
(242, 14.59, 0, 2, 3, 2),
(243, 7.96, 0, 2, 11, 2),
(244, 8.54, 0, 2, 13, 2),
(245, 6.62, 0, 2, 14, 1),
(246, 10.45, 0, 2, 2, 2),
(247, 11.47, 0, 2, 3, 2),
(248, 17.04, 0, 2, 11, 1),
(249, 17.69, 0, 2, 13, 2),
(250, 9.65, 0, 2, 14, 2),
(251, 5.91, 0, 2, 2, 1),
(252, 19.37, 0, 2, 3, 1),
(253, 9.16, 0, 2, 11, 2),
(254, 7.28, 0, 2, 13, 1),
(255, 9.25, 0, 2, 14, 2),
(256, 6.73, 0, 2, 2, 1),
(257, 16.94, 0, 2, 3, 1),
(258, 18.91, 0, 2, 11, 1),
(259, 16.42, 0, 2, 13, 1),
(260, 13.01, 0, 2, 14, 2),
(261, 8.47, 0, 3, 2, 1),
(262, 5.6, 0, 3, 3, 2),
(263, 6.95, 0, 3, 11, 2),
(264, 6.21, 0, 3, 13, 1),
(265, 9.45, 0, 3, 14, 2),
(266, 16.8, 0, 3, 2, 2),
(267, 16.8, 0, 3, 3, 1),
(268, 6.28, 0, 3, 11, 1),
(269, 4.32, 0, 3, 13, 2),
(270, 14.79, 0, 3, 14, 1),
(271, 4.56, 0, 3, 2, 1),
(272, 1.97, 0, 3, 3, 1),
(273, 8.22, 0, 3, 11, 1),
(274, 12.15, 0, 3, 13, 1),
(275, 13.26, 0, 3, 14, 2),
(276, 5.93, 0, 3, 2, 2),
(277, 1.21, 0, 3, 3, 1),
(278, 12.07, 0, 3, 11, 1),
(279, 8.04, 0, 3, 13, 1),
(280, 19.24, 0, 3, 14, 1),
(281, 12.29, 0, 4, 2, 1),
(282, 11.54, 0, 4, 3, 1),
(283, 3.35, 0, 4, 11, 2),
(284, 2.39, 0, 4, 13, 1),
(285, 1.54, 0, 4, 14, 2),
(286, 3.51, 0, 4, 2, 1),
(287, 8.16, 0, 4, 3, 1),
(288, 1.89, 0, 4, 11, 2),
(289, 10.2, 0, 4, 13, 1),
(290, 4.91, 0, 4, 14, 2),
(291, 13.66, 0, 4, 2, 1),
(292, 8.27, 0, 4, 3, 2),
(293, 12.45, 0, 4, 11, 1),
(294, 2.03, 0, 4, 13, 2),
(295, 9.08, 0, 4, 14, 2),
(296, 3.58, 0, 4, 2, 1),
(297, 5.29, 0, 4, 3, 2),
(298, 0.05, 0, 4, 11, 1),
(299, 1.16, 0, 4, 13, 1),
(300, 14, 0, 4, 14, 1),
(301, 15.54, 0, 5, 2, 2),
(302, 3.91, 0, 5, 3, 1),
(303, 8.89, 0, 5, 11, 1),
(304, 2.43, 0, 5, 13, 2),
(305, 8.53, 0, 5, 14, 1),
(306, 4.11, 0, 5, 2, 2),
(307, 0.75, 0, 5, 3, 2),
(308, 15.93, 0, 5, 11, 1),
(309, 9.8, 0, 5, 13, 1),
(310, 2.02, 0, 5, 14, 1),
(311, 18.18, 0, 5, 2, 1),
(312, 14.33, 0, 5, 3, 2),
(313, 14.89, 0, 5, 11, 1),
(314, 1.7, 0, 5, 13, 1),
(315, 18.66, 0, 5, 14, 2),
(316, 1.79, 0, 5, 2, 2),
(317, 16.89, 0, 5, 3, 1),
(318, 9.26, 0, 5, 11, 2),
(319, 11.8, 0, 5, 13, 2),
(320, 13.72, 0, 5, 14, 2),
(321, 11.61, 0, 6, 2, 1),
(322, 16.32, 0, 6, 3, 1),
(323, 5.39, 0, 6, 11, 1),
(324, 18.28, 0, 6, 13, 2),
(325, 19.47, 0, 6, 14, 2),
(326, 4.03, 0, 6, 2, 1),
(327, 15.4, 0, 6, 3, 1),
(328, 18.32, 0, 6, 11, 1),
(329, 14.81, 0, 6, 13, 2),
(330, 8.48, 0, 6, 14, 2),
(331, 7.47, 0, 6, 2, 2),
(332, 13.92, 0, 6, 3, 2),
(333, 10.88, 0, 6, 11, 2),
(334, 3.47, 0, 6, 13, 1),
(335, 12.8, 0, 6, 14, 1),
(336, 16.88, 0, 6, 2, 2),
(337, 2.19, 0, 6, 3, 2),
(338, 19.68, 0, 6, 11, 2),
(339, 6.34, 0, 6, 13, 1),
(340, 3.69, 0, 6, 14, 2),
(341, 4.95, 0, 7, 2, 1),
(342, 13.91, 0, 7, 3, 2),
(343, 18.84, 0, 7, 11, 2),
(344, 4.97, 0, 7, 13, 2),
(345, 7, 0, 7, 14, 2),
(346, 6.6, 0, 7, 2, 2),
(347, 17.42, 0, 7, 3, 1),
(348, 12.93, 0, 7, 11, 2),
(349, 13.98, 0, 7, 13, 2),
(350, 11.35, 0, 7, 14, 1),
(351, 0.98, 0, 7, 2, 2),
(352, 9.61, 0, 7, 3, 1),
(353, 17.56, 0, 7, 11, 1),
(354, 19.11, 0, 7, 13, 2),
(355, 19.89, 0, 7, 14, 2),
(356, 4.67, 0, 7, 2, 1),
(357, 1.84, 0, 7, 3, 1),
(358, 5.05, 0, 7, 11, 1),
(359, 0.69, 0, 7, 13, 2),
(360, 11.65, 0, 7, 14, 1),
(361, 6.03, 0, 8, 2, 2),
(362, 5.79, 0, 8, 3, 2),
(363, 7.24, 0, 8, 11, 1),
(364, 16.03, 0, 8, 13, 1),
(365, 0.71, 0, 8, 14, 2),
(366, 5.72, 0, 8, 2, 1),
(367, 13.53, 0, 8, 3, 1),
(368, 15.21, 0, 8, 11, 1),
(369, 15.03, 0, 8, 13, 1),
(370, 3.7, 0, 8, 14, 2),
(371, 2.13, 0, 8, 2, 2),
(372, 19.26, 0, 8, 3, 1),
(373, 14.38, 0, 8, 11, 1),
(374, 9.2, 0, 8, 13, 2),
(375, 13.03, 0, 8, 14, 1),
(376, 1.47, 0, 8, 2, 2),
(377, 5.88, 0, 8, 3, 1),
(378, 2.92, 0, 8, 11, 2),
(379, 2.62, 0, 8, 13, 1),
(380, 5.86, 0, 8, 14, 2),
(381, 11.97, 0, 9, 2, 1),
(382, 19.63, 0, 9, 3, 1),
(383, 1.61, 0, 9, 11, 1),
(384, 11.13, 0, 9, 13, 2),
(385, 0.09, 0, 9, 14, 2),
(386, 4.62, 0, 9, 2, 1),
(387, 5.64, 0, 9, 3, 1),
(388, 4.5, 0, 9, 11, 2),
(389, 5.7, 0, 9, 13, 2),
(390, 5.15, 0, 9, 14, 1),
(391, 6.47, 0, 9, 2, 2),
(392, 5.13, 0, 9, 3, 2),
(393, 14.38, 0, 9, 11, 2),
(394, 6.76, 0, 9, 13, 1),
(395, 4.36, 0, 9, 14, 1),
(396, 2.14, 0, 9, 2, 2),
(397, 17.94, 0, 9, 3, 2),
(398, 12.39, 0, 9, 11, 1),
(399, 11.11, 0, 9, 13, 2),
(400, 19.04, 0, 9, 14, 2),
(401, 17.34, 0, 10, 2, 2),
(402, 13.32, 0, 10, 3, 1),
(403, 17.28, 0, 10, 11, 1),
(404, 3.37, 0, 10, 13, 1),
(405, 9.96, 0, 10, 14, 1),
(406, 9.3, 0, 10, 2, 1),
(407, 13.38, 0, 10, 3, 1),
(408, 17.17, 0, 10, 11, 1),
(409, 7.42, 0, 10, 13, 2),
(410, 6.34, 0, 10, 14, 2),
(411, 8.36, 0, 10, 2, 2),
(412, 12.8, 0, 10, 3, 1),
(413, 12.72, 0, 10, 11, 2),
(414, 4.71, 0, 10, 13, 1),
(415, 3.36, 0, 10, 14, 2),
(416, 2.14, 0, 10, 2, 1),
(417, 6.14, 0, 10, 3, 2),
(418, 2.32, 0, 10, 11, 2),
(419, 5.91, 0, 10, 13, 2),
(420, 6.06, 0, 10, 14, 1),
(421, 17.14, 0, 11, 2, 1),
(422, 0.37, 0, 11, 3, 2),
(423, 8.6, 0, 11, 11, 2),
(424, 16.97, 0, 11, 13, 1),
(425, 9.7, 0, 11, 14, 2),
(426, 9.42, 0, 11, 2, 2),
(427, 17.22, 0, 11, 3, 2),
(428, 7.76, 0, 11, 11, 1),
(429, 7.27, 0, 11, 13, 2),
(430, 11.64, 0, 11, 14, 1),
(431, 5.28, 0, 11, 2, 2),
(432, 18.35, 0, 11, 3, 1),
(433, 7.86, 0, 11, 11, 1),
(434, 19.05, 0, 11, 13, 1),
(435, 3.88, 0, 11, 14, 1),
(436, 16.66, 0, 11, 2, 1),
(437, 10.5, 0, 11, 3, 2),
(438, 18.56, 0, 11, 11, 1),
(439, 9.77, 0, 11, 13, 2),
(440, 17.42, 0, 11, 14, 1);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `version` int(11) NOT NULL,
  `titre_professionnel_id` bigint(20) DEFAULT NULL,
  `ville_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5rlh3nu3d7xdtom8jbtpjgote` (`titre_professionnel_id`),
  KEY `FK7mmafxoxs1tr0dksu7lqv03as` (`ville_id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `date_debut`, `date_fin`, `version`, `titre_professionnel_id`, `ville_id`) VALUES
(1, '2022-10-03', '2023-09-29', 0, 1, 1),
(2, '2022-10-03', '2023-09-29', 0, 1, 5),
(3, '2022-10-03', '2023-09-29', 0, 1, 7),
(4, '2022-10-03', '2023-09-29', 0, 1, 8),
(5, '2022-10-03', '2023-09-29', 0, 1, 9),
(6, '2022-09-01', '2023-08-31', 0, 2, 1),
(7, '2022-09-01', '2023-08-31', 0, 2, 3),
(8, '2022-09-01', '2023-08-31', 0, 2, 5),
(9, '2022-09-01', '2023-08-31', 0, 2, 7),
(10, '2022-09-01', '2023-08-31', 0, 2, 8),
(11, '2022-10-01', '2023-09-30', 0, 3, 1),
(12, '2022-10-01', '2023-09-30', 0, 3, 5),
(13, '2022-10-01', '2023-09-30', 0, 3, 7),
(14, '2022-10-01', '2023-09-30', 0, 3, 8),
(15, '2022-10-01', '2023-09-30', 0, 3, 9),
(16, '2022-09-01', '2023-08-31', 0, 4, 1),
(17, '2022-09-01', '2023-08-31', 0, 4, 5),
(18, '2022-09-01', '2023-08-31', 0, 4, 7),
(19, '2022-09-01', '2023-08-31', 0, 4, 8),
(20, '2022-09-01', '2023-08-31', 0, 4, 9),
(21, '2022-05-02', '2023-04-30', 0, 5, 14),
(22, '2022-05-02', '2023-04-30', 0, 5, 15),
(23, '2022-05-02', '2023-04-30', 0, 5, 11),
(24, '2022-05-02', '2023-04-30', 0, 5, 3),
(25, '2022-05-02', '2023-04-30', 0, 5, 1),
(26, '2022-09-01', '2023-08-31', 0, 6, 1),
(27, '2022-09-01', '2023-08-31', 0, 6, 3),
(28, '2022-09-01', '2023-08-31', 0, 6, 5),
(29, '2022-09-01', '2023-08-31', 0, 6, 7),
(30, '2022-09-01', '2023-08-31', 0, 6, 8),
(31, '2022-09-01', '2023-08-31', 0, 7, 1),
(32, '2022-09-01', '2023-08-31', 0, 7, 5),
(33, '2022-09-01', '2023-08-31', 0, 7, 7),
(34, '2022-09-01', '2023-08-31', 0, 7, 8),
(35, '2022-09-01', '2023-08-31', 0, 7, 9),
(36, '2022-09-01', '2023-08-31', 0, 8, 1),
(37, '2022-09-01', '2023-08-31', 0, 8, 3),
(38, '2022-09-01', '2023-08-31', 0, 8, 5),
(39, '2022-09-01', '2023-08-31', 0, 8, 7),
(40, '2022-09-01', '2023-08-31', 0, 8, 8),
(41, '2022-09-01', '2023-08-31', 0, 9, 1),
(42, '2022-09-01', '2023-08-31', 0, 9, 5),
(43, '2022-09-01', '2023-08-31', 0, 9, 7),
(44, '2022-09-01', '2023-08-31', 0, 9, 8),
(45, '2022-09-01', '2023-08-31', 0, 9, 9),
(46, '2022-09-01', '2023-08-31', 0, 5, 1),
(47, '2022-09-01', '2023-08-31', 0, 5, 3),
(48, '2022-09-01', '2023-08-31', 0, 5, 5),
(49, '2022-09-01', '2023-08-31', 0, 5, 7),
(50, '2022-09-01', '2023-08-31', 0, 5, 8);

-- --------------------------------------------------------

--
-- Structure de la table `titre_professionnel`
--

DROP TABLE IF EXISTS `titre_professionnel`;
CREATE TABLE IF NOT EXISTS `titre_professionnel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) DEFAULT NULL,
  `titre_court` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `objectives` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `titre_professionnel`
--

INSERT INTO `titre_professionnel` (`id`, `titre`, `titre_court`, `version`, `description`, `objectives`, `slug`) VALUES
(1, 'Titre professionnel BIM modeleur du batîment (TP-01361m01)', NULL, 1, NULL, NULL, 'titre-professionnel-bim-modeleur-du-batiment-tp-01361m01'),
(2, 'Titre professionnel Développeur web et web mobile (TP-01280m03)', NULL, 1, NULL, NULL, 'titre-professionnel-developpeur-web-et-web-mobile-tp-01280m03'),
(3, 'Titre professionnel Administrateur d\'infrastructures sécurisées (TP-01352m01)', NULL, 1, NULL, NULL, 'titre-professionnel-administrateur-d-infrastructures-securisees-tp-01352m01'),
(4, 'Titre professionnel Monteur / monteuse audiovisuel (TP-01236m04))', NULL, 1, NULL, NULL, 'titre-professionnel-monteur-monteuse-audiovisiuel-tp-01236m04'),
(5, 'Titre professionnel Formateur professionnel d\'adultes (TP-00350m06)', NULL, 1, NULL, NULL, 'titre-professionnel-formateur-professionnel-d-adultes-tp-00350m06'),
(6, 'Titre professionnel Négociateur technico-commercial (TP-00338m8)', NULL, 1, NULL, NULL, 'titre-professionnel-negociateur-technico-commercial-tp-00338m8'),
(7, 'Titre professionnel Technicien supérieur systèmes et réseaux (TP-01351m01)', NULL, 1, NULL, NULL, 'titre-professionnel-technicien-superieur-systemes-et-reseaux-tp-01351m01'),
(8, 'Titre professionnel Concepteur Designer UI (TP-01411m01)', NULL, 1, NULL, NULL, 'titre-professionnel-concepteur-designer-ui-tp-01411m01'),
(9, 'Titre professionnel Concepteur Développeur d\'Applications (TP-01281m03)', NULL, 1, NULL, NULL, 'titre-professionnel-concepteur-developpeur-d-applications-tp-01281m03'),
(165, 'Titre professionnel Designer Web (TP-00469m05)', NULL, 0, NULL, NULL, 'titre-professionnel-designer-web-tp-00469m05');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `statut` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rma38wvnqfaf66vvmi57c71lo` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `active`, `email`, `nom`, `password`, `prenom`, `statut`, `version`) VALUES
(6, b'0', 'yanis971.62@gmail.com', 'ADEKALOM', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Yanis', 1, 0),
(7, b'0', 'yanis971.6@gmail.com', 'VILLENEUVE', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Quentin', 1, 0),
(15, b'0', 'yanis971.60@gmail.com', 'ROBERT', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Lisa', 1, 0),
(17, b'0', 'yanis971.63@gmail.com', 'ARGELIER', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Edwige', 1, 0),
(19, b'0', 'yanis971.64@gmail.com', 'BELLOGE', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Samantha', 1, 0),
(20, b'0', 'yanis971.65@gmail.com', 'TROUILLEFOU', '9ce23f6bd7bde8956b1ecf0126194deed0e125b5c7a8153513ffc2b915faf7a05a0b62099e5956f215fe41e20f6c7581a4ba768c0d266b0db046d86c1bc755a5', 'Lubert', 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `name`, `slug`, `version`) VALUES
(1, 'Bordeaux', 'bordeaux-france', 0),
(2, 'Bruxelles', 'bruxelles-belgique', 0),
(3, 'Distance', 'distance-france', 0),
(4, 'Geneve', 'geneve-suisse', 0),
(5, 'Lille', 'lille-france', 0),
(6, 'Luxembourg', 'luxembourg-luxembourg', 0),
(7, 'Lyon', 'lyon-france', 0),
(8, 'Marseille', 'marseille-france', 0),
(9, 'Montpellier', 'montpellier-france', 0),
(10, 'Nantes', 'nantes-france', 0),
(11, 'Nice', 'nice-france', 0),
(12, 'Paris', 'paris-france', 0),
(13, 'Rennes', 'rennes-france', 0),
(14, 'Strasbourg', 'strasbourg-france-1', 0),
(15, 'Toulouse', 'toulouse-france', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bloc_competences`
--
ALTER TABLE `bloc_competences`
  ADD CONSTRAINT `FKdesf2t5voe73i0op0ub0l0h9h` FOREIGN KEY (`titre_professionnel_id`) REFERENCES `titre_professionnel` (`id`);

--
-- Contraintes pour la table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `FK19jbnhide0qnvv8u9bwiyan03` FOREIGN KEY (`bloc_competences_id`) REFERENCES `bloc_competences` (`id`);

--
-- Contraintes pour la table `epreuve`
--
ALTER TABLE `epreuve`
  ADD CONSTRAINT `FKao3iv01ss5630y71def77tl62` FOREIGN KEY (`bloc_competences_id`) REFERENCES `bloc_competences` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FKk1k5fhsjak7v0uk031i5geqj9` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `etudiant_promotions`
--
ALTER TABLE `etudiant_promotions`
  ADD CONSTRAINT `FKfyy22nh9lj0rwxa85ltiq3es0` FOREIGN KEY (`promotions_id`) REFERENCES `promotion` (`id`),
  ADD CONSTRAINT `FKg5vs91jnxuqc9c395lhekes89` FOREIGN KEY (`etudiants_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FKhurd0ycs98i7fc3jmk76rjsvy` FOREIGN KEY (`epreuve_id`) REFERENCES `epreuve` (`id`),
  ADD CONSTRAINT `FKmqlqxgh4i7owgulrjvida40xo` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`),
  ADD CONSTRAINT `FKplr8nstfplr2tfglvcb8kyelc` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FK5rlh3nu3d7xdtom8jbtpjgote` FOREIGN KEY (`titre_professionnel_id`) REFERENCES `titre_professionnel` (`id`),
  ADD CONSTRAINT `FK7mmafxoxs1tr0dksu7lqv03as` FOREIGN KEY (`ville_id`) REFERENCES `ville` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
