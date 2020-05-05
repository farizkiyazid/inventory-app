-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: tugasDay24
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `invAlloc`
--

DROP TABLE IF EXISTS `invAlloc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invAlloc` (
  `idInvAlloc` bigint NOT NULL AUTO_INCREMENT,
  `idStaff` bigint NOT NULL,
  `idItem` bigint NOT NULL,
  `timeAlloc` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeReturned` datetime DEFAULT NULL,
  `returned` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idInvAlloc`),
  UNIQUE KEY `idinvAlloc_UNIQUE` (`idInvAlloc`),
  KEY `idStaff_idx` (`idStaff`),
  CONSTRAINT `idStaff` FOREIGN KEY (`idStaff`) REFERENCES `staffs` (`idStaff`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invAlloc`
--

LOCK TABLES `invAlloc` WRITE;
/*!40000 ALTER TABLE `invAlloc` DISABLE KEYS */;
INSERT INTO `invAlloc` VALUES (7,1,1,'2020-05-01 15:41:17',NULL,0),(8,1,3,'2020-05-01 15:44:46',NULL,0),(9,2,4,'2020-05-01 15:57:17',NULL,0);
/*!40000 ALTER TABLE `invAlloc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `idItem` bigint NOT NULL AUTO_INCREMENT,
  `itemType` varchar(60) NOT NULL,
  `available` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'Laptop',0),(2,'Laptop',1),(3,'Monitor',0),(4,'Laptop',0),(6,'Monitor',1);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffs`
--

DROP TABLE IF EXISTS `staffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staffs` (
  `idStaff` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `division` varchar(45) NOT NULL,
  PRIMARY KEY (`idStaff`),
  UNIQUE KEY `idstaff_UNIQUE` (`idStaff`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (1,'Kiki','Tech'),(2,'Wahyu','HR');
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-01 21:02:17
