# ===== Étape 1 : build =====
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copier uniquement le pom.xml d'abord pour profiter du cache Docker
# sur les dépendances (elles ne changent pas à chaque modif de code)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et construire le jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# ===== Étape 2 : exécution =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Récupérer uniquement le jar généré à l'étape précédente
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
