# jBPMRest
Simple jBPM Rest API client java library based on Retrofit. This library provides simple interface for jBPM Execution Server REST API.

# How to use

## Build ##

### Requisites ###

* JDK 8
* Maven 3.1 or newer

### Compile, package and install locally ###

```
mvn clean install
```

## Usage ##

### Maven ###
```
<dependency>
	<groupId>de.pawlidi</groupId>
	<artifactId>jBPMRest</artifactId>
	<version>1.0.3</version>
</dependency>

```

### Gradle ###
```
dependencies {
    compile group: 'de.pawlidi', name: 'jBPMRest', version: '1.0.3'
}
```

### Ivy ###
```
<dependency org="de.pawlidi" name="jBPMRest" rev="1.0.3"/>
```

### Example ###


Add jBPMRest jar file to your application classpath. The following example shows the basic usage of this library.

```java
final String URL = "http://localhost:8181";
final String USER = "wbadmin";
final String PASSWORD = "wbadmin";

JbpmRestClient client = new JbpmRestClient(URL, USER, PASSWORD);

// read all containers from jbpm server
Optional<Containers> containers = client.getContainers();

containerId = ..

// read process instaces from container
Optional<ProcessInstances> instances = client.getProcessInstances(containerId, 0, 999, null, null, ProcessInstanceStatus.Active);

```
