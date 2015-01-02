# gfc-time

A library that contains time related scala utility classes. Part of the gilt foundation classes.

## Contents and Example Usage

### com.gilt.gfc.time.Timer

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


### com.gilt.gfc.time.Timestamp

A immutable lightweight wrapper around a millisecond timestamp.

## License
Copyright 2014 Gilt Groupe, Inc.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
