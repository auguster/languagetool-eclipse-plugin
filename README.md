languagetool-eclipse-plugin
===========================

Eclipse plugin providing grammar checking using LanguageTool.
LanguageTool official website is https://languagetool.org/

## Presentation

This plugin contains all of LanguageTool and its dependencies (hence the size).
The org/ folder of the standalone version of languagetool has been package into a jar file called languagetool-standalone.jar and placed into libs

The whole repository is an Eclipse Plugin project that can be imported directly into Eclipse for modification.

## Extension

The plugin provide an extension for org.eclipse.ui.workbench.texteditor.spellingEngine to provide Eclipe wide grammar checking in the various editors.
It also extends org.eclipse.core.runtime.preferences in order to remember the parameters.

## How to run

Onces the export is done the provided jar can be copied into the plugins/ folder of your Eclipse install

The (few) options can be access through the menu: Window -> Preferences -> General -> Editors -> Text Editors -> Spelling

## Install Only

You can also download the file [cx.ath.remisoft.languagetool-1.0.0-SNAPSHOT.jar](https://build.vogella.com/ci/job/C-MASTER-Eclipse-LanguageTool/lastSuccessfulBuild/artifact/cx.ath.remisoft.languagetool/target/cx.ath.remisoft.languagetool-1.0.0-SNAPSHOT.jar) that contains the built plugin.

Copy the extracted content to your Eclipse install folder.

## How to build the project with maven

First of all you have to ensure that you have installed Maven3 properly.
See http://maven.apache.org/download.html#Installation  for further information.

In order to build a certain project you can use the Console or Terminal, go into the project folder and then call `mvn clean verify`. In case you build the cx.ath.remisoft.languagetool.master project all projects will be build.

Example:

	cd [...]\YourGitRepo\cx.ath.remisoft.languagetool.master

	mvn clean verify
	

If you want to make your project available as an update-site on a certain ftp server, you have to add the following to the *pom.xml* file in the cx.ath.remisoft.languagetool.p2updatesite project:

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
	
And in your maven settings.xml you can store your login information for the server.

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
	
I put those information, which you need to define in {brackets} and explained them inside those {brackets}.

In order to run a build and upload the update-site just need to run the `mvn install -P uploadRepoProfileId` command on the command line.