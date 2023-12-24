-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hospital
-- -----------------------------------------------------
-- Hospital(give the patient a diagnosis and prescribe treatment (put in a hospital or write a prescription and make an appointment))
-- 

-- -----------------------------------------------------
-- Schema hospital
--
-- Hospital(give the patient a diagnosis and prescribe treatment (put in a hospital or write a prescription and make an appointment))
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `hospital` ;

-- -----------------------------------------------------
-- Table `hospital`.`positions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`positions` (
  `name` VARCHAR(45) NOT NULL,
  `is_doctor` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`employees` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `degree` VARCHAR(45) NULL,
  `position_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employees_positions_idx` (`position_name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_employees_positions`
    FOREIGN KEY (`position_name`)
    REFERENCES `hospital`.`positions` (`name`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`declarations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`declarations` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT UNSIGNED NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `expires` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_declarations_employees1_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_declarations_employees1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital`.`employees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`patients` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `declaration_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_patients_declarations1_idx` (`declaration_id` ASC) VISIBLE,
  CONSTRAINT `fk_patients_declarations1`
    FOREIGN KEY (`declaration_id`)
    REFERENCES `hospital`.`declarations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`conclusions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`conclusions` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `complaint` VARCHAR(500) NULL,
  `medical_history` VARCHAR(500) NULL,
  `observation` VARCHAR(500) NULL,
  `diagnosis` VARCHAR(500) NULL,
  `recommendations` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`medicines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`medicines` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` VARCHAR(500) NULL,
  `is_recepted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`appointments` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  `doctor_id` BIGINT UNSIGNED NOT NULL,
  `conclusion_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_appointments_patients1_idx` (`patient_id` ASC) VISIBLE,
  INDEX `fk_appointments_employees1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_appointments_conclusions1_idx` (`conclusion_id` ASC) VISIBLE,
  CONSTRAINT `fk_appointments_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_employees1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital`.`employees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_conclusions1`
    FOREIGN KEY (`conclusion_id`)
    REFERENCES `hospital`.`conclusions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`services` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`vaccinations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`vaccinations` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `expires` TIMESTAMP NOT NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_vaccinations_patients1_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_vaccinations_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patients` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`hospitalizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`hospitalizations` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `place` VARCHAR(45) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `patients_condition` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_hospitalizations_patients1_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_hospitalizations_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patients` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`analyses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`analyses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `healthy_value` VARCHAR(45) NOT NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_analyses_patients1_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_analyses_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patients` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`appointment_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`appointment_services` (
  `appointment_id` BIGINT UNSIGNED NOT NULL,
  `service_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`appointment_id`, `service_id`),
  INDEX `fk_appointments_has_services_services1_idx` (`service_id` ASC) VISIBLE,
  INDEX `fk_appointments_has_services_appointments1_idx` (`appointment_id` ASC) VISIBLE,
  CONSTRAINT `fk_appointments_has_services_appointments1`
    FOREIGN KEY (`appointment_id`)
    REFERENCES `hospital`.`appointments` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_has_services_services1`
    FOREIGN KEY (`service_id`)
    REFERENCES `hospital`.`services` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`conclusion_medicines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`conclusion_medicines` (
  `conclusion_id` BIGINT UNSIGNED NOT NULL,
  `medicine_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`conclusion_id`, `medicine_id`),
  INDEX `fk_conclusions_has_medicines_medicines1_idx` (`medicine_id` ASC) VISIBLE,
  INDEX `fk_conclusions_has_medicines_conclusions1_idx` (`conclusion_id` ASC) VISIBLE,
  CONSTRAINT `fk_conclusions_has_medicines_conclusions1`
    FOREIGN KEY (`conclusion_id`)
    REFERENCES `hospital`.`conclusions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conclusions_has_medicines_medicines1`
    FOREIGN KEY (`medicine_id`)
    REFERENCES `hospital`.`medicines` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`allergies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`allergies` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_allergies_patients1_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_allergies_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patients` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
