import java.util.ArrayList;

public class RowAtom extends Atom {
    ArrayList<Atom> row;
    public RowAtom(){
        row = new ArrayList<Atom>();
    }
    public RowAtom(String x){
        row = new ArrayList<>();
        for(int i = 0;i<x.length();i++){
            Atom tem = new SymbolAtom(x.charAt(i));
            row.add(tem);
            tem.setParent(this);
        }
    }

    @Override
    public String generate() {
        String ret = "{";
        for(Atom a : row){
            ret += a.generate();
        }
        return ret + "}";
    }
    @Override
    public void dfs(ArrayList<Atom> l){
        for(var i:row){
            i.dfs(l);
        }
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
    public Atom getParent() {
        return parent;
    }
}
