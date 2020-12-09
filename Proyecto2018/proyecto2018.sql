-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema proyecto2018
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto2018` DEFAULT CHARACTER SET latin1 ;
USE `proyecto2018` ;

-- -----------------------------------------------------
-- Table `proyecto2018`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2018`.`cliente` ;

CREATE TABLE IF NOT EXISTS `proyecto2018`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(300) NOT NULL,
  `apellidos` VARCHAR(300) NOT NULL,
  `pais` VARCHAR(200) NOT NULL,
  `direccion` VARCHAR(300) NOT NULL,
  `telefono` INT(11) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto2018`.`habitacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2018`.`habitacion` ;

CREATE TABLE IF NOT EXISTS `proyecto2018`.`habitacion` (
  `numeroHabitacion` INT(11) NOT NULL,
  `tipoHabitacion` VARCHAR(40) NOT NULL,
  `tipoCama` VARCHAR(40) NOT NULL,
  `descripción` VARCHAR(100) NOT NULL,
  `precio` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`numeroHabitacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto2018`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2018`.`reserva` ;

CREATE TABLE IF NOT EXISTS `proyecto2018`.`reserva` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `numeroHabitacion` INT(11) NOT NULL,
  `fechaReserva` DATE NOT NULL,
  `fechaLlegada` DATE NULL,
  `fechaSalida` DATE NULL,
  `estadoReserva` VARCHAR(25) NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_reserva_cliente_idx` (`cliente_id` ASC),
  INDEX `fk_reserva_habitacion1_idx` (`numeroHabitacion` ASC),
  CONSTRAINT `fk_reserva_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `proyecto2018`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_habitacion1`
    FOREIGN KEY (`numeroHabitacion`)
    REFERENCES `proyecto2018`.`habitacion` (`numeroHabitacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto2018`.`pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2018`.`pago` ;

CREATE TABLE IF NOT EXISTS `proyecto2018`.`pago` (
  `reserva_codigo` INT NOT NULL,
  `cliente_id` INT(11) NOT NULL,
  `fechaPago` DATE NOT NULL,
  `monto` DECIMAL(8,2) NOT NULL,
  INDEX `fk_pago_reserva1_idx` (`reserva_codigo` ASC),
  INDEX `fk_pago_cliente1_idx` (`cliente_id` ASC),
  PRIMARY KEY (`reserva_codigo`, `cliente_id`),
  CONSTRAINT `fk_pago_reserva1`
    FOREIGN KEY (`reserva_codigo`)
    REFERENCES `proyecto2018`.`reserva` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pago_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `proyecto2018`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto2018`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2018`.`usuario` ;

CREATE TABLE IF NOT EXISTS `proyecto2018`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(300) NULL,
  `telefono` INT NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;

USE `proyecto2018` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
