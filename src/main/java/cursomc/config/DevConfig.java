package cursomc.config;

import cursomc.services.DBTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategyCreationTables;

    private final DBTestService dbTestService;

    @Bean
    public void iniciatizeDB() {
        if ("create".equals(this.strategyCreationTables)) {
            this.dbTestService.setUp();
        }
    }


}
