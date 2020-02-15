CREATE TABLE `management_db`.`services` (
	`name` VARCHAR(50) NOT NULL,
	`application_id` VARCHAR(50) NOT NULL,	
	`description` VARCHAR(255) NOT NULL,
	`port` INT NOT NULL,
	`git_url` VARCHAR(255) NOT NULL,
	`documentation_url` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`name`),
	UNIQUE KEY (`port`),
	FOREIGN KEY (`application_id`) REFERENCES `management_db`.`applications`(`id`)
);