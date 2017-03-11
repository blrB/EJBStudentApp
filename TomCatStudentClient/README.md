##TomCat Student Client

#Start server and deploy

```sh

$ cd TomCatStudentClient
$ mvn war:exploded
$ cp -a target/TomCatStudentClient-1.0-SNAPSHOT <folder_to_tomcat>/webapps/ROOT
$ cd <folder_to_tomcat>/bin
$ ./catalina.sh run 

```
