# Blogure

My first Clojure study-in-use project. 

Blogure is an open-source blogging system written in Clojure. It is designed for out-of-box experience.
It has no database needed, and can be compiled into a single file.

Run it, with no configure but just Java Runtime Enviornment and love :)

## Prerequisites

### To use it via a compiled jar version
Nothing.

### To use it via a compiled war
A standard Java web server, like Tomcat or something.

### To directly run via clj source files

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To run it, download latest standalone jar from releases, and 
~~~
java -jar (jarfile).jar 
~~~

If you would like run from code,
cd to the folder, and
~~~
lein run
~~~

Then just enjoy it.

## License

Copyright © 2017 Xiaodai.
You can redistribute and modify this as you like, but without commerical use.
