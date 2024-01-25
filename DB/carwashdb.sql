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
-- Table `service_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_level` ;

CREATE TABLE IF NOT EXISTS `service_level` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `cost_per_wash` DECIMAL(5,2) NULL,
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
-- Table `membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `membership` ;

CREATE TABLE IF NOT EXISTS `membership` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vehicle_id` INT NOT NULL,
  `service_level_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `expiration_date` DATETIME NULL,
  `cost_per_wash` DECIMAL(5,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_membership_vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_membership_service_level1_idx` (`service_level_id` ASC),
  CONSTRAINT `fk_membership_vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_membership_service_level1`
    FOREIGN KEY (`service_level_id`)
    REFERENCES `service_level` (`id`)
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
  `price_paid` DECIMAL(5,2) NULL,
  `membership_id` INT NULL,
  `service_level_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_car_wash_log_vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_car_wash_log_store1_idx` (`store_id` ASC),
  INDEX `fk_wash_membership1_idx` (`membership_id` ASC),
  INDEX `fk_wash_service_level1_idx` (`service_level_id` ASC),
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
  CONSTRAINT `fk_wash_membership1`
    FOREIGN KEY (`membership_id`)
    REFERENCES `membership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wash_service_level1`
    FOREIGN KEY (`service_level_id`)
    REFERENCES `service_level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `enabled`, `role`, `address_id`, `create_date`, `last_update`) VALUES (1, 'John', 'Doe', 'some@gmail.com', 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'CUSTOMER', 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vehicle`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `vehicle` (`id`, `make`, `model`, `year`, `user_id`, `license_plate`, `create_date`, `last_update`, `enabled`, `color`) VALUES (1, 'Ford', 'F150', 2023, 1, '1234', NULL, NULL, 1, 'green');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `store` (`id`, `manager_id`, `phone`, `email`, `name`, `address_id`, `create_date`, `last_update`, `enabled`, `image_url`, `description`) VALUES (1, 1, '720-100-2000', 'some@gmail.com', 'CarWash', 1, NULL, NULL, 1, 'somepic.com', 'best store');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `service_level` (`id`, `name`, `description`, `image_url`, `cost_per_wash`, `store_id`) VALUES (1, 'basic', 'basic wash', NULL, 8.99, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `membership`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `membership` (`id`, `vehicle_id`, `service_level_id`, `create_date`, `expiration_date`, `cost_per_wash`) VALUES (1, 1, 1, NULL, NULL, 8.99);

COMMIT;


-- -----------------------------------------------------
-- Data for table `wash`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `wash` (`id`, `vehicle_id`, `store_id`, `create_date`, `price_paid`, `membership_id`, `service_level_id`) VALUES (1, 1, 1, NULL, 8.99, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `comment` (`id`, `content`, `store_id`, `user_id`, `comment_date`) VALUES (1, 'cool place', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `store_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `store_rating` (`user_id`, `store_id`, `rating`, `create_date`, `rating_comment`) VALUES (1, 1, 5, NULL, 'great');

COMMIT;

