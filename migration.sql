CREATE USER 'TTadmin'@'localhost' IDENTIFIED BY 'p@ssw0rd';

CREATE DATABASE IF NOT EXISTS top_teer_db;

GRANT ALL ON top_teer_db.* TO 'TTadmin'@'localhost';