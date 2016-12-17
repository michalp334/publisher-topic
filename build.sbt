organization in ThisBuild := "sample.helloworld"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val helloworldApi = project("helloworld-api")
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies += lagomJavadslApi
  )

lazy val helloworldImpl = project("helloworld-impl")
  .enablePlugins(LagomJava)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslTestKit,
      lagomJavadslBroker

//        libraryDependencies += "com.lightbend.lagom" % "lagom-javadsl-broker_2.11" % "1.2.1"
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(helloworldApi)


def project(id: String) = Project(id, base = file(id))
  .settings(eclipseSettings: _*)
  .settings(javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation"))
  .settings(jacksonParameterNamesJavacSettings: _*) // applying it to every project even if not strictly needed.


// See https://github.com/FasterXML/jackson-module-parameter-names
lazy val jacksonParameterNamesJavacSettings = Seq(
  javacOptions in compile += "-parameters"
)

