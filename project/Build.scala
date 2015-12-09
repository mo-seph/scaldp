//import com.typesafe.sbt.web.SbtWeb
//import org.sbtidea.SbtIdeaPlugin._
//import play.twirl.sbt.Import.TwirlKeys
//import play.twirl.sbt.SbtTwirl
import sbt.Keys._
import sbt.{ExclusionRule, _}

object ApplicationBuild extends Build {

  val buildSettings = Seq(
    description := "LDP library for Scala",
    organization := "bblfish.net",
    version := "0.7.4-SNAPSHOT",
    scalaVersion := "2.11.7",
//    crossScalaVersions := Seq("2.11.2", "2.10.4"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    fork := false,
    parallelExecution in Test := false,
    offline := true,
    // TODO
    testOptions in Test += Tests.Argument("-oDS"),
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-optimize", "-feature", "-language:implicitConversions,higherKinds", "-Xmax-classfile-name", "140", "-Yinline-warnings"),
    scalacOptions in(Compile, doc) := Seq("-groups", "-implicits"),

    startYear := Some(2012),
    resolvers += "bblfish-snapshots" at "http://bblfish.net/work/repo/snapshots/",
    resolvers += "bblfish.net repository" at "http://bblfish.net/work/repo/releases/",
    resolvers += "sesame-repo-releases" at "http://maven.ontotext.com/content/repositories/aduna/",
    resolvers += Resolver.url("inthenow-releases", url("http://dl.bintray.com/inthenow/releases"))(Resolver.ivyStylePatterns),
    libraryDependencies ++= appDependencies,
    //ideaExcludeFolders := Seq(".idea", ".idea_modules"),
    //sourceDirectories in(Compile, TwirlKeys.compileTemplates) := (unmanagedSourceDirectories in Compile).value,

    initialize := {
      //thanks to http://stackoverflow.com/questions/19208942/enforcing-java-version-for-scala-project-in-sbt/19271814?noredirect=1#19271814
      val _ = initialize.value // run the previous initialization
      val specVersion = sys.props("java.specification.version")
      assert(java.lang.Float.parseFloat(specVersion) >= 1.8f, "Java 1.8 or above required. Your version is " + specVersion)
    }
  )

  unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(Seq(_))

  unmanagedSourceDirectories in Test <<= (scalaSource in Test)(Seq(_))


  val scala_stm = ExclusionRule(organization = "org.scala-stm")
  val scala_z = ExclusionRule(organization = "org.scalaz")
  val in_the_now = ExclusionRule(organization = "com.github.inthenow")
  val banana = (name: String) => "org.w3" %% name % "0.7.2-SNAPSHOT" excludeAll (scala_stm)
  val semargl = (name: String) => "org.semarglproject" % {"semargl-"+name} % "0.6.1"
  //see http://bblfish.net/work/repo
  val playTLS = (name: String) =>  "com.typesafe.play" %% name % "2.3.11-TLS"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
//  val scalaActors = "org.scala-lang" % "scala-actors" % "2.10.2"

  val banana_version = "0.8.1"

  /**
   * @see http://repo1.maven.org/maven2/com/typesafe/akka/akka-http-core-experimental_2.10/
   */
  val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0"

  val testsuiteDeps =
    Seq(
      //        scalaActors,
      scalatest
    )

  val appDependencies = //Seq("sesame", "jena", "plantain_jvm", "ntriples_jvm").map(banana) ++
//  Seq("core",)
  Seq(
    akkaHttpCore,
    "net.rootdev" % "java-rdfa" % "0.4.2-RC2",
    "nu.validator.htmlparser" % "htmlparser" % "1.2.1",
    "org.scalaz" %% "scalaz-core" % "7.0.7", // from "http://repo.typesafe.com/typesafe/releases/org/scalaz/scalaz-core_2.10.0-M6/7.0.0-M2/scalaz-core_2.10.0-M6-7.0.0-M2.jar"
    "org.bouncycastle" % "bcprov-jdk15on" % "1.51",
    "org.bouncycastle" % "bcpkix-jdk15on" % "1.51",
    "net.sf.uadetector" % "uadetector-resources" % "2014.01",
    "com.typesafe.play" % "play-iteratees_2.11" % "2.4.4",
    "com.typesafe.play" % "play-ws_2.11" % "2.4.4",
    "com.ning" % "async-http-client" % "1.8.14",
    scalatest,
    // https://code.google.com/p/guava-libraries/
    "com.google.guava" % "guava" % "16.0.1",
    "com.google.code.findbugs" % "jsr305" % "2.0.2",
    "com.typesafe.play" %% "play-mailer" % "2.4.1",
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2" ,
    // https://bitbucket.org/inkytonik/kiama/
    "com.googlecode.kiama" %% "kiama" % "1.8.0",
    "org.w3" %% "examples" % "0.7.1",
    "org.w3" %% "banana-rdf" % banana_version  exclude("org.w3","examples") excludeAll( scala_stm, scala_z ),
    "org.w3" %% "banana-sesame" % banana_version excludeAll( scala_stm, scala_z ),
    "org.w3" %% "banana-jena" % banana_version excludeAll( scala_stm, scala_z ),
    "org.w3" %% "banana-plantain" % banana_version excludeAll( scala_stm, scala_z )
    //"org.w3" %% "banana-rdf-examples" % banana_version excludeAll( scala_stm )
    //"org.w3" %% "jena" % banana_version excludeAll( scala_stm ),
    //"org.w3" %% "plantain_jvm" % banana_version excludeAll( scala_stm, scala_z ),
    //"org.w3" %% "ntriples_jvm" % banana_version excludeAll( scala_stm, scala_z )
    
  )


  val main = Project(
    id = "scaldp",
    base = file("."),
    settings =  buildSettings
  )


}

