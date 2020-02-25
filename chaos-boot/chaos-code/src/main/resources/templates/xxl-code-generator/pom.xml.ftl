<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cui.tech</groupId>
        <artifactId>chaos-parent</artifactId>
        <version>2.1.0-SNAPSHOT</version>
        <relativePath/>
    </parent>

    <groupId>com.cui.tech</groupId>
    <artifactId>${package.ModuleName}-service</artifactId>
    <version>0.0.2-SNAPSHOT</version>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>com.cui.tech</groupId>
            <artifactId>chaos-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.cui.tech</groupId>
            <artifactId>${package.ModuleName}-api</artifactId>
            <version>0.0.2-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

</project>
