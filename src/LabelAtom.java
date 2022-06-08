import javax.swing.*;

public class LabelAtom extends JLabel {
    private Atom atom;
    public LabelAtom(Atom a){
        this.setAtom(a);
    }
    public void setAtom(Atom atom) {
        this.atom = atom;
        this.setText(atom.generate());
    }
    public Atom getAtom() {
        return atom;
    }
}
