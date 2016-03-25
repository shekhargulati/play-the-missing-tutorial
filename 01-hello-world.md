Let's say Hello World! with Play Framework
---

[Play framework](https://www.playframework.com/) is a MVC style web application framework for JVM built using Scala, Akka, and Netty. It provides API for both Java and Scala programming languages. You can use it to build either your traditional web applications with server side rendering or modern single page web applications(SPA) that uses REST with JavaScript MVC framework like AngularJS. One design decision that makes Play different from other Java/Scala MVC web frameworks is that it is not built on top of the Servlet standard. It is full stack Java framework that runs your application stand-alone. **Play is a framework not a library**.

## Application

In this tutorial series, we will build a blogging platform called `blogy` that you can use to write and publish blogs.

## Prerequisite

To work along with this tutorial, you will need following installed on your machine.

1. [Download and install latest JDK 8 update](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) on your operating system. I am using JDK version `1.8.0_60`.

2. [Download and install latest Scala version](http://www.scala-lang.org/download/) on your machine. I am using Scala `2.11.7`.

3. [Download and install the latest SBT version](http://www.scala-sbt.org/download.html) on your machine. I am using SBT version `0.13.11`.

4. An IDE of your choice. You can use either of [IntelliJ](https://www.jetbrains.com/idea/download/) or [Eclipse](http://scala-ide.org/). I prefer IntelliJ.

5. You should be comfortable reading and writing Scala code. Throughout the tutorial series, we will also use SBT so in case you are not familiar with SBT then you can read [my SBT tutorial](https://github.com/shekhargulati/52-technologies-in-2016/tree/master/02-sbt).

## Github repository

The code for demo application is available on github: [blogy](./blogy). You should get the `part-01` release. If you are not comfortable with Git, then you can directly download the [part-01.zip](https://github.com/shekhargulati/play-the-missing-tutorial/archive/part-01.zip).

## Getting started with Play framework

Okay, let's get started with application development.

In this series, we will use the latest Play framework version `2.5.0`. Play documentation recommends that one should use a tool called **Activator** to quickly get started with Play framework. Activator is a wrapper over SBT. It scaffolds project using templates. There are a lot of official and community templates that one can choose from. I had a bad experience with official Play Scala template so I prefer manual approach of setting my projects. The official `Play Scala` template generates a lot of code and it becomes difficult to understand why you need all the things. We will go the manual way so that we understand how everything stitch together. I find manual way of setting Play project less intimidating and easier to understand.

Open a new command-line terminal and navigate to a convenient location on your file system where you want to host the application source code. Let's call the application folder `blogy`.

```bash
$ mkdir blogy && cd blogy
```

Create the following layout inside the `blogy` directory.

```
|-- app
|   `-- controllers
|-- conf
`-- project
```

You can use the command mentioned below to create the above mentioned directory structure.

```bash
$ mkdir -p app/controllers conf project
```

We will start with creating our build file `build.sbt`. Play uses SBT to build and run the application. Create a new file `build.sbt` inside the root i.e. `blogy` directory and populate it with following contents.

```scala
name := "blogy"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
```

The build file shown above does the following:

1. It specified name of the project as `blogy`.
2. Then, we specified version of the project as `1.0.0-SNAPSHOT`.
3. Next, it enables `PlayScala` plugin for `blogy` project. Later, we will add Play SBT plugin to the `blogy` application.
4. Finally, we set Scala version to `2.11.7`.

Now, we will add Play Scala plugin to our project so that it is treated as a Play application. In SBT, you declare plugins you need in your project by adding them to `plugins.sbt` file inside the `project` directory. Create a new file `plugins.sbt` inside the project directory and populate it with following contents.

```scala
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.0")
```

This is one required SBT plugin that we will need in our project. There are many more plugins like `sbt-coffeescript`, `sbt-less`, `sbt-scalariform`,etc that we can add for adding different capabilities to our project. We will add few more plugins later in this series.

It is a good practice in SBT projects to lock down the version of SBT. By default, the installed version of SBT will be used by the application. This might cause compatibility issues if future version of SBT becomes incompatible with Play. To force a SBT version, create a new file `build.properties` inside the `project` directory and add following line to it.

```
sbt.version=0.13.11
```

Now, to test our current setup launch the `sbt` command from inside the `blogy` directory. You will see output as shown below.

```bash
$ sbt
```
```
[info] Loading project definition from /Users/shekhargulati/dev/git/play-the-missing-tutorial/blogy/project
[info] Updating {file:/Users/shekhargulati/dev/git/play-the-missing-tutorial/blogy/project/}blogy-build...
[info] Resolving org.fusesource.jansi#jansi;1.4 ...
[info] Done updating.
[info] Set current project to blogy (in build file:/Users/shekhargulati/dev/git/play-the-missing-tutorial/blogy/)
```

Write `play` and press tab you will see all play specific tasks.

```
[blogy] $ play
playAggregateReverseRoutes   playAllAssets                playAssetsClassloader        playAssetsWithCompilation    playCommonClassloader        playCompileEverything
playDefaultAddress           playDefaultPort              playDependencyClassloader    playDependencyClasspath      playDevSettings              playDocsJar
playDocsModule               playDocsName                 playExternalizeResources     playExternalizedResources    playGenerateReverseRouter    playGenerateSecret
playInteractionMode          playJarSansExternalized      playMonitoredFiles           playNamespaceReverseRouter   playOmnidoc                  playPackageAssets
playPlugin                   playPrefixAndAssets          playReload                   playReloaderClassloader      playReloaderClasspath        playRoutes
playRoutesGenerator          playRoutesImports            playRoutesTasks              playRunHooks                 playStop                     playUpdateSecret
```

To run a Play project, you can use the `run` task. It will start the server at port `9000`.

```
[blogy] $ run

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.NettyServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Ctrl+D to stop and go back to the console...)
```

> **You can specify a different port number by just passing it along with run task like `run 8080`. This will start Play application at port 8080.**

You can access the application at [http://localhost:9000/](http://localhost:9000/). On accessing the page, you will be greeted with following error message.

```
java.io.IOException: resource not found on classpath: application.conf, application.json
```

Every play framework application needs a configuration file `application.conf` inside the configuration directory. Create a new file `application.conf` and populate it with following contents.

```
application{
  langs = "en"
}
```

This is the minimal configuration that you will need in your application. The above configuration specifies the language supported by the application. You can specify multiple languages by separating them by comma.

`application.conf` uses HOCON(Human Optimized Config Object Notation) notation.  According to [HOCON documentation](https://github.com/typesafehub/config/blob/master/HOCON.md),

> **The primary goal is: keep the semantics (tree structure; set of types; encoding/escaping) from JSON, but make it more convenient as a human-editable config file format.**

Access the index page [http://localhost:9000/](http://localhost:9000/) again, this time you will be greeted by different error page.

![](images/action-not-found.png)

The reason for this error message is that we have not mapped any action to the GET request to index `\` url.

## "Hello, World" with Play

You now have a working Play application environment. Let's write our first Play controller. In Play, you write controllers which define `Action` methods that process the request and return response. The decision to select an `Action` is made by the `router` which uses the `routes` configuration to select the correct Action. The `router` looks at the request details like method, uri, and query parameters to decide which `Action` should handle the request.

Create your first controller inside the `app/controllers` directory. We will name our controller `IndexController` as it is handling the index request.

```scala
package controllers

import play.api.mvc._

class IndexController extends Controller {

  def index() = Action {
    Ok("Hello, World!")
  }

}
```

The code shown above does the following:

1. It import all the classes and traits inside the `play.api.mvc` package.
2. Next, we created a Scala class that extends `Controller` trait. `Controller` provides access to all the utility methods to generate `Action` and `Result` types.
3. Finally, we created a method `index` that returns an `Action`. Action is a function `Request[A] => Result` that takes a request and returns a result. We returned Http status Ok i.e. 200 with `Hello, World!` text in the response body. Action syntax `Action{}` is possible because of a `apply` method defined in the `Action` object that takes a block `final def apply(block: => Result): Action[AnyContent]`.

Now, we will map the index URL `\` to index `Action` by defining configuration in the `routes` configuration. Create a new file `routes` inside the `conf` directory.

```
# Routes
# This file defines all application routes (Higher priority routes first)
# ~~~~

GET     /       controllers.IndexController.index()
```

As is obvious, we are mapping HTTP GET request to `\` to `controllers.IndexController.index()` action method.

You can now test that `\` url is working by either opening [http://localhost:9000/](http://localhost:9000/) in the browser or using a command-line tool like cURL as shown below.

```
$ curl -i http://localhost:9000/
HTTP/1.1 200 OK
Content-Length: 13
Content-Type: text/plain; charset=utf-8
Date: Thu, 24 Mar 2016 19:06:54 GMT

Hello, World!
```

You can map multiple URL to same action by defining the route configuration for new URL. Let's suppose we want to map both `\` and `index` to the same controller then we can update our `routes` file as shown below.

```
GET     /            controllers.IndexController.index()
GET     /index       controllers.IndexController.index()
```

Every Play application has access to the Play documentation at [http://localhost:9000/@documentation/Home](http://localhost:9000/@documentation/Home).

> **When you run the Play application using the `run` task, Play application is launched in the `dev` mode. In dev mode, sources are constantly watches for changes, and whenever source changes project is reloaded with new changes. There are two other modes of Play application - `test` and `production`. We will look at them later in this series.**

You can stop the running server by pressing `Ctrl+D`. This will take you back to the SBT shell. If you will press `Ctrl+C` then SBT process will also exit and you will be back to your command-line terminal.

---

That's it for the first part of Play framework. If you have any feedback then you can add a comment to this Github issue [https://github.com/shekhargulati/play-the-missing-tutorial/issues/1](https://github.com/shekhargulati/play-the-missing-tutorial/issues/1).

[![Analytics](https://ga-beacon.appspot.com/UA-59411913-4/shekhargulati/play-the-missing-tutorial/01-hello-world)](https://github.com/igrigorik/ga-beacon)
