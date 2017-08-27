# mytodo
The simple **ToDo List App** of our code camp.

### Prerequisites
1. Install git
1. Install java set `JAVA_HOME`


###Quick Start
1. Clone this repository
1. Execute the following gradle task (preferably using gradle warpper) to setup your your local *H2 Database*: `flywayMigrate`. This will setup will create and setup a local H2 database with a single table for a starter. The H2 file will be located in your home directory `mytodo_db.mv.db`
1. Run the App either using gradle `run` oder directly from the IDE.
1. Check to see if the server is running by opening http://localhost:4567/ping
1. Check to see if the database setup was successfull by opening http://localhost:4567/persistence
6. Let's code :)