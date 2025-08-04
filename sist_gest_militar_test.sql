-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: sist_gest_militar
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `army_bodies`
--

DROP TABLE IF EXISTS `army_bodies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `army_bodies` (
  `id_body` int NOT NULL AUTO_INCREMENT,
  `denomination` varchar(255) NOT NULL,
  PRIMARY KEY (`id_body`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `army_bodies`
--

LOCK TABLES `army_bodies` WRITE;
/*!40000 ALTER TABLE `army_bodies` DISABLE KEYS */;
INSERT INTO `army_bodies` VALUES (1,'Cuerpo de Infantería de Marina'),(2,'Cuerpo de Operaciones Especiales Navales'),(3,'Cuerpo de Ingenieros Navales'),(4,'Cuerpo de Sanidad Naval'),(5,'Cuerpo de Comunicaciones y Guerra Electrónica'),(6,'Cuerpo de Aviación Naval'),(7,'Cuerpo de Inteligencia Marítima'),(8,'Cuerpo de Logística y Abastecimiento'),(9,'Cuerpo de Seguridad y Policía Naval'),(10,'Cuerpo de Salvamento y Rescate Marítimo'),(26,'Cuerpo de prueba armada'),(27,'Cuerpo de prueba armada');
/*!40000 ALTER TABLE `army_bodies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barracks`
--

DROP TABLE IF EXISTS `barracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barracks` (
  `id_barrack` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`id_barrack`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barracks`
--

