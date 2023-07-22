# MaskedCratesAPI 
[![](https://jitpack.io/v/Furnesse/MaskedCratesAPI.svg)](https://jitpack.io/#Furnesse/MaskedCratesAPI)

Include the API using Gradle:
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Furnesse:MaskedCratesAPI:version'
}
```

Include the API using Maven:
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>


<dependency>
    <groupId>com.github.Furnesse</groupId>
    <artifactId>MaskedCratesAPI</artifactId>
    <version>version</version>
    <scope>provided</scope>
</dependency>
```

The API is accessible through a implementation of the MaskedCratesAPI interface. It is recommended to bind this instance to a class variable, if possible.

`MaskedCratesAPI api = MaskedCratesAPI.of(plugin);`