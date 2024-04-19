CREATE SCHEMA IF NOT EXISTS screeny;

-- SET NAMES 'UTF8MB4';
-- SET TIME_ZONE 'Europe/Riga';
-- SET TIME_ZONE = '+3:00';

USE screeny;

DROP TABLE IF EXISTS BelowInfo;
CREATE TABLE BelowInfo
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(200) NOT NULL
);


DROP TABLE IF EXISTS MidInfo;
CREATE TABLE MidInfo
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    created_at DATETIME,
    description VARCHAR(200),
    illustration VARCHAR(200)
);

DROP TABLE IF EXISTS Events;
CREATE TABLE Events
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    created_at DATETIME,
    description VARCHAR(200)
);