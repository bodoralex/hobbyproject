# TestjobProject

### Requirements
You need the above software's to start the project:
- Apache Tomcat 9.0.5 version or newer
- MySQL 5.7.21-log version or newer
- Apache Maven 3.5.2 version or newer

### Start the application

1. You need to clone or download this github repository to your workspace folder.
2. The default database options are the following:
- host: localhost
- username: root
- password admin
- port: 3306

If you don't want to use this options, open terminal and navigate your apache-tomcat/bin folder than you type the next command with your settings:
```
export JAVA_OPTS="-DdbHost=yourHostname -DdbUser=yourUsernameInDatabase -DdbPass=yourPasswordInDatabase -DdbPort=yourDatabasePortNumber"
```

3. Then you need to start your Tomcat server. For this use the above command:
```
./catalina.sh run
```
4. You need to create maven .war file. Go to workspace/TestjobProject folder and use this command:
```
mvn package
```
5. Open your browser and navigate localhost:8080.
6. Click the Manager App tab.
7. Select your file from /target/TestjobProject-0.0.1-SNAPSHOT.war, then click Deploy.
8. Click the next Application path: TestjobProject-0.0.1-SNAPSHOT or click this link: 
[TestjobProject](http://localhost:8080/TestjobProject-0.0.1-SNAPSHOT/)
