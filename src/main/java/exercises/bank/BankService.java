package exercises.bank;

public class BankService {

    private final BankDao dao;

    public BankService(BankDao dao) {
        this.dao = dao;
    }

    public double getBalanceFor(String name) {
        return dao.getBalanceFor(name);
    }

    public void withdraw(String name, double amount) throws InsufficentFundsException {
        double newBalance = dao.getBalanceFor(name) - amount;
        if (newBalance < 0) {
            throw new InsufficentFundsException();
        }
        dao.setBalanceFor(name, newBalance);
    }

    public void newAccount(String name, double deposit) throws AccountAlreadyExistsException {
        try {
            dao.create(name, deposit);
        } catch (RuntimeException e) {
            throw new AccountAlreadyExistsException(name);
        }
    }
}