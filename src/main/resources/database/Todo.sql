-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: todo
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `calendars_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`calendars_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (1,'개발 회의','2025-02-12','서울 사무실','2025-02-12','test'),(2,'개발 회의','2025-02-12','서울 사무실','2025-02-11','test'),(3,'개발 회의','2025-02-14','서울 사무실','2025-02-11','test'),(4,'개발 회의3','2025-02-14','서울 사무실3','2025-02-11','test'),(5,'개발 회의4','2025-02-14','서울 사무실4','2025-02-11','test'),(6,'개발 회의5','2025-02-14','서울 사무실5','2025-02-11','test'),(7,'개발 회의1','2025-02-14','서울 사무실1','2025-02-12','test'),(8,'개발 회의2','2025-02-14','서울 사무실2','2025-02-12','test'),(9,'개발 회의3','2025-02-14','서울 사무실3','2025-02-12','test'),(10,'개발 회의4','2025-02-14','서울 사무실4','2025-02-12','test'),(11,'개발 회의5','2025-02-14','서울 사무실5','2025-02-12','test'),(12,'개발 회의1','2025-02-14','서울 사무실1','2025-02-13','test'),(13,'개발 회의2','2025-02-14','서울 사무실2','2025-02-13','test'),(14,'개발 회의3','2025-02-14','서울 사무실3','2025-02-13','test'),(15,'개발 회의4','2025-02-14','서울 사무실4','2025-02-13','test'),(16,'개발 회의5','2025-02-14','서울 사무실5','2025-02-13','test'),(17,'개발 회의1','2025-02-14','서울 사무실1','2025-02-13','test'),(18,'개발 회의2','2025-02-15','서울 사무실2','2025-02-14','test'),(19,'개발 회의3','2025-02-15','서울 사무실3','2025-02-14','test'),(20,'개발 회의4','2025-02-15','서울 사무실4','2025-02-14','test'),(21,'개발 회의5','2025-02-15','서울 사무실5','2025-02-14','test');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar_user`
--

DROP TABLE IF EXISTS `calendar_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar_user` (
  `calendar_users_id` bigint NOT NULL AUTO_INCREMENT,
  `invite_status` enum('ACCEPTED','DECLINED','PENDING') NOT NULL,
  `calendar_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`calendar_users_id`),
  KEY `FKfba2l6n14ef6aqcn26ndnq4m3` (`calendar_id`),
  KEY `FK1ekdcbpyi5xumqtfw99c13kma` (`user_id`),
  CONSTRAINT `FK1ekdcbpyi5xumqtfw99c13kma` FOREIGN KEY (`user_id`) REFERENCES `user` (`users_id`),
  CONSTRAINT `FKfba2l6n14ef6aqcn26ndnq4m3` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`calendars_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar_user`
--

