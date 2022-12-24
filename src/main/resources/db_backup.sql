-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'fa fa-laptop mr-1',1,'Development'),(2,'fa fa-handshake-o mr-1',2,'Marketing'),(3,'fa fa-pencil-square-o mr-1',3,'Design'),(4,'fa fa-camera-retro mr-1',4,'Photography'),(5,'fa fa-bar-chart mr-1',5,'Business');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `hour_count` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_update` date DEFAULT NULL,
  `letures_count` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `promotion_price` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `lecturer_id` varchar(255) DEFAULT NULL,
  `is_delete` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK72l5dj585nq7i6xxv1vj51lyn` (`category_id`),
  KEY `FKit07wvai906qvcttvvg8pi1rr` (`lecturer_id`),
  CONSTRAINT `FK72l5dj585nq7i6xxv1vj51lyn` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKit07wvai906qvcttvvg8pi1rr` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Tiến Anh Nguyễn',2,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','No cmt',0,0,'','2020-03-25',0,1000000,1000000,'Hiền khô',0,NULL,1),(2,'Tiến Anh Nguyễn',1,'Lofasdasdasdas','Mlem Mlem',10,222,'',NULL,112,90000,81000,'Shopping',0,NULL,1),(3,'Nguyen Anh',1,'[ MIỄN PHÍ ]\r\nBản thiết kế Website giúp tạo sản phẩm thực tế để đi làm\r\nAi muốn nhận File thiết kế PSD này để luyện tay không ạ\r\n','CSS Beginer To Advancer',5,10,'','2020-03-25',20,100000000,95000000,'CSS',0,NULL,1),(4,'Tiến Anh Nguyễn',2,'sssaaaaaaaaaaaaaaaaaaaadasdasdasdas','Mlem Mlem',22,20,'','2020-03-23',32,9231231222,7200360354,'Marketing',0,NULL,1),(5,'Nguyen Anh',1,'','Quản lý hệ thống',0,0,'','2020-03-24',0,0,0,'Marketing',0,NULL,1),(6,'Nguyen Anh',1,'','Quản lý hệ thống',0,0,'','2020-03-24',0,0,0,'Development',0,NULL,1),(7,'Tiến Anh Nguyễn',1,'asdasssssssssssssssss','Ai biết :))',7,4,'','2020-03-26',3,999999,930000,'aawww',1,'7087a795-add3-4110-bd35-fa4f23e9d89d',1),(10,'Tiến Anh Nguyễn',1,'Hack được mới lạ','Hack NASA By Html :)))',10,20,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585646059/Elearning/download_3_zgpkoh.jpg','2020-04-07',12,1000000,900000,'Ethical Hacking	',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(11,'Tiến Anh Nguyễn',2,'dan tranh,nhac tieng anh soi dong,nhac hoa,hoa tau dan tranh,nhạc phim trung quốc hay nhất,hoa tau sao truc,nhac phim trung quoc,đàn tranh,nhung ca khuc nhac hoa hay nhat,những bài hát trung quốc hay nhất,nhac hoa,nhac trung quoc,nhac hoa hay ','Mlem Mlem',12,12,'','2020-03-26',12,9999999,8800000,'Test',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',1),(12,'Tiến Anh Nguyễn',1,'Quảng cáo OOH','Quảng cáo',4,9,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585646441/Elearning/strategii-de-marketing-1567135466007693827794_xe5cfb.jpg','2020-04-06',5,900000,864000,'Marketing',0,NULL,0),(13,'Nguyễn Tiến Anh',1,'','Quản lý',0,0,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585646060/Elearning/download_gpsbyh.png','2020-03-27',0,0,0,'Ethical Hacking',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',1),(14,'Tiến Anh Nguyễn',1,'','Ai chơi đâu mà biết',0,0,'','2020-03-28',0,0,0,'Thảo dược',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',1),(15,'Nguyễn Tiến Anh',1,'Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can \"just run\".','We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need minimal Spring configuration.',5,120,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585646060/Elearning/download_gpsbyh.png','2020-03-29',10,10000000,9500000,'SpringBoot',1,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(16,'Tiến Anh Nguyễn',1,'Cha cha cha là một điệu nhảy đáng để học','Học nhảy cha cha cha',12,99,'http://res.cloudinary.com/dinw4xpxn/image/upload/v1586161634/Elearning/maxresdefault.jpg','2020-04-06',88,1099999,968000,'Học nhảy ',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(17,'Tiến Anh Nguyễn',2,'Học bói toán','Học đoán tương lai',10,100,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585645640/Elearning/91002935_831989837286658_6934430187807309824_n_fdxigc.jpg','2020-04-07',99,9999000,8999100,'Meme Creator',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',1),(18,'Nguyễn Tiến Anh',1,'Võ cổ truyền Việt Nam dùng để chỉ những hệ phái võ thuật lưu truyền trong suốt trường kỳ lịch sử của dân tộc Việt Nam, được người Việt sáng tạo và bồi đắp qua nhiều thế hệ.','Võ việt nam',1,108,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585645640/Elearning/crash-course-font_cvgu5l.png','2020-04-06',49,999999,990000,'Võ cổ truyền',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(19,'Đéo phải Nguyễn Tiến Anh',2,'Học đi là biết','Học edm',12,3,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585645905/Elearning/images_tddllt.jpg','2020-04-06',3,99999,88000,'Music',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(20,'Tiến Anh Nguyễn',4,'Photoshop course','Quản lý hệ thống',2,24,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585645640/Elearning/download_1_kwzy2w.jpg','2020-04-06',12,9900000,9702000,'Photoshop',0,NULL,0),(21,'Tiến Anh Nguyễn',1,'I think you are right','Học nhạc cùng Zen',12,81,'https://res.cloudinary.com/dinw4xpxn/image/upload/v1585646509/Elearning/578650fe544c4_xzgphp.jpg','2020-04-06',82,100000,88000,'Music full of Zen',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(22,'Nguyen Anh',5,'No content','Nghĩ nhiều quá làm gì :))',5,200,'http://res.cloudinary.com/dinw4xpxn/image/upload/v1585648760/Elearning/eye.png','2020-04-06',12,1000000,950000,'Thinker',0,NULL,0),(23,'Nguyễn Tiến Anh',1,'No cmt','Học máy',5,10,'http://res.cloudinary.com/dinw4xpxn/image/upload/v1586152891/Elearning/machine-learning-avatar-696x365.jpg','2020-04-07',80,1000000,950000,'Machine learning',1,'7087a795-add3-4110-bd35-fa4f23e9d89d',0),(24,'Tiến Anh Nguyễn',4,'No Cmt','No cmt',5,20,'http://res.cloudinary.com/dinw4xpxn/image/upload/v1586268801/Elearning/need_for_speed_heat_lamborghini-wallpaper-1920x1080.jpg','2020-04-07',100,100000030,95000029,'Khoá học mới',0,'7087a795-add3-4110-bd35-fa4f23e9d89d',0);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `password_reset_token` (
  `id` int(11) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g0guo4k8krgpwuagos61oc06j` (`token`),
  KEY `FK83nsrttkwkb6ym0anu051mtxn` (`user_id`),
  CONSTRAINT `FK83nsrttkwkb6ym0anu051mtxn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (1,'2020-04-07 17:23:23','1907c89c-c88a-4e5e-9db7-cd7e53b7282d','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(2,'2020-04-07 17:24:16','55970bb6-2eea-482e-8b68-85d66c3bc8a1','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(3,'2020-04-07 17:24:46','e545a71e-3f39-4a4e-b6ba-8a251b94a6b9','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(4,'2020-04-07 17:24:50','2711ce63-9d2e-4c00-932d-35423a1d2871','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(5,'2020-04-07 17:25:02','0872aa3f-e365-4da1-aa18-b00b6c7ce930','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(6,'2020-04-07 17:25:06','5110521c-88e6-4645-aab4-9f81bf501fec','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(7,'2020-04-07 17:28:00','5928b7fc-fc70-47ef-9cdd-e73bbbf1a9e7','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(8,'2020-04-07 17:28:12','25ba02de-12b9-4b32-9f8d-da2654825ed8','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(9,'2020-04-07 17:30:13','308256db-3646-4e3a-b019-5142479ea421','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(10,'2020-04-07 17:41:09','4d56b779-c0b1-4d8f-b51d-8cd89f029806','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(11,'2020-04-07 17:42:07','4ddfd1e9-d0bd-4dd7-9398-f513edf3bc6a','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(12,'2020-04-07 17:42:10','ebb15af4-bfda-4465-9611-8ef62c8cb039','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(13,'2020-04-07 17:43:26','31fbb16e-a07c-4dbc-8189-dde003feae56','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(14,'2020-04-07 17:47:10','a051993d-50dd-4dd7-b0e8-644c23c1eb03','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(15,'2020-04-07 17:50:27','0f839ddb-a9ee-45f9-a651-958e81093002','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(16,'2020-04-07 17:53:15','69f5f7fb-e950-4c1a-8c28-06cc245e40c7','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(17,'2020-04-07 17:55:53','5b15e534-3f25-467a-a1f1-5e8c185d0423','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(18,'2020-04-07 17:56:20','af789161-a9c2-4fae-8887-5631a2abfcab','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(19,'2020-04-07 17:56:41','19f57893-c318-473b-ba12-a239e661c00f','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(20,'2020-04-07 17:57:05','91d79333-6d88-4486-bf88-8f5b5f788199','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(21,'2020-04-07 17:57:34','0f659449-2307-4fab-bec2-59c390dc7bbe','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(22,'2020-04-07 17:57:38','8bc757be-037d-4f3b-93b0-90fc7c61d4a3','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(23,'2020-04-07 17:58:27','f7cc3d65-cd6a-4ba7-9a24-e62c05603484','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(24,'2020-04-07 17:58:50','7f852795-c27a-45f5-ba23-d3e8e3d92e4e','162d1ca7-7a94-4d10-9f5c-d70398f28ca8'),(25,'2020-04-07 17:59:44','d756e1ef-24d9-4e3d-9bf0-b985065fa388','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(26,'2020-04-07 18:05:35','2bd34c6d-1fd5-4fdf-a093-3f4b8b25d1be','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(27,'2020-04-07 18:14:51','6c89807d-66eb-4522-9587-088c1094ec79','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(28,'2020-04-07 18:15:04','7c33ad85-c94c-431f-8166-d2ae4e761cea','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(29,'2020-04-07 18:17:39','9c335276-6b29-4548-bc2b-82fafbd87fa4','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(30,'2020-04-07 18:20:10','9ee30211-e8ae-4645-9179-6f4938c21ac4','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(31,'2020-04-07 20:05:32','ad3b47fb-02ed-43d1-ac77-f3c7cc2c262a','c331fa61-f16c-4a59-a9b4-ab831459e2e8');
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('5f85e643-c929-47ac-ae5c-3f4b40fdee6b','Quản lý hệ thống','ROLE_ADMIN'),('5f85e643-c929-47ac-ae5c-3f4b40fdee6c','Học viên','ROLE_MEMBER'),('5f85e643-c929-47ac-ae5c-3f4b40fdee6d','Quản lý khoá học','ROLE_TEACHER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `targets`
--

DROP TABLE IF EXISTS `targets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `targets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `order_index` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoyy2lgy3w04ylavtb7paw1v1d` (`course_id`),
  CONSTRAINT `FKoyy2lgy3w04ylavtb7paw1v1d` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `targets`
