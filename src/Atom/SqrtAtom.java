import java.util.ArrayList;

public class SqrtAtom extends Atom {
    Atom nth;
    Atom inside;
    Atom superscript;
    public SqrtAtom(){}

    public SqrtAtom(String s){
        inside = new SymbolAtom(s);
        inside.setParent(this);
        nth = new SpaceAtom();
        nth.setParent(this);
    }

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
        if(nth!=null) {
            nth.dfs(l);
        }
        l.add(this);
        inside.dfs(l);
    }

    @Override
    public boolean hasPos(Latex.AtomPos pos) {
        switch(pos){
            case TOP_RIGHT :{
                return true;
            }
            default:{
                return false;
            }
        }
    }

    @Override
    public void setSuperscript(Atom a) {
        if(a!=null){
            superscript = a;
            a.setParent(this);
        }
    }

    @Override
    public void setSubscript(Atom a) {

    }

    @Override
    public Atom getSuperscript() {
        return superscript;
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
        if(a.equals(nth)){
            b.setParent(this);
            b.setSuperscript(nth.getSuperscript());
            b.setSubscript(nth.getSubscript());
            nth=b;
        }
        else if(a.equals(inside)){
            b.setParent(this);
            b.setSuperscript(inside.getSuperscript());
            b.setSubscript(inside.getSubscript());
            inside=b;
        }
        else if(a.equals(superscript)){
            b.setParent(this);
            b.setSuperscript(superscript.getSuperscript());
            b.setSubscript(superscript.getSubscript());
            superscript=b;
        }
    }

    @Override
    public Atom getParent() {
        return parent;
    }
}
