package scalaby.patterns.adt.java;

public class ParagraphIterator implements Iterator {

        protected Paragraph p;

        public ParagraphIterator(Paragraph p) {
                this.p = p;
        }

        public void iterate(Action a) {
                a.apply(p);
        }
}