LOCK TABLES `barracks` WRITE;
/*!40000 ALTER TABLE `barracks` DISABLE KEYS */;
INSERT INTO `barracks` VALUES (1,'Cuartel Fénix','Ciudad Arcadia'),(2,'Cuartel Centauro','Villa Solaris'),(3,'Cuartel Titanes','Fortaleza Nova'),(4,'Cuartel Dragón','Puerto Valhalla'),(5,'Cuartel Centinela','Base Eclipse'),(31,'test barrack','test barrack');
/*!40000 ALTER TABLE `barracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id_company` int NOT NULL AUTO_INCREMENT,
  `activity` varchar(255) NOT NULL,
  PRIMARY KEY (`id_company`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Entrenamiento de Infantería'),(2,'Operaciones de Inteligencia'),(3,'Mantenimiento de Vehículos Militares'),(4,'Logística y Suministro de Equipos'),(5,'Ciberseguridad y Defensa Digital'),(6,'Operaciones Especiales'),(7,'Control de Fronteras y Patrullaje'),(8,'Gestión de Comunicaciones Tácticas'),(9,'Rescate y Atención Médica en Combate'),(10,'Equipo de Testing de la Fuerza Naval');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMINISTRADOR','administrarán toda la aplicación'),('OFICIAL','administrarán todo'),('SOLDADO','sólo lo podrán consultar'),('SUB_OFICIAL','administrarán a los soldados y los servicios');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id_service` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id_service`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'Mantenimiento de armamento'),(3,'Operación de rescate'),(4,'Entrenamiento táctico'),(5,'Seguridad en base'),(6,'Supervisión de suministros'),(7,'Misión de inteligencia'),(8,'Vigilancia costera'),(10,'Defensa aérea estratégica'),(23,'test servicio'),(24,'cambios de aceite en los vehiculos'),(25,'test servicio'),(27,'dormir deespues de comer?'),(28,'test servicio de aerodinamica'),(31,'test servicio de infanteria'),(32,'Fundamentos de programación en Python'),(33,'Mi aplicación msb-project'),(34,'Exploración de diferentes formas de arte'),(35,'Matematica'),(36,'Programacion Super Basica'),(37,'test message'),(44,'Mi aplicación msb-project'),(46,'Exploración de diferentes formas de arte'),(47,'Test de servicios'),(48,'test test'),(49,'Vigilancia de buque de navegación');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services_soldiers`
--

DROP TABLE IF EXISTS `services_soldiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services_soldiers` (
  `id_services_soldiers` bigint NOT NULL AUTO_INCREMENT,
  `id_soldier` int NOT NULL,
  `id_service` bigint NOT NULL,
  `end_service` datetime DEFAULT NULL,
  `at_service` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_services_soldiers`),
  KEY `fk_service_many_idx` (`id_service`),
  KEY `fk_soldier_many_idx` (`id_soldier`),
  CONSTRAINT `fk_service_many` FOREIGN KEY (`id_service`) REFERENCES `services` (`id_service`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_soldier_many` FOREIGN KEY (`id_soldier`) REFERENCES `soldiers` (`id_soldier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_soldiers`
--

LOCK TABLES `services_soldiers` WRITE;
/*!40000 ALTER TABLE `services_soldiers` DISABLE KEYS */;
INSERT INTO `services_soldiers` VALUES (6,1,3,'2025-04-10 17:43:16','2025-04-10 17:00:00'),(7,1,4,'2025-04-10 17:42:17','2025-04-10 17:00:04'),(8,1,5,'2025-04-10 17:42:18','2025-04-10 17:00:06'),(9,1,6,'2025-04-10 17:42:18','2025-04-10 17:00:09'),(10,1,7,'2025-04-10 17:42:18','2025-04-10 17:00:11'),(11,1,7,'2025-04-10 17:43:49','2025-04-10 17:43:31'),(45,1,5,'2025-04-11 10:29:59','2025-04-11 10:29:47'),(46,1,3,'2025-04-11 10:30:01','2025-04-11 10:29:47'),(47,1,5,'2025-04-11 10:30:06','2025-04-11 10:29:48'),(51,1,5,'2025-04-11 10:30:52','2025-04-11 10:29:49'),(52,1,5,'2025-04-11 12:25:59','2025-04-11 12:25:50'),(53,2,4,NULL,'2025-04-14 18:41:14'),(54,2,7,NULL,'2025-04-14 18:48:29'),(58,8,4,'2025-04-21 17:53:14','2025-04-21 17:44:07'),(59,8,4,'2025-04-21 17:54:23','2025-04-21 17:54:16'),(60,8,4,'2025-04-21 17:54:25','2025-04-21 17:54:16'),(61,8,3,'2025-04-21 17:59:25','2025-04-21 17:57:44'),(62,8,2,NULL,'2025-04-21 17:59:58'),(65,7,3,NULL,'2025-04-18 17:59:58'),(66,1,4,NULL,'2025-04-22 14:16:13'),(67,2,6,NULL,'2025-04-22 14:17:15'),(68,6,6,NULL,'2025-04-22 14:17:25'),(69,1,32,NULL,'2025-04-22 14:17:37'),(70,3,32,NULL,'2025-04-22 14:17:37'),(71,2,32,NULL,'2025-04-22 14:18:02'),(72,4,4,NULL,'2025-04-22 14:18:02'),(73,5,32,NULL,'2025-04-22 14:18:02'),(74,6,32,NULL,'2025-04-22 14:18:02'),(75,7,32,NULL,'2025-04-22 14:18:02'),(76,5,33,NULL,'2025-04-22 14:18:21'),(77,6,33,NULL,'2025-04-22 14:18:21'),(78,7,33,NULL,'2025-04-22 14:18:21'),(79,8,33,NULL,'2025-04-22 14:18:21'),(80,9,33,NULL,'2025-04-22 14:18:21'),(81,8,6,NULL,'2025-04-22 14:19:08'),(82,9,6,NULL,'2025-04-22 14:19:08'),(83,26,6,NULL,'2025-04-22 14:19:08'),(84,5,34,NULL,'2025-04-22 14:19:23'),(85,7,34,NULL,'2025-04-22 14:19:23'),(86,8,34,NULL,'2025-04-22 14:19:23'),(87,1,6,NULL,'2025-04-22 16:27:30'),(88,2,6,NULL,'2025-04-22 16:27:30'),(89,3,6,NULL,'2025-04-22 16:27:30'),(90,4,6,NULL,'2025-04-22 16:27:30'),(91,5,6,NULL,'2025-04-22 16:27:30'),(92,6,6,NULL,'2025-04-22 16:27:30'),(93,7,6,NULL,'2025-04-22 16:27:30'),(94,8,6,NULL,'2025-04-22 16:27:30'),(95,9,6,NULL,'2025-04-22 16:27:30'),(96,26,6,NULL,'2025-04-22 16:27:30'),(97,1,35,NULL,'2025-04-22 16:27:54'),(98,2,35,NULL,'2025-04-22 16:27:54'),(99,3,35,NULL,'2025-04-22 16:27:54'),(100,4,35,NULL,'2025-04-22 16:27:54'),(101,5,35,NULL,'2025-04-22 16:27:54'),(102,6,35,NULL,'2025-04-22 16:27:54'),(103,7,35,NULL,'2025-04-22 16:27:54'),(104,8,3,NULL,'2025-04-22 16:27:54'),(105,28,5,'2025-04-23 12:42:25','2025-04-23 12:42:02'),(106,28,3,'2025-04-24 11:31:56','2025-04-23 12:42:08'),(107,7,4,NULL,'2025-04-23 13:56:37'),(116,3,5,NULL,'2025-04-24 11:35:57'),(117,29,3,'2025-04-24 11:37:53','2025-04-24 11:35:57'),(118,29,8,'2025-04-24 11:37:59','2025-04-24 11:36:46'),(119,28,7,NULL,'2024-04-24 11:36:46'),(120,29,8,'2025-04-25 16:01:54','2025-04-24 14:21:13'),(121,4,47,NULL,'2025-04-24 17:55:36'),(122,7,47,NULL,'2025-04-24 17:55:36'),(123,30,47,NULL,'2025-04-24 17:55:36'),(126,7,5,NULL,'2025-05-02 09:52:12'),(127,3,4,NULL,'2025-05-02 09:53:03'),(128,3,5,NULL,'2025-04-30 09:53:03'),(129,7,4,NULL,'2025-04-28 09:53:03'),(130,5,4,NULL,'2025-05-02 10:11:54'),(131,4,6,NULL,'2025-05-05 13:32:36'),(132,8,6,NULL,'2025-05-05 13:32:36'),(136,29,6,'2025-05-09 10:55:54','2025-05-09 10:42:18'),(137,29,7,'2025-05-12 16:27:46','2025-05-09 10:42:24'),(138,4,5,NULL,'2025-05-12 16:21:17'),(139,29,5,'2025-05-12 16:27:49','2025-05-12 16:21:17'),(140,4,8,NULL,'2025-05-12 16:21:26'),(141,29,5,'2025-05-12 16:27:53','2025-05-12 16:21:26'),(142,2,6,NULL,'2025-05-12 16:56:06'),(143,2,49,NULL,'2025-05-12 16:56:25'),(144,61,5,NULL,'2025-05-12 17:25:45');
/*!40000 ALTER TABLE `services_soldiers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldiers`
--

DROP TABLE IF EXISTS `soldiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soldiers` (
  `id_soldier` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `graduation` datetime(6) DEFAULT NULL,
  `id_company` int DEFAULT NULL,
  `id_barrack` int DEFAULT NULL,
  `id_body` int DEFAULT NULL,
  PRIMARY KEY (`id_soldier`),
  KEY `fk_soldier_barrack_idx` (`id_barrack`),
  KEY `fk_soldier_body_idx` (`id_body`),
  KEY `fk_soldier_company_idx` (`id_company`),
  CONSTRAINT `fk_soldier_bodies` FOREIGN KEY (`id_body`) REFERENCES `army_bodies` (`id_body`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_soldiers_barracks` FOREIGN KEY (`id_barrack`) REFERENCES `barracks` (`id_barrack`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_soldiers_companies` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id_company`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldiers`
--

LOCK TABLES `soldiers` WRITE;
/*!40000 ALTER TABLE `soldiers` DISABLE KEYS */;
INSERT INTO `soldiers` VALUES (1,'Pepe','Gonzales',NULL,1,2,2),(2,'Martin','Salazar',NULL,2,2,3),(3,'GabrielEx22','Fernandez',NULL,5,4,1),(4,'Olivia','Menendez',NULL,2,2,4),(5,'Rodrigo','Carbonel',NULL,1,3,1),(6,'Felix','Castillo',NULL,4,2,1),(7,'Kevin','Simino',NULL,3,3,1),(8,'Marisa','Paredes',NULL,2,1,3),(9,'Mauricio123','Chambi123',NULL,4,2,10),(11,'hola21232','23',NULL,2,5,8),(16,'Mauricio','Chambi','2025-03-31 00:00:00.000000',6,3,9),(20,'Mauricio','Chambi','2025-04-08 00:00:00.000000',7,1,5),(21,'test','Chambi','2025-03-31 00:00:00.000000',6,2,NULL),(22,'prueba','Chambi',NULL,6,2,9),(23,'Mauricio','Chambi','2025-04-07 00:00:00.000000',7,3,10),(24,'Mauricio','Chambi',NULL,8,3,6),(25,'Mauricio','Chambi','2025-04-08 00:00:00.000000',7,3,9),(26,'Mauricio1','Chambi','2025-04-23 00:00:00.000000',9,5,5),(27,'DB_HOST','Chambi',NULL,6,3,9),(28,'Sara','Antony',NULL,6,2,8),(29,'Olivia','Fernandez','2025-04-15 00:00:00.000000',8,1,9),(30,'Oscar','Hernandez','2026-04-22 00:00:00.000000',7,3,7),(45,'test','test',NULL,1,2,NULL),(46,'test','test','2025-04-09 00:00:00.000000',4,2,8),(47,'Mauricio','Chambi',NULL,4,2,4),(48,'Mark','Gonzales','2025-05-06 00:00:00.000000',7,5,8),(49,'hola','hol123',NULL,3,1,2),(50,'hol123a','hol12233',NULL,3,3,2),(51,'hol123a','hol12233','2025-03-30 00:00:00.000000',3,4,2),(52,'hol1231a','hol12233','2024-03-30 00:00:00.000000',3,4,2),(53,'Mauricio','Chambi',NULL,8,31,8),(54,'Mauricio','Chambi',NULL,7,4,8),(55,'prueba','Chambi',NULL,8,5,9),(56,'MauricioASADA','Chambi',NULL,9,2,8),(57,'Mauricio Chambi','Chambi',NULL,8,5,8),(58,'Mauricio','Antony',NULL,7,2,7),(59,'John','Gonzales',NULL,8,2,7),(60,'Mauricio','Chambi',NULL,9,2,6),(61,'Mauricio','Chambi',NULL,8,2,8),(62,'Testuseroficial12','Testuseroficial12',NULL,8,4,4);
/*!40000 ALTER TABLE `soldiers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `id_soldier` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_users_rol_idx` (`role`),
  KEY `fk_users_soldiers_idx` (`id_soldier`),
  CONSTRAINT `fk_users_rol` FOREIGN KEY (`role`) REFERENCES `role` (`role`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_soldiers` FOREIGN KEY (`id_soldier`) REFERENCES `soldiers` (`id_soldier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'te2stuseern32ame912322','$2a$10$eJszBmLJUofx70xg4Ab0iefw5.2dDpdRkvZSZ7GUofJrIu7PcEfvi','SOLDADO',3),(5,'te2stuseern32ame9123','$2a$10$y3e/5GpLNWWbFx75LT1jOuPUFqcoZooGmvsrkUsHcWsRWhFahYE1a','SOLDADO',1),(6,'test212e232Oe2Ftes123','$2a$10$.y3g4EFyAb.VhRNobwClcOSvwRCjf8mIp8cZar0uhkr1R2SrUyFqe','SUB_OFICIAL',NULL),(7,'43816test','$2a$10$LH67ciV.NWoMqb97rt6UG.Q0jcthajSHNp1mXYB8C0pfBlRgwVoCS','SUB_OFICIAL',NULL),(8,'testusersuboficial','$2a$10$UysFzojrQyIkd2Qx/UlM0.9mUUIWtnUk.yOlOtCxwE6lSZGB2NmcG','SUB_OFICIAL',NULL),(9,'te2stuseern32ame91232','$2a$10$fDb8huaWCYtWfIsEZbfHcubXNY5HZkFXEEb1oHJ.tvgov0N8kUdu2','SOLDADO',2),(10,'testsoldier1','$2a$10$q83J3a8NFJHuHbWYn7PuSeRwSkaBWAmcdXzMxFjEk/H28P2tK24KS','SOLDADO',4),(11,'testsoldier2','$2a$10$lgBo1ndEWLOY1J3XYPnikuULCsPH35nzveLvFnlhr9HvJIzcDhRVi','SOLDADO',5),(12,'testsoldier3','$2a$10$pWOIN9.uYaYXom2aRZcY6exOCgwjqtN1mu2BOJopf9ekQVertsE/u','SOLDADO',6),(13,'testsoldier4','$2a$10$btDBvJBMFT.ig9fti.oXleGvAcVHlOcAfFzjMQhP48nFnxMor1e0u','SOLDADO',7),(14,'testsoldier5','$2a$10$IX2Pw3rkFAMa0mkX4FsO7Ohd6F6Y0KvqRG8L6mN/FnSSwWX/DKy1u','SOLDADO',8),(37,'Te2stuseern32ame912321','$2a$10$kUc73DaguqDxwa8fQZlCRuGq8E7pUJT8A.PDODdVn3DttAWnG1MSO','SOLDADO',28),(38,'OlviaFernandez1.','$2a$10$h2iyo7Dh211uo3b6OOPgQer7u.BdWmrMgOMedoMJ3gvcmeFMJ9vb6','SOLDADO',29),(39,'Testuseroficial1','$2a$10$V2lhPvktiX4YmvcChllPyu/dho7e0ZTOICm2yn0anMx7AANRcf7MG','OFICIAL',NULL),(59,'ASdtest123','$2a$10$ZPFEFuGNHmBDfOzrNIBOXe/cNG.elMFxSqwV0gtfBW2lbjkqC7Tae','SUB_OFICIAL',NULL),(62,'Suboficial123B','$2a$10$97Eiu35gLlf1v6zUtWI70uoKSWOjB3PHqP.EF24pdprRgQLp0OMOu','SUB_OFICIAL',NULL),(64,'Suboficial123BCA','$2a$10$qYlvSkT6giBnL17eEs5h9e39ohgxtWMgp4.nXUhwjsx5t.i3rVtIi','SUB_OFICIAL',48),(65,'Test1234','$2a$10$edRLA/nJILniU88UnDX9teDSobJoWs9d56fWogWnC7cfA6nnzsULy','SUB_OFICIAL',NULL),(66,'Test12345','$2a$10$Y4HrvJKgi/F4w81TPXEwE.WPa/NWpahmQX.0dGvlSzaGXjFXHIqX.','SUB_OFICIAL',NULL),(70,'testusersuboficial232','$2a$10$bu0RS0wYbiwmW4Y7NoVDA.phbz0YW2QA8YtfUeeR9yvUeY5JLJAUO','SUB_OFICIAL',NULL),(71,'testusersuboficial23','$2a$10$0.fXCLTr8KcfXnQnyEZVVOFfpLPQAXUOsY8KtXk6sApNZajJ8PCXm','SUB_OFICIAL',NULL),(73,'Testuseroficial112','$2a$10$YNVHWxP7pRyrvjNn2i3kg.nBt0JBqeJ/9xHg5qosr94hREIiwC6QC','SOLDADO',58),(74,'Testusersuboficial5465','$2a$10$qu6hUz4uhbvUuQtEu3Di3OtSOeMGge5VvFWj2.vvx//aw6fczytW2','SOLDADO',59),(76,'Testusersuboficial54','$2a$10$d0OAiM07TYD2Lt9PcEsQKOLJl60gK9UFXPVFM7s4W8o5338CjCSVW','SOLDADO',61);
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

-- Dump completed on 2025-05-12 18:46:42
