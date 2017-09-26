insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(12,200,400,'ASE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(11,200,475,'SE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(10,200,550,'SSE');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(9,200,550,'TL');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(8,200,550,'AM');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(7,200,550,'M');
insert into career_level(career_level,shiftb_amount,shiftc_amount,designation) values(6,200,550,'SM');



insert into location(name) values('chennai');
insert into location(name) values('hyderabad');

--Technology

insert into technology values(1,'Java Enterprise Edition','Java Enterprise Edition');
insert into technology values(2,'Oracle-PL/SQL','Oracle-PL/SQL');

--Portfolio
insert into project_portfolio values(1,null,null,'ADS');
insert into project_portfolio values(2,null,null,'AMS');

--Program
insert into project_program values(1,null,null,1,'BAS');

--Project
insert into projects values(1,null,1,null,'Alternative Contracting');

--Admin Employee
insert into employee values(10034517,true,9876789760,null,true,'A','Rakesh',null,'active','rakesh.k.chanana',null,6,null,null,null,'login',null,null,null,null,1);
insert into employee values(10665074,true,9876789761,null,true,'A','Vasanth',null,'active','vasanth.kuma',null,8,null,null,null,'login',null,null,null,10034517,1);
insert into employee values(11092417,true,9876789762,null,true,'A','Ramesh',null,'active','ramesh.muthyala',null,8,null,null,null,'login',1,null,null,10034517,1);
insert into employee values(11325313,true,9876789765,null,true,'A','Ilakkia',null,'active','ilakkia.shanmugam',null,12,null,null,null,'login',1,null,null,11092417,1);
insert into employee values(11285922
,true,9876789705,null,true,'A','Renga Rajan',null,'active','renga.r.santh.ledge',null,12,null,null,null,'login',1,null,null,11092417,1);

--Authorities
insert into AUTHORITIES values(1,'ADMIN',10034517);
insert into AUTHORITIES values(2,'DEFAULT',11092417);
insert into AUTHORITIES values(3,'LCR_VIEW',11092417);
insert into AUTHORITIES values(4,'TIME_APPROVE',11092417);
insert into AUTHORITIES values(5,'SHIFT_REPORT',11092417);
insert into AUTHORITIES values(6,'IDEA_REVIEW',10034517);
insert into AUTHORITIES values(7,'HUB_VIEW',11092417);
insert into AUTHORITIES values(8,'HUB_UPDATE',11092417);
insert into AUTHORITIES values(9,'DEFAULT',11325313);
insert into AUTHORITIES values(10,'DEFAULT',11285922);

insert into AUTHORITIES values(11,'DEFAULT',10665074);
insert into AUTHORITIES values(12,'LCR_VIEW',10665074);
insert into AUTHORITIES values(13,'TIME_APPROVE',10665074);
insert into AUTHORITIES values(14,'SHIFT_REPORT',10665074);
insert into AUTHORITIES values(15,'HUB_VIEW',10665074);
insert into AUTHORITIES values(16,'HUB_UPDATE',10665074);
insert into AUTHORITIES values(17,'MASTER_SETUP',10665074);
insert into AUTHORITIES values(18,'EMPLOYEE_DETAILS',10665074);



-- Menu
create table menu( id int primary key auto_increment,name varchar(50) not null, parent_id int , url varchar(100));
create table roles(id int primary key auto_increment, name varchar(50));
create table  menu_role(id int primary key auto_increment,role_id int, menu_id int);

--Roles
insert into roles values(1,'ADMIN');
INSERT INTO ROLES VALUES(2,'DEFAULT');
INSERT INTO ROLES VALUES(3,'LCR_VIEW');
INSERT INTO ROLES VALUES(4,'LCR_UPDATE');
INSERT INTO ROLES VALUES(5,'TIME_APPROVE');
INSERT INTO ROLES VALUES(6,'HUB_VIEW');
INSERT INTO ROLES VALUES(7,'HUB_UPDATE');
INSERT INTO ROLES VALUES(8,'SHIFT_REPORT');
INSERT INTO ROLES VALUES(9,'IDEA_REVIEW');
INSERT INTO ROLES VALUES(10,'STATISTICS');
INSERT INTO ROLES VALUES(11,'MASTER_SETUP');
INSERT INTO ROLES VALUES(12,'EMPLOYEE_DETAILS');


--Menu Roles
insert into menu_role values(1,1,1);
insert into menu_role values(2,1,2);
insert into menu_role values(3,1,3);
insert into menu_role values(4,1,4);
insert into menu_role values(5,1,5);
insert into menu_role values(6,1,6);
insert into menu_role values(7,1,7);
insert into menu_role values(8,1,8);
insert into menu_role values(9,1,9);
insert into menu_role values(10,1,10);
insert into menu_role values(11,1,11);
insert into menu_role values(12,1,12);
insert into menu_role values(13,1,13);
insert into menu_role values(14,1,14);
insert into menu_role values(15,1,15);
insert into menu_role values(16,1,16);
insert into menu_role values(17,1,17);
insert into menu_role values(18,1,18);
insert into menu_role values(19,1,19);
insert into menu_role values(20,1,20);
insert into menu_role values(21,1,21);
insert into menu_role values(22,1,22);
insert into menu_role values(23,1,23);
insert into menu_role values(24,1,24);
insert into menu_role values(25,1,25);
insert into menu_role values(26,1,26);
insert into menu_role values(27,1,27);
insert into menu_role values(28,1,28);
insert into menu_role values(29,1,29);
insert into menu_role values(30,1,30);
insert into menu_role values(31,1,31);
insert into menu_role values(32,1,32);
insert into menu_role values(33,1,33);
insert into menu_role values(34,1,34);

insert into menu_role values(35,2,6);
insert into menu_role values(36,2,7);
insert into menu_role values(37,2,8);
insert into menu_role values(38,2,9);
insert into menu_role values(39,2,10);
insert into menu_role values(40,2,19);
insert into menu_role values(41,2,25);
insert into menu_role values(42,2,26);
insert into menu_role values(43,2,27);
insert into menu_role values(44,2,28);
insert into menu_role values(45,2,29);
insert into menu_role values(46,2,33);
insert into menu_role values(47,2,34);

insert into menu_role values(48,5,11);
insert into menu_role values(49,5,12);
insert into menu_role values(50,5,22);

insert into menu_role values(51,8,18);
insert into menu_role values(52,8,21);

insert into menu_role values(53,6,17);
insert into menu_role values(54,6,23);

insert into menu_role values(55,10,14);
insert into menu_role values(56,10,15);
insert into menu_role values(57,10,16);

insert into menu_role values(58,11,1);
insert into menu_role values(59,11,2);
insert into menu_role values(60,11,3);
insert into menu_role values(61,11,4);
insert into menu_role values(62,11,5);

insert into menu_role values(63,12,13);
insert into menu_role values(64,12,20);
insert into menu_role values(65,12,24);

insert into menu_role values(66,1,35);
insert into menu_role values(67,1,36);
insert into menu_role values(68,1,37);
insert into menu_role values(69,1,38);
insert into menu_role values(70,2,35);
insert into menu_role values(71,1,39);
insert into menu_role values(72,1,40);

insert into menu_role values(73,1,41);
insert into menu_role values(74,2,41);

insert into menu_role values(75,1,42);
insert into menu_role values(76,1,43);

--Menu Insert

INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Masters',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Portfolio',1,'allPortfolioDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Project',1,'allProjectDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Technology',1,'allTechnologyDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Program',1,'allProgramDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('My Profile',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Personal Details',6,'redirect.htm?pageName=myPersonaldetails');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Change Login Password',6,'redirect.htm?pageName=updatePassword');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('TimeSheet',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('My TimeSheet',9,'redirect.htm?pageName=timesheet');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Approve TimeSheet',9,'approve.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('MyTeam',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('EmployeeDetails',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Statistics',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Overall Statistics',14,'statistics.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Projectwise Statistics',14,'projectwiseStatistics.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Hub',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Reports',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Profile',6,'redirect.htm?pageName=myProfile');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('All Employee Details',13,'allEmployeeDetails.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Shift Reports',18,'redirect.htm?pageName=report');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('MyTeam',12,'employeesUnderSupervisor.htm');
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('EmployeeHub',17,'employeesOnHub.htm');
insert into menu values(24,'BulkUpload',13,'redirect.htm?pageName=employeeExcelUpload');
insert into menu(name,parent_id,url) values('Innovations',null,null);
insert into menu(name,parent_id,url) values('Add Idea',25,'redirect.htm?pageName=navriti');
insert into menu(name,parent_id,url) values('Ideas',25,'getAllIdeas.htm');
insert into menu(name,parent_id,url) values('My Ideas',25,'getMyIdeas.htm');
insert into menu(name,parent_id,url) values('Idea Progress',25,'getMyIdeasUnderDevelopment.htm ');
insert into menu(name,parent_id,url) values( 'WSR Tool', null, null);
insert into menu(name,parent_id,url) values('Weekly Status' ,30 ,'weeklyStatusReport.htm' );
insert into menu(name,parent_id,url) values( 'Report' ,30, 'getWsrReport.htm' );
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Trainings',NULL,NULL);
INSERT INTO MENU(NAME,PARENT_ID,URL) VALUES('Mandatory Trainings', 23, 'mandatory.htm'),('Additional Trainings', 23, 'additional.htm');
insert into menu(NAME,PARENT_ID,URL) values('Attachments',null,null);
insert into menu(NAME,PARENT_ID,URL) values('Upload Files',36,'redirect.htm?pageName=Attachments');
insert into menu(NAME,PARENT_ID,URL) values('Search Files',36,'redirect.htm?pageName=searchAttachment'); 
insert into menu(NAME,PARENT_ID,URL) values('Templates',null,null);
insert into menu(NAME,PARENT_ID,URL) values('Templates',39,'allTemplateDetails.htm'); 
insert into menu(NAME,PARENT_ID,URL) values('Review Ideas',25,'getIdeasByReviewers.htm');
insert into menu(NAME,PARENT_ID,URL) values('CareerLevels',1,'getAllLevels.htm');
insert into menu(NAME,PARENT_ID,URL) values('Menu',1,'getAllMenu.htm');

