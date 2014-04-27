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
You can also download the file LanguageTool-Plugin.zip that contains de built plugin.
Copy the extracted content to your Eclipse install folder.
