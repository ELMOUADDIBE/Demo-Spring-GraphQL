package ma.enset.accountservice;

import ma.enset.accountservice.entities.BankAccount;
import ma.enset.accountservice.enums.AccountType;
import ma.enset.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, RepositoryRestConfiguration restConfiguration) {
        {
            return args -> {
                restConfiguration.exposeIdsFor(BankAccount.class);
                for (int i = 0; i < 10; i++) {
                    bankAccountRepository.save(
                            BankAccount.builder()
                                    .balance(1000 * Math.random())
                                    .currency(Math.random() > 0.5 ? "USD" : "EUR")
                                    .creationDate(new Date())
                                    .accountType(i % 2 == 0 ? AccountType.CURRENT : AccountType.SAVING)
                                    .build()
                    );
                }
            };
        }
    }
}
