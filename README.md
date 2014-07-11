languagetool-eclipse-plugin
===========================

Eclipse plugin providing grammar checking using [LanguageTool](https://languagetool.org).

# Presentation

This plugin contains all of LanguageTool and its dependencies (hence the size).
The org/ folder of the standalone version of languagetool has been package into a jar file called languagetool-standalone.jar and placed into libs

The whole repository is an Eclipse Plugin project that can be imported directly into Eclipse for modification.

## Extension

The plugin provide an extension for `org.eclipse.ui.workbench.texteditor.spellingEngine` to provide Eclipe wide grammar checking in the various editors.
It also extends `org.eclipse.core.runtime.preferences` in order to remember the parameters.

# Get it

## Install a pre-compiled JAR

You can also download the file [cx.ath.remisoft.languagetool-1.0.0-SNAPSHOT.jar](https://build.vogella.com/ci/job/C-MASTER-Eclipse-LanguageTool/lastSuccessfulBuild/artifact/cx.ath.remisoft.languagetool/target/cx.ath.remisoft.languagetool-1.0.0-SNAPSHOT.jar) that contains the built plugin.

Copy the extracted content to your Eclipse install folder.

## Eclipse p2 Update Site

Alternatively, you can install it via the following repository: 

```
http://download.vogella.com/p2/C-MASTER-Eclipse-LanguageTool/workspace/cx.ath.remisoft.languagetool.p2updatesite/target/repository/
```

## How to run

Onces the export is done the provided jar can be copied into the plugins/ folder of your Eclipse install

The (few) options can be access through the menu: _Window_ → _Preferences_ → _General_ → _Editors_ → _Text Editors_ → _Spelling_

# Build it

## Get the dependencies and build with Maven

Go to https://languagetool.org/download/snapshots/ and download a recent ZIP file.
Unzip it and move all JAR files into the `cx.ath.remisoft.languagetool/libs` folder.
Zip the rest of the folder as `languagetool-standalone.jar` and also place it into the `cx.ath.remisoft.languagetool/libs` folder.

Next, you have to ensure that you have installed Maven 3 properly.
See http://maven.apache.org/download.html#Installation for further information.

In order to build a certain project you can use the console: go into the project folder and then call `mvn clean verify`.
In case you build the cx.ath.remisoft.languagetool.master project all projects will be build.

Example:

```bash
cd cx.ath.remisoft.languagetool.master
mvn clean verify
```

The whole process is automatized in the Linux shell script `./build.sh` which can find in the root folder.

## Create and upload a P2 update site.

If you want to make your project available as an update-site on a certain FTP server, you have to add the following to the *pom.xml* file in the _cx.ath.remisoft.languagetool.p2updatesite project_:

```xml
	<build>
	  <extensions>
	   <!-- Enabling the use of FTP -->
	   <extension>
		<groupId>org.apache.maven.wagon</groupId>
		<artifactId>wagon-ftp</artifactId>
		<version>1.0-beta-6</version>
	   </extension>
	  </extensions>
	</build>

	<profiles>
	  <!-- This profile is used to upload the repo -->
	  <profile>
	   <id>uploadRepoProfileId</id>
	   
	   <properties>
			<!-- Properties relative to the 
			distant host where to upload the repo -->
			<ftp.url>{repos.example.org}</ftp.url>
			<ftp.toDir>{/eclipse/p2}</ftp.toDir>
			<!-- Relative path to the repo being uploaded -->
			<repo.path>${project.build.directory}/repository/</repo.path>
		</properties>

	   <build>
		<plugins>
		 <!-- Upload the repo to the server -->
		 <plugin>
		  <groupId>org.codehaus.mojo</groupId>
		  <artifactId>wagon-maven-plugin</artifactId>
		  <version>1.0-beta-4</version>
		  <executions>
		   <execution>
			<id>upload-repo</id>
			<phase>install</phase>
			<goals>
			 <goal>upload</goal>
			</goals>
			<configuration>
			 <fromDir>${repo.path}</fromDir>
			 <includes>**</includes>
			 <toDir>${ftp.toDir}</toDir>
			 <url>${ftp.url}</url>
			 <serverId>{The server id of the configuration in the maven settings.xml file}</serverId>
			</configuration>
		   </execution>
		  </executions>
		 </plugin>
		</plugins>
	   </build>
	  </profile>
	</profiles>
```

And in your maven *settings.xml* you can store your login information for the server.

```xml
	<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
						  http://maven.apache.org/xsd/settings-1.0.0.xsd">
	  <servers>
		<server>
		  <id>{id, which is referenced inside the <serverId> tag in the *pom.xml* file of the cx.ath.remisoft.languagetool.p2updatesite project}</id>
		  <username>{your ftp user name}</username>
		  <password>{your ftp password}</password>
		</server>
	  </servers>
	  <mirrors/>
	  <proxies/>
	  <profiles/>
	  <activeProfiles/>
	</settings>
```

You need to replace the placeholders in `{brackets}` with your own configuration.

In order to run a build and upload the update-site just need to run the `mvn install -P uploadRepoProfileId` command on the command line.
