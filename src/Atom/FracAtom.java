import java.util.ArrayList;

public class FracAtom extends Atom {
    @Override
    public String generate() {
        return String.format("\\frac{%s}{%s}",numerator.generate(),denominator.generate());
    }
    Atom numerator;
    Atom denominator;
    public FracAtom(){
        numerator = new SpaceAtom();
        denominator = new SpaceAtom();
        numerator.setParent(this);
        denominator.setParent(this);
    };
    public FracAtom(String x,String y){
        numerator = new RowAtom(x);
        denominator = new RowAtom(y);
        numerator.setParent(this);
        denominator.setParent(this);
    }
    public void setNumerator(Atom a){
        numerator = a;
        numerator.setParent(this);
    }
    public void setDenominator(Atom a){
        denominator = a;
        denominator.setParent(this);
    }
    @Override
    public void dfs(ArrayList<Atom> l){
        numerator.dfs(l);
        denominator.dfs(l);
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
        if(numerator.equals(a)){
            b.setParent(this);
            b.setSuperscript(numerator.getSuperscript());
            b.setSubscript(numerator.getSubscript());
            numerator = b;
        }
        else if(denominator.equals(a)){
            b.setParent(this);
            b.setSuperscript(denominator.getSuperscript());
            b.setSubscript(denominator.getSubscript());
            denominator=b;
        }
    }

    @Override
    public Atom getParent() {
        return parent;
    }
}
