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
INSERT INTO `army_bodies` VALUES (1,'Cuerpo de Infantería de Marina'),(2,'Cuerpo de Operaciones Especiales Navales'),(3,'Cuerpo de Ingenieros Navales'),(4,'Cuerpo de Sanidad Naval'),(5,'Cuerpo de Comunicaciones y Guerra Electrónica'),(6,'Cuerpo de Aviación Naval'),(7,'Cuerpo de Inteligencia Marítima'),(8,'Cuerpo de Logística y Abastecimiento'),(9,'Cuerpo de Seguridad y Policía Naval'),(10,'Cuerpo de Salvamento y Rescate Marítimo'),(26,'Cuerpo de prueba armada');
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
INSERT INTO `barracks` VALUES (1,'Cuartel Fénix','Ciudad Arcadia'),(2,'Cuartel Centauro','Villa Solaris'),(3,'Cuartel Titanes','Fortaleza Nova'),(4,'Cuartel Dragón','Puerto Valhalla'),(5,'Cuartel Centinela','Base Eclipse');
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
INSERT INTO `services` VALUES (2,'Mantenimiento de armamento'),(3,'Operación de rescate'),(4,'Entrenamiento táctico'),(5,'Seguridad en base'),(6,'Supervisión de suministros'),(7,'Misión de inteligencia'),(8,'Vigilancia costera'),(10,'Defensa aérea estratégica'),(24,'cambios de aceite en los vehiculos'),(28,'test servicio de aerodinamica'),(31,'test servicio de infanteria'),(34,'Exploración de diferentes formas de arte'),(46,'Exploración de diferentes formas de arte'),(49,'Vigilancia de buque de navegación');
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
