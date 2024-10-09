package ma.enset.accountservice.controllers;

import jakarta.validation.Valid;
import ma.enset.accountservice.dto.BankAccountInput;
import ma.enset.accountservice.entities.BankAccount;
import ma.enset.accountservice.enums.AccountType;
import ma.enset.accountservice.repositories.BankAccountRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AccountGraphQLController {
    private final BankAccountRepository bankAccountRepository;

    public AccountGraphQLController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount account(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
    }

    @MutationMapping
    public BankAccount addAccount(@Argument @Valid BankAccountInput accountInput) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(accountInput.getBalance())
                .currency(accountInput.getCurrency())
                .creationDate(new Date())
                .accountType(AccountType.valueOf(accountInput.getAccountType()))
                .build();
        return bankAccountRepository.save(bankAccount);
    }

    @MutationMapping
    public BankAccount updateAccount(@Argument String id, @Argument @Valid BankAccountInput accountInput) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
        bankAccount.setBalance(accountInput.getBalance());
        bankAccount.setCurrency(accountInput.getCurrency());
        bankAccount.setAccountType(AccountType.valueOf(accountInput.getAccountType()));
        return bankAccountRepository.save(bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("Bank account not found");
        }
        bankAccountRepository.deleteById(id);
        return true;
    }
}
