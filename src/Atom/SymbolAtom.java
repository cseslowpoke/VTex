import java.util.ArrayList;

public class SymbolAtom extends Atom {
    private boolean boldFace;
    private boolean italicFace;
    private char symbol;
    private Atom superscriptAtom;
    private Atom subscriptAtom;
    private Atom parent;
    public SymbolAtom(char symbol) {
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

    public char getSymbol() {
        return symbol;
    }

    public boolean isBoldFace() {
        return boldFace;
    }

    public boolean isItalicFace() {
        return italicFace;
    }

    public Atom getSuperscriptAtom() {
        return superscriptAtom;
    }

    public Atom getSubscriptAtom() {
        return subscriptAtom;
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
    }

    @Override
    public boolean hasSuperscript() {
        return true;
    }

    @Override
    public boolean hasSubscript() {
        return true;
    }

    @Override
    public void setSuperscript(Atom a) {
        superscriptAtom = a;
        superscriptAtom.setParent(this);
    }

    @Override
    public void setSubscript(Atom a) {
        subscriptAtom = a;
        subscriptAtom.setParent(this);
    }

    @Override
    public Atom getParent() {
        return parent;
    }

    public void setParent(Atom a){
        parent = a;
    }
}
