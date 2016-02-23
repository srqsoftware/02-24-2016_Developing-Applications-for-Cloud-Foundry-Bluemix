create database activitydb;
use activitydb;
CREATE TABLE IF NOT EXISTS `activities` (
  `id` INT NOT NULL auto_increment,
  `userid` VARCHAR(45) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  `distance` FLOAT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

