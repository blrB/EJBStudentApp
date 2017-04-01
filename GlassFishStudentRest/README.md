# GlassFish Rest Student Server

## Start server and deploy

```sh

$ cd GlassFishStudentRest
$ mvn war:war
$ cd <folder_glassfish4/bin>
$ ./asadmin start-domain domain1
$ ./asadmin deploy  --contextroot "/" '<pach_for_war> GlassFishStudentRest-1.0-SNAPSHOT.war'

```
## Undeploy and stop server

```sh

$ cd <folder_glassfish4/bin>
$ ./asadmin undeploy GlassFishStudentRest-1.0-SNAPSHOT   
$ ./asadmin stop-domain domain1

```