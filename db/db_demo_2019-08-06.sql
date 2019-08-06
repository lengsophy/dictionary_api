# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: db_demo
# Generation Time: 2019-08-06 07:34:38 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
	(3);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;

INSERT INTO `person` (`id`, `age`, `firstname`, `lastname`)
VALUES
	(1,12,'test','leng'),
	(2,25,'Sophy','Leng');

/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_category`;

CREATE TABLE `tbl_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prefix` int(11) NOT NULL,
  `category_key` varchar(25) DEFAULT NULL,
  `category_name_en` varchar(255) DEFAULT NULL,
  `category_name_kh` varchar(255) DEFAULT NULL,
  `category_name_fn` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;

INSERT INTO `tbl_category` (`id`, `prefix`, `category_key`, `category_name_en`, `category_name_kh`, `category_name_fn`, `created_at`, `updated_at`)
VALUES
	(1,1,'HEALTH','Health','សុខភាព','Santé','2019-04-29 04:06:38',NULL),
	(2,1,'SCIENCE','Science','វិទ្យាសាស្រ្ត','Science','2019-04-29 04:06:38',NULL);

/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_language
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_language`;

CREATE TABLE `tbl_language` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_language` WRITE;
/*!40000 ALTER TABLE `tbl_language` DISABLE KEYS */;

INSERT INTO `tbl_language` (`id`, `key`, `description`, `created_at`, `updated_at`)
VALUES
	(1,'en','Englist','2019-04-29 04:06:38',NULL),
	(2,'kh','Khmer','2019-04-29 04:06:38',NULL),
	(3,'fn','Franch','2019-04-29 04:06:38',NULL);

/*!40000 ALTER TABLE `tbl_language` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_translation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_translation`;

CREATE TABLE `tbl_translation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prefix` int(11) NOT NULL,
  `desc_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc_kh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc_fn` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path_kh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path_fn` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `tbl_translation` WRITE;
/*!40000 ALTER TABLE `tbl_translation` DISABLE KEYS */;

INSERT INTO `tbl_translation` (`id`, `prefix`, `desc_en`, `desc_kh`, `desc_fn`, `path_en`, `path_kh`, `path_fn`, `parent_id`, `status`, `created_at`, `updated_at`)
VALUES
	(1,3,'Book is thing which use to write, read, and describe','វត្ថុដែលអាចសរសេរបាន អានបាន និង ពិពណ៏បាន','book, reserve, set aside, save, store, have in store',NULL,NULL,NULL,1,NULL,NULL,NULL);

/*!40000 ALTER TABLE `tbl_translation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_word
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_word`;

CREATE TABLE `tbl_word` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prefix` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `word_key` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `translate_en` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `translate_kh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `translate_fn` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `tbl_word` WRITE;
/*!40000 ALTER TABLE `tbl_word` DISABLE KEYS */;

INSERT INTO `tbl_word` (`id`, `prefix`, `category_id`, `word_key`, `translate_en`, `translate_kh`, `translate_fn`, `created_at`, `updated_at`)
VALUES
	(1,2,1,'book','book','សៀវភៅ','livre','2019-04-29 04:06:38',NULL);

/*!40000 ALTER TABLE `tbl_word` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
