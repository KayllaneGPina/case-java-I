# Usar uma imagem base do Maven com JDK 17 para compilar e construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e o diretório src para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Compilar o projeto
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK 17 para executar a aplicação
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR do estágio de build anterior
COPY --from=build /app/target/case-java.jar app.jar

# Expor a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
