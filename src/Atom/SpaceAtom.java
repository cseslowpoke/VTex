import java.util.ArrayList;

public class SpaceAtom extends Atom {
    @Override
    public String generate() {
        return "\\ \\ ";
    }
    @Override
    public void dfs(ArrayList<Atom> l){
        l.add(this);
    }

    @Override
    public boolean hasSuperscript() {
        return false;
    }

    @Override
    public boolean hasSubscript() {
        return false;
    }

    @Override
    public void setSuperscript(Atom a) {
    }

    @Override
    public void setSubscript(Atom a) {
    }


    Atom parent;
    public void setParent(Atom a){
        parent = a;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public void replace(Atom a, Atom b) {}

    @Override
    public Atom getParent() {
        return parent;
    }
}
