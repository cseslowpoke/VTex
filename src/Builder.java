public class Builder {

    Atom nowAtom;
    Builder() {
        nowAtom = null;
    }
    public void setAtom(Atom atom) {
        nowAtom = atom;
        Window.modifyPanel.setMainPanel(nowAtom);
    }
}
