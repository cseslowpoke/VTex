import java.util.ArrayList;

public abstract class Atom {
        public abstract String generate();
        public abstract  void dfs(ArrayList<Atom> l);
        public abstract boolean hasSuperscript();
        public abstract boolean hasSubscript();
        public abstract void setSuperscript(Atom a);
        public abstract void setSubscript(Atom a);
        public abstract  Atom getParent();
        public abstract  void setParent(Atom a);

}
