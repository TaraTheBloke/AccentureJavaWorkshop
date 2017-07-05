package exercises.bank;

public class AccountAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 5679710146194395849L;

    public AccountAlreadyExistsException(String name) {
        super("Account with name " + name + " already exists");
    }
}
