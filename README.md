# logentries-cloudreach-connect
Sending log messages to Logentries

## Example
``` java
LogentriesLogService logger = new LogentriesLogService("my-token");
logger.info("Info message");
logger.error("Error message");
try {
    Integer.parseInt("not-a-number");
} catch (NumberFormatException e) {
    logger.error("Exception message", e);
}
```

Install
Add the following to your pom.xml file.

``` xml
<repositories>
  <repository>
      <id>cloudreach-connect</id>
      <url>https://raw.githubusercontent.com/EgidioCaprinoCloudreach/logentries-cloudreach-connect/mvn-repo/
      </url>
  </repository>
</repositories>

<dependencies>
  <dependency>
      <groupId>com.cloudreach</groupId>
      <artifactId>logentries-cloudreach-connect</artifactId>
      <version>1.0</version>
  </dependency>
</dependencies>
```

## Details
The message processing and sending to Logentries is run a new thread.
