-- 1. Create a brand new schema in a remote database hosted on Heroku or AWS

DROP SCHEMA  IF EXISTS `cs5200-spring2020-dharme`;
CREATE SCHEMA `cs5200-spring2020-dharme`;
USE `cs5200-spring2020-dharme`;   -- Setting this schema as default

-- To check if default Schem is set properly
-- SELECT DATABASE() FROM DUAL;

-- 2. Create tables person, developer, and user.

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `dob` date,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
); 

CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personid` int(11) NOT NULL,
  `developer_key` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`personid`) REFERENCES `person` (`id`) ON DELETE CASCADE
);

 CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personid` int(11),
  `user_agreement` tinyint(1) DEFAULT NULL,
  `approved_user` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`personid`) REFERENCES `person` (`id`) ON DELETE CASCADE
);


-- 3. Create tables website, page, widget, heading, html, youtube, image. Implement generalization using a single table, e.g., the denormalized strategy.

CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerid` int(11),
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `visits` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `website_developer_aggregation` FOREIGN KEY (`developerid`) REFERENCES `developer` (`id`)
) ;

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `views` int(11) DEFAULT '0',
  `websiteid` int(11),
  PRIMARY KEY (`id`),
  CONSTRAINT `page_website_composition` FOREIGN KEY (`websiteid`) REFERENCES `website` (`id`)
);

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  UNIQUE KEY `type` (`type`)
) ;

insert into type(id,type) values (1,'heading');
insert into type(id,type) values (2,'html');
insert into type(id,type) values (3,'image');
insert into type(id,type) values (4,'youTube');
insert into type(id,type) values (5,'question');
insert into type(id,type) values (6,'answer');

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `module` varchar(20) NOT NULL,
  UNIQUE KEY `module` (`module`)
) ;

insert into module(id,module) values (1,'Project1');
insert into module(id,module) values (2,'Project2');
insert into module(id,module) values (3,'Assignment1');
insert into module(id,module) values (4,'Assignment2');
insert into module(id,module) values (5,'Quiz1');
insert into module(id,module) values (6,'Exam');
insert into module(id,module) values (7,'Logistics');


CREATE TABLE `widget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `css_class` varchar(10) DEFAULT NULL,
  `css_style` varchar(10) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `widget_order` int(11) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `shareble` tinyint(1) DEFAULT NULL,
  `expandable` tinyint(1) DEFAULT NULL,
  `src` varchar(20) DEFAULT NULL,
  `size` int(11) DEFAULT '2',
  `html` varchar(20) DEFAULT NULL,
  `type` varchar(20),
  `asked_by` int(3) DEFAULT NULL,
  `posted_by` int(3) DEFAULT NULL,
  `posted_on` date DEFAULT NULL,
  `length` int(3) DEFAULT NULL,
  `views` int(3),
  `endorsed_by_instructor` tinyint(1) DEFAULT NULL,
  `module` varchar(10) DEFAULT NULL,
  `correct_answer` tinyint(1) DEFAULT NULL,
  `up_votes` int(11) DEFAULT NULL,
  `down_votes` int(11) DEFAULT NULL,
  `question_widget_id` int(11) DEFAULT NULL,
  `pageid` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  CONSTRAINT `question_answer_self_reference` FOREIGN KEY (`question_widget_id`) REFERENCES `widget` (`id`),
  CONSTRAINT `widget_user_posted_by` FOREIGN KEY (`posted_by`) REFERENCES `user` (`id`),
  CONSTRAINT `widget_user_asked_by` FOREIGN KEY (`asked_by`) REFERENCES `user` (`id`),
  CONSTRAINT `widget_module_enum` FOREIGN KEY (`module`) REFERENCES `module` (`module`),
  CONSTRAINT `widget_page_composition` FOREIGN KEY (`pageid`) REFERENCES `page` (`id`) ON DELETE CASCADE
);


-- 4.Create tables address, phones, website and page roles, and enumerations.
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personid` int(11) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `primary_phone` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  CONSTRAINT `phone_person_composition` FOREIGN KEY (`personid`) REFERENCES `person` (`id`) ON DELETE CASCADE
);

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personid` int(11) NOT NULL,
  `street1` varchar(20) NOT NULL,
  `street2` varchar(20) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zip` varchar(6) NOT NULL,
  `primary_address` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  CONSTRAINT `address_person_composition` FOREIGN KEY (`personid`) REFERENCES `person` (`id`) ON DELETE CASCADE
) ;

CREATE TABLE `role` (
	`id` int(11) NOT NULL,
  `role` varchar(10) NOT NULL,
  UNIQUE KEY `role` (`role`)
);

-- Inserting values into enum role
insert into role(id,role) values (1,'owner');
insert into role(id,role) values (2,'admin');
insert into role(id,role) values (3,'writer');
insert into role(id,role) values (4,'reviewer');
insert into role(id,role) values (5,'editor');



CREATE TABLE `priviledge` (
  `id` int(11) NOT NULL,
  `priviledge` varchar(10) NOT NULL,
  UNIQUE KEY `priviledge` (`priviledge`)
) ;
-- Inserting values into enum priviledge
insert into priviledge(id,priviledge) values (1,'create');
insert into priviledge(id,priviledge) values (2,'read');
insert into priviledge(id,priviledge) values (3,'update');
insert into priviledge(id,priviledge) values (4,'delete');


CREATE TABLE `website_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerid` int(11) DEFAULT NULL,
  `websiteid` int(11) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `website_role_developer_fk` FOREIGN KEY (`developerid`) REFERENCES `developer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `website_role_website_fk` FOREIGN KEY (`websiteid`) REFERENCES `website` (`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_website_role` UNIQUE (`developerid`,`websiteid`,`role`)
);

CREATE TABLE `website_priviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerid` int(11) DEFAULT NULL,
  `websiteid` int(11) DEFAULT NULL,
  `priviledge` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `website_priviledge_developer_fk` FOREIGN KEY (`developerid`) REFERENCES `developer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `website_priviledge_page_fk` FOREIGN KEY (`websiteid`) REFERENCES `website` (`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_website_priviledge` UNIQUE (`developerid`,`websiteid`,`priviledge`)
);

CREATE TABLE `page_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerid` int(11),
  `pageid` int(11),
  `role` varchar(10),
  PRIMARY KEY (`id`),
  CONSTRAINT `page_role_developer_fk` FOREIGN KEY (`developerid`) REFERENCES `developer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `page_role_page_fk` FOREIGN KEY (`pageid`) REFERENCES `page` (`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_page_role` UNIQUE (`developerid`,`pageid`,`role`)
);

CREATE TABLE `page_priviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerid` int(11) DEFAULT NULL,
  `pageid` int(11) DEFAULT NULL,
  `priviledge` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `page_priviledge_developer_fk` FOREIGN KEY (`developerid`) REFERENCES `developer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `page_priviledge_page_fk` FOREIGN KEY (`pageid`) REFERENCES `page` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `priviledge_enum` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`priviledge`),
  CONSTRAINT `unique_page_priviledge` UNIQUE (`developerid`,`pageid`,`priviledge`)
);


