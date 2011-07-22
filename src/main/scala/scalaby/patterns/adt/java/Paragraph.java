package scalaby.patterns.adt.java;

public class Paragraph implements Component {

        protected String body;

        public Paragraph(String body) {
                setBody(body);
        }

        public void setBody(String s) {
                body = s;
        }

        public String getBody() {
                return body;
        }

        public Iterator getIterator() {
                return new ParagraphIterator(this);
        }

        public void accept(Visitor v) {
                v.visitParagraph(this);
        }
}
