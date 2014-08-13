import play.PlayScala
import sbt._
import sbt.Keys._
import play.Play.autoImport._
import PlayKeys._
import com.typesafe.sbt.web.Import._

name := """angtst"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

unmanagedResourceDirectories in Assets <+= baseDirectory( _ / "ui" / "app" / "views" )

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)
