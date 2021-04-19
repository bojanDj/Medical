# medic

A Clojure  web application designed to determine based on the entered symptoms the probability that you are suffering from some disease
	Home page (route /index) presents some health news.
	Search page (route /index) is page on which user can chose from dropdown menu some questions about his health and based on that answers, after click on button with title "Analyze", the user is shown a graph which determines the probability of suffering from some diseases (route /score).

## Prerequisites

You will need Leiningen 2.0.0 or above installed.

## Running

To start a web server for the application, run:

	lein run
	
In your webser type http://localhost:8080/

## License

Copyright © 2021 Bojan Djekic

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
