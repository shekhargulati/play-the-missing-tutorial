Let's say Hello World! with Play Framework
---

The Play framework is a MVC style web application framework for JVM. It provides API for both Java and Scala programming languages. You can use it to build either your traditional web applications with server side rendering or modern single page web applications(SPA) that uses REST with JavaScript MVC framework like AngularJS. One design decision that makes Play different from other Java/Scala MVC web frameworks is that it is not built on top of the Servlet standard. Instead, it is full stack Java framework that runs your application stand-alone. **Play is a framework not a library**.

## Prerequisite

To work along with this tutorial, you will need following installed on your machine.

1. [Download and install latest JDK 8 update](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) on your operating system. I am using JDK version `1.8.0_60`.

2. [Download and install latest Scala version](http://www.scala-lang.org/download/) on your machine. I am using Scala `2.11.7`.

3. [Download and install the latest SBT version](http://www.scala-sbt.org/download.html) on your machine. I am using SBT version `0.13.11`.

4. An IDE of your choice. You can use either of [IntelliJ](https://www.jetbrains.com/idea/download/) or [Eclipse](http://scala-ide.org/). I prefer IntelliJ.
