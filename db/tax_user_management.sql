-- MariaDB dump 10.19  Distrib 10.6.8-MariaDB, for debian-linux-gnu (aarch64)
--
-- Host: localhost    Database: tax_user_management
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('3d86f501-10ab-4707-acd9-4de463446437','admin','System Administrator','admin@gmail.com','$2a$10$iBy8cEw4UYJXEv0E4pniBuarWB69c2yIR1vr2Ki/R.1oF7s.nAY.a','ADMIN'),('3dfbfe12-c82d-45fe-be9a-4576dbf4b5a5','maker','User maker','maker123@gmail.com','$2a$10$h0QCQstJEpJ5.ybC8s4xtOhCm5RGCzL2G2eki7/ZMehkFCWyPvlLK','MAKER'),('754919ef-4894-400e-9916-15420cf68b64','checker2','User checker 2','checker12342@gmail.com','$2a$10$sffzLji1rcLCxfvGionBPudtn2V/xsEhy2AHU5TyAUWyHvYdI4Cvq','CHECKER'),('9e4780f2-6ee5-4cfb-bba4-4d95b559f251','checker','User checker','checker1234@gmail.com','$2a$10$82IWXSvYtR/S/yXzd2nU8ecaz.1cet/2Zuya62M3ouh3pwKJQMQe6','CHECKER'),('aefe9734-6234-4bd7-982f-028c77335549','maker2','User Maker 2','maker1234@gmail.com','$2a$10$otXmP4bKBcvmoj6yqcj6p.nkyCyQD/vqSQtEGe0wSRaYuyW3IOawq','MAKER'),('bad7a649-ec10-4820-b05f-aab74f892ac5','approver','User approver update','maker1234@gmail.com','$2a$10$K/bzEnmRTlvBoHwcxdhqn.lL0WfF.ujWu8.5v2Q/nrTKzw8IE5g92','APPROVER');
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

-- Dump completed on 2023-03-05 12:52:12
