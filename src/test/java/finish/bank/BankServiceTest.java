package finish.bank;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import exercises.bank.AccountAlreadyExistsException;
import exercises.bank.BankDao;
import exercises.bank.BankService;
import exercises.bank.InsufficentFundsException;

public class BankServiceTest {

    private final BankDao bankDao = mock(BankDao.class);

    private final BankService bankService = new BankService(bankDao);

    @Test
    public void shouldRetrieveUsersBalance() throws Exception {
        when(bankService.getBalanceFor("tara")).thenReturn(100.00);
        assertThat(bankService.getBalanceFor("tara"), is(100.00));
    }

    @Test
    public void shouldWithdrawWhenSufficientFunds() throws Exception {
        when(bankService.getBalanceFor("tara")).thenReturn(100.00);

        bankService.withdraw("tara", 40.00);
        verify(bankDao, times(1)).setBalanceFor("tara", 60.0);
    }

    @Test(expected=InsufficentFundsException.class)
    public void shouldThrowInsufficientFundsExceptionOnWithdrawOfMoreThanAvailableBalance() throws Exception {
        when(bankDao.getBalanceFor("tara")).thenReturn(100.00);
        try {
            bankService.withdraw("tara", 100.01);
        } finally {
            verify(bankDao, times(0)).setBalanceFor(anyString(), anyDouble());
        }
    }

    @Test
    public void shouldCreateNewAccount() throws Exception {
        bankService.newAccount("Ringo Starr", 10000.0);
        verify(bankDao, only()).create("Ringo Starr", 10000.0);
    }

    @Test(expected=AccountAlreadyExistsException.class)
    public void shouldThrowAccountAlreadyExistsExceptionOnCreateAccount() throws Exception {
        when(bankDao.create("Ringo Starr", 10000.0))
            .thenThrow(new RuntimeException());
        bankService.newAccount("Ringo Starr", 10000.0);
    }
}