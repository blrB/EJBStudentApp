# TomCat Student Client for REST Server

<p align="center">
  <img src="https://raw.githubusercontent.com/blrB/EJBStudentApp/master/TomCatStudentClient/img.png" alt="table"/>
</p>

## Client:

Servlets

JSP

log4j

## Start server and deploy

```sh

$ cd TomCatStudentClient
$ mvn war:exploded
$ cp -a target/TomCatStudentClient-1.0-SNAPSHOT <folder_to_tomcat>/webapps/ROOT
$ cd <folder_to_tomcat>/bin
$ ./catalina.sh run 

```
