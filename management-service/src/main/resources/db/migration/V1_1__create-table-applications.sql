CREATE TABLE `management_db`.`applications` (
	`id` VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`enabled` BOOLEAN NOT NULL DEFAULT FALSE,
	`home_uri` VARCHAR(255) NOT NULL,
	`documentation_url` VARCHAR(255) NOT NULL,
	`version` INT NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE KEY (`name`)
);