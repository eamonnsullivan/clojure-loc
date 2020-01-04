# clojure-loc

My own slightly modified take on the lines of code example application
from the book Programming Clojure

## Installation

Download from https://github.com/eamonnsullivan/clojure-loc.

## Usage

clojure-loc .
clojure-loc -v ../bigproject
clojure-loc -h
etc.

Run the project directly:

    $ clojure -m eamonnsullivan.clojure-loc

Run the project's tests (they'll fail until you edit them):

    $ clojure -A:test:runner

Build an uberjar:

    $ clojure -A:uberjar

Run that uberjar:

    $ java -jar clojure-loc.jar

## Options

-h or --help: prints usage
-v or --verbose: prints out the filenames as it's counting

## License

Copyright © 2020 Eamonn Sullivan

Distributed under the Eclipse Public License either version 2.0 or (at
your option) any later version.
