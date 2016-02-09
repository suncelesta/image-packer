enablePlugins(ScalaJSPlugin)

name := "packer"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-js" % "scalajs-library_2.11" % "0.6.6",
  "org.scala-js" % "scalajs-dom_sjs0.6_2.11" % "0.8.2"
)
    