import java.util.ArrayList;

public class SqrtAtom extends Atom {
    Atom nth;
    Atom inside;
    Atom superscript;
    public SqrtAtom(){}

    public void setNth(Atom a){
        nth = a;
        nth.setParent(this);
    }

    public void setInside(Atom a){
        inside = a;
        inside.setParent(this);
    }

    @Override
    public String generate() {
        String ret = "\\sqrt";
        if(nth!=null){
            ret+=String.format("[%s]",nth.generate());
        }
        if(inside!=null){
            ret+=String.format("{%s}",inside.generate());
        }
        else{
            ret+="{}";
        }
        if(superscript!=null){
            ret+=String.format("^{%s}",superscript.generate());
        }
        return ret;
    }
    @Override
    public void dfs(ArrayList<Atom> l){
        l.add(this);
        if(nth!=null) {
            nth.dfs(l);
        }
        inside.dfs((l));
    }

    @Override
    public boolean hasSuperscript() {
        return true;
    }

    @Override
    public boolean hasSubscript() {
        return false;
    }

    @Override
    public void setSuperscript(Atom a) {
        superscript = a;
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
