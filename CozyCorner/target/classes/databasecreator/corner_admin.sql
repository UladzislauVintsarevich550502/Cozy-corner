CREATE TABLE admin
(
  adminId       INT(11) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  adminLogin    VARCHAR(45) NOT NULL
  COMMENT 'Данный столбец хранит уникальные логины администраторов, для авторизации администраторов в системе.',
  adminPassword TEXT        NOT NULL
  COMMENT 'Пароль, использующийся для опеспечения безопасности доступа в систему.',
  isMain        TINYINT(1)  NULL,
  CONSTRAINT adminId_UNIQUE
  UNIQUE (adminId),
  CONSTRAINT adminLogin_UNIQUE
  UNIQUE (adminLogin)
)
  COMMENT 'Таблица для хренении информации об Администраторах.'
  ENGINE = InnoDB;


INSERT INTO corner.admin (adminId, adminLogin, adminPassword, isMain) VALUES (3, 'Admin', '4D26A5BAFD3AE19DA1C6E8D5A5B1FFDDD096411A', 1);
INSERT INTO corner.admin (adminId, adminLogin, adminPassword, isMain) VALUES (4, 'Odmen', '4D26A5BAFD3AE19DA1C6E8D5A5B1FFDDD096411A', null);