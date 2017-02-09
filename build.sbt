import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys.{libraryDependencies, _}
import sbt.Project.projectToRef
import sbt._

import scala.language.postfixOps

val apiVersion = "4.14.1"
val scalaJsIOVersion = "0.3.0.3"
val scalaJsVersion = "2.12.1"

homepage := Some(url("https://github.com/scalajs.io/express.js"))

val commonSettings = Seq(
  version := apiVersion,
  scalaVersion := scalaJsVersion,
  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-Xlint"),
  scalacOptions in(Compile, doc) ++= Seq("-no-link-warnings"),
  autoCompilerPlugins := true,
  scalaJSModuleKind := ModuleKind.CommonJSModule,
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaJsVersion,
    "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
    "io.scalajs" %%% "nodejs" % "7.4.0"
  ))

lazy val root = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  aggregate(npm_express, npm_express_csv, npm_express_fileupload, npm_express_multer, npm_express_ws).
  dependsOn(npm_express, npm_express_csv, npm_express_fileupload, npm_express_multer, npm_express_ws).
  settings(commonSettings: _*).
  settings(
    name := "express-bundle",
    organization := "io.scalajs.npm",
    description := "Express.js API bindings for Scala.js"
  )

/////////////////////////////////////////////////////////////////////////////////
//      Express.js packages
/////////////////////////////////////////////////////////////////////////////////

lazy val npm_express = (project in file("core")).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    name := "express",
    organization := "io.scalajs.npm",
    description := "express binding for Scala.js"
  )

lazy val npm_express_csv = (project in file("csv")).
  dependsOn(npm_express).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    name := "express-csv",
    organization := "io.scalajs.npm",
    description := "expressjs-csv binding for Scala.js"
  )

lazy val npm_express_fileupload = (project in file("fileupload")).
  dependsOn(npm_express).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    name := "express-fileupload",
    organization := "io.scalajs.npm",
    description := "expressjs-fileupload binding for Scala.js"
  )

lazy val npm_express_multer = (project in file("multer")).
  dependsOn(npm_express).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    name := "express-multer",
    organization := "io.scalajs.npm",
    description := "expressjs-multer binding for Scala.js"
  )

lazy val npm_express_ws = (project in file("ws")).
  dependsOn(npm_express).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    name := "express-ws",
    organization := "io.scalajs.npm",
    description := "expressjs-ws binding for Scala.js"
  )

/////////////////////////////////////////////////////////////////////////////////
//      Publishing
/////////////////////////////////////////////////////////////////////////////////

lazy val publishingSettings = Seq(
  sonatypeProfileName := "org.xerial",
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra :=
    <url>https://github.com/ldaniels528/scalajs-node-npm</url>
      <licenses>
        <license>
          <name>MIT License</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/scalajs-io/express.js.git</connection>
        <developerConnection>scm:git:git@github.com:scalajs-io/express.js.git</developerConnection>
        <url>github.com/scalajs-io/express.js.git</url>
      </scm>
      <developers>
        <developer>
          <id>ldaniels528</id>
          <name>Lawrence Daniels</name>
          <email>lawrence.daniels@gmail.com</email>
          <organization>io.scalajs</organization>
          <organizationUrl>https://github.com/scalajs-io</organizationUrl>
          <roles>
            <role>Project-Administrator</role>
            <role>Developer</role>
          </roles>
          <timezone>+7</timezone>
        </developer>
      </developers>
)

// loads the Scalajs-io root project at sbt startup
onLoad in Global := (Command.process("project root", _: State)) compose (onLoad in Global).value
