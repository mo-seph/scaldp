// Comment to get more information during initialization
logLevel := Level.Warn

scalaVersion := "2.10.4"

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
//resolvers += "Typesafe repository plugin" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

// The bblfish.net repository
resolvers += "bblfish.net repository" at "http://bblfish.net/work/repo/releases/"

resolvers += Resolver.url("bblfish ivy repository",url("http://bblfish.net/work/repo/ivy/releases/"))(Resolver.ivyStylePatterns)

//resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

//addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

//resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"


addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.0")
