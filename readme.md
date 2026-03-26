# MTS Bulk CR

## Prerequisites

- **Java 1.8** — Ensure Java 8 is installed and configured as your default JDK.  
  Set `JAVA_HOME` to your Java 8 installation path (e.g., `C:\Program Files\Java\jdk1.8.0_xxx`).
- **Apache Kafka 2.13-2.8.2** — Download the binary `.tgz` from the [Apache Kafka Downloads](https://kafka.apache.org/downloads) page.

---

## Kafka Setup

### Step 1 — Install Kafka

1. Download **kafka_2.13-2.8.2.tgz** from [https://kafka.apache.org/downloads](https://kafka.apache.org/downloads).
2. Extract the archive and place the folder at `C:\kafka`.

Your directory structure should look like:
```
C:\kafka\
  ├── bin\
  │   └── windows\
  ├── config\
  │   ├── server.properties
  │   └── zookeeper.properties
  └── ...
```

---

### Step 2 — Start Zookeeper (Terminal 1)

Open a **new terminal** and run:

```bat
cd C:\kafka
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

> Keep this terminal open. Zookeeper must remain running before starting the Kafka broker.

---

### Step 3 — Start Kafka Broker (Terminal 2)

Open a **second terminal** and run:

```bat
cd C:\kafka
bin\windows\kafka-server-start.bat config\server.properties
```

> Keep this terminal open. The Kafka broker must remain running.

---

### Step 4 — Create the Kafka Topic (Terminal 3)

Open a **third terminal** and run:

```bat
C:\kafka\bin\windows\kafka-topics.bat --create --topic bulk-queue-validation --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

---

### Step 5 — Verify the Topic

In the same terminal, verify the topic was created successfully:

```bat
C:\kafka\bin\windows\kafka-topics.bat --describe --topic bulk-queue-validation --bootstrap-server localhost:9092
```

Expected output:
```
Topic: bulk-queue-validation  PartitionCount: 3  ReplicationFactor: 1  Configs:
  Topic: bulk-queue-validation  Partition: 0  Leader: 0  Replicas: 0  Isr: 0
  Topic: bulk-queue-validation  Partition: 1  Leader: 0  Replicas: 0  Isr: 0
  Topic: bulk-queue-validation  Partition: 2  Leader: 0  Replicas: 0  Isr: 0
```

---

## Running the Application

Ensure all three Kafka terminals from above are running before starting the Spring Boot application.

```bash
mvn spring-boot:run
```

> The application requires **Java 1.8**. Make sure `JAVA_HOME` points to your JDK 8 installation.

---

## Project Structure

| Layer       | Technology          |
|-------------|---------------------|
| Language    | Java 1.8            |
| Framework   | Spring Boot         |
| Messaging   | Apache Kafka 2.13-2.8.2 |
| Topic       | `bulk-queue-validation` |
| Partitions  | 3                   |
