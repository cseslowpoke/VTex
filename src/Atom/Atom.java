import java.util.ArrayList;

public abstract class Atom {
        public abstract String generate();
        public abstract void dfs(ArrayList<Atom> l);
        public abstract boolean hasSuperscript();
        public abstract boolean hasSubscript();
        public abstract void setSuperscript(Atom a);
        public abstract void setSubscript(Atom a);
        public Atom getSuperscript(){
                return null;
        };
        public Atom getSubscript(){
                return null;
        };
        public abstract Atom getParent();
        public abstract void setParent(Atom a);
        public abstract boolean isTerminal();
        public abstract void replace(Atom a,Atom b);
}