package ma.enset.accountservice.entities;

import ma.enset.accountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullAccount", types = BankAccount.class)
public interface AccountsProjection {
    public String getID();
    public double getBalance();
    public String getCurrency();
    public AccountType getAccountType();
}
