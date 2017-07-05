package exercises.bank;

public interface BankDao {

    double getBalanceFor(String name);

    void setBalanceFor(String name, double newBalance);

    boolean create(String name, double deposit);
}
