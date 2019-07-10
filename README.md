# Streaming from csv file

## requirements

- maven
- java 11
- wget/curl

## installation

```bash
wget https://pysparksampledata.blob.core.windows.net/sampledata/sampledata.csv > src/main/resources/static/sampledata.csv
mvn clean package
java -jar target/csvStream-1.0-SNAPSHOT.jar
```

## call endpoint

```bash
curl localhost:8080/stream
```

## documentation

In this repository a csv file is read via stream and this data is made available as streaming endpoint. The data is read line by line and not all at once, therefore not all data is stored in the main memory.
