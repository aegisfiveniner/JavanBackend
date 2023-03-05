-- MariaDB dump 10.19  Distrib 10.6.8-MariaDB, for debian-linux-gnu (aarch64)
--
-- Host: localhost    Database: tax_management
-- ------------------------------------------------------
-- Server version	10.6.8-MariaDB-1:10.6.8+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tax` (
  `id` varchar(50) NOT NULL,
  `receipt_number` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `checked_at` timestamp NULL DEFAULT NULL,
  `checked_by` varchar(50) DEFAULT NULL,
  `approved_at` timestamp NULL DEFAULT NULL,
  `approved_by` varchar(50) DEFAULT NULL,
  `rejected_at` timestamp NULL DEFAULT NULL,
  `rejected_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tax_receipt_number_index` (`receipt_number`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES ('20adb019-7937-4cb9-966c-7fe301dc983d','RECEIPT-MAKER2-1678017266531','REJECTED','aefe9734-6234-4bd7-982f-028c77335549','maker2','User Maker 2','2023-03-05 18:54:26','maker2','2023-03-05 19:02:56','checker','2023-03-05 19:09:54','maker2',NULL,NULL),('4a1a9689-d452-4955-b901-44696eae859f','RECEIPT-MAKER-1678013137188','SUBMITTED','3dfbfe12-c82d-45fe-be9a-4576dbf4b5a5','maker','User Maker test kafka','2023-03-05 17:45:37','maker',NULL,NULL,NULL,NULL,NULL,NULL),('4b8b9c53-4b09-4a1b-b7fb-7914a6b9cb7d','RECEIPT-MAKER-1678013148089','SUBMITTED','3dfbfe12-c82d-45fe-be9a-4576dbf4b5a5','maker','User Maker test kafka','2023-03-05 17:45:48','maker',NULL,NULL,NULL,NULL,NULL,NULL),('8698b098-3c22-4c9b-9cb4-ef202fd590fc','RECEIPT-MAKER2-1678017267184','CHECKED','aefe9734-6234-4bd7-982f-028c77335549','maker2','User Maker 2','2023-03-05 18:54:27','maker2','2023-03-05 19:04:39','maker2',NULL,NULL,NULL,NULL),('d864d0eb-bf98-410f-9e69-171884c55148','RECEIPT-MAKER2-1678017725919','SUBMITTED','aefe9734-6234-4bd7-982f-028c77335549','maker2','User Maker 2','2023-03-05 19:02:05','maker2',NULL,NULL,NULL,NULL,NULL,NULL),('dc632ba6-017d-4d9a-a486-7dccc1fd1dd6','RECEIPT-MAKER-1678013149008','APPROVED','3dfbfe12-c82d-45fe-be9a-4576dbf4b5a5','maker','User Maker test kafka','2023-03-05 17:45:49','maker','2023-03-05 19:03:13','checker','2023-03-05 19:05:08','approver',NULL,NULL);
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('3dfbfe12-c82d-45fe-be9a-4576dbf4b5a5','maker','User Maker test kafka','maker123@gmail.com','MAKER'),('bad7a649-ec10-4820-b05f-aab74f892ac5','approver','User approver update','maker1234@gmail.com','APPROVER'),('9e4780f2-6ee5-4cfb-bba4-4d95b559f251','checker','User checker','checker1234@gmail.com','CHECKER'),('aefe9734-6234-4bd7-982f-028c77335549','maker2','User Maker 2','maker1234@gmail.com','MAKER'),('754919ef-4894-400e-9916-15420cf68b64','checker2','User checker 2','checker12342@gmail.com','CHECKER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-05 12:51:40
