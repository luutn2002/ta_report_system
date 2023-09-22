-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: ie04t2022c
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `authority_id` bigint unsigned NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `ibfk_account_authority_id_authorities_id` (`authority_id`),
  CONSTRAINT `ibfk_account_authority_id_authorities_id` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'m5251201','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(2,1,'m5261202','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(3,1,'m5251111','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(4,2,'s100','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(5,3,'f100','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistance_type`
--

DROP TABLE IF EXISTS `assistance_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistance_type` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `hourly_pay` bigint unsigned NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistance_type`
--

LOCK TABLES `assistance_type` WRITE;
/*!40000 ALTER TABLE `assistance_type` DISABLE KEYS */;
INSERT INTO `assistance_type` VALUES (1,'TA',1000,0),(2,'SA',900,0);
/*!40000 ALTER TABLE `assistance_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant`
--

DROP TABLE IF EXISTS `assistant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistant` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `assistant_nationality_id` bigint unsigned NOT NULL,
  `assistant_graduate_id` bigint unsigned NOT NULL,
  `student_year` int unsigned NOT NULL,
  `student_id` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `ibfk_assistant_assistant_graduates_id_assistant_graduate_id_idx` (`assistant_graduate_id`),
  KEY `ibfk_assistant_assistant_nationality_id_nationalities_id` (`assistant_nationality_id`),
  CONSTRAINT `ibfk_assistant_assistant_graduates_id_assistant_graduate_id` FOREIGN KEY (`assistant_graduate_id`) REFERENCES `assistant_graduate` (`id`),
  CONSTRAINT `ibfk_assistant_assistant_nationality_id_nationalities_id` FOREIGN KEY (`assistant_nationality_id`) REFERENCES `assistant_nationality` (`id`),
  CONSTRAINT `ibfk_assistant_user_id_users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant`
--

LOCK TABLES `assistant` WRITE;
/*!40000 ALTER TABLE `assistant` DISABLE KEYS */;
INSERT INTO `assistant` VALUES (1,1,1,2,25,'m5251201',0),(2,2,1,2,26,'m5261202',0),(3,3,1,2,27,'m5251111',0);
/*!40000 ALTER TABLE `assistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant_course`
--

DROP TABLE IF EXISTS `assistant_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistant_course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `assistant_id` bigint unsigned NOT NULL,
  `course_id` bigint unsigned NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_assistant_course_assistant_id_assistants_id` (`assistant_id`),
  KEY `ibfk_assistant_course_course_id_courses_id` (`course_id`),
  CONSTRAINT `ibfk_assistant_course_assistant_id_assistants_id` FOREIGN KEY (`assistant_id`) REFERENCES `assistant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ibfk_assistant_course_course_id_courses_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant_course`
--

LOCK TABLES `assistant_course` WRITE;
/*!40000 ALTER TABLE `assistant_course` DISABLE KEYS */;
INSERT INTO `assistant_course` VALUES (1,1,1,0),(2,3,1,0);
/*!40000 ALTER TABLE `assistant_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant_graduate`
--

DROP TABLE IF EXISTS `assistant_graduate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistant_graduate` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant_graduate`
--

LOCK TABLES `assistant_graduate` WRITE;
/*!40000 ALTER TABLE `assistant_graduate` DISABLE KEYS */;
INSERT INTO `assistant_graduate` VALUES (1,'Undergraduate',0),(2,'Graduate',0);
/*!40000 ALTER TABLE `assistant_graduate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant_nationality`
--

DROP TABLE IF EXISTS `assistant_nationality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistant_nationality` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `weekly_work_hour_limit` time NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant_nationality`
--

LOCK TABLES `assistant_nationality` WRITE;
/*!40000 ALTER TABLE `assistant_nationality` DISABLE KEYS */;
INSERT INTO `assistant_nationality` VALUES (1,'Japanese','40:00:00',0),(2,'International','28:00:00',0);
/*!40000 ALTER TABLE `assistant_nationality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'TA',0),(2,'Staff',0),(3,'Faculty',0);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `coordinator_id` bigint unsigned NOT NULL,
  `course_name_id` bigint unsigned NOT NULL,
  `period_id` bigint unsigned NOT NULL,
  `open_year` year NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_cource_period_id_periods_id_idx` (`period_id`),
  KEY `ibfk_course_course_name_id_course_name_id_idx` (`course_name_id`),
  KEY `ibfk_course_coordinator_id_faculties_id` (`coordinator_id`),
  CONSTRAINT `ibfk_course_coordinator_id_faculties_id` FOREIGN KEY (`coordinator_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `ibfk_course_course_name_id_course_name_id` FOREIGN KEY (`course_name_id`) REFERENCES `course_name` (`id`),
  CONSTRAINT `ibfk_course_period_id_periods_id` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1,178,4,2022,0),(2,1,95,6,2022,0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_name`
--

DROP TABLE IF EXISTS `course_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_name` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_name`
--

LOCK TABLES `course_name` WRITE;
/*!40000 ALTER TABLE `course_name` DISABLE KEYS */;
INSERT INTO `course_name` VALUES (1,'HS01',0),(2,'HS03',0),(3,'HS04',0),(4,'HS05',0),(5,'HS06',0),(6,'HS07',0),(7,'HS09',0),(8,'HS10',0),(9,'HS11',0),(10,'HS12',0),(11,'HS13',0),(12,'HS16',0),(13,'HS17',0),(14,'HS19',0),(15,'HS20',0),(16,'HS21',0),(17,'HS22',0),(18,'HS23',0),(19,'PA01',0),(20,'PA02',0),(21,'PA03',0),(22,'PA04',0),(23,'EN01',0),(24,'EN02',0),(25,'EN03',0),(26,'EN04',0),(27,'EN05',0),(28,'EN06',0),(29,'EN07',0),(30,'EN08',0),(31,'EG10',0),(33,'JP01',0),(34,'JP02',0),(35,'JP03',0),(36,'JP04',0),(37,'JP05',0),(38,'JP06',0),(39,'JP07',0),(40,'EL10',0),(41,'EL11',0),(42,'EL13',0),(44,'EL14',0),(46,'EL15',0),(49,'EL21',0),(50,'EL22',0),(51,'EL24',0),(54,'EL31',0),(58,'EL32',0),(60,'EL33',0),(62,'MA01',0),(63,'MA02',0),(64,'MA03',0),(65,'MA04',0),(66,'MA05',0),(67,'MA06',0),(68,'MA07',0),(69,'MA08',0),(70,'MA09',0),(71,'MA10',0),(72,'NS01',0),(73,'NS02',0),(74,'NS03',0),(75,'NS04',0),(76,'NS05',0),(77,'LI01',0),(78,'LI03',0),(79,'LI04',0),(80,'LI06',0),(81,'LI07',0),(82,'LI08',0),(83,'LI09',0),(84,'LI10',0),(85,'LI11',0),(86,'LI12',0),(87,'LI13',0),(88,'LI14',0),(89,'PL01',0),(90,'PL02',0),(91,'PL03',0),(92,'PL04',0),(93,'PL05',0),(94,'PL06',0),(95,'FU01',0),(96,'FU02',0),(97,'FU03',0),(98,'FU04',0),(99,'FU05',0),(100,'FU06',0),(101,'FU08',0),(102,'FU09',0),(103,'FU10',0),(104,'FU11',0),(105,'FU14',0),(106,'FU15',0),(107,'SY02',0),(108,'SY04',0),(109,'SY05',0),(110,'SY06',0),(111,'SY07',0),(112,'CN02',0),(113,'CN03',0),(114,'CN04',0),(115,'CN05',0),(116,'IT01',0),(117,'IT02',0),(118,'IT03',0),(119,'IT05',0),(120,'IT06',0),(121,'IT08',0),(122,'IT09',0),(123,'IT10',0),(124,'IT11',0),(125,'SE01',0),(126,'SE04',0),(127,'SE05',0),(128,'SE06',0),(129,'SE07',0),(130,'SE08',0),(131,'OT01',0),(133,'OT02',0),(140,'OT04',0),(141,'OT05',0),(142,'OT06',0),(143,'OT08',0),(145,'OT09',0),(146,'OT10',0),(151,'OT11',0),(152,'TE01',0),(153,'TE02',0),(154,'TE03',0),(155,'TE04',0),(156,'TE05',0),(157,'TE06',0),(158,'TE07',0),(159,'TE08',0),(160,'TE09',0),(161,'TE10',0),(162,'TE11',0),(163,'TE12',0),(164,'TE13',0),(165,'TE14',0),(166,'TE15',0),(167,'TE16',0),(168,'TE17',0),(169,'TE18',0),(170,'TE19',0),(171,'TE20',0),(172,'TE21',0),(173,'TE22',0),(174,'TE23',0),(175,'IE01',0),(176,'IE02',0),(177,'IE03',0),(178,'IE04',0),(179,'OT03',0),(180,'HS02',0),(181,'HS08',0),(182,'HS14',0),(183,'HS15',0),(184,'HS18',0);
/*!40000 ALTER TABLE `course_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `ibfk_faculty_user_id_users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,5,0);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_course`
--

DROP TABLE IF EXISTS `faculty_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `faculty_id` bigint unsigned NOT NULL,
  `course_id` bigint unsigned NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_faculty_course_course_id_faculties_id` (`faculty_id`),
  KEY `ibfk_faculty_course_faculty_id_courses_id` (`course_id`),
  CONSTRAINT `ibfk_faculty_course_course_id_faculties_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `ibfk_faculty_course_faculty_id_courses_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_course`
--

LOCK TABLES `faculty_course` WRITE;
/*!40000 ALTER TABLE `faculty_course` DISABLE KEYS */;
INSERT INTO `faculty_course` VALUES (1,1,1,0);
/*!40000 ALTER TABLE `faculty_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `period` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `term_id` bigint unsigned NOT NULL,
  `period_from` date NOT NULL,
  `period_to` date NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_period_term_id_terms_id_idx` (`term_id`),
  CONSTRAINT `ibfk_period_term_id_terms_id` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES (1,1,'2022-04-01','2022-09-30',0),(2,2,'2022-04-01','2022-06-12',0),(3,3,'2022-06-13','2022-09-30',0),(4,4,'2022-10-01','2022-03-31',0),(5,5,'2022-10-01','2022-12-06',0),(6,6,'2022-12-07','2022-03-31',0);
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `report_id` bigint unsigned NOT NULL,
  `work_category_id` bigint unsigned NOT NULL,
  `target_date` date NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `break_minute` int NOT NULL DEFAULT '0',
  `total_work_minute` int NOT NULL DEFAULT '0',
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_record_report_id_reports_id` (`report_id`),
  KEY `ibfk_record_work_category_id_work_categories_id` (`work_category_id`),
  CONSTRAINT `ibfk_record_report_id_reports_id` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`),
  CONSTRAINT `ibfk_record_work_category_id_work_categories_id` FOREIGN KEY (`work_category_id`) REFERENCES `work_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,1,1,'2022-11-01','09:00:00','10:40:00',0,100,0),(2,1,1,'2022-11-02','13:20:00','15:00:00',0,100,0),(3,1,1,'2022-11-08','13:00:00','19:00:00',60,300,0),(4,2,1,'2022-11-05','09:00:00','19:40:00',280,360,0),(5,2,1,'2022-11-02','09:00:00','18:00:00',60,480,1),(6,2,1,'2022-11-03','10:00:00','19:00:00',60,480,1),(7,2,1,'2022-11-04','10:00:00','19:00:00',60,480,1),(8,2,1,'2022-11-01','12:00:00','22:00:00',240,360,0),(9,3,1,'2022-10-31','10:00:00','19:00:00',180,360,0),(10,2,1,'2022-11-06','13:00:00','22:00:00',180,360,1),(11,3,1,'2022-10-30','10:00:00','19:00:00',60,480,1),(12,1,1,'2022-11-03','08:00:00','17:00:00',60,480,0),(13,1,1,'2022-11-04','10:00:00','19:00:00',60,480,0),(14,1,1,'2022-11-05','10:00:00','19:00:00',60,480,0),(15,1,4,'2022-11-08','10:00:00','13:00:00',0,180,0),(16,5,3,'2023-01-12','12:00:00','15:00:00',0,180,0),(17,2,1,'2022-11-04','10:00:00','17:00:00',60,360,0),(18,2,1,'2022-11-02','10:00:00','19:00:00',180,360,0),(19,2,1,'2022-11-03','10:00:00','17:00:00',60,360,0),(20,3,1,'2022-10-30','10:00:00','12:20:00',0,140,1),(21,3,1,'2022-10-30','09:00:00','16:00:00',60,360,1);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `assistant_id` bigint unsigned NOT NULL,
  `course_id` bigint unsigned NOT NULL,
  `assistance_type_id` bigint unsigned NOT NULL,
  `target_year` year NOT NULL,
  `target_month` date NOT NULL,
  `monthly_total_work_minute` int NOT NULL DEFAULT '0',
  `monthly_total_allocated_minute` int DEFAULT '120',
  `verified` tinyint NOT NULL,
  `verified_staff_id` bigint unsigned DEFAULT NULL,
  `verified_date` date DEFAULT NULL,
  `approved` tinyint NOT NULL,
  `approved_faculty_id` bigint unsigned DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `edited_user_id` bigint unsigned NOT NULL,
  `edited_date` date DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ibfk_report_assistant_types_id_assistant_type_id_idx` (`assistance_type_id`),
  KEY `ibfk_report_assistant_id_assistants_id_idx` (`assistant_id`),
  KEY `ibfk_report_course_id_courses_id_idx` (`course_id`),
  KEY `ibfk_report_approved_faculty_id_faculties_id` (`approved_faculty_id`),
  KEY `ibfk_report_edited_user_id_users_id` (`edited_user_id`),
  KEY `ibfk_report_verified_staff_id_staffs_id` (`verified_staff_id`),
  CONSTRAINT `ibfk_report_approved_faculty_id_faculties_id` FOREIGN KEY (`approved_faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `ibfk_report_assistance_type_id_assistance_types_id` FOREIGN KEY (`assistance_type_id`) REFERENCES `assistance_type` (`id`),
  CONSTRAINT `ibfk_report_assistant_id_assistants_id` FOREIGN KEY (`assistant_id`) REFERENCES `assistant` (`id`),
  CONSTRAINT `ibfk_report_course_id_courses_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `ibfk_report_edited_user_id_users_id` FOREIGN KEY (`edited_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `ibfk_report_verified_staff_id_staffs_id` FOREIGN KEY (`verified_staff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,1,1,1,2022,'2022-11-01',2120,7200,0,NULL,NULL,0,NULL,NULL,1,'2022-11-21',0),(2,3,1,1,2022,'2022-11-01',1800,7200,1,1,'2022-12-03',1,1,'2022-12-01',5,'2022-12-03',0),(3,3,1,1,2022,'2022-10-01',360,7200,0,NULL,NULL,0,NULL,NULL,3,'2023-01-31',0),(4,1,2,1,2022,'2022-11-01',480,7200,0,NULL,NULL,0,NULL,NULL,1,'2023-01-31',0),(5,3,2,1,2023,'2023-01-01',180,7200,0,NULL,NULL,0,NULL,NULL,3,'2023-02-01',0);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `ibfk_staff_user_id_users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,4,0);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'S1',0),(2,'Q1',0),(3,'Q2',0),(4,'S2',0),(5,'Q3',0),(6,'Q4',0);
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `account_id` bigint unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `start_date` datetime NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  CONSTRAINT `ibfk_user_account_id_accounts_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'Makoto',NULL,'Takeuchi','2020-04-01 00:00:00',0),(2,2,'Atsuki',NULL,'Yanada','2021-04-01 00:00:00',0),(3,3,'Sosyu',NULL,'Kurakane','2020-04-01 00:00:00',0),(4,4,'Takanori',NULL,'Fukuchi','2012-04-01 00:00:00',0),(5,5,'Rentaro',NULL,'Yoshioka','2012-04-01 00:00:00',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_category`
--

DROP TABLE IF EXISTS `work_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_category` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_category`
--

LOCK TABLES `work_category` WRITE;
/*!40000 ALTER TABLE `work_category` DISABLE KEYS */;
INSERT INTO `work_category` VALUES (1,'Lectures and exercises',0),(2,'Supervising examinations',0),(3,'Preparation of teaching materials',0),(4,'Grading',0),(5,'Others',0);
/*!40000 ALTER TABLE `work_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-01  9:28:39
