Expressjs API for Scala.js
================================
This is a Scala.js type-safe binding for [Express.js v4.x](http://expressjs.com/en/4x/api.html)

Express is a Fast, unopinionated, minimalist web framework.

The following npm packages have been bundle in this project.

| Package               | Version | Artifact ID            | Description                                             | Status          |
|-----------------------|---------|------------------------|---------------------------------------------------------|-----------------|
| express               | 4.13.4  | express                | Fast, unopinionated, minimalist web framework for Node.js | Stable |
| express-csv           | 0.6.0   | express-csv            | express-csv provides response csv easily to express. | Stable |
| express-fileupload    | 0.0.5   | express-fileupload     | Simple express file upload middleware that wraps around connect-busboy | Stable |
| express-multer        | 1.1.0   | express-multer         | Multer is a node.js middleware for handling multipart/form-data. | Tesing required |
| express-ws            |2.0.0-rc1| express-ws             | WebSocket endpoints for Express applications | Stable |


#### Build Requirements

* [ScalaJs.io v0.3.x](https://github.com/ldaniels528/scalajs.io)
* [SBT v0.13.13](http://www.scala-sbt.org/download.html)

#### Build/publish the SDK locally

```bash
 $ sbt clean publish-local
```

#### Running the tests

Before running the tests the first time, you must ensure the npm packages are installed:

```bash
$ npm install
```

Then you can run the tests:

```bash
$ sbt test
```

#### Artifacts and Resolvers

To add the `express` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs" %%% "express" % "4.13.4"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```