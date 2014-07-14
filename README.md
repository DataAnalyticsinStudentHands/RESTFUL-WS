RESTFUL-WS
==========

Collection of RESTful web service implementation for core DASH functions. The source code is developed as Eclipse Maven project using facets "Dynamic Web Project" and "Java". The code has been tested for a default JRE Java 1.7 The following assumptions have been made:

1. Java backend provides a RESTful web service.

2. AngularJS consumes the web service.

Description of the Tomcat 
=========================
We are using Tomcat 7. The following changes have been made to a standard Tomcat installation.

a) Configure Tomcat To Support SSL Or Https

in server.xml add:

    <Connector SSLEnabled="true" clientAuth="false" keystoreFile="/path/to/certificate" keystorePass="***" maxThreads="150" port="8443" protocol="org.apache.coyote.http11.Http11Protocol" scheme="https" secure="true" sslProtocol="TLS"/>
    
 b) setup a shared lib folder for libraries shared by DASH RESTFUL-WS
 
 in catalina.properties modify/add to the following to the shared.loader property
 
 shared.loader=${catalina.base}/lib/shared/*.jar   
    

Description of the Java backend
================================

Jersey is the Java reference implementation for providing REST.


Instructions for authentication against the security filter
===========================================================

1. Set up the webSecurityConfig.xml to connect to the database you keep the security tables in.  By default it connects to the DB `test` with the login credentials "root" and no password.

2. Set up the tables in the DB you pointed to in step 1. Use the following query:

```
create table users(
username varchar(50) not null primary key,
password varchar(50) not null,
enabled boolean not null);

create table authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);`
```

3. Insert into table `users` the users you would like to be able to login as.  The table `authorities` must have at least one entry for each user in `users` defining a role. The included filter only checks that the user attempting to login has a role, but does not care what it is.

4. When making a request to any url behind "/services/" you will need to include a BASIC authorization header with a valid username and password that is in the tables set up in step 2.

5. *Coming Soon* Set up the ACL tables witht the following query:

```   
CREATE TABLE IF NOT EXISTS `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;# MySQL returned an empty result set (i.e. zero rows).


CREATE TABLE IF NOT EXISTS `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;# MySQL returned an empty result set (i.e. zero rows).


CREATE TABLE IF NOT EXISTS `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;# MySQL returned an empty result set (i.e. zero rows).


CREATE TABLE IF NOT EXISTS `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;# MySQL returned an empty result set (i.e. zero rows).
```
6. Then do this to set the contraints
```
ALTER TABLE `acl_entry`
  ADD CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
  ADD CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`);
  
 ALTER TABLE `acl_object_identity`
  ADD CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  ADD CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  ADD CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`);
```


