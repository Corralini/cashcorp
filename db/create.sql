-- MySQL Script generated by MySQL Workbench
-- Tue Jan 12 13:20:43 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cashcorp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cashcorp` ;

-- -----------------------------------------------------
-- Schema cashcorp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cashcorp` DEFAULT CHARACTER SET utf8 ;
USE `cashcorp` ;

-- -----------------------------------------------------
-- Table `cashcorp`.`PERSONA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PERSONA` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PERSONA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(255) NOT NULL,
  `APELLIDO1` VARCHAR(255) NOT NULL,
  `APELLIDO2` VARCHAR(255) NULL,
  `NIF` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(255) NULL,
  `TLF` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`PAIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PAIS` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PAIS` (
  `ID` VARCHAR(2) NOT NULL,
  `PAIS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`DIRECCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`DIRECCION` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`DIRECCION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DIRECCION` VARCHAR(255) NOT NULL,
  `NUMERO` VARCHAR(45) NOT NULL DEFAULT 'S/N',
  `PUERTA` VARCHAR(45) NULL,
  `PISO` VARCHAR(45) NULL,
  `COD_POSTAL` VARCHAR(45) NULL,
  `ID_PAIS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_DIRECCION_PAIS_idx` (`ID_PAIS` ASC) VISIBLE,
  CONSTRAINT `fk_DIRECCION_PAIS`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `cashcorp`.`PAIS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`PERSONA_DIRECCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PERSONA_DIRECCION` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PERSONA_DIRECCION` (
  `ID_PERSONA` INT NOT NULL,
  `ID_DIRECCION` INT NOT NULL,
  PRIMARY KEY (`ID_PERSONA`, `ID_DIRECCION`),
  INDEX `fk_PERSONA_has_DIRECCION_DIRECCION1_idx` (`ID_DIRECCION` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_DIRECCION_PERSONA1_idx` (`ID_PERSONA` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_DIRECCION_PERSONA1`
    FOREIGN KEY (`ID_PERSONA`)
    REFERENCES `cashcorp`.`PERSONA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSONA_has_DIRECCION_DIRECCION1`
    FOREIGN KEY (`ID_DIRECCION`)
    REFERENCES `cashcorp`.`DIRECCION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`USUARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`USUARIO` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`USUARIO` (
  `ID` INT NOT NULL,
  `USUARIO` VARCHAR(255) NOT NULL,
  `PSSWD` VARCHAR(255) NOT NULL,
  INDEX `fk_USUARIO_PERSONA1_idx` (`ID` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `USUARIO_UNIQUE` (`USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_PERSONA1`
    FOREIGN KEY (`ID`)
    REFERENCES `cashcorp`.`PERSONA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`ROL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`ROL` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`ROL` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ROL` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`INTERNO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`INTERNO` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`INTERNO` (
  `ID` INT NOT NULL,
  `ID_ROL` INT NOT NULL,
  INDEX `fk_INTERNO_USUARIO1_idx` (`ID` ASC) VISIBLE,
  INDEX `fk_INTERNO_ROL1_idx` (`ID_ROL` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_INTERNO_USUARIO1`
    FOREIGN KEY (`ID`)
    REFERENCES `cashcorp`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INTERNO_ROL1`
    FOREIGN KEY (`ID_ROL`)
    REFERENCES `cashcorp`.`ROL` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`CLIENTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`CLIENTE` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`CLIENTE` (
  `USUARIO_ID` INT NOT NULL,
  `FECHA_CREACION` DATETIME NOT NULL DEFAULT NOW(),
  INDEX `fk_CLIENTE_USUARIO1_idx` (`USUARIO_ID` ASC) VISIBLE,
  PRIMARY KEY (`USUARIO_ID`),
  CONSTRAINT `fk_CLIENTE_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `cashcorp`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`CUENTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`CUENTA` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`CUENTA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_PAIS` VARCHAR(2) NOT NULL,
  `DC_IBAN` INT(2) NOT NULL,
  `ENTIDAD` INT(4) NOT NULL,
  `OFICINA` INT(4) NOT NULL,
  `DC` INT(2) NOT NULL,
  `CUENTA` INT(9) NOT NULL,
  `SALDO` DOUBLE NOT NULL DEFAULT 0,
  `FECHA_CREACION` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`ID`),
  INDEX `fk_CUENTA_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  CONSTRAINT `fk_CUENTA_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `cashcorp`.`PAIS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`OPERACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`OPERACION` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`OPERACION` (
  `ID` INT NOT NULL,
  `OPERACION` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`MOVIMIENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`MOVIMIENTO` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`MOVIMIENTO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_CUENTA` INT NOT NULL,
  `CANTIDAD` DOUBLE NOT NULL,
  `DESCRIPCION` VARCHAR(255) NOT NULL,
  `ID_OPERACION` INT NOT NULL,
  `ID_CUENTA_DEST` INT NULL,
  `FECHA_CREACION` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`ID`),
  INDEX `fk_MOVIMIENTO_OPERACION1_idx` (`ID_OPERACION` ASC) VISIBLE,
  INDEX `fk_MOVIMIENTO_CUENTA1_idx` (`ID_CUENTA` ASC) VISIBLE,
  INDEX `fk_MOVIMIENTO_CUENTA2_idx` (`ID_CUENTA_DEST` ASC) VISIBLE,
  CONSTRAINT `fk_MOVIMIENTO_OPERACION1`
    FOREIGN KEY (`ID_OPERACION`)
    REFERENCES `cashcorp`.`OPERACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIMIENTO_CUENTA1`
    FOREIGN KEY (`ID_CUENTA`)
    REFERENCES `cashcorp`.`CUENTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIMIENTO_CUENTA2`
    FOREIGN KEY (`ID_CUENTA_DEST`)
    REFERENCES `cashcorp`.`CUENTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`PLAZO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PLAZO` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PLAZO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PLAZO` VARCHAR(45) NOT NULL,
  `VAR_INT` DOUBLE NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`PRESTAMO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PRESTAMO` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PRESTAMO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CANTIDAD` DOUBLE NOT NULL,
  `FINALIZADO` INT(1) NOT NULL DEFAULT 0,
  `DEVUELTO` DOUBLE NOT NULL,
  `INTERES` DOUBLE NOT NULL,
  `ID_PLAZO` INT NOT NULL,
  `ID_CLIENTE` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PRESTAMO_PLAZO1_idx` (`ID_PLAZO` ASC) VISIBLE,
  INDEX `fk_PRESTAMO_CLIENTE1_idx` (`ID_CLIENTE` ASC) VISIBLE,
  CONSTRAINT `fk_PRESTAMO_PLAZO1`
    FOREIGN KEY (`ID_PLAZO`)
    REFERENCES `cashcorp`.`PLAZO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRESTAMO_CLIENTE1`
    FOREIGN KEY (`ID_CLIENTE`)
    REFERENCES `cashcorp`.`CLIENTE` (`USUARIO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcorp`.`PERSONA_CUENTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashcorp`.`PERSONA_CUENTA` ;

CREATE TABLE IF NOT EXISTS `cashcorp`.`PERSONA_CUENTA` (
  `ID_PERSONA` INT NOT NULL,
  `ID_CUENTA` INT NOT NULL,
  PRIMARY KEY (`ID_PERSONA`, `ID_CUENTA`),
  INDEX `fk_PERSONA_has_CUENTA_CUENTA1_idx` (`ID_CUENTA` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_CUENTA_PERSONA1_idx` (`ID_PERSONA` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_CUENTA_PERSONA1`
    FOREIGN KEY (`ID_PERSONA`)
    REFERENCES `cashcorp`.`PERSONA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSONA_has_CUENTA_CUENTA1`
    FOREIGN KEY (`ID_CUENTA`)
    REFERENCES `cashcorp`.`CUENTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
