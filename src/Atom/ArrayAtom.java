import java.util.ArrayList;

public class ArrayAtom extends Atom {
    ArrayList<ArrayList<Atom>> item;
    int maxRow;
    int maxCol;
    int rightStyle;
    int leftStyle;

    @Override
    public String generate() {
        return null;
    }

    @Override
    public void dfs(ArrayList<Atom> l){}

    @Override
    public boolean hasPos(Latex.AtomPos pos) {
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
        return false;
    }

    @Override
    public void replace(Atom a, Atom b) {

    }

    @Override
    public Atom getParent() {
        return parent;
    }

}
