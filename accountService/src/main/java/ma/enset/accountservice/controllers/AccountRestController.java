package ma.enset.accountservice.controllers;

import ma.enset.accountservice.entities.BankAccount;
import ma.enset.accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

//    @GetMapping("/bankAccounts")
//    public List<BankAccount> bankAccounts(){
//        return bankAccountRepository.findAll();
//    }
//
//    @GetMapping("bankAccounts/{id}")
//    public BankAccount bankAccount(@PathVariable String id){
//        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Bank account not found"));
//    }
}
