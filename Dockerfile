FROM openjdk:21-jdk-slim

WORKDIR /app

# Cambiamos el JAR al del microservicio de envíos
COPY target/microservicio-envios-0.0.1-SNAPSHOT.jar app.jar

# Copiamos el wallet
COPY Wallet_G76VPGAYD2UU9GM4.zip /app/

RUN apt-get update && apt-get install -y unzip && \
    unzip Wallet_G76VPGAYD2UU9GM4.zip -d /app/oracle_wallet && \
    rm Wallet_G76VPGAYD2UU9GM4.zip

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
