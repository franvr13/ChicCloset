-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TiendaRopa
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TiendaRopa` ;

-- -----------------------------------------------------
-- Schema TiendaRopa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TiendaRopa` DEFAULT CHARACTER SET utf8 ;
USE `TiendaRopa` ;

-- -----------------------------------------------------
-- Table `TiendaRopa`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`producto` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `proveedor` VARCHAR(45) NULL,
  `material` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `descripcion` TEXT NULL,
  `cantidad_stock` SMALLINT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `TiendaRopa`.`carrito` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `fecha` DATE,
    `precio` DOUBLE,
    `cantidad` INTEGER,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`usuario` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(80) NULL,
  `contasena` VARCHAR(45) NULL,
  `email` VARCHAR(70) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`pedidos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`pedidos` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`pedidos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NULL,
  `fecha_pedido` DATETIME NULL,
  `fecha_entrega` DATETIME NULL,
  `estado` VARCHAR(45) NULL,
  `codigo_cliente` INT NULL,
  `comentarios` TEXT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedidos_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedidos_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `TiendaRopa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`venta` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`venta` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `producto_id` INT NULL,
  `pedido_id` INT NULL,
  `fecha` DATETIME NULL,
  `precio_unidad` DECIMAL(10,2) NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `producto_id_idx` (`producto_id` ASC) VISIBLE,
  INDEX `FK_pedido_id_idx` (`pedido_id` ASC) VISIBLE,
  CONSTRAINT `FK_producto_id`
    FOREIGN KEY (`producto_id`)
    REFERENCES `TiendaRopa`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_id`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `TiendaRopa`.`pedidos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`facturas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`facturas` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`facturas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `importe` INT NULL,
  `fecha_factura` DATETIME NULL,
  `fecha_vencimiento` DATETIME NULL,
  `pedidos_id` INT NOT NULL,
  PRIMARY KEY (`id`, `pedidos_id`),
  INDEX `fk_facturas_pedidos1_idx` (`pedidos_id` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_pedidos1`
    FOREIGN KEY (`pedidos_id`)
    REFERENCES `TiendaRopa`.`pedidos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`devoluciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`devoluciones` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`devoluciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `producto_id` INT NULL,
  `cantidad` INT NULL,
  `precio_unidad` DECIMAL NULL,
  `factura_id` INT NULL,
  `comentario` TEXT NULL,
  `estado` VARCHAR(45) NULL,
  `venta_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_producto_pedido_id_idx` (`venta_id` ASC) VISIBLE,
  INDEX `factura_id_idx` (`factura_id` ASC) VISIBLE,
  INDEX `producto_id_idx` (`producto_id` ASC) VISIBLE,
  CONSTRAINT `FK_producto_pedido_id`
    FOREIGN KEY (`venta_id`)
    REFERENCES `TiendaRopa`.`venta` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `factura_id`
    FOREIGN KEY (`factura_id`)
    REFERENCES `TiendaRopa`.`facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producto_id`
    FOREIGN KEY (`producto_id`)
    REFERENCES `TiendaRopa`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`metodos_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`metodos_pago` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`metodos_pago` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`pago` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`pago` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `forma_pago` VARCHAR(45) NULL,
  `fecha_pago` DATETIME NULL,
  `total` DECIMAL NULL,
  `facturas_id` INT NOT NULL,
  `external_payment_id` VARCHAR(45) NOT NULL,
  `metodos_pago_id` INT NOT NULL,
  PRIMARY KEY (`id`, `facturas_id`),
  INDEX `fk_pago_facturas1_idx` (`facturas_id` ASC) VISIBLE,
  INDEX `fk_pago_metodos_pago1_idx` (`metodos_pago_id` ASC) VISIBLE,
  CONSTRAINT `fk_pago_facturas1`
    FOREIGN KEY (`facturas_id`)
    REFERENCES `TiendaRopa`.`facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pago_metodos_pago1`
    FOREIGN KEY (`metodos_pago_id`)
    REFERENCES `TiendaRopa`.`metodos_pago` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`categoria` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `id_categoria_padre` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`categoria_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`categoria_producto` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`categoria_producto` (
  `producto_id` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  INDEX `fk_subcategoria_producto1_idx` (`producto_id` ASC) VISIBLE,
  INDEX `fk_subcategoria_categoria1_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_subcategoria_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `TiendaRopa`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subcategoria_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `TiendaRopa`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`det_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`det_usuario` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`det_usuario` (
  `id_user` INT NULL,
  `DNI` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `TiendaRopa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`historial_busquedas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`historial_busquedas` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`historial_busquedas` (
  `usuario_id` INT NOT NULL,
  INDEX `fk_historial_busquedas_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_historial_busquedas_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `TiendaRopa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`direcciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`direcciones` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`direcciones` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`ciudades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`ciudades` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`ciudades` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`datos_facturacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`datos_facturacion` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`datos_facturacion` (
  `id_user` INT NULL,
  `DNI` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `id_user0`
    FOREIGN KEY (`id_user`)
    REFERENCES `TiendaRopa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TiendaRopa`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TiendaRopa`.`roles` ;

CREATE TABLE IF NOT EXISTS `TiendaRopa`.`roles` (
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