--hub as project 8/1/2017
insert into project_portfolio values(3,null,null,'HUB');

insert into project_program values(2,null,null,3,'ADS_HUB');
insert into project_program values(3,null,null,3,'AMS_HUB');

insert into projects values(2,null,2,null,'ADS_HUB');
insert into projects values(3,null,3,null,'AMS_HUB'); 

--Foreign Key Contraints

alter table project_program add foreign key(portfolio_id) references project_portfolio(id);

alter table projects add foreign key(program_id) references project_program(id);

alter table project_location add foreign key(location_id) references location(id);

alter table project_location add foreign key(portfolio_id) references project_portfolio(id);

alter table employee add foreign key(career_level) references career_level(career_level);

alter table employee add foreign key(location_id) references location(id);

alter table employee add foreign key(project_id) references projects(project_id);

alter table employee add foreign key(technology_id) references technology(technology_id);

alter table employee_project add foreign key(employee_id) references employee(employee_id);

alter table employee_project add foreign key(project_id) references projects(project_id);

alter table authorities add foreign key(employee_id) references employee(employee_id);

alter table timesheet add foreign key(employee_id) references employee(employee_id);

alter table idea_developers add foreign key(idea_id) references idea(id);

alter table idea_status add foreign key(idea_id) references idea(id);

alter table idea_progressnote add foreign key(idea_id) references idea(id);

alter table menu_role add foreign key(role_id) references roles(id);

alter table menu_role add foreign key(menu_id) references menu(id);

alter table training_details add foreign key(topic_id) references training_topics(topic_id);

alter table resourcetasks add foreign key(work_id) references weeklystatus(work_id);







