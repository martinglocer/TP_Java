-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: boliche_java
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `asistente`
--

DROP TABLE IF EXISTS `asistente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistente` (
  `tipo_doc` varchar(20) NOT NULL,
  `nro_doc` int NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `email` varchar(65) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `saldo` float DEFAULT '0',
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`tipo_doc`,`nro_doc`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `celular_UNIQUE` (`celular`),
  KEY `nro_doc_idx` (`nro_doc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistente`
--

LOCK TABLES `asistente` WRITE;
/*!40000 ALTER TABLE `asistente` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bebidas`
--

DROP TABLE IF EXISTS `bebidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bebidas` (
  `idbebida` int NOT NULL AUTO_INCREMENT,
  `nombre_bebida` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idbebida`),
  UNIQUE KEY `nombre_bebida_UNIQUE` (`nombre_bebida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebidas`
--

LOCK TABLES `bebidas` WRITE;
/*!40000 ALTER TABLE `bebidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `bebidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bebidas_historico`
--

DROP TABLE IF EXISTS `bebidas_historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bebidas_historico` (
  `idbebida` int NOT NULL,
  `fecha_desde` datetime NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`idbebida`,`fecha_desde`),
  CONSTRAINT `idbebida` FOREIGN KEY (`idbebida`) REFERENCES `bebidas` (`idbebida`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebidas_historico`
--

LOCK TABLES `bebidas_historico` WRITE;
/*!40000 ALTER TABLE `bebidas_historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `bebidas_historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `cod_postal` int NOT NULL,
  `nombre_ciudad` varchar(60) NOT NULL,
  PRIMARY KEY (`cod_postal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumicion`
--

DROP TABLE IF EXISTS `consumicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumicion` (
  `tipo_doc` varchar(20) NOT NULL,
  `nro_doc` int NOT NULL,
  `idbebida` int NOT NULL,
  `fecha_hora_compra` datetime NOT NULL,
  PRIMARY KEY (`tipo_doc`,`nro_doc`,`idbebida`,`fecha_hora_compra`),
  KEY `nro_docu_idx` (`nro_doc`),
  KEY `id_bebida_idx` (`idbebida`),
  CONSTRAINT `id_bebida` FOREIGN KEY (`idbebida`) REFERENCES `bebidas` (`idbebida`) ON DELETE CASCADE,
  CONSTRAINT `nro_docu` FOREIGN KEY (`nro_doc`) REFERENCES `asistente` (`nro_doc`) ON DELETE CASCADE,
  CONSTRAINT `tipo_docu` FOREIGN KEY (`tipo_doc`) REFERENCES `asistente` (`tipo_doc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumicion`
--

LOCK TABLES `consumicion` WRITE;
/*!40000 ALTER TABLE `consumicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada` (
  `tipo_doc` varchar(20) NOT NULL,
  `nro_doc` int NOT NULL,
  `idfiesta` int NOT NULL,
  `idlugar` int NOT NULL,
  `fecha_hora_fiesta` datetime NOT NULL,
  `fecha_hora_compra` datetime NOT NULL,
  `identrada` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`identrada`),
  KEY `tipodoc_idx` (`tipo_doc`),
  KEY `nrodoc_idx` (`nro_doc`),
  KEY `id_fiesta_idx` (`idfiesta`),
  KEY `id_lugar_idx` (`idlugar`),
  KEY `fecha_hora_idx` (`fecha_hora_fiesta`),
  CONSTRAINT `fecha_hora` FOREIGN KEY (`fecha_hora_fiesta`) REFERENCES `fiesta_lugar` (`fecha_hora_fiesta`),
  CONSTRAINT `fk_id_fiesta` FOREIGN KEY (`idfiesta`) REFERENCES `fiesta_lugar` (`idfiesta`),
  CONSTRAINT `fk_id_lugar` FOREIGN KEY (`idlugar`) REFERENCES `fiesta_lugar` (`idlugar`),
  CONSTRAINT `nrodoc` FOREIGN KEY (`nro_doc`) REFERENCES `asistente` (`nro_doc`),
  CONSTRAINT `tipodoc` FOREIGN KEY (`tipo_doc`) REFERENCES `asistente` (`tipo_doc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiesta`
--

DROP TABLE IF EXISTS `fiesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fiesta` (
  `idfiesta` int NOT NULL AUTO_INCREMENT,
  `nombre_fiesta` varchar(50) NOT NULL,
  `descripcion` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`idfiesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiesta`
--

LOCK TABLES `fiesta` WRITE;
/*!40000 ALTER TABLE `fiesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiesta_lugar`
--

DROP TABLE IF EXISTS `fiesta_lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fiesta_lugar` (
  `idfiesta` int NOT NULL,
  `idlugar` int NOT NULL,
  `fecha_hora_fiesta` datetime NOT NULL,
  PRIMARY KEY (`idfiesta`,`idlugar`,`fecha_hora_fiesta`),
  KEY `id_lugar_idx` (`idlugar`),
  KEY `fecha_hora_idx` (`fecha_hora_fiesta`),
  CONSTRAINT `id_fiesta` FOREIGN KEY (`idfiesta`) REFERENCES `fiesta` (`idfiesta`),
  CONSTRAINT `id_lugar` FOREIGN KEY (`idlugar`) REFERENCES `lugar` (`idlugar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiesta_lugar`
--

LOCK TABLES `fiesta_lugar` WRITE;
/*!40000 ALTER TABLE `fiesta_lugar` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiesta_lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar` (
  `idlugar` int NOT NULL AUTO_INCREMENT,
  `nombre_lugar` varchar(60) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `capacidad` int DEFAULT NULL,
  `cod_postal` int DEFAULT NULL,
  PRIMARY KEY (`idlugar`),
  KEY `codigo_postal_idx` (`cod_postal`),
  CONSTRAINT `codigo_postal` FOREIGN KEY (`cod_postal`) REFERENCES `ciudad` (`cod_postal`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_fiestas_historicos`
--

DROP TABLE IF EXISTS `precios_fiestas_historicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_fiestas_historicos` (
  `idfiesta` int NOT NULL,
  `fecha_desde` datetime NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`idfiesta`,`fecha_desde`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_fiestas_historicos`
--

LOCK TABLES `precios_fiestas_historicos` WRITE;
/*!40000 ALTER TABLE `precios_fiestas_historicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `precios_fiestas_historicos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26 10:40:58
