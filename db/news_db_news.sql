-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: news_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `users_id` int DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `brief` varchar(600) NOT NULL,
  `news_date` datetime NOT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_news_users1_idx` (`users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,26,'Air-gen превращает воздух, которым мы дышим, в электричество','Группа американских инженеров разработала инновационную технологию Air-gen, способную вырабатывать электричество из влаги, содержащейся в воздухе. Этот прорыв может произвести революцию в производстве и потреблении энергии, обеспечив чистый, возобновляемый и повсеместный источник энергии.','2023-07-26 00:00:00',123),(2,26,'Ученые хотят подключить человеческий мозг к компьютеру через вены! (edited)','Компания Илона Маска Neuralink стремится внедрить чип размером с маленькую монету в мозг человека, чтобы подключить его к машинам. Однако группа австралийских исследователей предлагает другое решение для достижения аналогичного результата. Это включает в себя использование вен, гораздо менее инвазивной альтернативы.','2023-07-29 10:14:40',123),(4,26,'Кольцевые червоточины могут сделать невозможное: путешествие во времени','Червоточины, теоретические структуры в пространстве-времени, согласно некоторым теориям, могут служить порталами в другие вселенные или обеспечивать путешествия во времени.','2023-07-26 00:00:00',123),(7,26,'Модели искусственного интеллекта предсказывают будущие научные прорывы и вероятных авторов','Искусственный интеллект уже сегодня преобразует многие области, и научные исследования не являются исключением. Исследователи из Чикагского университета недавно разработали модели искусственного интеллекта, способные предвидеть научные достижения...','2023-07-28 00:00:00',123),(8,26,'Ядерный синтез: новый метод зажигания, который может изменить игру','В 2020 году ученые предложили новую модель зажигания для инерционного термоядерного синтеза (ИЯС). Эта технология может значительно оптимизировать процесс зажигания термоядерных реакторов. Это важный шаг на пути к экологически чистому, надежному и доступному производству энергии.','2023-07-28 00:00:00',123);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-29 11:17:09
