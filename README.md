# Game Gratification Application
Application to perform real-time stream analysis of user game progress events and take action as per defined business criteria.
Please refer [Problem-Statement.txt](https://github.com/SandeepGitHub3/game-gratification-application/blob/main/Problem-Statement.txt) for detailed requirement spec.

# Tech Stack:

1.Spring Boot

2.Java 8

3.Spring Cloud Stream

4.Spring Kafka

# Kafka Start/Stop
Navigate to root folder

    /game-gratification-application
    
Start Kafka Cluster

    `docker-compose -f kafka-docker-compose.yml up`
Stop Kafka Cluster

    `docker-compose -f kafka-docker-compose.yml down`
    
# Start Event Producer
    cd /game-gratification-application/game-event-producer
    ./gradlew bootrun
    
# Start Event Processor    
    cd /game-gratification-application/game-event-processor
    ./gradlew bootrun
    
# Rest Endpoints
Producer HealthCheck: http://localhost:8081//actuator/health

Processor HealthCheck: http://localhost:8082//actuator/health

Produce Sample Events: http://localhost:8081///game-event-producer/generate-event    
    
    
    
