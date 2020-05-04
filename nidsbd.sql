-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.19 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table nidsbd.notif
CREATE TABLE IF NOT EXISTS `notif` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  `isChecked` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.notif: ~0 rows (approximately)
/*!40000 ALTER TABLE `notif` DISABLE KEYS */;
INSERT INTO `notif` (`id`, `content`, `date`, `idUser`, `isChecked`, `type`) VALUES
	(3, 'la tache num 2est terminée', '2019-05-12 21:33:20', 1, 1, 'Tache'),
	(4, 'la réparation num 1est terminée', '2019-05-12 22:27:25', 1, 1, 'Reparation'),
	(5, 'la tache num 1est terminée', '2019-05-12 22:55:32', 1, 0, 'Tache');
/*!40000 ALTER TABLE `notif` ENABLE KEYS */;

-- Dumping structure for table nidsbd.reparation
CREATE TABLE IF NOT EXISTS `reparation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cout` double NOT NULL,
  `dateDebut` varchar(50) DEFAULT NULL,
  `duree` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vehicule_id` int(11) DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qnxnva8h67l1fvusmk96ktaq8` (`user_id`),
  KEY `FK_pp4j23wxobw9pigk4v590ybfd` (`vehicule_id`),
  CONSTRAINT `FK_pp4j23wxobw9pigk4v590ybfd` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`),
  CONSTRAINT `FK_qnxnva8h67l1fvusmk96ktaq8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.reparation: ~1 rows (approximately)
/*!40000 ALTER TABLE `reparation` DISABLE KEYS */;
INSERT INTO `reparation` (`id`, `cout`, `dateDebut`, `duree`, `type`, `user_id`, `vehicule_id`, `completed`) VALUES
	(1, 123, '20-05-2019', 50, 'type 2', 2, 1, b'1');
/*!40000 ALTER TABLE `reparation` ENABLE KEYS */;

-- Dumping structure for table nidsbd.tache
CREATE TABLE IF NOT EXISTS `tache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateDebut` varchar(50) DEFAULT NULL,
  `trajet_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mffb0ff4kpdy8n0h8bne62kx5` (`trajet_id`),
  KEY `FK_7j8i2lno5l50ip2ufqbfwqqec` (`user_id`),
  CONSTRAINT `FK_7j8i2lno5l50ip2ufqbfwqqec` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_mffb0ff4kpdy8n0h8bne62kx5` FOREIGN KEY (`trajet_id`) REFERENCES `trajet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.tache: ~2 rows (approximately)
/*!40000 ALTER TABLE `tache` DISABLE KEYS */;
INSERT INTO `tache` (`id`, `dateDebut`, `trajet_id`, `user_id`, `completed`) VALUES
	(1, '2019-05-27', 2, 1, b'1'),
	(2, '2019-05-23', 1, 1, b'1');
/*!40000 ALTER TABLE `tache` ENABLE KEYS */;

-- Dumping structure for table nidsbd.trajet
CREATE TABLE IF NOT EXISTS `trajet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.trajet: ~2 rows (approximately)
/*!40000 ALTER TABLE `trajet` DISABLE KEYS */;
INSERT INTO `trajet` (`id`, `depart`, `destination`, `nom`, `type`) VALUES
	(1, 'tunis', 'sfax', 'trajet 1', 'type 1'),
	(2, 'Nabeul', 'Sousse', 'trajet 2', 'typpe2');
/*!40000 ALTER TABLE `trajet` ENABLE KEYS */;

-- Dumping structure for table nidsbd.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `disponible` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.user: ~3 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `adresse`, `disponible`, `email`, `enabled`, `login`, `nom`, `password`, `phone`, `prenom`, `role`) VALUES
	(1, 'adr', b'1', 'chauffeur1@email.com', b'1', 'chauffeur1', 'chauffeur', '$2a$12$ihHygO4vPCEVQXNybD5yBONBHNN2rWQ9lSgwCKR0sQwR08Urovdsu', '52497659', 'one', 'chauffeur'),
	(2, 'adr', b'0', 'mecanicien1@email.com', b'1', 'mecanicien1', 'mecanicien', '$2a$12$ihHygO4vPCEVQXNybD5yBONBHNN2rWQ9lSgwCKR0sQwR08Urovdsu', '52497659', 'one', 'mecanicien'),
	(3, 'adr', b'1', 'admin@email.com', b'1', 'admin', 'admin', '$2a$12$i1zWxfpmzjAU069qndUNF.kzpM6kSBtXglixlZm/byvcdtqlGNrsO', '52497659', 'admin', 'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table nidsbd.vehicule
CREATE TABLE IF NOT EXISTS `vehicule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `panne` bit(1) NOT NULL,
  `poids` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_plow0jqdpsgud1rcf2bat8t6w` (`user_id`),
  CONSTRAINT `FK_plow0jqdpsgud1rcf2bat8t6w` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table nidsbd.vehicule: ~3 rows (approximately)
/*!40000 ALTER TABLE `vehicule` DISABLE KEYS */;
INSERT INTO `vehicule` (`id`, `matricule`, `modele`, `panne`, `poids`, `type`, `user_id`) VALUES
	(1, '203Tun1502', 'M3', b'0', 250, 'BMW', NULL),
	(2, '150Tun1965', 'M5', b'0', 150, 'BMW', NULL),
	(3, '220Tun5632', 'C2', b'1', 500, 'Mercedes', 1);
/*!40000 ALTER TABLE `vehicule` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
