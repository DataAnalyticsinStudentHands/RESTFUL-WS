RESTFUL-WS
==========

Collection of RESTful web service implementation for core DASH functions. The source code is developed as Eclipse "Dynamic Web Project". The following assumptions have been made:

1. Java backend provides a RESTful web service.

2. AngularJS consumes the web service.

Description of the Java backend
================================

Jersey is the Java reference implementation for providing REST.


Instructions for authentication against the security filter
===========================================================

1. Set up the webSecurityConfig.xml to connect to the database you keep the security tables in.  By default it connects to the DB `test` with the login credentials "root" and no password.

2. Set up the tables in the DB you pointed to in step 1. Use the following query:


create table users(
username varchar(50) not null primary key,
password varchar(50) not null,
enabled boolean not null);

create table authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

3. Insert into table `users` the users you would like to be able to login as.  The table `authorities` must have at least one entry for each user in `users` defining a role. The included filter only checks that the user attempting to login has a role, but does not care what it is.

4. When making a request to any url behind "/services/" you will need to include a BASIC authorization header with a valid username and password that is in the tables set up in step 2.



