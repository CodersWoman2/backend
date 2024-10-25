# 1. Temel bir Java görüntüsü kullanın
FROM openjdk:17-jdk-alpine

# 2. Uygulamanın çalışacağı dizini belirtin
WORKDIR /app

# 3. Maven'in Spring Boot projenizi derlemesi ve paketlemesi için gereken dosyayı ekleyin
COPY target/studySmart-0.0.1-SNAPSHOT.jar app.jar

# 4. Uygulamayı çalıştırın
ENTRYPOINT ["java", "-jar", "app.jar"]
