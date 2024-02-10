-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema carwashdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carwashdb` ;

-- -----------------------------------------------------
-- Schema carwashdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carwashdb` DEFAULT CHARACTER SET utf8 ;
USE `carwashdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `user_name_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  `year` INT NULL,
  `user_id` INT NOT NULL,
  `license_plate` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `enabled` TINYINT NULL,
  `color` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_VEHICLE_USER_idx` (`user_id` ASC),
  CONSTRAINT `fk_VEHICLE_USER`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `manager_id` INT NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `enabled` TINYINT NULL,
  `image_url` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_user1_idx` (`manager_id` ASC),
  INDEX `fk_store_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_store_user1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service` ;

CREATE TABLE IF NOT EXISTS `service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `cost` DECIMAL(5,2) NULL,
  `store_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_level_store1_idx` (`store_id` ASC),
  CONSTRAINT `fk_service_level_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wash`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wash` ;

CREATE TABLE IF NOT EXISTS `wash` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vehicle_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_car_wash_log_vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_car_wash_log_store1_idx` (`store_id` ASC),
  INDEX `fk_wash_service_level1_idx` (`service_id` ASC),
  CONSTRAINT `fk_car_wash_log_vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_wash_log_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wash_service_level1`
    FOREIGN KEY (`service_id`)
    REFERENCES `service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `membership` ;

CREATE TABLE IF NOT EXISTS `membership` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NULL,
  `expiration_date` DATETIME NULL,
  `cost_per_wash` DECIMAL(5,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `store_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_store1_idx` (`store_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store_rating` ;

CREATE TABLE IF NOT EXISTS `store_rating` (
  `user_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `rating` INT NULL,
  `create_date` DATETIME NULL,
  `rating_comment` TEXT NULL,
  PRIMARY KEY (`user_id`, `store_id`),
  INDEX `fk_user_has_store_store1_idx` (`store_id` ASC),
  INDEX `fk_user_has_store_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_store_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_store_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS carwash@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'carwash'@'localhost' IDENTIFIED BY 'carwash';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'carwash'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, '123 Fake st', 'Denver', 'CO', '80913');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `enabled`, `role`, `address_id`, `create_date`, `last_update`) VALUES (1, 'John', 'Doe', 'some@gmail.com', 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'CUSTOMER', 1, '2024-01-30 12:30:53', '2024-01-30 12:30:53');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `enabled`, `role`, `address_id`, `create_date`, `last_update`) VALUES (2, 'Jane', 'Doe', 'customer1@gmail.com', 'jane', 'jane', 1, 'CUSTOMER', 1, '2024-01-30 12:30:53', '2024-01-30 12:30:53');

COMMIT;


-- -----------------------------------------------------
-- Data for table `vehicle`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `vehicle` (`id`, `make`, `model`, `year`, `user_id`, `license_plate`, `create_date`, `last_update`, `enabled`, `color`) VALUES (1, 'Ford', 'F150', 2023, 1, '1234', '2024-01-30 12:30:53', '2024-01-30 12:30:53', 1, 'green');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `store` (`id`, `manager_id`, `phone`, `email`, `name`, `address_id`, `create_date`, `last_update`, `enabled`, `image_url`, `description`) VALUES (1, 1, '720-100-2000', 'info@splashnshinecarwash.com', 'Splash \'n Shine', 1, '2024-01-30 12:30:53', '2024-01-30 12:30:53', 1, 'https://c8.alamy.com/comp/R2XEWC/-R2XEWC.jpg', 'Dive into a world of cleanliness with Splash \'n Shine Carwash! Our state-of-the-art facilities and expert technicians ensure that your vehicle receives a thorough cleaning, leaving it sparkling and gleaming. Whether it\'s a quick exterior wash or a comprehensive interior detailing, we guarantee top-notch service and results that shine!');
INSERT INTO `store` (`id`, `manager_id`, `phone`, `email`, `name`, `address_id`, `create_date`, `last_update`, `enabled`, `image_url`, `description`) VALUES (2, 1, '720-100-2001', 'contact@crystalcleanautospa.com', 'Crystal Clean Auto Spa', 1, '2024-01-30 12:30:53', '2024-01-30 12:30:53', 1, 'https://starkravingredhead.files.wordpress.com/2017/03/carwash-image-1.png?w=455&h=294', 'Experience the ultimate in automotive rejuvenation at Crystal Clean Auto Spa! Step into our luxurious spa-like atmosphere and let our skilled team pamper your vehicle from bumper to bumper. With premium wash packages and meticulous attention to detail, we\'ll restore your car\'s shine and leave it looking like new.');
INSERT INTO `store` (`id`, `manager_id`, `phone`, `email`, `name`, `address_id`, `create_date`, `last_update`, `enabled`, `image_url`, `description`) VALUES (3, 1, '720-100-2002', 'inquiries@sparklewashexpress.com', 'SparkleWash Express', 1, '2024-01-30 12:30:53', '2024-01-30 12:30:53', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqUITr6j6LgxUz7s1v1gb5pjNK3_qjSHeomTkIQdhbOL9x_oRLaeBjo0Oljn5-hr_mQMQ&usqp=CAU', 'Get ready to dazzle the streets with SparkleWash Express! Our convenient express wash services offer a quick and efficient way to keep your vehicle looking its best. With advanced cleaning technology and eco-friendly products, we deliver a sparkling clean finish in no time. Drive in dirty, drive out dazzling with SparkleWash Express!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `service` (`id`, `name`, `description`, `image_url`, `cost`, `store_id`) VALUES (1, 'basic', 'basic wash', NULL, 8.99, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `wash`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `wash` (`id`, `vehicle_id`, `store_id`, `create_date`, `service_id`) VALUES (1, 1, 1, '2024-01-30 12:30:53', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `membership`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `membership` (`id`, `create_date`, `expiration_date`, `cost_per_wash`) VALUES (1, NULL, NULL, 8.99);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (1, 'cool place', 1, 1, '2024-01-30 12:30:53');
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (2, 'cool place 2', 2, 1, '2024-01-30 12:30:53');
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (3, 'cool place 3', 3, 1, '2024-01-30 12:30:53');
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (4, 'cool place #1', 1, 2, '2024-01-30 12:30:53');
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (5, 'cool place #2', 2, 2, '2024-01-30 12:30:53');
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (6, 'cool place #3', 3, 2, '2024-01-30 12:30:53');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `store_rating` (`user_id`, `store_id`, `rating`, `create_date`, `rating_comment`) VALUES (1, 1, 5, '2024-01-30 12:30:53', 'great');

COMMIT;

