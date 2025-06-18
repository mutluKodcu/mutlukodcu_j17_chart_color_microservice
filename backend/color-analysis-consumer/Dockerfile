# Temel image
FROM eclipse-temurin:17-jdk-alpine

# Uygulama JAR'ını kopyalamak için geçici bir yapı aşaması
WORKDIR /app

# Maven ile derlemek istersen, multi-stage yapabilirsin
# Ama burada hazır JAR'ı kullanacağız (Spring Boot plugin sayesinde)
COPY target/color-analysis-consumer-0.0.1-SNAPSHOT.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
