package helpClasses;

public class Pair <
        FirstType extends Comparable<FirstType>,
        SecondType extends Comparable<SecondType> >
        implements Comparable<Pair<FirstType,SecondType>> {

    private FirstType first;
    private SecondType second;

    public Pair(FirstType first, SecondType second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(FirstType first){
        this.first = first;
    }

    public void setSecond(SecondType second) {
        this.second = second;
    }

    public FirstType getFirst() {
        return this.first;
    }

    public SecondType getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return ("First = " + this.first + "; Second = " + this.second);
    }

    @Override
    public boolean equals(Object rhs) {
        if(rhs == null || !(rhs instanceof Pair))
            return false;

        Pair pair = (Pair)rhs;
        return (first.equals(pair.first) && second.equals(pair.second));
    }

    @Override
    public int compareTo(Pair<FirstType,SecondType> rhs) {
        int resValue = first.compareTo(rhs.first);
        if(resValue == 0) {
            resValue = second.compareTo(rhs.second);
        }
        return (resValue);
    }

}
