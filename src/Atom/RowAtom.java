import java.util.ArrayList;

public class RowAtom extends Atom {
    ArrayList<Atom> row;
    public RowAtom(){
        row = new ArrayList<Atom>();
    }
    @Override
    public String generate() {
        String ret = "{";
        for(Atom a : row){
            ret += a.generate();
        }
        return ret + "}";
    }
}
