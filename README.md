# RSocket Server

This is a monolith POC RSocket server

### Other Dependencies
- MongoDB (MongoDB Community Edition, MongoDB Compass)

### Start local environment
MongoDB:
```
// syslog is reflected on console but not saved to any log file
mongod --dbpath data/db

// or

// no logs printed on console but logs are saved to configured log file
mongodb --config mongod.conf
```

CLI startup:
```
// hot build
./gradlew build --continuous

// and

// hot reload
./gradlew bootRun
```