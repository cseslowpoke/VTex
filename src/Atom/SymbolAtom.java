import java.util.ArrayList;

public class SymbolAtom extends Atom {
    private boolean boldFace;
    private boolean italicFace;
    private String symbol;
    private Atom superscriptAtom;
    private Atom subscriptAtom;
    private Atom parent;
    public SymbolAtom(String symbol) {
        this.symbol = symbol;
        this.boldFace = false;
        this.italicFace = false;
    }

    public void setBoldFace(boolean boldFace) {
        this.boldFace = boldFace;
    }

    public void setItalicFace(boolean italicFace) {
        this.italicFace = italicFace;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isBoldFace() {
        return boldFace;
    }

    public boolean isItalicFace() {
        return italicFace;
    }

    @Override
    public String generate() {
        String ret = "{";
        if (boldFace) {
            ret += "\\bf{ ";
        }
        if (italicFace) {
            ret += "\\it{ ";
        }
        ret += symbol;
        if (italicFace) {
            ret += "}";
        }
        if (boldFace) {
            ret += "}";
        }
        if(superscriptAtom != null) {
            ret += "^" + superscriptAtom.generate();
        }
        if(subscriptAtom != null) {
            ret += "_" + subscriptAtom.generate();
        }
        return ret + "}";
    }
    @Override
    public void dfs(ArrayList<Atom> l){
        l.add(this);
        if(superscriptAtom!=null){
            superscriptAtom.dfs(l);
        }
        if(subscriptAtom!=null){
            subscriptAtom.dfs((l));
        }
    }

    @Override
    public boolean hasPos(Latex.AtomPos pos) {
        switch(pos){
            case TOP_RIGHT :
            case BOTTOM_RIGHT:
            case RIGHT:
            case CENTER:{
                return true;
            }
            default:{
                return false;
            }
        }
    }

    @Override
    public void setSuperscript(Atom a) {
        if(a!=null) {
            superscriptAtom = a;
            superscriptAtom.setParent(this);
        }
    }

    @Override
    public void setSubscript(Atom a) {
        if(a!=null) {
            subscriptAtom = a;
            subscriptAtom.setParent(this);
        }
    }

    @Override
    public Atom getSuperscript() {
        return  superscriptAtom;
    }

    @Override
    public Atom getSubscript() {
        return subscriptAtom;
    }

    @Override
    public Atom getParent() {
        return parent;
    }

    public void setParent(Atom a){
        parent = a;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public void replace(Atom a, Atom b) {}
}
