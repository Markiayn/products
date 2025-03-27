-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: production
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `factories`
--

DROP TABLE IF EXISTS `factories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factories` (
  `name` varchar(255) DEFAULT NULL,
  `produced` int DEFAULT NULL,
  `sold` int DEFAULT NULL,
  `date_made` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factories`
--

LOCK TABLES `factories` WRITE;
/*!40000 ALTER TABLE `factories` DISABLE KEYS */;
INSERT INTO `factories` VALUES ('nike',20000,14506,'2025-01-15',1),('hp',4000,2753,'2023-05-06',2),('casio',300,287,'2020-07-01',3),('CocaCola',1000000,934000,'2025-03-20',4),('addidas',15000,9834,'2024-09-19',5),('lego',5000000,4988251,'2024-03-05',6),('Ford',7500,6301,'2024-02-01',7),('Sumsung',150000,137052,'2023-09-10',8);
/*!40000 ALTER TABLE `factories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_name` varchar(255) DEFAULT NULL,
  `product_code` int DEFAULT NULL,
  `cost_price` float DEFAULT NULL,
  `selling_price` float DEFAULT NULL,
  `date_made` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `factories_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_factories_id` (`factories_id`),
  CONSTRAINT `fk_factories_id` FOREIGN KEY (`factories_id`) REFERENCES `factories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('NissanGtr',598742,30200,50500,'2018-05-15',1,7),('NikeAirJordan6',219874,60,150,'2023-06-17',2,1),('PT-30m74',300150,150,550,'2021-02-01',3,3),('CocaCola_can_0.5',149687,0.2,1,'2025-03-18',4,4),('HpPavilion',957351,500,1250,'2024-05-04',5,8),('Iphone',357951,450,800,'2023-09-05',6,8),('Pixel',7419632,400,700,'2023-07-10',7,8);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-28  0:18:10
