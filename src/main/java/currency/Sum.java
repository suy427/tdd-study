package currency;

public class Sum implements Expression {
    public Expression augend;
    public Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int amount = augend.reduce(bank, to).getAmount() + addend.reduce(bank, to).getAmount();
        return new Money(amount, to);
    }
}
