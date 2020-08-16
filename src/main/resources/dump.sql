-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tripId` bigint DEFAULT NULL,
  `userId` bigint NOT NULL,
  `vehicleSpecification` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`userId`),
  CONSTRAINT `user_id` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (89,58,10,'specification'),(90,58,10,'specificationghjgjgjg'),(91,60,6,'specification');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vehicleId` bigint DEFAULT NULL,
  `bookingId` bigint DEFAULT NULL,
  `createDate` date NOT NULL,
  `status` enum('OPEN','IN_WORK','CLOSED') NOT NULL DEFAULT 'OPEN',
  `task` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `vehicle_od_idx` (`vehicleId`),
  KEY `booking_id_idx` (`bookingId`),
  CONSTRAINT `bookingId` FOREIGN KEY (`bookingId`) REFERENCES `booking` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `vehicleId` FOREIGN KEY (`vehicleId`) REFERENCES `vehicle` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (55,1,NULL,'2020-08-12','CLOSED','gfdgdgdg'),(56,9,NULL,'2020-11-24','CLOSED','sdfga sd fsdfsdf'),(57,1,NULL,'2020-08-27','IN_WORK','gfdgdgd'),(58,7,89,'2020-08-24','OPEN','fghdh'),(60,10,91,'2020-08-25','OPEN','gdfgdfgd');
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('ADMIN','DISPATCHER','DRIVER') NOT NULL,
  `enabled` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin@mail.com','ADMIN',1),(2,'Jake','Jake','jake@mail.com','DISPATCHER',1),(3,'Mike','Mike','mike@mail.com','DISPATCHER',0),(4,'Ivan','Ivan','ivan@mail.com','DRIVER',0),(6,'Edward','21232F297A57A5A743894A0E4A801FC3','admin@mail.com','ADMIN',1),(10,'Voldemar','21232F297A57A5A743894A0E4A801FC3','driver@mail.com','DRIVER',1),(11,'Jake','21232F297A57A5A743894A0E4A801FC3','dispatcher@mail.com','DISPATCHER',1),(12,'TestCreate','21232F297A57A5A743894A0E4A801FC3','test@mail.com','DISPATCHER',1),(14,'Test','A8A76790FF950B754DE0B8FA5824CEEE','dsfsdfsg@gsrdfgsdg.ua','ADMIN',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sits` bigint NOT NULL DEFAULT '2',
  `type` enum('SEDAN','SUV','MINIVAN','VAN','MINITRUCK') NOT NULL,
  `trunkSize` enum('TINY','SMALL','MEDIUM','LARGE') NOT NULL DEFAULT 'TINY',
  `class` varchar(8) DEFAULT 'w/o',
  `condition` enum('GOOD','BROKEN') NOT NULL DEFAULT 'GOOD',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'bmw',1,'SEDAN','SMALL','Q','GOOD'),(7,'toyota',7,'MINIVAN','SMALL','M','BROKEN'),(8,'ford transit',2,'VAN','LARGE',NULL,'GOOD'),(9,'toyota rav4',5,'SUV','MEDIUM','J','GOOD'),(10,'Tata ace',2,'MINITRUCK','LARGE',NULL,'GOOD');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-16 13:10:36
