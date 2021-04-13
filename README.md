# Kixelflut [![Push tests](https://img.shields.io/github/workflow/status/Poeschl/Kixelflut/Master%20check)](https://github.com/Poeschl/Kixelflut/actions)

A [Pixelflut](https://github.com/defnull/pixelflut) interface for Java and Kotlin written in Kotlin.

## Installation

[![Latest version](https://img.shields.io/maven-central/v/xyz.poeschl/Kixelflut&maxAge=3600)](https://search.maven.org/search?q=xyz.poeschl)

Kixelflut is available via Maven Central, so copy one of the snippets below into your maven or gradle project and it will be installed.

```groovy

dependencies {
    implementation 'xyz.poeschl:Kixelflut:{version}'
}

```

To use it in a maven project add this in the pom.xml:

```xml
</project>
    ...    
    <dependencies> 
        <dependency>
          <groupId>xyz.poeschl</groupId>
          <artifactId>Kixelflut</artifactId>
          <version>{version}</version>
          <type>pom</type>
        </dependency>
    </dependencies>
</project>
```


## Usage

The Pixelflut interface is located in the `Pixelflut` class. There are the basic commands of the protocol.

Additional utils for drawing lines or areas can be found in `DrawUtils`.

To get yourself going fast the `Painter` class is a nice way to inherit.
For examples take a look at the [Mazedrawer classes](https://github.com/Poeschl/PixelMaze/blob/master/src/main/kotlin/io/github/poeschl/pixelflutmaze/labyrinth/LabyrinthDrawer.kt).

The interface uses KotlinLogging for logging output and will put it to any slf4j logger available.
