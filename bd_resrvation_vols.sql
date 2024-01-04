-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 19 déc. 2023 à 11:12
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_vols`
--

-- --------------------------------------------------------

--
-- Structure de la table `passager`
--

CREATE TABLE `passager` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `addresse` varchar(50) NOT NULL,
  `NumPassport` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `passager`
--

INSERT INTO `passager` (`id`, `nom`, `addresse`, `NumPassport`) VALUES
(4, 'mika', 'Damas', '45fdd4f55d');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `addresse` varchar(100) NOT NULL,
  `NumPassport` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `addresse`, `NumPassport`) VALUES
(1, 'mika', 'Damas', '45fdd4f55d'),
(2, 'Sophia', 'Nsaa', '98lk21po'),
(3, 'Tagro', 'Mimboman', '1578kj21kl');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `DateReservation` datetime NOT NULL,
  `id_personne` int(11) NOT NULL,
  `num_vol` int(11) NOT NULL,
  `num_reservation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`DateReservation`, `id_personne`, `num_vol`, `num_reservation`) VALUES
('2023-12-19 09:21:45', 1, 2, 9);

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `num_vol` int(11) NOT NULL,
  `compagnie` varchar(50) NOT NULL,
  `dateDep` datetime NOT NULL,
  `destination` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `vol`
--

INSERT INTO `vol` (`num_vol`, `compagnie`, `dateDep`, `destination`) VALUES
(1, 'Camairco', '2023-12-12 01:59:21', 'newyork'),
(2, 'EthiopianAL', '2024-02-05 12:00:00', 'strasbourg'),
(3, 'Air France', '2024-05-11 22:00:00', 'bruxel');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `passager`
--
ALTER TABLE `passager`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`num_reservation`),
  ADD KEY `id_personne` (`id_personne`),
  ADD KEY `num_vol` (`num_vol`);

--
-- Index pour la table `vol`
--
ALTER TABLE `vol`
  ADD PRIMARY KEY (`num_vol`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `passager`
--
ALTER TABLE `passager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `num_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `vol`
--
ALTER TABLE `vol`
  MODIFY `num_vol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`num_vol`) REFERENCES `vol` (`num_vol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
