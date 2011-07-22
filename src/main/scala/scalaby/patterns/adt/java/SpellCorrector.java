package scalaby.patterns.adt.java;

public class SpellCorrector implements Action {

        public void apply(Paragraph p) {
                p.setBody(correct(p.getBody()));
        }

        public String correct(String s) {
                return s.toLowerCase();
        }
}