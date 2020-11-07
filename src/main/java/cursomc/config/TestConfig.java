package cursomc.config;

import cursomc.services.DBTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

    private final DBTestService dbTestService;

    @Bean
    public void iniciatizeDB() {
        this.dbTestService.setUp();
    }


}
