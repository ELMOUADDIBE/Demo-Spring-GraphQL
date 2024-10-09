package ma.enset.accountservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.accountservice.enums.AccountType;

import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double balance;
    private String currency;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
