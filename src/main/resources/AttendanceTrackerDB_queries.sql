

CREATE TABLE `employee` (
  `employee_id` int(10) NOT NULL,
  `enterprise_id` varchar(30) DEFAULT NULL,
  `employee_name` varchar(25) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `designation` varchar(5) DEFAULT NULL,
  `career_level` int(3) DEFAULT NULL,
  `supervisor_id` int(10) DEFAULT NULL,
  `technology` varchar(10) DEFAULT NULL,
  `default_shift` varchar(2) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `edited_by` varchar(10) DEFAULT NULL,
  `edited_time` timestamp NULL ,

  `project_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) 

INSERT INTO `employee` VALUES (11111111,'admin','Administrator','admin','Admin',12,1,'','','','2017-05-31 13:23:25',NULL,'2017-05-31 13:23:25',NULL,NULL,true);
INSERT INTO `employee` VALUES (11285922,'renga.r.santh.ledge','RengaRajan','hello','ASE',12,12134567,'Java','A','','2017-05-31 09:14:19',NULL,NULL,NULL,true);
INSERT INTO `employee` VALUES (12134567,'ramesh.muthyala','Ramesh Muthyala','login','AM',7,34356543,'Java','A','','2017-05-31 09:14:19',NULL,NULL,NULL,true);


CREATE TABLE `projects` (
  `project_id` int(5) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(25) DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edited_by` varchar(20) DEFAULT NULL,
  `edited_time` timestamp  NULL  ,
  `description` text,
  PRIMARY KEY (`project_id`)
);

CREATE TABLE `timesheet` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) DEFAULT NULL,
  `date` int(3) DEFAULT NULL,
  `month` varchar(15) DEFAULT NULL,
  `year` int(5) DEFAULT NULL,
  `shift` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ;


-- on 6/09/17 for hub staffing
create table PROJECT_PORTFOLIO (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, DESCRIPTION VARCHAR(255) NULL,CREATED_BY VARCHAR(32) NOT NULL,CRETAED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, UPDATED_BY VARCHAR(32), UPDATED_TIME TIMESTAMP NULL);

create table PROJECT_PROGRAM (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, DESCRIPTION VARCHAR(255) NULL,PORTFOLIO_ID INT NULL,CREATED_BY VARCHAR(32) NOT NULL,CRETAED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, UPDATED_BY VARCHAR(32), UPDATED_TIME TIMESTAMP NULL, 
FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `PROJECT_PORTFOLIO` (`ID`)
);

ALTER TABLE PROJECTS ADD COLUMN PROGRAM_ID INT;
ALTER TABLE PROJECTS ADD FOREIGN KEY (PROGRAM_ID) REFERENCES  PROJECT_PROGRAM (ID); 

alter table employee add column active boolean(1) ;
update employee set active=true; 

alter table PROJECT_PORTFOLIO ADD COLUMN audit text;

--Added on 6/15/2017 6:24PM
--Added to : Merger approve and reject columns in the table to a single column "status"

alter table timesheet drop column approve;
alter table timesheet drop column reject;
alter table timesheet add column status varchar(15);

-- Added on 6/16/2017
-- seperate table for technology
CREATE TABLE TECHNOLOGY(TECHNOLOGY_ID INT PRIMARY KEY,TECHNOLOGY_NAME VARCHAR(25));
ALTER TABLE technology MODIFY technology_id INTEGER NOT NULL AUTO_INCREMENT;
alter table employee drop column technology;
ALTER TABLE employee ADD COLUMN technology_ID INT;
ALTER TABLE employee ADD FOREIGN KEY (technology_ID) REFERENCES  technology(technology_id);
alter table technology add column technology_description text; 

--Added on 6/16/2017 2:49PM
--Added to : A new table for career_level with shift amounts
--Added by : RengaRajan

