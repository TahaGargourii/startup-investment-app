package design.startupInvestment.springboot;

import design.startupInvestment.springboot.configuration.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SpringBootStartupInvestmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootStartupInvestmentApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
