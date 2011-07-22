package scalaby.patterns.adt.java;

public interface Builder {

        int addParagraph(String body, int parent) throws InvalidBuilderId;

        int addSection(String title, int parent) throws InvalidBuilderId;
}