CREATE TABLE career_level(id int NOT NULL AUTO_INCREMENT,  career_level int(3) , shiftb_amount int(10),shiftc_amount int(10) , PRIMARY KEY (ID));
insert into career_level(career_level,shiftb_amount,shiftc_amount) values(12,200,400);
insert into career_level(career_level,shiftb_amount,shiftc_amount) values(11,200,475);
insert into career_level(career_level,shiftb_amount,shiftc_amount) values(10,200,550);
insert into career_level(career_level,shiftb_amount,shiftc_amount) values(9,200,550);

--Added on 6/16/2017 2:53PM
--Added to : New columns billable,non billable wbs for project
--Added by : RengaRajan

alter table projects add column billable_wbs varchar(15);
alter table projects add column non_billable_wbs varchar(15);

--Added on 6/19/2017
--Added to : employee contact details
--Added by : Ilakkia


alter table employee add column pan_no varchar(20);
alter table employee add column passport_no varchar(20);
alter table employee add column contact_no number;


-- authentication & authorization script
CREATE TABLE AUTHORITIES(ID INT PRIMARY KEY AUTO_INCREMENT, EMPLOYEE_ID INT NOT NULL, AUTHORITY VARCHAR(50), FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (EMPLOYEE_ID))
update employee set employee_id=11086986 where employee_name='Administrator'
insert into authorities(EMPLOYEE_ID,AUTHORITY) values(12134567,'SUPERVISOR');
insert into authorities(EMPLOYEE_ID,AUTHORITY) values(11086986,'ADMIN');
insert into authorities(EMPLOYEE_ID,AUTHORITY) values(11285922,'DEVELOPER');

-- 22/6 Project Hub
create table EMPLOYEE_PROJECT( ID INT PRIMARY KEY AUTO_INCREMENT, EMPLOYEE_ID INT NOT NULL, PROJECT_ID INT NOT NULL, ROLE_START_DATE TIMESTAMP NOT NULL, ROLE_END_DATE TIMESTAMP, LAN_ID VARCHAR(20), ROLE_NAME VARCHAR(20), RECOMMENDED_TO_HOLD BOOLEAN, ACTIVE BOOLEAN NOT NULL, CREATED_BY VARCHAR(20), CREATED_TIME  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID), FOREIGN KEY  (PROJECT_ID ) REFERENCES PROJECTS(PROJECT_ID));

--Added on 6/19/2017
--Added to : employee lcr details
--Added by : Ilakkia

alter table employee_project add column lcr decimal;

create table EMPLOYEE_HUB( ID INT PRIMARY KEY AUTO_INCREMENT, EMPLOYEE_ID INT NOT NULL,  HUB_START_DATE TIMESTAMP NOT NULL, HUB_END_DATE TIMESTAMP, POTENTIAL_FUTURE_ROLE VARCHAR(255),CREATED_BY VARCHAR(20), CREATED_TIME  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,UPDATED_BY VARCHAR(32), UPDATED_TIME TIMESTAMP NULL, FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID));


--Added on 6/28/2017
--Added to : career_level
--Added by : Ilakkia

drop table career_level;

CREATE TABLE career_level(career_level  int(3) NOT NULL, shiftb_amount int(10),shiftc_amount int(10) ,designation varchar(5), PRIMARY KEY (career_level ));

insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(12,200,400,'ASE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(11,200,475,'SE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(10,200,550,'SSE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(9,200,550,'TL');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(8,200,550,'AM');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(7,200,550,'M');


alter table employee drop column designation;

ALTER TABLE EMPLOYEE ADD FOREIGN KEY (CAREER_LEVEL) REFERENCES  CAREER_LEVEL (CAREER_LEVEL); 

--Added on 7/4/2017
--Added to : location table
--Added by : Ilakkia


create table location (id integer auto_increment ,name varchar(15), primary key(id));

insert into location(name) values('chennai');
insert into location(name) values('hyderabad');

create table project_location(id integer auto_increment,portfolio_id integer, location_id integer, billable_wbs varchar(15), non_billable_wbs varchar(15), primary key(id), foreign key(portfolio_id) references project_portfolio(id),foreign key(location_id) references location(id));

alter table employee add column location_id integer;

alter table employee add foreign key(location_id) references location(id);

--Added on 7/10/2017
--Added to : location table
--Added by : Ilakkia

alter table employee add column default_password Boolean;
update employee set default_password=false;


-- Table for holiday incorporation to timesheet 7/10/2017 7:25PM
DROP TABLE IF EXISTS holiday;
create table holiday(id integer, date date, reason varchar(20),is_off_shore_holiday Boolean, is_on_shore_holiday Boolean, primary key(id));

insert into holiday values(1,'2017-07-10','Ramzan',true,false);
insert into holiday values(2,'2017-07-15','Pongal',true,false);
insert into holiday values(3,'2017-07-18','Diwali',false,true);
insert into holiday values(4,'2017-07-30','Christmas',true,false);

-- Employee RoleoffDate 7/11/2017 12.34 PM
alter table employee add column role_off_date timestamp;
alter table employee add column employee_status varchar(15) default 'active';

-- WSR table query 7/12/2017 3:29PM

CREATE TABLE `weeklyStatus` (
  `WORK_id` int(5) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) DEFAULT NULL,
  `release_name` varchar(32) DEFAULT NULL,
  `release_date` varchar(32) DEFAULT NULL,
   `week_start` varchar(32) DEFAULT NULL,
   `week_end` varchar(32) DEFAULT NULL,
  `accomplishment` varchar(255) DEFAULT NULL,
   `next_week_plan` varchar(255) DEFAULT NULL,
   `risk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`WORK_id`)
);

CREATE TABLE `resourcetasks` (
  `ResourceTask_id` int(5) NOT NULL AUTO_INCREMENT,
  `WORK_id` int(5) NOT NULL ,
  `resource_Name` varchar(32) DEFAULT NULL,
  `task_Update` varchar(32) DEFAULT NULL,
  `vacation_Date_To` varchar(32) DEFAULT NULL,
   `vacation_Date_From` varchar(32) DEFAULT NULL,
     PRIMARY KEY (`WORK_id`),
FOREIGN KEY (`WORK_id`) REFERENCES `WEEKLYSTATUS` (`WORK_id`) 
);

-- menu 7/12/17
create table menu( id int primary key auto_increment,name varchar(50) not null, parent_id int , url varchar(100));

create table roles(id int primary key auto_increment, name varchar(50));
create table  menu_role(id int primary key auto_increment,role_id int, menu_id int);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Masters',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Portfolio',1,'allPortfolioDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Project',1,'allProjectDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Technology',1,'allTechnologyDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Program',1,'allProgramDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('My Profile',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Personal Details',6,'redirect.htm?pageName=myPersonaldetails');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Change Login Password',6,'changePassword.htm');

INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('TimeSheet',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('My TimeSheet',9,'redirect.htm?pageName=timesheet');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Approve TimeSheet',9,'approve.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('MyTeam',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('EmployeeDetails',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Statistics',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Overall Statistics',14,'statistics.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Projectwise Statistics',14,'#');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Hub',NULL,'employeesOnHub.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Reports',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Profile',6,'redirect.htm?pageName=myProfile');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('All Employee Details',13,'allEmployeeDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Shift Reports',18,'redirect.htm?pageName=report');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('MyTeam',12,'employeesUnderSupervisor.htm');



INSERT INTO ROLES(NAME) VALUES('DEFAULT');
INSERT INTO ROLES(NAME) VALUES('SHIFTDETAILS_VIEW');
INSERT INTO ROLES(NAME) VALUES('LCR_VIEW');
INSERT INTO ROLES(NAME) VALUES('LCR_UPDATE');
INSERT INTO ROLES(NAME) VALUES('ADMIN');
INSERT INTO ROLES(NAME) VALUES('TL');
INSERT INTO ROLES(NAME) VALUES('AM');
INSERT INTO ROLES(NAME) VALUES('M');
INSERT INTO ROLES(NAME) VALUES('SM');
INSERT INTO ROLES(NAME) VALUES('TIME_APPROVE');
INSERT INTO ROLES(NAME) VALUES('SHIFT_ALLOWANCE_APPROVE');
INSERT INTO ROLES(NAME) VALUES('HUB_VIEW');
INSERT INTO ROLES(NAME) VALUES('SHIFT_REPORT');
INSERT INTO ROLES(NAME) VALUES('HUB_UPDATE');

INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,1);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,2);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,3);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,4);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,5);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,13);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,14);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,15);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,16);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(5,20);

INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,6);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,7);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,8);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,9);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,10);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,16);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,19);

INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(10,11);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(13,18);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(13,21);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(10,12);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(10,22);



-- Table for attachments 7/13/2017

CREATE TABLE `attachments` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) NOT NULL,
 `file_type` varchar(50) DEFAULT NULL,
 `file_content` binary(1000) NOT NULL,
`created_by` varchar(20) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);  
alter table employee_hub add column active boolean default false;

--Added on 7/14/2017
--Added to : Resource Task table
--Added by: Suraj

alter table  RESOURCETASKS  drop column WORK_ID;
alter table  RESOURCETASKS add column WORK_ID int(10) NOT NULL;
alter table  RESOURCETASKS add constraint R_pk primary key(RESOURCETASK_ID);
alter table  RESOURCETASKS add constraint R_fk foreign key (WORK_ID) references WEEKLYSTATUS(WORK_ID);


-- HCSC University (14/7/2017)
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Trainings',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('HCSC University',23,'hcscMenu.htm');

INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,23);
INSERT INTO MENU_ROLE(ROLE_ID,MENU_ID) VALUES(1,24);

-- Navriti menu
--7/17/2017
insert into menu(name,parent_id,url) values('Innovations',null,null);
insert into menu(name,parent_id,url) values('Add Idea',35,'redirect.htm?pageName=navriti');
insert into menu(name,parent_id,url) values('Ideas',35,'getAllIdeas.htm');

create table idea( id int(5) not null auto_increment,
enterprise_id varchar(30),
idea_description varchar(50),
team varchar(15),
primary key(id)
);


alter table idea drop column team;

alter table idea alter column enterprise_id rename to idea_owner;

alter table idea add column category varchar(50);

alter table idea add column sub_category varchar(50);

alter table idea add column created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;

create table idea_status(
id int(5) not null,
idea_id int(5),
status varchar(10),
created_by varchar(20),
created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
primary key(id),
foreign key(idea_id) references idea(id)
);

create table idea_developers(
id int(5) not null,
idea_id int(5),
developer_id varchar(20),
primary key(id),
foreign key(idea_id) references idea(id)
);

create table idea_progressnote(
id int(5) not null,
idea_id int(5),
progress varchar(80),
developer_id varchar(20),
created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
primary key(id),
foreign key(idea_id) references idea(id)
);


--Update on employee table 7-18-2017 7:79PM

alter table employee add column employee_photo blob;
alter table employee add column hcsc_mail_id varchar(35);
alter table employee add column role_on_date date;

--This insert works provided 13 is EmpDetails tab and 24 has no data in it
insert into menu values(24,'BulkUpload',13,'redirect.htm?pageName=employeeExcelUpload');
--This insert works if 24(ID column) has no data, 5 is admin role, 24 is the bulkupload menu
insert into menu_role values(24,5,24);

-- Navriti 7/19/2017
alter table idea_status add column comments varchar(60);

alter table idea_status alter column id int(10) not null auto_increment;

-- Table for Trainings 7/19/2017 4:43PM
create table Training_topics(topic_id int(10) primary key AUTO_INCREMENT, 
				topic_name varchar(20) NOT NULL, 
				topic_description varchar(500), 
				active boolean(1) DEFAULT TRUE);

create table Training_details(training_id int(10) primary key AUTO_INCREMENT, 
				enterprise_id varchar(50) DEFAULT NULL, 
				topic_id int(10), 
				completion_status boolean(1) DEFAULT FALSE, 
				slide_number int(3) DEFAULT 1, 
				completion_date timestamp DEFAULT NULL,  
				foreign key (topic_id) references Training_topics(topic_id)
				);

insert into TRAINING_TOPICS values(1, 'BCP', 'Business Continuity Plan', true), 
				(2, 'Navriti', 'Navriti tool and ideas', true), 
				(3, 'HCSC', 'Overview of HCSC', true), 
				(4, 'Health', 'Overview of HealthCare in US', true), 
				(5, 'CDP', 'Client data protection & Information security', true), 
				(6, 'OrgChart', 'Organization chart in HCSC', true), 
				(7, 'Tech', 'Multi-skilling & Technology stack', true),
				(8, 'HealthDay1', 'Subtopic in HealthCare overview in US', true), 
				(9, 'HealthDay2', 'Subtopic in HealthCare overview in US', true), 
				(10, 'HealthDay3', 'Subtopic in HealthCare overview in US', true), 
				(11, 'HealthDay4', 'Subtopic in HealthCare overview in US', true), 
				(12, 'HealthDay5', 'Subtopic in HealthCare overview in US', true), 
				(13, 'HealthDay6', 'Subtopic in HealthCare overview in US', true),
				(14, 'Navriti Framework', 'Subtopic in Navriti Tools and Ideas', true), 
				(15, 'Tool Assets', 'Subtopic in Navriti Tools and Ideas', true);
				
-- Table for Attachments 7/19/2017 6:50PM
				
create table attachment(file_id integer AUTO_INCREMENT,file_name varchar(100),file_type varchar(10),file_content blob, created_by varchar(35),created_date timestamp DEFAULT CURRENT_TIMESTAMP, primary key(file_id));

---MENU Table Entry 7/20/2017 10.16 AM---
UPDATE MENU SET URL='projectwiseStatistics.htm' WHERE ID=16;

--Navriti 20/7/2017

alter table idea_developers alter column id int(10) not null auto_increment;
alter table idea_progressnote alter column id int(10) not null auto_increment;

--wsr 20/7/2017 7:59PM
insert into menu(name,parent_id,url) values( 'WSR Tool', null, null);
insert into menu(name,parent_id,url) values('Weekly Status' ,36 ,'weeklyStatusReport.htm' );
insert into menu(name,parent_id,url) values( 'Report' ,36, 'getWsrReport.htm' );


insert into menu_role values(55,1,36);
insert into menu_role values(56,1,37);
insert into menu_role values(57,1,38);
-- Navriti
insert into menu(name,parent_id,url) values('My Ideas',35,'getMyIdeas.htm');
insert into menu(name,parent_id,url) values('Idea Progress',35,'getMyIdeasUnderDevelopment.htm ');

--attachment menu 
  
insert into menu values(28,'Attachments',null,null);
insert into menu values(29,'Upload Files',28,'redirect.htm?pageName=Attachments');
insert into menu values(30,'Search Files',28,'redirect.htm?pageName=searchAttachment'); 

insert into menu_role values(58,1,28);
insert into menu_role values(59,1,29);
insert into menu_role values(60,1,30);
 

-- Navriti role
insert into roles(name) values('IDEA_REVIEW');

--Trainings(7/31/2017)
delete from MENU where NAME = 'HCSC University';
insert into MENU(name,parent_id,url) values('Mandatory Trainings', 23, 'mandatory.htm'),('Additional Trainings', 23, 'additional.htm');
--Assign Menu Role



--NAVRITI FIELDS----
ALTER TABLE IDEA  ADD COLUMN IDEA_TITLE VARCHAR(100) ;
ALTER TABLE IDEA ADD CURRENT_EFFORT int;
ALTER TABLE IDEA ADD PLANNED_EFFORT int;
ALTER TABLE IDEA ADD EFFORT_SAVING int;
ALTER TABLE IDEA ADD BUSINESS_SAVING int;

--Navriti fields
ALTER TABLE IDEA  ADD COLUMN APPROVERS VARCHAR(255) ;