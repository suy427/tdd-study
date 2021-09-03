package currency;

import java.util.HashMap;

public class Bank {
    private HashMap<Pair, Integer> rates = new HashMap<>();

    public void addRate(String from, String to, int value) {
        rates.put(new Pair(from, to), value);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    private class Pair {
        private String from;
        private String to;

        Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return from.equals(pair.from) && to.equals(pair.to);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
