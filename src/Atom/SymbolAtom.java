public class SymbolAtom extends Atom {
    private boolean boldFace;
    private boolean italicFace;
    private String symbol;
    private Atom superscriptAtom;
    private Atom subscriptAtom;

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

    public void setSuperscriptAtom(Atom superscriptAtom) {
        this.superscriptAtom = superscriptAtom;
    }

    public void setSubscriptAtom(Atom subscriptAtom) {
        this.subscriptAtom = subscriptAtom;
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
}
