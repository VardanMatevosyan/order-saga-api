# order-saga-api
An example of distributed transaction Saga pattern support
An entry point when saga distributed transaction starts
when the user calls the API endpoint to create an order


# Start Mongo in Docker
```
docker run --name mongodb_container -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=pass -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

