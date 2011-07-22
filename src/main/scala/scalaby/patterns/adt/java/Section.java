package scalaby.patterns.adt.java;

import java.util.Vector;
import java.util.Enumeration;

public class Section implements Component {

        protected Vector children;
        protected String title;

        public Section(String title) {
                children = new Vector();
                this.title = title;
        }

        public String getTitle() {
                return title;
        }

        public void addComponent(Component c) {
                children.addElement(c);
        }

        public Enumeration getChildren() {
                return children.elements();
        }

        public Iterator getIterator() {
                return new SectionIterator(this);
        }

        public void accept(Visitor v) {
                v.visitSection(this);
        }
}