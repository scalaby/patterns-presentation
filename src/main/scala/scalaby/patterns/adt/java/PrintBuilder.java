package scalaby.patterns.adt.java;

import java.util.Vector;

public class PrintBuilder implements Builder {

        protected class Record {

                public int id;
                public int last;
                public String line;
                public String indent;

                public Record(int id, int last,
                        String line, String indent) {
                        this.id = id;
                        this.last = last;
                        this.line = line;
                        this.indent = indent;
                }
        }
        protected Vector records = new Vector();

        protected Record recordAt(int i) {
                return (Record) records.elementAt(i);
        }

        protected int ﬁnd(int id, int start) {
                while (start < records.size() && recordAt(start).id != id) {
                        start++;
                }
                if (start < records.size()) {
                        return start;
                } else {
                        return - 1;
                }
        }
        protected int nextId = 0;
        protected SpellCorrector c = new SpellCorrector();

        public int addParagraph(String body, int pid)
                throws InvalidBuilderId {
                return addComponent(c.correct(body), pid);
        }

        public int addSection(String title, int pid)
                throws InvalidBuilderId {
                return addComponent(title, pid);
        }

        public String[] getProduct() {
                String[] ss = new String[records.size()];
                for (int i = 0; i < ss.length; i++) {
                        ss[i] = recordAt(i).indent + recordAt(i).line;
                }
                return ss;
        }

        protected int addComponent(String s, int pId)
                throws InvalidBuilderId {
                if (pId < 0) { // root component
                        if (records.isEmpty()) {
                                records.addElement(new Record(nextId, nextId, s, ""));
                                return nextId++;
                        } else {
                                throw new InvalidBuilderId("Duplicate root");
                        }
                } else { // non-root
                        int x = ﬁnd(pId, 0);
                        Record r = recordAt(x);
                        String indent = r.indent;
                        if (x == - 1) {
                                throw new InvalidBuilderId("Non-existent parent");
                        } else {
                                int y = x; // ids [x] = ids [y] = pid
                                while (r.id != r.last) {
                                        y = x;
                                        x = ﬁnd(r.last, x);
                                        r = recordAt(x);
                                } // lasts [y] = lasts [x] = ids [x]
                                records.insertElementAt(new Record(nextId, nextId, s, indent + " "), x + 1);
                                recordAt(y).last = nextId;
                                return nextId++;
                        }
                }
        }
}