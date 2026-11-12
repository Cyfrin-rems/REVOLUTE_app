package com.revolute;                                       
import org.junit.jupiter.api.Test;                          
import java.math.BigDecimal;                                                                                            import static org.junit.jupiter.api.Assertions.*;           
class WalletTest {

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    @Test
    void depositIncreasesBalance() {                                Wallet w = new Wallet("Rems");
        w.deposit(bd("100"));
        assertEquals(bd("100.00"), w.getBalance());             }
                                                                @Test                                                       void withdrawReducesBalance() {
        Wallet w = new Wallet("Rems");                              w.deposit(bd("100"));                                       w.withdraw(bd("40"));
        assertEquals(bd("60.00"), w.getBalance());              }                                                       
    @Test
    void withdrawMoreThanBalanceFails() {
        Wallet w = new Wallet("Rems");                              w.deposit(bd("10"));                                        assertThrows(InsufficientFundsException.class, () -> w.withdraw(bd("50")));
        assertEquals(bd("10.00"), w.getBalance());              }                                                                                                                       @Test                                                       void zeroOrNegativeAmountIsRejected() {                         Wallet w = new Wallet("Rems");                              assertThrows(IllegalArgumentException.class, () -> w.deposit(bd("0")));                                                 assertThrows(IllegalArgumentException.class, () -> w.deposit(bd("-5")));                                                assertThrows(IllegalArgumentException.class, () -> w
.deposit(null));                                                }

    @Test
    void transferMovesMoneyAndLogsBothSides() {
        Wallet a = new Wallet("Rems");
        Wallet b = new Wallet("Ada");
        a.deposit(bd("200"));                                                                                                   a.transferTo(b, bd("75"));
	assertEquals(bd("125.00"), a.getBalance());
        assertEquals(bd("75.00"), b.getBalance());                  assertEquals(Transaction.Type.TRANSFER_OUT, a.getHistory().get(0).getType());
        assertEquals(Transaction.Type.TRANSFER_IN, b.getHistory().get(0).getType());                                        }
                                                                @Test                                                       void transferWithoutFundsChangesNothing() {
        Wallet a = new Wallet("Rems");
        Wallet b = new Wallet("Ada");                               a.deposit(bd("20"));                                                                                                    assertThrows(InsufficientFundsException.class, () -> a.transferTo(b, bd("100")));
                                                                    assertEquals(bd("20.00"), a.getBalance());                  assertEquals(bd("0"), b.getBalance());                  }                                                                                                                       @Test                                                       void cannotSendToYourself() {                                   Wallet a = new Wallet("Rems");                              a.deposit(bd("50"));                                        assertThrows(IllegalArgumentException.class, () -> a.transferTo(a, bd("10")));
    }
    @Test
    void historyIsNewestFirst() {
        Wallet w = new Wallet("Rems");                              w.deposit(bd("10"));
        w.withdraw(bd("5"));
        assertEquals(Transaction.Type.WITHDRAWAL, w.getHistory().get(0).getType());                                             assertEquals(Transaction.Type.DEPOSIT, w.getHistory(
).get(1).getType());                                            }

    @Test                                                       void htmlEscapeBlocksMarkup() {                                 assertEquals("&lt;script&gt;", Html.escape("<script>"));
    }                                                       }
