package sham1.manacraft.api;

import com.sun.istack.internal.NotNull;

public class GenericPair <F, S>{
    private final F first;
    private final S second;

    public GenericPair(@NotNull F first, @NotNull S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "( "+first.toString()+", "+second.toString()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericPair<?, ?> that = (GenericPair<?, ?>) o;

        return first.equals(that.first) && second.equals(that.second);

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
