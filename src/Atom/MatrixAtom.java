import java.util.ArrayList;

public class MatrixAtom extends ArrayAtom{
    @Override
    public String generate() {
        return super.generate();
    }
    @Override
    public void dfs(ArrayList<Atom> l){}
    Atom parent;
    public void setParent(Atom a){
        parent = a;
    }
    @Override
    public Atom getParent() {
        return parent;
    }
}
