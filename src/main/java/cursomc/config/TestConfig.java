package cursomc.config;

import cursomc.services.DBTestService;
import cursomc.services.EmailService;
import cursomc.services.MockEmailService;
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

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }


}
