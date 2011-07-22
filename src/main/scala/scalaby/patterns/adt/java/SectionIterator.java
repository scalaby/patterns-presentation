package scalaby.patterns.adt.java;

import java.util.Enumeration;

public class SectionIterator implements Iterator {

        protected Section s;

        public SectionIterator(Section s) {
                this.s = s;
        }

        public void iterate(Action a) {
                for (Enumeration e = s.getChildren(); e.hasMoreElements();) {
                        ((Component) (e.nextElement())).getIterator().iterate(a);
                }
        }
}