package scalaby.patterns.adt.java;

import java.util.Enumeration;
import java.util.Vector;

public class PrintVisitor implements Visitor {

        protected String indent = "";
        protected Vector lines = new Vector();

        public String[] getResult() {
                String[] ss = new String[0];
                ss = (String[]) lines.toArray(ss);
                return ss;
        }

        public void visitParagraph(Paragraph p) {
                lines.addElement(indent + p.getBody());
        }

        public void visitSection(Section s) {
                String currentIndent = indent;
                lines.addElement(indent + s.getTitle());
                for (Enumeration e = s.getChildren();
                        e.hasMoreElements();) {
                        indent = currentIndent + " ";
                        ((Component) e.nextElement()).accept(this);
                }
                indent = currentIndent;
        }
}