<div align="center">
  <h1 style="text-decoration: underline">SimplePets</h1>
  <img src="https://img.shields.io/maven-metadata/v?color=red&label=Current%20Version&metadataUrl=https%3A%2F%2Frepo.bsdevelopment.org%2Freleases%2Fsimplepets%2Fbrainsynder%2FAPI%2Fmaven-metadata.xml"></br>
  <a href="https://www.codefactor.io/repository/github/brainsynder-dev/simplepets"><img src="https://www.codefactor.io/repository/github/brainsynder-dev/simplepets/badge" alt="CodeFactor" /></a> 
  <img src="https://ci.bsdevelopment.org/job/SimplePets_v5/badge/icon?subject=v5%20Recode (1.20 -> LATEST)"></br>
  <img src="https://i.imgur.com/EUDSE8P.png" alt="SimplePets Logo" height="600"/>
</div>

## Requirements:
- Spigot Version 1.20 - 1.20.6
- Java 21 (If on 1.20.5 and up)
- Java 17 (If on 1.19 -> 1.20.4)

## API
<div align="center">
    <img src="https://img.shields.io/maven-metadata/v?color=red&label=Current%20Version&metadataUrl=https%3A%2F%2Frepo.bsdevelopment.org%2Freleases%2Fsimplepets%2Fbrainsynder%2FAPI%2Fmaven-metadata.xml&style=for-the-badge"><br>
</div>

```xml
<repository>
    <id>bs-repo-releases</id>
    <url>https://repo.bsdevelopment.org/releases</url>
</repository>

<dependency>
    <groupId>simplepets.brainsynder</groupId>
    <artifactId>API</artifactId>
    <version>{LATEST VERSION}</version>
</dependency>
```

## How to compile yourself:
#### Notice as of `May 1st 2024`
When compiling a custom version you need to supply a 'revision' variable  
which will be your custom version. If no revision is supplied the version  
will default to be `5.0-BUILD-0`  
**Example:** `-Drevision=5.0-BUILD-100`

There are a few different ways you can compile the plugin (as of `May 1st 2024`): 
- If you want to compile all current supported version you can run this command: `mvn clean install -Drevision={version}`
- If you want to compile a specific supported version run a command similar to this: `mvn clean install -Drevision={version} -Dtarget-mc=1.20.6`
- If you want to compile the latest supported version run this command: `mvn clean install -Drevision={version} -Platest`