LOCK TABLES `calendar_user` WRITE;
/*!40000 ALTER TABLE `calendar_user` DISABLE KEYS */;
INSERT INTO `calendar_user` VALUES (1,'ACCEPTED',1,1),(2,'ACCEPTED',2,1),(3,'ACCEPTED',3,1),(4,'ACCEPTED',4,1),(5,'ACCEPTED',5,1),(6,'ACCEPTED',6,1),(7,'ACCEPTED',7,2),(8,'ACCEPTED',8,2),(9,'ACCEPTED',9,2),(10,'ACCEPTED',10,2),(11,'ACCEPTED',11,2),(12,'ACCEPTED',12,3),(13,'ACCEPTED',13,3),(14,'ACCEPTED',14,3),(15,'ACCEPTED',15,3),(16,'ACCEPTED',16,3),(17,'ACCEPTED',17,4),(18,'ACCEPTED',18,4),(19,'ACCEPTED',19,4),(20,'ACCEPTED',20,4),(21,'ACCEPTED',21,4),(22,'PENDING',1,2),(23,'PENDING',1,4),(24,'PENDING',1,3),(25,'PENDING',3,2),(26,'PENDING',3,5),(27,'PENDING',4,5),(28,'PENDING',4,2),(29,'PENDING',13,5),(30,'DECLINED',15,2),(31,'ACCEPTED',14,5),(32,'DECLINED',13,5),(33,'ACCEPTED',15,4),(34,'ACCEPTED',12,2),(35,'PENDING',11,2),(36,'PENDING',15,4),(37,'PENDING',6,4),(38,'ACCEPTED',3,2);
/*!40000 ALTER TABLE `calendar_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo_lists`
--

DROP TABLE IF EXISTS `todo_lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo_lists` (
  `todo_list_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `due_time` datetime(6) DEFAULT NULL,
  `is_completed` bit(1) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `calendar_id` bigint NOT NULL,
  PRIMARY KEY (`todo_list_id`),
  KEY `FKd42gajq7tum2caxiv6xdansa8` (`calendar_id`),
  CONSTRAINT `FKd42gajq7tum2caxiv6xdansa8` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`calendars_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo_lists`
--

LOCK TABLES `todo_lists` WRITE;
/*!40000 ALTER TABLE `todo_lists` DISABLE KEYS */;
INSERT INTO `todo_lists` VALUES (1,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',2),(2,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',1),(3,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',1),(4,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',3),(5,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',4),(6,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',6),(7,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',7),(8,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',7),(9,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title1',7),(10,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','운영','title3',10),(11,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title5',6),(12,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','마케팅','title2',10),(13,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','개발','title5',5),(14,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','마케팅','title2',2),(15,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','디자인','title5',4),(16,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','운영','title5',9),(17,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','기획','title5',3),(18,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','운영','title2',4),(19,'description','2025-02-12 00:00:00.000000',_binary '\0','2025-02-12 00:00:00.000000','운영','title3',9);
/*!40000 ALTER TABLE `todo_lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_store`
--

DROP TABLE IF EXISTS `token_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_store` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `refresh_token` varchar(500) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKheec006cvyn61bjq0bgmodukr` (`user_id`),
  CONSTRAINT `FK618bw15s4shrew372bjircqm3` FOREIGN KEY (`user_id`) REFERENCES `user` (`users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_store`
--

LOCK TABLES `token_store` WRITE;
/*!40000 ALTER TABLE `token_store` DISABLE KEYS */;
INSERT INTO `token_store` VALUES (1,NULL,1),(2,NULL,2),(3,NULL,3),(4,NULL,4),(5,NULL,5);
/*!40000 ALTER TABLE `token_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `users_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(225) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER') NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`users_id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2025-02-16 21:06:49.841387','2025-02-16 21:06:49.841387','testuser1@naver.com','$2a$10$FrThji1f4P28bB6PfoSyr.12TBvSN59ZxO.c0yW/POfaq56JmjqCq','ROLE_USER','testuser1'),(2,'2025-02-16 21:06:55.319102','2025-02-16 21:06:55.319102','testuser2@naver.com','$2a$10$FQXq6TMpNkfbDYiGflq84.rx6FsX8uBIHHBU.WakHf9YQH.5zlAJ.','ROLE_USER','testuser2'),(3,'2025-02-16 21:06:59.951450','2025-02-16 21:06:59.951450','testuser3@naver.com','$2a$10$4Uoq.a5FUuFSPZWyjOicoO2QQBf/gi0xU7Kyu1cvs8gC2mciKbAgG','ROLE_USER','testuser3'),(4,'2025-02-16 21:07:04.949064','2025-02-16 21:07:04.949064','testuser4@naver.com','$2a$10$fU4FWZx6CDvuOqqPTFDDm.ZV2vJwiXt80F8kjZL5nUUspDw2kJnHi','ROLE_USER','testuser4'),(5,'2025-02-16 21:07:09.410017','2025-02-16 21:07:09.410017','testuser5@naver.com','$2a$10$ls.qvgnurkb4y4IrGtqWWOEGeRgLnry1lzUNnBA7oKv419ZnKFCLC','ROLE_USER','testuser5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-16 21:44:15
