# gfc-time [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.gfccollective/gfc-time_2.12/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/org.gfccollective/gfc-time_2.12) [![Build Status](https://github.com/gfc-collective/gfc-time/workflows/Scala%20CI/badge.svg)](https://github.com/gfc-collective/gfc-time/actions) [![Coverage Status](https://coveralls.io/repos/gfc-collective/gfc-time/badge.svg?branch=master&service=github)](https://coveralls.io/github/gfc-collective/gfc-time?branch=master) 


A library that contains time related scala utility classes.
A fork and new home of the former Gilt Foundation Classes (`com.gilt.gfc`), now called the [GFC Collective](https://github.com/gfc-collective), maintained by some of the original authors.

## Getting gfc-time

The latest version is 1.0.0, which is cross-built against Scala 2.12.x and 2.13.x.

If you're using SBT, add the following line to your build file:

```scala
libraryDependencies += "org.gfccollective" %% "gfc-time" % "1.0.0"
```

For Maven and other build tools, you can visit [search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Corg.gfccollective).
(This search will also list other available libraries from the GFC Collective.)

## Contents and Example Usage

### org.gfccollective.time.Timer

Various utilities for timing & reporting on blocks of code and scala Futures.

* Format a nanosecond timespan into a human-readable string, like "37 us" or "45 days 08:55:01".

    val start = System.nanoTime()
    doSomething()
    val elapsed = Timer.pretty(System.nanoTime() - start)
    println(s"doSomething() took $elapsed")

* Report ns result (Long value)

    val result = Timer.time(l => println(s"Operation took $l ns")) {
      // timed function, returning a
      result
    }

* Report formatted result. The result String is using the best suitable time unit
  (e.g. "10 ns", "456 ms", "45 days 08:55:01" etc.)

    val result = Timer.time(s => println(s"Operation took ${s}")) {
      // timed function, returning a
      result
    }

* Time the body, then calls pretty on the elapsed time to get a more human-friendly time
  String, then passes that to the "format" method.

    val result = Timer.time("Operation took %s", println _)) {
      // timed function, returning a
      result
    }

* Similar functions as above are available to time scala Future completions.


### org.gfccollective.time.Timestamp

A immutable lightweight wrapper around a millisecond timestamp.

## License

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
