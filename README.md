# connections
A coding challenge that maps train station connections from a CSV file written in Java (8).
 
## Background
For this coding challenge, it's fairly low level (command prompt), and I could have decomposed things had I had more 
time and that's what I would normally have done: create proper interfaces for loading different types of data from 
different sources (I've dealt with different types flat files many, Many, MANY times over the years...), different 
types of delimiters, file line formats, etc., but I digress.

Some might wonder why the CSV file (stations.cvs) is loaded from the classpath, and the reason for this is that, over 
the years, I've had all kinds of deployment-type nightmares in file locations across environments, i.e. DEV, QA, UAT 
& PROD along with a variance of system/platform issues among those environments. In order to load most resources reliably, 
especially file (flat files like this CSV as well as properties/config files and resource bundles), I tend to include 
them into the deployable artifact/archive via the build and load them from the classpath in cases like this where I'm 
building infrastructure components to digest them internally.

The way this works is that it loads the CSV file from the classpath root, digests the contents and builds an in-memory 
data structure representing the permutations of connections via recursion. I will actually be expanding on this 
 project personally in order to incorporate my own learning of the Java 8 Collections/Lambdas going forward as 
 I'm a bit rusty when it comes to changes between 7 & 8.

## Setup
 Clone & go: git clone https://github.com/nectrtech/connections 
 
 I cheated and opened it in IntelliJ 15 CE as an environment as I realized I didn't have Maven >2 installed locally. If
 you do it this way too, you can "just press play" to run it.

## TODOS
 1. Add test cases - I didn't have time to convert it to a Maven project and so I didn't add dependencies for testing. I did test it though.
 2. Expand on use of Java 8 Collections/Lambdas.
 3. Move build to Maven
