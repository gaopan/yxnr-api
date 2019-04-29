CREATE DATABASE  IF NOT EXISTS `yxnr_auth` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yxnr_auth`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: yxnr_auth
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorizationcode`
--

DROP TABLE IF EXISTS `authorizationcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `authorizationcode` (
  `code` varchar(32) NOT NULL,
  `state` varchar(32) DEFAULT NULL,
  `scope` varchar(128) DEFAULT NULL,
  `clientId` varchar(32) DEFAULT NULL,
  `redirectUri` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorizationcode`
--

LOCK TABLES `authorizationcode` WRITE;
/*!40000 ALTER TABLE `authorizationcode` DISABLE KEYS */;
INSERT INTO `authorizationcode` VALUES ('f0f91345dfd945a4a9f2244970fb12e9','123456',NULL,'7f216e6deeb14a36b05587a04a1ff401','https://youxiunanren.cn/service/file');
/*!40000 ALTER TABLE `authorizationcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `clientId` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `logo` blob,
  `redirectUri` varchar(256) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `clientType` varchar(16) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `clientSecret` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('7f216e6deeb14a36b05587a04a1ff401','优秀男人文件服务',NULL,'https://youxiunanren.cn/service/file',NULL,'app',NULL,NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token` (
  `accessToken` varchar(32) DEFAULT NULL,
  `refreshToken` varchar(32) DEFAULT NULL,
  `expiresIn` int(11) DEFAULT NULL,
  `tokenType` varchar(32) DEFAULT NULL,
  `clientId` varchar(32) DEFAULT NULL,
  `expireTime` datetime DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `username` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES ('193611bc848b4aaca44e3be9b27a8210','48ef29f63a3e4569b10c28c7d9b08e59',86400,NULL,'7f216e6deeb14a36b05587a04a1ff401','2019-04-30 06:01:09','f0f91345dfd945a4a9f2244970fb12e9',NULL),('9fa22428db3e48049c5eab9e1d9a64f0','a02c7a0153d84c2bb9d77776c8d13bf3',86400,NULL,'7f216e6deeb14a36b05587a04a1ff401','2019-04-30 06:02:24','f0f91345dfd945a4a9f2244970fb12e9',NULL),('da4ff1031fe64fadb9c1ff2093dfeb43','b85acb01e2204694b843f0133ff39a16',86400,NULL,'7f216e6deeb14a36b05587a04a1ff401','2019-04-30 06:06:16','f0f91345dfd945a4a9f2244970fb12e9',NULL),('6ca64b2996d34a7b849ef702ae10307f','6488a6c9ad604297b2a269e83c39eaf6',86400,'Bearer','7f216e6deeb14a36b05587a04a1ff401','2019-04-30 06:08:18','f0f91345dfd945a4a9f2244970fb12e9',NULL);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-29 18:12:48