--

LOCK TABLES `targets` WRITE;
/*!40000 ALTER TABLE `targets` DISABLE KEYS */;
INSERT INTO `targets` VALUES (1,3,0,' kinh nghiêm rửa chén 8 năm :))');
/*!40000 ALTER TABLE `targets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:45
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `user_courses`
--

DROP TABLE IF EXISTS `user_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb84hga2qpwc4vv44lmyb8mwux` (`course_id`),
  KEY `FK5i2mwg17kvpk92fy6cdii93da` (`user_id`),
  CONSTRAINT `FK5i2mwg17kvpk92fy6cdii93da` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKb84hga2qpwc4vv44lmyb8mwux` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_courses`
--

LOCK TABLES `user_courses` WRITE;
/*!40000 ALTER TABLE `user_courses` DISABLE KEYS */;
INSERT INTO `user_courses` VALUES (13,15,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3f6b1258-d45f-4a65-a907-3f43f3390bad'),(14,12,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3f6b1258-d45f-4a65-a907-3f43f3390bad'),(15,12,'5f85e643-c929-47ac-ae5c-3f4b40fdee6b','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(16,19,'5f85e643-c929-47ac-ae5c-3f4b40fdee6b','c331fa61-f16c-4a59-a9b4-ab831459e2e8'),(17,19,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3f6b1258-d45f-4a65-a907-3f43f3390bad'),(19,18,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3f6b1258-d45f-4a65-a907-3f43f3390bad'),(20,20,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3f6b1258-d45f-4a65-a907-3f43f3390bad'),(21,10,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','3396aa55-d1bf-4233-83d0-47ea48f2d588'),(22,19,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','18d0b240-d118-43f4-b68f-6ad416d74b6c'),(23,23,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c','0dc13893-41bb-43cd-b602-19101b890c29');
/*!40000 ALTER TABLE `user_courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:45
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `confirm` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `is_delete` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('09898b32-c867-43b8-b23a-8ba41279b64d',NULL,NULL,NULL,'cccccccc@gmail.com',NULL,NULL,'$2a$12$spX5mc2mjFpAXhY7vnJvr.XLlYNVjWZ62mDJ57CBxyRGHuUKONgMG',NULL,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c',NULL,1),('0dc13893-41bb-43cd-b602-19101b890c29','260/3/43 Hiep Tan, Tan Phu, Ho Chi Minh','http://res.cloudinary.com/dinw4xpxn/image/upload/v1586268408/Elearning/jukes_-_pain_l.jpg',NULL,'anhbren@gmail.com','','Nguyen Tien Anh','$2a$12$oiqZvzSn41QpceGmcsDFYOy1WhIHki58cEhWc6ct.9xeeWmzD17au','0974742081','5f85e643-c929-47ac-ae5c-3f4b40fdee6c','',0),('162d1ca7-7a94-4d10-9f5c-d70398f28ca8','2wqdwe12wqd 12',NULL,NULL,'socksockmoi4zui@gmail.com','','Anh Hung Thien Ha','Maket007','123123123122333','5f85e643-c929-47ac-ae5c-3f4b40fdee6b','',0),('18d0b240-d118-43f4-b68f-6ad416d74b6c','213, Hoà Bình, Tân Phú, TP.HCM','http://res.cloudinary.com/dinw4xpxn/image/upload/v1586151874/Elearning/userAvatar/jukes_-_pain_l.jpg',NULL,'admin@gmail.com','profile.php?id=100014765583606','Nguyễn Tiến Anh','$2a$12$Wqfvwhw52dbyjXkDScMkFOOcWIxausPx42tWMtbHrkWK7CbrF0mES','09123123123','5f85e643-c929-47ac-ae5c-3f4b40fdee6c','https://www.instagram.com/',0),('1dd4ae4e-c451-42d0-88f9-81f2ab6b4a28',NULL,NULL,NULL,'Manhlong@gmail.com',NULL,NULL,'$2a$12$TAV8Nl7NfEQ7pQ6sSeaMVuJwnYWGuDFjRLdIBG5NgyLDuHC5aW50O',NULL,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c',NULL,0),('267eb413-7bb5-405a-baf7-7d688bb8963a','','',NULL,'socksockmoizui45@gmail.com','','adminsaskkk','$2a$12$8rNBg18SdgEFOe2eCOYWY.SLh64PtyvX/V5uOhEqfnZ2hszIWEgme','07322222222','5f85e643-c929-47ac-ae5c-3f4b40fdee6b','',0),('2eba8b51-2a2e-4cf2-98c7-633b69aae1da','2wqdwe12wqd 12',NULL,NULL,'socksockmoizui1@gmail.com','','kakass','$2a$12$X9IagHzM/vv0qJvMiKOLweuHUBJJ17faACpx0fYzQPADIKAecA2/m','12312312312','5f85e643-c929-47ac-ae5c-3f4b40fdee6b','',1),('3396aa55-d1bf-4233-83d0-47ea48f2d588','','http://res.cloudinary.com/dinw4xpxn/image/upload/v1586016617/Elearning/userAvatar/nam-mo-thay-rong-bay-507x380.jpg',NULL,'Manhlongquagiang@gmail.com','','Katana','$2a$12$sFCTTPKw8AvSaR4CSe26Oe/UYWh1Na5O/AxZSXiD.CaVIxGLd9Aou','012383232323','5f85e643-c929-47ac-ae5c-3f4b40fdee6c','',0),('3f6b1258-d45f-4a65-a907-3f43f3390bad','AAAAAAAAAAAAAA','http://res.cloudinary.com/dinw4xpxn/image/upload/v1585645433/Elearning/userAvatar/0abf6c47e3febb4d59d93c252542da1a.jpg',NULL,'socksockmoizui2@gmail.com','AAAAAAAAA','AnhGrew','$2a$12$sFCTTPKw8AvSaR4CSe26Oe/UYWh1Na5O/AxZSXiD.CaVIxGLd9Aou','093555555','5f85e643-c929-47ac-ae5c-3f4b40fdee6c','AAAAAAAAAAA',0),('7087a795-add3-4110-bd35-fa4f23e9d89d','213, Hoà Bình, Tân Phú, TP.HCM','http://res.cloudinary.com/dinw4xpxn/image/upload/v1586165284/Elearning/download%20%283%29.jpg',NULL,'socksockmoizui3@gmail.com','profile.php?id=100014765583606','Anhbren333','$2a$12$TAV8Nl7NfEQ7pQ6sSeaMVuJwnYWGuDFjRLdIBG5NgyLDuHC5aW50O','0973732081','5f85e643-c929-47ac-ae5c-3f4b40fdee6d','https://www.youtube.com/watch?v=wM6Uoow7Xw8',0),('75951a76-45ce-4856-b797-404054b75cc3','AAAAAAAAAAAAAA',NULL,NULL,'anhbrennbk@gmail.com','','Aka47','$2a$12$TAV8Nl7NfEQ7pQ6sSeaMVuJwnYWGuDFjRLdIBG5NgyLDuHC5aW50O','12312312312','5f85e643-c929-47ac-ae5c-3f4b40fdee6c','AAAAAAAAAAA',1),('7b063349-c419-45d2-b2b2-884a1f2f36c9',NULL,NULL,NULL,'jjjjjj@gmail.com',NULL,NULL,'$2a$12$ITylAasrSJhW7QPCyeg55ehMJ76j2Y23nzlfZwBrVBxdtBodPpEAS',NULL,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c',NULL,1),('be17d840-8885-4fbf-8be2-c8380af24e40',NULL,NULL,NULL,'anhreee@gmail.com',NULL,NULL,'$2a$12$ijkWO1RamModoqUS/oG.2.VQn2KqxJ.sPdo.HkFtSBumMo1EVjf2m',NULL,'5f85e643-c929-47ac-ae5c-3f4b40fdee6c',NULL,0),('c331fa61-f16c-4a59-a9b4-ab831459e2e8','213, Hoà Bình, Tân Phú, TP.HCM','http://res.cloudinary.com/dinw4xpxn/image/upload/v1585648560/Elearning/userAvatar/49165498_531197590715756_3596665760392413184_o.jpg',NULL,'socksockmoizui@gmail.com','','Anh Hung Thien Ha','$2a$12$Ga9n6lQ6IXEGk7DqKGiDu.e6T.VTYKkwtBWviP5YqRYRVW/udrT02','032311111232','5f85e643-c929-47ac-ae5c-3f4b40fdee6b','',0),('ddbfbb44-cac5-4ca0-8464-928a103ee8ff','2wqdwe12wqd 12',NULL,NULL,'asdf@gmail.com','','kakass','$2a$12$lz1t73yI3P1peSUl7enALe8oNrJ1VM4pldVS7ZAum1Tfuhi3jZtWe','12312312312','5f85e643-c929-47ac-ae5c-3f4b40fdee6d','AAAAAAAAAAA',1);
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

-- Dump completed on 2020-04-07 22:18:44
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearningspringboot
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL,
  `time_count` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrccvc1a5qx2dqsyienwhuk66j` (`course_id`),
  CONSTRAINT `FKrccvc1a5qx2dqsyienwhuk66j` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (9,3,0,0,'Snowboarding','https://youtu.be/wlsdMpnDBn8'),(12,3,0,0,'Developer','https://youtu.be/wlsdMpnDBn8'),(14,2,0,0,'Photography','https://youtu.be/wlsdMpnDBn8'),(16,3,4,6,'Design','https://youtu.be/Jtxb5QcLXBU'),(17,11,0,12,'Op','https://youtu.be/wlsdMpnDBn8'),(18,11,0,0,'Đánh lộn','https://youtu.be/wlsdMpnDBn8'),(19,10,0,29,'Illenium - Ashes (The Remixes) [FULL ALBUM MIX]','https://youtu.be/cPqmoP_DAdc'),(21,15,1,4,'Đàn Tranh: Tân Uyên Ương Hồ Diệp Mộng','https://youtu.be/Oo7cvzXuZI8'),(22,15,2,4,'Flutter','https://youtu.be/D77DeiIOv14'),(23,16,2,4,'Music','https://youtu.be/wlsdMpnDBn8'),(24,17,2,4,'Học bài đi','https://youtu.be/wlsdMpnDBn8'),(25,17,2,4,'Diamond Eyes - Everything (Lyrics)','https://youtu.be/nvZCtQAEBoE'),(26,18,1,1,'Song đấu luyện 1','https://youtu.be/TipV8oR0gC4'),(27,18,2,2,'Song đấu luyện 2','https://youtu.be/MsnWlpsaMCs'),(28,18,3,1,'Song đấu luyện 3','https://youtu.be/FyH-MOkT34I'),(29,19,0,4,'Flutter','https://youtu.be/lEHM9HZf0IA'),(30,19,1,3,'Powfu Đéo phải Wibu','https://youtu.be/n78Gg6_zEQg'),(31,19,2,2,'WillV - hate me','https://youtu.be/UYSa_c7Dzfc'),(32,17,4,120,'Best Of MrSuicideSheep Mix ','https://youtu.be/Fww1E3csOv8'),(33,16,2,6,'TONES AND I - DANCE MONKEY ','https://youtu.be/JcjjiwzxUJI'),(34,23,1,6,'Practical Machine Learning Tutorial with Python Intro p.1','https://youtu.be/OGxgnH8y2NM'),(35,23,2,11,'Regression Intro - Practical Machine Learning Tutorial with Python p.2','https://youtu.be/JcI5Vnw0b2c'),(36,23,3,10,'Regression Features and Labels - Practical Machine Learning Tutorial with Python p.3','https://youtu.be/lN5jesocJjk');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 22:18:44
