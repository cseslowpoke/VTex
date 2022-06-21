import java.util.ArrayList;

public class SymbolAtom extends Atom {
    private boolean boldFace;
    private boolean italicFace;
    private String symbol;
    private Atom superscriptAtom;
    private Atom subscriptAtom;
    private Atom parent;

    String[] dfsOrderIsDown = {"\\sum ","\\lim "};
    public SymbolAtom(String symbol) {
        this.symbol = symbol;
        this.boldFace = false;
        this.italicFace = false;
        for(int i=0;i<dfsOrderIsDown.length;i++){
            if(symbol.equals(dfsOrderIsDown[i])){
                dfsorder = dfsOrder.DOWN;
            }
        }
    }

    public enum dfsOrder{TOP, DOWN};
    dfsOrder dfsorder = dfsOrder.TOP;
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

        switch(dfsorder){
            case TOP:{
                l.add(this);
                System.out.println(symbol);
                if(superscriptAtom!=null){
                    superscriptAtom.dfs(l);
                }
                if(subscriptAtom!=null){
                    subscriptAtom.dfs((l));
                }
                break;
            }
            case DOWN:{
                if(superscriptAtom!=null){
                    superscriptAtom.dfs(l);
                }
                System.out.println(symbol);
                if(symbol=="\\lim "){
                    l.add(this);l.add(this);
                }
                l.add(this);
                if(subscriptAtom!=null){
                    subscriptAtom.dfs((l));
                }
                break;
            }
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
    public void replace(Atom a, Atom b) {
        if(superscriptAtom!=null && superscriptAtom.equals(a)){
            b.setSuperscript(superscriptAtom.getSuperscript());
            b.setSubscript(superscriptAtom.getSubscript());
            superscriptAtom = b;
            superscriptAtom.setParent(this);
        }
        else if(subscriptAtom!=null && subscriptAtom.equals(a)){
            b.setSuperscript(subscriptAtom.getSuperscript());
            b.setSubscript(subscriptAtom.getSubscript());
            subscriptAtom = b;
            subscriptAtom.setParent(this);
        }
    }
}
