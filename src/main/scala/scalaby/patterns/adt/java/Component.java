package scalaby.patterns.adt.java;

public interface Component {

        void accept(Visitor v);

        Iterator getIterator();
}