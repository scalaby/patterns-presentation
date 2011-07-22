package scalaby.patterns.adt.java;

public class InvalidBuilderId extends Exception {

        public InvalidBuilderId(String reason) {
                super(reason);
        }
}