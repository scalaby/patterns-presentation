package scalaby.patterns.adt.java;

public interface Visitor {

        void visitParagraph(Paragraph p);

        void visitSection(Section s);
}