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
  `user_type` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `user_name_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  `year` VARCHAR(45) NULL,
  `USER_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_VEHICLE_USER_idx` (`USER_id` ASC),
  CONSTRAINT `fk_VEHICLE_USER`
    FOREIGN KEY (`USER_id`)
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
  `address` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_store_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pricing` ;

CREATE TABLE IF NOT EXISTS `pricing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `store_id` INT NOT NULL,
  `pricing_type` VARCHAR(45) NULL,
  `cost` DECIMAL NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pricing_store1_idx` (`store_id` ASC),
  CONSTRAINT `fk_pricing_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `car_wash_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_wash_log` ;

CREATE TABLE IF NOT EXISTS `car_wash_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vehicle_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `pricing_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_car_wash_log_vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_car_wash_log_store1_idx` (`store_id` ASC),
  INDEX `fk_car_wash_log_pricing1_idx` (`pricing_id` ASC),
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
  CONSTRAINT `fk_car_wash_log_pricing1`
    FOREIGN KEY (`pricing_id`)
    REFERENCES `pricing` (`id`)
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
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `carwashdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `user_type`, `enabled`, `role`) VALUES (1, 'John', 'Doe', 'some@gmail.com', 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', NULL, 1, NULL);

COMMIT;

