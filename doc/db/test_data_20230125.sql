-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'m5251201','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(2,1,'m5261202','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(3,1,'s1270141','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(4,2,'s100','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0),(5,3,'f100','$2a$12$jdDLEOdt/FaZTlTcE6GXgOoq1Xr/H1QGq2hjuRfeL3ALZ1IofyNZW',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assistance_type`
--

LOCK TABLES `assistance_type` WRITE;
/*!40000 ALTER TABLE `assistance_type` DISABLE KEYS */;
INSERT INTO `assistance_type` VALUES (1,'TA',1000,0),(2,'SA',900,0);
/*!40000 ALTER TABLE `assistance_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assistant`
--

LOCK TABLES `assistant` WRITE;
/*!40000 ALTER TABLE `assistant` DISABLE KEYS */;
INSERT INTO `assistant` VALUES (1,1,1,2,25,'m5251201',0),(2,2,1,2,26,'m5261202',0),(3,3,2,1,27,'s1270141',0);
/*!40000 ALTER TABLE `assistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assistant_course`
--

LOCK TABLES `assistant_course` WRITE;
/*!40000 ALTER TABLE `assistant_course` DISABLE KEYS */;
INSERT INTO `assistant_course` VALUES (1,1,1,0),(2,3,1,0);
/*!40000 ALTER TABLE `assistant_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assistant_graduate`
--

LOCK TABLES `assistant_graduate` WRITE;
/*!40000 ALTER TABLE `assistant_graduate` DISABLE KEYS */;
INSERT INTO `assistant_graduate` VALUES (1,'Undergraduate',0),(2,'Graduate',0);
/*!40000 ALTER TABLE `assistant_graduate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assistant_nationality`
--

LOCK TABLES `assistant_nationality` WRITE;
/*!40000 ALTER TABLE `assistant_nationality` DISABLE KEYS */;
INSERT INTO `assistant_nationality` VALUES (1,'Japanese','40:00:00',0),(2,'International','28:00:00',0);
/*!40000 ALTER TABLE `assistant_nationality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'TA',0),(2,'Staff',0),(3,'Faculty',0);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1,178,4,2022,0),(2,1,95,6,2022,0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `course_name`
--

LOCK TABLES `course_name` WRITE;
/*!40000 ALTER TABLE `course_name` DISABLE KEYS */;
INSERT INTO `course_name` VALUES (1,'HS01',0),(2,'HS03',0),(3,'HS04',0),(4,'HS05',0),(5,'HS06',0),(6,'HS07',0),(7,'HS09',0),(8,'HS10',0),(9,'HS11',0),(10,'HS12',0),(11,'HS13',0),(12,'HS16',0),(13,'HS17',0),(14,'HS19',0),(15,'HS20',0),(16,'HS21',0),(17,'HS22',0),(18,'HS23',0),(19,'PA01',0),(20,'PA02',0),(21,'PA03',0),(22,'PA04',0),(23,'EN01',0),(24,'EN02',0),(25,'EN03',0),(26,'EN04',0),(27,'EN05',0),(28,'EN06',0),(29,'EN07',0),(30,'EN08',0),(31,'EG10',0),(33,'JP01',0),(34,'JP02',0),(35,'JP03',0),(36,'JP04',0),(37,'JP05',0),(38,'JP06',0),(39,'JP07',0),(40,'EL10',0),(41,'EL11',0),(42,'EL13',0),(44,'EL14',0),(46,'EL15',0),(49,'EL21',0),(50,'EL22',0),(51,'EL24',0),(54,'EL31',0),(58,'EL32',0),(60,'EL33',0),(62,'MA01',0),(63,'MA02',0),(64,'MA03',0),(65,'MA04',0),(66,'MA05',0),(67,'MA06',0),(68,'MA07',0),(69,'MA08',0),(70,'MA09',0),(71,'MA10',0),(72,'NS01',0),(73,'NS02',0),(74,'NS03',0),(75,'NS04',0),(76,'NS05',0),(77,'LI01',0),(78,'LI03',0),(79,'LI04',0),(80,'LI06',0),(81,'LI07',0),(82,'LI08',0),(83,'LI09',0),(84,'LI10',0),(85,'LI11',0),(86,'LI12',0),(87,'LI13',0),(88,'LI14',0),(89,'PL01',0),(90,'PL02',0),(91,'PL03',0),(92,'PL04',0),(93,'PL05',0),(94,'PL06',0),(95,'FU01',0),(96,'FU02',0),(97,'FU03',0),(98,'FU04',0),(99,'FU05',0),(100,'FU06',0),(101,'FU08',0),(102,'FU09',0),(103,'FU10',0),(104,'FU11',0),(105,'FU14',0),(106,'FU15',0),(107,'SY02',0),(108,'SY04',0),(109,'SY05',0),(110,'SY06',0),(111,'SY07',0),(112,'CN02',0),(113,'CN03',0),(114,'CN04',0),(115,'CN05',0),(116,'IT01',0),(117,'IT02',0),(118,'IT03',0),(119,'IT05',0),(120,'IT06',0),(121,'IT08',0),(122,'IT09',0),(123,'IT10',0),(124,'IT11',0),(125,'SE01',0),(126,'SE04',0),(127,'SE05',0),(128,'SE06',0),(129,'SE07',0),(130,'SE08',0),(131,'OT01',0),(133,'OT02',0),(140,'OT04',0),(141,'OT05',0),(142,'OT06',0),(143,'OT08',0),(145,'OT09',0),(146,'OT10',0),(151,'OT11',0),(152,'TE01',0),(153,'TE02',0),(154,'TE03',0),(155,'TE04',0),(156,'TE05',0),(157,'TE06',0),(158,'TE07',0),(159,'TE08',0),(160,'TE09',0),(161,'TE10',0),(162,'TE11',0),(163,'TE12',0),(164,'TE13',0),(165,'TE14',0),(166,'TE15',0),(167,'TE16',0),(168,'TE17',0),(169,'TE18',0),(170,'TE19',0),(171,'TE20',0),(172,'TE21',0),(173,'TE22',0),(174,'TE23',0),(175,'IE01',0),(176,'IE02',0),(177,'IE03',0),(178,'IE04',0),(179,'OT03',0);
/*!40000 ALTER TABLE `course_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,5,0);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faculty_course`
--

LOCK TABLES `faculty_course` WRITE;
/*!40000 ALTER TABLE `faculty_course` DISABLE KEYS */;
INSERT INTO `faculty_course` VALUES (1,1,1,0);
/*!40000 ALTER TABLE `faculty_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES (1,1,'2022-04-01','2022-09-30',0),(2,2,'2022-04-01','2022-06-12',0),(3,3,'2022-06-13','2022-09-30',0),(4,4,'2022-10-01','2022-03-31',0),(5,5,'2022-10-01','2022-12-06',0),(6,6,'2022-12-07','2022-03-31',0);
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,1,1,'2022-11-01','09:00:00','10:40:00',0,100,0),(2,1,1,'2022-11-02','13:20:00','15:00:00',0,100,0),(3,1,4,'2022-11-08','13:00:00','16:00:00',30,150,0),(4,2,2,'2022-11-05','09:00:00','10:40:00',0,100,0),(5,2,3,'2022-11-02','09:00:00','10:40:00',0,100,0);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,1,1,1,2022,'2022-11-01',350,7200,0,NULL,NULL,0,NULL,NULL,1,'2022-11-21',0),(2,3,1,2,2022,'2022-11-01',200,7200,1,1,'2022-12-03',1,1,'2022-12-01',5,'2022-12-03',0);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,4,0);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'S1',0),(2,'Q1',0),(3,'Q2',0),(4,'S2',0),(5,'Q3',0),(6,'Q4',0);
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'Makoto',NULL,'Takeuchi','2020-04-01 00:00:00',0),(2,2,'Atsuki',NULL,'Yanada','2021-04-01 00:00:00',0),(3,3,'Sosyu',NULL,'Kurakane','2020-04-01 00:00:00',0),(4,4,'Takanori',NULL,'Fukuchi','2012-04-01 00:00:00',0),(5,5,'Rentaro',NULL,'Yoshioka','2012-04-01 00:00:00',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2023-01-25 11:36:50
