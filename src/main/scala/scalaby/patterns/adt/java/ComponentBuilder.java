package scalaby.patterns.adt.java;

import java.util.AbstractMap;
import java.util.HashMap;

public class ComponentBuilder implements Builder {

        protected int nextId = 0;
        protected AbstractMap comps = new HashMap();

        public int addParagraph(String body, int pId)
                throws InvalidBuilderId {
                return addComponent(new Paragraph(body), pId);
        }

        public int addSection(String title, int pId)
                throws InvalidBuilderId {
                return addComponent(new Section(title), pId);
        }

        public Component getProduct() {
                return (Component) comps.get(new Integer(0));
        }

        protected int addComponent(Component c, int pId)
                throws InvalidBuilderId {
                if (pId < 0) { // root component
                        if (comps.isEmpty()) {
                                comps.put(new Integer(nextId), c);
                                return nextId++;
                        } else {
                                throw new InvalidBuilderId("Duplicate root");
                        }
                } else { // non-root
                        Component parent = (Component) comps.get(new Integer(pId));
                        if (parent == null) {
                                throw new InvalidBuilderId("Non-existent parent");
                        } else {
                                if (parent instanceof Paragraph) {
                                        throw new InvalidBuilderId("Adding child to paragraph");
                                } else {
                                        Section s = (Section) parent;
                                        s.addComponent(c);
                                        comps.put(new Integer(nextId), c);
                                        return nextId++;
                                }
                        }
                }
        }
}