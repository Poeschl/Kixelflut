# Kixelflut [![Travis master](https://img.shields.io/travis/Poeschl/Kixelflut/master.svg?maxAge=3600)](https://travis-ci.org/Poeschl/Kixelflut/)

A [Pixelflut](https://github.com/defnull/pixelflut) interface for Java and Kotlin written in Kotlin.

## Installation

[![Latest version](https://img.shields.io/bintray/v/poeschl/maven/Kixelflut.svg?label=latest%20release&maxAge=3600)](https://bintray.com/poeschl/maven/Kixelflut)

Kixelflut is available via jCenter, so copy one of the snippets below into your maven or gradle project and it will be installed.

```groovy
repositories {
    jcenter()
}

dependencies {
    testCompile 'io.github.poeschl:Kixelflut:1.0.0'
}

```

To use it in a maven project add this in the pom.xml:

```xml
</project>
    ...
    <repositories>
        <repository>
          <id>jcenter</id>
          <url>https://jcenter.bintray.com/</url>
        </repository>
    </repositories>
    
    <dependencies> 
        <dependency>
          <groupId>io.github.poeschl</groupId>
          <artifactId>Kixelflut</artifactId>
          <version>1.0.0</version>
          <type>pom</type>
          <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```


## Usage

The Pixelflut interface is located in the `Pixelflut` class. There are the basic commands of the protocol.

Additional utils for drawing lines or areas can be found in `DrawUtils`.

To get yourself going very fast the `Painter` class is a nice way to inherit.
For examples take a look at the [Mazedrawer classes](https://github.com/Poeschl/PixelMaze/blob/master/src/main/kotlin/io/github/poeschl/pixelflutmaze/labyrinth/LabyrinthDrawer.kt).
