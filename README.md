# Farmu 2023
Tech Lead interview for Farmu (2023-Feb)

## Requirements

* [Java 11](https://openjdk.org/install/)
* [Maven 3.6+](https://maven.apache.org/download.cgi)
* [Git](https://git-scm.com/)
* [Docker](https://www.docker.com/)
* [Docker compose](https://docs.docker.com/compose/)

## Docker compose

Docker compose on this repo has all that anybody need to start up this application locally.

* [Redis, in-memory data structure store](https://redis.io/)
* [Redis commander, redis client](https://joeferner.github.io/redis-commander/)
* [Postgres database](https://www.postgresql.org/)
* [PGAdmin4](https://www.pgadmin.org/)


## Steps to run

**Build**

```
cd url-shortener-service
mvn install
```

```
cd image-resizer-service
mvn install
```

**Run**

```
docker-compose up
```

**Run component, except `component`:**

```
$ docker-compose up --scale <component>=0
```
`components`:
* **redis** - redis server runnig on port 6379
* **redis-commander** - redis web client running on port 7000 (http://localhost:7000)
* **postgres** - postgres server running on port 5432. When the server starts for the first time, it creates a data folder named ".data" on current path and creates a **pgadmin** user with password **1234**. It also creates database **farmu_shortener** with user **url_shortener_service**/**1234**
* **flyway_shortener** - it runs flyway to update url shortener's database.
* **pgadmin4** - Postgres client
* **url-shortener** - url shortener service
* **image-resizer** - image resizer service

Eg.
```
docker-compose up --scale url-shortener=0
```
it won't run url shortener service