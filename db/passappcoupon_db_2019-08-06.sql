# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 52.220.54.102 (MySQL 5.7.27-0ubuntu0.16.04.1)
# Database: passappcoupon_db
# Generation Time: 2019-08-06 10:23:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table migrations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `migrations`;

CREATE TABLE `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;

INSERT INTO `migrations` (`id`, `migration`, `batch`)
VALUES
	(1,'2014_10_12_000000_create_users_table',1),
	(2,'2014_10_12_100000_create_password_resets_table',1),
	(3,'2019_01_18_155806_create_roles_table',1),
	(4,'2019_01_18_161319_create_permissions_table',1),
	(5,'2019_01_18_161443_create_permission_role_table',1),
	(6,'2019_06_28_103708_create_role_user_table',1);

/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table password_resets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `password_resets`;

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table permission_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permission_role`;

CREATE TABLE `permission_role` (
  `role_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  `role_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `permission_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  KEY `permission_role_role_id_foreign` (`role_id`),
  KEY `permission_role_permission_id_foreign` (`permission_id`),
  CONSTRAINT `permission_role_permission_id_foreign` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `permission_role_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `permission_role` WRITE;
/*!40000 ALTER TABLE `permission_role` DISABLE KEYS */;

INSERT INTO `permission_role` (`role_id`, `permission_id`, `role_key`, `permission_key`)
VALUES
	(1,1,'admin','dashboard'),
	(1,2,'admin','admin_user'),
	(1,3,'admin','role_permission'),
	(1,4,'admin','discount');

/*!40000 ALTER TABLE `permission_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `display_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;

INSERT INTO `permissions` (`id`, `name`, `display_name`, `description`, `created_at`, `updated_at`, `deleted_at`)
VALUES
	(1,'dashboard','Dashboard','Dashboard Management',NULL,NULL,NULL),
	(2,'admin_user','Admin User','Admin User Management',NULL,NULL,NULL),
	(3,'role_permission','Role & Permission','Role & Permission',NULL,NULL,NULL),
	(4,'discount','Discount','Discount',NULL,NULL,NULL);

/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role_user`;

CREATE TABLE `role_user` (
  `role_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  KEY `role_user_role_id_foreign` (`role_id`),
  KEY `role_user_user_id_foreign` (`user_id`),
  CONSTRAINT `role_user_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `role_user_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;

INSERT INTO `role_user` (`role_id`, `user_id`)
VALUES
	(1,1),
	(2,2);

/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `display_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;

INSERT INTO `roles` (`id`, `name`, `display_name`, `description`, `created_at`, `updated_at`, `deleted_at`, `key`)
VALUES
	(1,'admin','Admin','Admin User','2016-04-17 00:00:00','2016-04-17 00:00:00',NULL,'admin'),
	(2,'staff','Staff',NULL,'2019-07-08 03:18:07','2019-07-08 03:18:07',NULL,'staff');

/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_coupon_audit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_coupon_audit`;

CREATE TABLE `tbl_coupon_audit` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender_previouse_point` double DEFAULT NULL,
  `sender_current_point` double DEFAULT NULL,
  `coupon` double DEFAULT NULL,
  `order_id` char(36) DEFAULT NULL,
  `condition_id` int(11) DEFAULT NULL,
  `condition_type` varchar(100) DEFAULT NULL,
  `audit_type_id` int(11) DEFAULT NULL,
  `sender_id` char(36) DEFAULT NULL,
  `reciever_id` char(36) DEFAULT NULL,
  `reciever_previouse_point` double DEFAULT NULL,
  `reciever_current_point` double DEFAULT NULL,
  `recieve_point_from` text,
  `status_id` int(11) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table tbl_discount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_discount`;

CREATE TABLE `tbl_discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `percentage` double DEFAULT NULL,
  `trip_number` int(10) DEFAULT NULL,
  `share_number` int(11) DEFAULT NULL,
  `begin_at` datetime DEFAULT NULL,
  `end_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` smallint(1) unsigned DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tbl_discount` WRITE;
/*!40000 ALTER TABLE `tbl_discount` DISABLE KEYS */;

INSERT INTO `tbl_discount` (`id`, `percentage`, `trip_number`, `share_number`, `begin_at`, `end_at`, `description`, `status`, `created_at`, `updated_at`, `deleted_at`, `deleted_by`, `created_by`, `updated_by`)
VALUES
	(1,0,0,NULL,'2019-07-08 00:00:00','2019-07-22 00:00:00','Hello',1,'2019-07-08 02:31:55','2019-07-08 02:40:55',NULL,NULL,1,NULL),
	(2,15000,1000,NULL,'2019-07-29 00:00:00','2019-07-31 00:00:00','Hi 700',1,'2019-07-08 02:33:15','2019-07-08 02:56:31',NULL,NULL,1,1),
	(3,150,100,NULL,'2019-07-22 00:00:00','2019-07-29 00:00:00','Hi 90',1,'2019-07-08 02:50:17','2019-07-08 02:50:17',NULL,NULL,1,NULL);

/*!40000 ALTER TABLE `tbl_discount` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `deleted_at` datetime DEFAULT NULL,
  `deleted_by` int(11) DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `name`, `email`, `password`, `status`, `deleted_at`, `deleted_by`, `remember_token`, `created_at`, `updated_at`)
VALUES
	(1,'Admin','admin@gmail.com','$2y$10$.3uzsXo4QKK0SIcZo1.J0.FIE98puxFX/Q6C7bQgkEbeNagCQYZLm',1,NULL,NULL,NULL,NULL,NULL),
	(2,'Sokhy','sokhy@gmail.com','$2y$10$omM5cDd8am3AsW4zPUM44O6dvKx1Dz7FC9ZIWLAcSDJBHcklNjJ2u',1,NULL,NULL,NULL,'2019-07-08 03:18:37','2019-07-08 03:22:27');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
