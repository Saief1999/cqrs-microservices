FROM adoptopenjdk/openjdk11:jdk-11.0.1.13-alpine-slim as build
WORKDIR /workspace/app

# copy necessary files to work dir
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# build all dependencies for offline use
RUN ./mvnw dependency:go-offline -B

# build a jar file
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11:jdk-11.0.1.13-alpine-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# copy the unpacked version of the app instead of the jar
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# execute with no shell wrapping
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.registryserver.RegistryServerApplication"]