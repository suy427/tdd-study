package currency;

public class Money implements Expression{
    private int amount;

    public int getAmount() {
        return amount;
    }

    private String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    public String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " "+ currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return this.amount == money.amount
                && currency().equals(money.currency());
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }
}
