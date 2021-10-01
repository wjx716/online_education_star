-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: online_education_star
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `crm_banner`
--

DROP TABLE IF EXISTS `crm_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crm_banner` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='首页banner表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_banner`
--

LOCK TABLES `crm_banner` WRITE;
/*!40000 ALTER TABLE `crm_banner` DISABLE KEYS */;
INSERT INTO `crm_banner` VALUES ('1194556896025845762','星辰教育_封面','https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/banner/cover%20%281%29.png','/#',1,0,'2019-11-13 18:05:32','2021-09-26 10:28:22'),('1194607458461214499','星辰教育-2','https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/banner/cover%20%282%29.png','/#',2,0,'2019-11-13 21:26:27','2021-09-26 10:28:22'),('1194607458461216769','星辰教育-1','https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/banner/cover%20%281%29.jpg','/#',0,0,'2019-11-13 21:26:27','2021-09-26 10:28:22');
/*!40000 ALTER TABLE `crm_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_chapter`
--

DROP TABLE IF EXISTS `edu_chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_chapter` (
  `id` char(19) NOT NULL COMMENT '章节ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='章节';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_chapter`
--

LOCK TABLES `edu_chapter` WRITE;
/*!40000 ALTER TABLE `edu_chapter` DISABLE KEYS */;
INSERT INTO `edu_chapter` VALUES ('1','14','第一章：HTML',0,'2019-01-01 12:27:40','2019-01-01 12:55:30'),('1440843545626890241','18','第六章：继承',0,'2021-09-23 09:00:46','2021-09-23 09:00:46'),('1440849770447982594','18','第零章：计算机基础',1,'2021-09-23 09:25:30','2021-09-23 09:31:15'),('1440907095032545281','18','第七章：XXX',0,'2021-09-23 13:13:17','2021-09-23 13:13:17'),('1440915737404317698','1440915694253318146','人工智能进阶课程-1',0,'2021-09-23 13:47:37','2021-09-23 13:47:44'),('1440915787832434690','1440915694253318146','人工智能进阶课程-2',0,'2021-09-23 13:47:49','2021-09-23 13:47:49'),('1441047383935926274','1441047343242788866','第一章',0,'2021-09-23 22:30:44','2021-09-23 22:30:44'),('1443027177791668225','1442054154372628482','Chapter One',0,'2021-09-29 09:37:44','2021-09-29 09:37:44'),('1443032583683293185','1443032539857010689','第一章',0,'2021-09-29 09:59:13','2021-09-29 09:59:13'),('1443038577066885122','1443038539691442177','第一章',0,'2021-09-29 10:23:02','2021-09-29 10:23:02'),('1443038751302467585','1443038539691442177','第二章',0,'2021-09-29 10:23:43','2021-09-29 10:23:43'),('1443378491528663041','1443378333105606658','第一章',0,'2021-09-30 08:53:44','2021-09-30 08:53:44'),('15','18','第一章：Java入门',0,'2019-01-01 12:27:40','2019-10-09 09:13:19'),('3','14','第二章：CSS',0,'2019-01-01 12:55:35','2019-01-01 12:27:40'),('32','18','第二章：控制台输入和输出',0,'2019-01-01 12:29:40','2019-01-01 12:27:40'),('44','18','第三章：控制流',0,'2019-01-01 12:30:40','2019-01-01 12:27:40'),('48','18','第四章：类的定义',0,'2019-01-01 12:32:40','2019-01-01 12:27:40'),('63','18','第五章：数组',0,'2019-01-01 12:33:40','2019-01-01 12:27:40');
/*!40000 ALTER TABLE `edu_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course`
--

DROP TABLE IF EXISTS `edu_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_course` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) DEFAULT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) DEFAULT NULL COMMENT '课程专业ID',
  `subject_parent_id` char(19) DEFAULT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) NOT NULL COMMENT '课程标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course`
--

LOCK TABLES `edu_course` WRITE;
/*!40000 ALTER TABLE `edu_course` DISABLE KEYS */;
INSERT INTO `edu_course` VALUES ('1192252213659774977','1189389726308478977','1178214681139539969','1178214681118568449','Java基础课程',0.01,2,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/23/DateTime_2021-09-23T13:37:12.543_default_cover.jpg',4,387,1,'Normal',1,'2019-11-07 09:27:33','2021-09-23 19:44:42'),('14','1189389726308478977','1178585108454121473','1178214681181483010','HTML CSS整站制作教程',0.00,3,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/28/DateTime_2021-09-28T11:03:48.770_2.jpg',3,44,15,'Normal',0,'2018-04-02 18:33:34','2021-09-28 11:07:52'),('1440513686023241729','1','1178585108407984130','1178214681118568449','test',111.00,1,'/static/default_cover.jpg',0,0,1,'Draft',0,'2021-09-22 11:10:01','2021-09-22 11:10:01'),('1440553072685314050','1','1178214681210843137','1178214681181483010','测试',1.00,1,'/static/default_cover.jpg',0,0,1,'Draft',0,'2021-09-22 13:46:31','2021-09-22 13:46:31'),('1440579522016669698','1189390295668469762','1178214681382809602','1178214681324089345','aabbcc',6.00,12,'/static/default_cover.jpg',0,0,1,'Draft',0,'2021-09-22 15:31:37','2021-09-22 16:44:37'),('1440915694253318146','1189390295668469762','1178214681584136193','1178214681554776066','人工智能进阶课程',5.00,300,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:35:39.316_10.jpg',0,0,1,'Normal',0,'2021-09-23 13:47:27','2021-09-29 19:58:43'),('1441047343242788866','1189390295668469762','1439868858776223746','1439868858377764865','SQL教程',2.00,3,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:35:23.721_8.jpg',0,0,1,'Normal',0,'2021-09-23 22:30:35','2021-09-29 09:06:49'),('1441209367843168258','1','1178585108407984130','1178214681118568449','XXX',3.00,3,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/24/DateTime_2021-09-24T09:14:21.824_default_cover.jpg',0,0,1,'Normal',1,'2021-09-24 09:14:24','2021-09-24 09:14:38'),('1441210445905096706','1','1178214681210843137','1178214681181483010','X',3.00,2,'/static/default_cover.jpg',0,0,1,'Normal',1,'2021-09-24 09:18:41','2021-09-24 09:19:27'),('1441258032372666370','1189390295668469762','1178214681382809602','1178214681324089345','X',1.00,1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/24/DateTime_2021-09-24T12:27:41.331_Taylor Swift-295.jpeg',0,0,1,'Draft',0,'2021-09-24 12:27:47','2021-09-24 12:27:47'),('1441258155173498881','1189390295668469762','1178214681382809602','1178214681324089345','X',1.00,1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/24/DateTime_2021-09-24T12:27:41.331_Taylor Swift-295.jpeg',0,0,1,'Draft',0,'2021-09-24 12:28:16','2021-09-24 12:28:16'),('1441260047056261121','1189390295668469762','1178214681382809602','1178214681324089345','TaylorSwift',1.00,1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/24/DateTime_2021-09-24T12:27:41.331_Taylor Swift-295.jpeg',0,0,1,'Normal',1,'2021-09-24 12:35:47','2021-09-24 22:54:57'),('1441579004464205825','1438861832122056705','1439868857723453442','1178214681181483010','TS',52.00,26,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/25/DateTime_2021-09-25T09:43:07.304_Taylor Swift-482.jpeg',0,0,1,'Normal',1,'2021-09-25 09:43:13','2021-09-25 11:36:18'),('1442015517916381186','1438790100686323714','1439868858776223746','1439868858377764865','厚大法考',65.00,479,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:37:44.263_1.jpg',0,0,1,'Normal',0,'2021-09-26 14:37:45','2021-09-28 11:15:17'),('1442054154372628482','1','1178214681139539969','1178214681118568449','TEST',1.00,1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/27/DateTime_2021-09-27T14:36:07.689_7.jpg',0,0,1,'Normal',0,'2021-09-26 17:11:17','2021-09-29 09:38:07'),('1443032539857010689','1','1178214681353449473','1178214681324089345','张三说刑法',5.00,500,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/29/DateTime_2021-09-29T10:00:53.996_3.jpg',0,0,1,'Normal',0,'2021-09-29 09:59:02','2021-09-29 10:00:59'),('1443038539691442177','1','1178214681139539969','1178214681118568449','XXX',0.00,3,'/default_cover.jpg',0,0,1,'Draft',0,'2021-09-29 10:22:53','2021-09-29 10:22:53'),('1443378333105606658','1189389726308478977','1178214681139539969','1178214681118568449','111222',111.00,2,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/30/DateTime_2021-09-30T08:53:01.536_9.jpg',0,0,1,'Normal',0,'2021-09-30 08:53:06','2021-09-30 08:55:36'),('15','1189389726308478977','1178214681504444418','1178214681483472898','Hadoop入门课程学习',0.00,23,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:35:09.916_6.jpg',0,51,17,'Normal',0,'2018-04-02 18:34:32','2021-09-28 20:24:40'),('18','1438790100686323714','1178214681139539969','1178214681118568449','罗翔说刑法',2.01,22,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:34:35.979_1.jpg',151,737,6,'Normal',0,'2018-04-02 21:28:46','2021-09-28 10:50:06');
/*!40000 ALTER TABLE `edu_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course_description`
--

DROP TABLE IF EXISTS `edu_course_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_course_description` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `description` text COMMENT '课程简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course_description`
--

LOCK TABLES `edu_course_description` WRITE;
/*!40000 ALTER TABLE `edu_course_description` DISABLE KEYS */;
INSERT INTO `edu_course_description` VALUES ('1104870479077879809','<p>11</p>','2019-03-11 06:23:44','2019-03-11 06:23:44'),('14','<p>这是课程简介<!-- pagebreak --></p>','2019-03-13 06:04:43','2021-09-28 11:07:52'),('1440513686023241729','<p>aaaa</p>','2021-09-22 11:10:01','2021-09-22 11:10:01'),('1440553072685314050','<p>测试</p>','2021-09-22 13:46:31','2021-09-22 13:46:31'),('1440579522016669698','<p><span style=\"text-decoration: underline;\"><strong>abc<span style=\"text-decoration: line-through;\">def</span></strong></span></p>','2021-09-22 15:31:37','2021-09-22 16:44:37'),('1440915694253318146','<ol>\n<li>人工智能进阶课程</li>\n</ol>','2021-09-23 13:47:27','2021-09-29 19:58:43'),('1441047343242788866','<ol>\n<li>啊啊啊啊</li>\n</ol>','2021-09-23 22:30:35','2021-09-26 14:35:25'),('1442015517916381186','<p><a href=\"www\" target=\"_blank\" rel=\"noopener\">这是课程的简介</a></p>','2021-09-26 14:37:46','2021-09-28 11:15:17'),('1442054154372628482','<p>TEST</p>','2021-09-26 17:11:17','2021-09-27 14:36:11'),('1443032539857010689','<ol>\n<li><span style=\"text-decoration: underline;\"><span style=\"text-decoration: line-through;\">ABC</span></span></li>\n</ol>','2021-09-29 09:59:03','2021-09-29 10:00:56'),('1443038539691442177','<p>啊啊啊</p>','2021-09-29 10:22:53','2021-09-29 10:22:53'),('1443378333105606658','<ul>\n<li>AAA</li>\n</ul>','2021-09-30 08:53:06','2021-09-30 08:54:39'),('15','<ul>\n<li style=\"text-align: center;\">a<hr />\n<blockquote><sub>a<sup>a</sup></sub></blockquote>\n</li>\n</ul>','2019-03-13 06:03:33','2021-09-28 20:24:40'),('18','<p><span style=\"text-decoration: underline;\"><strong>罗老师小课堂开课啦！</strong></span></p>','2019-03-06 18:06:36','2021-09-28 10:50:07');
/*!40000 ALTER TABLE `edu_course_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_subject`
--

DROP TABLE IF EXISTS `edu_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_subject` (
  `id` char(19) NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程科目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_subject`
--

LOCK TABLES `edu_subject` WRITE;
/*!40000 ALTER TABLE `edu_subject` DISABLE KEYS */;
INSERT INTO `edu_subject` VALUES ('1178214681118568449','后端开发','0',1,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681139539969','Java','1178214681118568449',1,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681181483010','前端开发','0',3,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681210843137','JavaScript','1178214681181483010',4,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681231814658','云计算','0',5,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681252786178','Docker','1178214681231814658',5,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681294729217','Linux','1178214681231814658',6,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681324089345','系统/运维','0',7,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681353449473','Linux','1178214681324089345',7,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681382809602','Windows','1178214681324089345',8,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681399586817','数据库','0',9,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681428946945','MySQL','1178214681399586817',9,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681454112770','MongoDB','1178214681399586817',10,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681483472898','大数据','0',11,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681504444418','Hadoop','1178214681483472898',11,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681529610242','Spark','1178214681483472898',12,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681554776066','人工智能','0',13,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681584136193','Python','1178214681554776066',13,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681613496321','编程语言','0',14,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681626079234','Java','1178214681613496321',14,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178585108407984130','Python','1178214681118568449',2,'2019-09-30 16:19:22','2019-09-30 16:19:22'),('1178585108454121473','HTML/CSS','1178214681181483010',3,'2019-09-30 16:19:22','2019-09-30 16:19:22'),('1439868855890542594','php','1178214681118568449',0,'2021-09-20 16:27:41','2021-09-20 16:27:41'),('1439868856360304641','c++','1178214681118568449',0,'2021-09-20 16:27:41','2021-09-20 16:27:41'),('1439868856817483777','jQuery','1178214681181483010',0,'2021-09-20 16:27:42','2021-09-20 16:27:42'),('1439868857723453442','vue','1178214681181483010',0,'2021-09-20 16:27:42','2021-09-20 16:27:42'),('1439868858046414849','react','1178214681181483010',0,'2021-09-20 16:27:42','2021-09-20 16:27:42'),('1439868858377764865','数据库开发','0',0,'2021-09-20 16:27:42','2021-09-20 16:27:42'),('1439868858776223746','mysql','1439868858377764865',0,'2021-09-20 16:27:42','2021-09-20 16:27:42');
/*!40000 ALTER TABLE `edu_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_teacher`
--

DROP TABLE IF EXISTS `edu_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_teacher` (
  `id` char(19) NOT NULL COMMENT '讲师ID',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_teacher`
--

LOCK TABLES `edu_teacher` WRITE;
/*!40000 ALTER TABLE `edu_teacher` DISABLE KEYS */;
INSERT INTO `edu_teacher` VALUES ('1','张三','金牌讲师简介','金牌讲师',2,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:21:12.343_file.png',0,0,'2019-10-30 14:18:46','2021-09-26 14:28:01'),('1189389726308478977','晴天','高级讲师简介','高级讲师资历',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:23:01.366_file.png',1,0,'2019-10-30 11:53:03','2021-09-26 14:23:04'),('1189390295668469762','李刚','高级讲师简介','高级讲师',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:23:17.132_file.png',2,0,'2019-10-30 11:55:19','2021-09-26 14:23:19'),('1189426437876985857','王二','高级讲师简介','高级讲师',1,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/e44a2e92-2421-4ea3-bb49-46f2ec96ef88.png',0,1,'2019-10-30 14:18:56','2019-11-12 13:37:35'),('1189426464967995393','王五','高级讲师简介','高级讲师',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:23:30.113_file.png',0,0,'2019-10-30 14:19:02','2021-09-26 14:23:32'),('1192249914833055746','李四','高级讲师简介','高级讲师',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:23:42.757_file.png',0,0,'2019-11-07 09:18:25','2021-09-26 14:23:44'),('1192327476087115778','1222-12-12','1111','11',2,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/5805c6cd-c8ad-4a77-aafd-d2e083bfd8a4.png',0,1,'2019-11-07 14:26:37','2019-11-11 16:26:26'),('1195337453429129218','test','sdfsdf','sdfdf',2,'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg',0,1,'2019-11-15 21:47:12','2019-11-15 21:47:27'),('1438782962882691074','string2','string','string',0,'string',0,1,'2021-09-17 16:32:44','2021-09-17 16:32:44'),('1438787987453362178','testing','test','test',1,NULL,1,1,'2021-09-17 16:52:42','2021-09-17 16:52:42'),('1438788552329641986','bbbbxxxx','aaaaa','aaaa',1,NULL,1,1,'2021-09-17 16:54:57','2021-09-17 16:54:57'),('1438790100686323714','罗翔','大家好，我是罗翔老师。','中国政法大学刑法学教授',2,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/26/DateTime_2021-09-26T14:26:24.208_file.png',1,0,'2021-09-17 17:01:06','2021-09-26 14:26:26'),('1438861832122056705','13','26','13',2,NULL,13,1,'2021-09-17 21:46:08','2021-09-19 14:46:06'),('1439050885173948418','x','x','x',1,NULL,3,1,'2021-09-18 10:17:22','2021-09-18 10:17:22'),('1439066070307647489','222Up','111','111',1,NULL,1,1,'2021-09-18 11:17:42','2021-09-18 11:31:00'),('1439066570977521665','2333','222','222',1,NULL,2,1,'2021-09-18 11:19:42','2021-09-18 11:19:42'),('1439066615487475714','2223333','2222','22',1,NULL,22,1,'2021-09-18 11:19:52','2021-09-18 11:19:52'),('1439067956255469570','啊啊啊啊','a','a',2,NULL,1,1,'2021-09-18 11:25:12','2021-09-18 11:25:12'),('1439629533970989058','pic','pic','pic',1,NULL,1,1,'2021-09-20 00:36:43','2021-09-20 00:36:43'),('1439629891657039873','aaafaegg','aefa','afaf',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/20/DateTime_2021-09-20T00:38:04.153_file.png',4,1,'2021-09-20 00:38:08','2021-09-20 00:38:08'),('1443377737438941186','yyy','xxx','XXX',1,'https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com/images/2021/9/30/DateTime_2021-09-30T08:51:04.767_file.png',1,0,'2021-09-30 08:50:44','2021-09-30 08:51:06'),('string','string','string','string',0,'string',0,1,'2021-09-14 10:31:53','2021-09-14 10:31:53'),('x','aaa','bbb','ccc',2,'ddd',0,1,'2021-09-13 11:12:52','2021-09-13 11:12:54');
/*!40000 ALTER TABLE `edu_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_video`
--

DROP TABLE IF EXISTS `edu_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_video` (
  `id` char(19) NOT NULL COMMENT '视频ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) NOT NULL COMMENT '章节ID',
  `title` varchar(50) NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_video`
--

LOCK TABLES `edu_video` WRITE;
/*!40000 ALTER TABLE `edu_video` DISABLE KEYS */;
INSERT INTO `edu_video` VALUES ('1182499307429339137','18','32','第一节','','',0,0,0,0,'',0,1,'2019-10-11 11:32:59','2019-10-11 11:57:38'),('1185312444399071234','14','1','12','','',0,0,0,0,'Empty',0,1,'2019-10-19 05:51:23','2019-10-19 05:51:33'),('1189434737808990210','18','44','3-1','','',1,0,0,0,'Empty',0,1,'2019-10-30 14:51:55','2021-09-23 10:30:56'),('1189471423678939138','18','1181729226915577857','test','2b887dc9584d4dc68908780ec57cd3b9','视频',1,0,0,0,'Empty',0,1,'2019-10-30 17:17:41','2019-10-30 17:17:41'),('1189476403626409986','18','1181729226915577857','22','5155c73dc112475cbbddccf4723f7cef','视频.mp4',0,0,0,0,'Empty',0,1,'2019-10-30 17:37:29','2019-10-30 17:37:29'),('1440865388769398786','18','1440849770447982594','0-2',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:27:33','2021-09-23 10:27:42'),('1440865467026722817','18','1440849770447982594','0-1',NULL,NULL,1,0,0,0,'Empty',0,1,'2021-09-23 10:27:52','2021-09-23 10:27:57'),('1440866037871448065','18','1440849770447982594','0-0','78de67c4b02d45c793a0a0f0d5224122','X.mp4',2,0,0,0,'Empty',0,1,'2021-09-23 10:30:08','2021-09-28 11:39:30'),('1440866125817614338','18','1440849770447982594','yyy',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:30:29','2021-09-23 10:30:35'),('1440866260370886657','18','44','3-2',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:01','2021-09-23 10:31:01'),('1440866285125668865','18','48','4-1',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:07','2021-09-23 10:31:07'),('1440866304260083714','18','48','4-2',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:12','2021-09-23 10:31:12'),('1440866328851288066','18','63','5-1',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:17','2021-09-23 10:31:17'),('1440866350732972034','18','63','5-2',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:23','2021-09-23 10:31:23'),('1440866368231608321','18','63','5-3',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:27','2021-09-23 10:31:27'),('1440866393187717122','18','1440843545626890241','6-1',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-23 10:31:33','2021-09-23 10:31:33'),('1440915826759770113','1440915694253318146','1440915737404317698','人工智能进阶课程-1-1','df20623451964d3c9560b677c1f05489','TestFileName.mp4',0,0,0,0,'Empty',0,1,'2021-09-23 13:47:59','2021-09-29 09:13:01'),('1440915858254798849','1440915694253318146','1440915737404317698','人工智能进阶课程-1-2','721529af5b1b41ca93e95da5dc226e73','Y.mp4',0,0,0,0,'Empty',0,1,'2021-09-23 13:48:06','2021-09-29 09:13:12'),('1441047408237723649','1441047343242788866','1441047383935926274','第一节','8d326bfe308e4bbca0e7978d3e79081e','Z.mp4',0,0,0,0,'Empty',0,1,'2021-09-23 22:30:50','2021-09-29 09:06:13'),('1443027249812062210','1442054154372628482','1443027177791668225','Video One','ed2fccaee5164d838c562c7a731c2f80','TestFileName.mp4',0,0,1,0,'Empty',0,1,'2021-09-29 09:38:01','2021-09-29 09:38:01'),('1443032699643215873','1443032539857010689','1443032583683293185','第一节','ee472d4a90774557b18ea6c7494530e5','6 - What If I Want to Move Faster.mp4',0,0,1,0,'Empty',0,1,'2021-09-29 09:59:41','2021-09-29 09:59:41'),('1443038633312501762','1443038539691442177','1443038577066885122','第一节',NULL,NULL,1,0,1,0,'Empty',0,1,'2021-09-29 10:23:15','2021-09-29 10:23:15'),('1443038783967707138','1443038539691442177','1443038751302467585','第二节',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-09-29 10:23:51','2021-09-29 10:23:51'),('1443378593945178114','1443378333105606658','1443378491528663041','第一节','083f6fae43924cc98e0597766f4f25da','Z.mp4',0,0,1,0,'Empty',0,1,'2021-09-30 08:54:08','2021-09-30 08:55:10'),('1443378769950756866','1443378333105606658','1443378491528663041','第二节',NULL,NULL,1,0,1,0,'Empty',0,1,'2021-09-30 08:54:50','2021-09-30 08:54:50'),('17','18','15','第一节：Java简介','196116a6fee742e1ba9f6c18f65bd8c1','1',1,1000,1,100,'Draft',0,1,'2019-01-01 13:08:57','2019-10-11 11:26:39'),('18','18','15','第二节：表达式和赋值语句','2d99b08ca0214909899910c9ba042d47','7 - How Do I Find Time for My ',2,999,1,100,'Draft',0,1,'2019-01-01 13:09:02','2019-03-08 03:30:27'),('19','18','15','第三节：String类','51120d59ddfd424cb5ab08b44fc8b23a','eae2b847ef8503b81f5d5593d769dde2.mp4',3,888,0,100,'Draft',0,1,'2019-01-01 13:09:05','2019-11-12 12:50:45'),('20','18','15','第四节：程序风格','796fdb200d554739b393542ce4600cfd','6 - What If I Want to Move Faster.mp4',4,666,0,100,'Draft',0,1,'2019-01-01 13:09:05','2021-09-29 09:39:57');
/*!40000 ALTER TABLE `edu_video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-01 13:42:11
