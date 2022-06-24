package org.saphka.finance.util;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(initializers = AbstractTest.Initializer.class)
public class AbstractTest {

    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:13");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            database.start();

            TestPropertyValues.of(
                    "spring.liquibase.url=" + database.getJdbcUrl(),
                    "spring.r2dbc.url=" + "r2dbc:postgresql://" + database.getHost() + ":" + database.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT) + "/" + database.getDatabaseName(),
                    "spring.r2dbc.username=" + database.getUsername(),
                    "spring.r2dbc.password=" + database.getPassword()
            ).applyTo(applicationContext);
        }
    }


}
