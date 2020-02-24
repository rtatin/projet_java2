-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 10 fév. 2020 à 17:24
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `contact_app`
--

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(40) NOT NULL,
  `firstname` varchar(40) NOT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `category` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `lastname`, `firstname`, `nickname`, `phone`, `email`, `address`, `birthday`,`category`) VALUES
(1, 'Master', 'Yoda', NULL, '0612345678', 'Yoda.Master@isen.yncrea.fr', 'Dagobah', NULL,'Work'),
(2, 'Skywalker', 'Anakin', 'Darth Vader', '0687654321', 'Anakin.Skywalker@isen.yncrea.fr', 'Tatouine', NULL,'Other'),
(3, 'Sheev', 'Palpatine', 'Darth Sidious', '0987654321', 'Palpatine.Sheev@isen.yncrea.fr', 'Naboo', '1989-01-13','Work'),
(4, 'Darth', 'Plagueis', 'The whise', '0412345678', NULL, NULL, '1970-02-21','Family'),
(5, 'Droide', 'R2D2', 'R2', '0787654321', NULL, NULL, NULL,'Friend'),
(6, 'Droide', 'C3PO', NULL, '0787654322', NULL, 'Tatouine', NULL,'Friend');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
