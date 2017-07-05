package exercises.collections.in.depth;

public final class Pair<T, U> {

    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("%s and %s", first.toString(), second.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Pair<?, ?>))
            return false;
        @SuppressWarnings("unchecked")
        Pair<T, U> other = (Pair<T, U>) obj;
        if (first.equals(other.first) && second.equals(other.second))
            return true;
        // allow pairs to be equal even if values are stored in a different
        // order
        if (second.equals(other.first) && first.equals(other.second))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}
