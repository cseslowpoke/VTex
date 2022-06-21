import java.util.ArrayList;

public class RowAtom extends Atom {
    ArrayList<Atom> row;
    public RowAtom(){
        row = new ArrayList<Atom>();
    }
    public RowAtom(String x){

    }
    public RowAtom(Atom a){
        row = new ArrayList<Atom>();
        row.add(a);
        a.setParent(this);
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
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void replace(Atom a, Atom b) {
        for(int i=0;i<row.size();i++){
            if(row.get(i).equals(a)){
                b.setParent(this);
                b.setSuperscript(row.get(i).getSuperscript());
                b.setSubscript(row.get(i).getSubscript());
                row.set(i,b);
                break;
            }
        }
    }

    @Override
    public Atom getParent() {
        return parent;
    }

    public void insert(Atom a,Atom b){
        for(int i=0;i<row.size();i++){
            if(row.get(i).equals(a)){
                row.add(i+1,b);
                b.setParent(this);
                break;
            }
        }
    }

    public void insert(Atom b){
        row.add(b);
        b.setParent(this);
    }
}
