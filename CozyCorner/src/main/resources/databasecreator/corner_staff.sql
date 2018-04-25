CREATE TABLE staff
(
  staffId       INT(11) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  staffLogin    VARCHAR(45) NOT NULL,
  staffPassword TEXT        NOT NULL,
  CONSTRAINT staff_staffId_uindex
  UNIQUE (staffId),
  CONSTRAINT staff_staffLogin_uindex
  UNIQUE (staffLogin)
)
  ENGINE = InnoDB;
INSERT INTO corner.staff (staffId, staffLogin, staffPassword) VALUES (5, 'Staff', '8296072A6232CA56FC9626094F0BF94F98C3B7B5');