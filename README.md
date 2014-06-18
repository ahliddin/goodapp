## Project OpenShift Branch 

This branch includes the project that is run on OpenShift (http://goodapp-shahapps.rhcloud.com).

### How to build the project

After cloning the project make sure to switch to the master branch:

`git checkout master`

To build the project:

`mvn clean compile`

`mvn package jboss-as:deploy`

You can as well import the project as Maven project into JBoss Developer Studio and run as "Run on a server".

