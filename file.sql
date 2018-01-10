CREATE TABLE `heroku_4af59489dcca747`.`message` (
  `idmessage` INT NOT NULL AUTO_INCREMENT,
  `idincoming` INT NOT NULL,
  `idoutgoing` INT NOT NULL,
  `content` VARCHAR(250) NOT NULL,
  `date` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idmessage`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `heroku_4af59489dcca747`.`users` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `avatar` VARCHAR(200) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `heroku_4af59489dcca747`.`friends` (
  `idfriends` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idfriend` INT NOT NULL,
  PRIMARY KEY (`idfriends`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
