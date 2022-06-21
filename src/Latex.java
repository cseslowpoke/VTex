import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Latex {
    public Atom root;
    final int fontSize = 100;
    ArrayList<Atom> dfsList;
    public String generateFormula(){
        return root.generate();
    }

    ArrayList<Atom> getDfsList(){
        ArrayList<Atom> l = new ArrayList<>();
        root.dfs(l);
        return l;
    }

    public String draw(Graphics g){
        g.clearRect(0,0,500,500);
        Box.Inset = new LinkedList<>();
        String latexFormula = generateFormula();
        TeXFormula formula = new TeXFormula(latexFormula);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, fontSize);
        BufferedImage image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        //System.out.println(icon.getIconHeight());
        icon.paintIcon(jl, g2, 0, 0);
        g.drawImage(image,0,0,null);
        dfsList = getDfsList();
        //debug
        gr=g;
        return latexFormula;
    }
    Graphics gr;
    void mouseClick(int x, int y){
        int clicked = -1;
        for(int i = 0; i< org.scilab.forge.jlatexmath.Box.Inset.size(); i++){
            Rectangle2D.Float rect = Box.Inset.get(i);
            if(rect.height==0) {
                rect.y-=0.5;
                rect.height=0.5f;
            }
            int ulx=(int)(rect.x*fontSize);
            int uly=(int)(rect.y*fontSize);
            int brx=(int)(rect.x*fontSize+rect.width*fontSize);
            int bry=(int)(rect.y*fontSize+rect.height*fontSize);
            if(ulx<=x&&x<=brx&&uly<=y&&y<=bry){
                clicked = i;
            }
        }
        if(clicked!=-1&&dfsList!=null){
            if(clicked<dfsList.size()){
                System.out.println(dfsList.get(clicked));
                if(dfsList.get(clicked) instanceof SymbolAtom){
                    System.out.printf("%s\n",((SymbolAtom) dfsList.get(clicked)).getSymbol());
                }
            }
        }
        addAtom(new SymbolAtom("A"),x,y);
    }
    enum AtomPos{TOP_RIGHT, RIGHT, BOTTOM_RIGHT,CENTER,NONE};

    int distSquare(int x1,int y1,int x2,int y2){
        int t1 = x2-x1;
        int t2 = y2-y1;
        return t1*t1+t2*t2;
    }

    boolean addAtom(Atom a,int x,int y){
        int select = -1;
        AtomPos pos = AtomPos.NONE;
        System.out.printf("%d\n",dfsList.size());
        for(int i=0;i<Box.Inset.size()&&i<dfsList.size();i++){
            Rectangle2D.Float rect = Box.Inset.get(i);
            if(rect.height==0) {
                rect.y-=0.5;
                rect.height=0.5f;
            }

            int centerx = (int)(rect.x*fontSize + rect.width*fontSize/2);
            int centery = (int)(rect.y*fontSize + rect.height*fontSize/2);
            int toprightx = (int)(rect.x*fontSize+rect.width*fontSize);
            int toprighty = (int)(rect.y*fontSize) - (int)(rect.height*fontSize*0.4f);
            int bottomrightx = (int)(rect.x*fontSize+rect.width*fontSize) - (int)(rect.width*fontSize*0.2);
            int bottomrighty = (int)(rect.y*fontSize+rect.height*fontSize) + (int)(rect.height*fontSize*0.3);
            int rightx = (int)(rect.x*fontSize+rect.width*fontSize) + (int)(rect.width*fontSize*0.15);
            int righty = (int)(rect.y*fontSize + rect.height*fontSize/2);
            int range = (int)(rect.height*fontSize*rect.height*fontSize*0.25);
            //System.out.printf("topright dist: %d\n",distSquare(x,y,toprightx,toprighty));
            //System.out.printf("bottomright dist :%d\n",distSquare(x,y,bottomrightx,bottomrighty));
            //System.out.printf("center dist :%d\n",distSquare(x,y,centerx,centery));

            //gr.drawOval(centerx-(int)(Math.sqrt(range)),centery-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
            //gr.drawOval(toprightx-(int)(Math.sqrt(range)),toprighty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
            //gr.drawOval(bottomrightx-(int)(Math.sqrt(range)),bottomrighty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
            //gr.drawOval(rightx-(int)(Math.sqrt(range)),righty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);

            if(dfsList.get(i).hasSuperscript() && distSquare(x,y,toprightx,toprighty)<range){
                pos = AtomPos.TOP_RIGHT;
                System.out.println("topright\n");
                select = i;
            }
            else if(dfsList.get(i).hasSubscript()&& distSquare(x,y,bottomrightx,bottomrighty)<range){
                pos = AtomPos.BOTTOM_RIGHT;
                System.out.println("bottomright\n");
                select = i;
            }
            else if(distSquare(x,y,centerx,centery)<range){
                pos = AtomPos.CENTER;
                System.out.println("center\n");
                select = i;
            }
            else if(distSquare(x,y,rightx,righty)<range){
                pos = AtomPos.RIGHT;
                System.out.println("right\n");
                select = i;
            }
        }
        if(select!=-1){
            switch (pos){
                case TOP_RIGHT : {
                    dfsList.get(select).setSuperscript(a);
                    break;
                }
                case BOTTOM_RIGHT : {
                    dfsList.get(select).setSubscript(a);
                    break;
                }
                case CENTER:{
                    Atom p = dfsList.get(select).getParent();
                    if(p!=null) {
                        p.replace(dfsList.get(select), a);
                    }
                    else{
                        Atom cur = dfsList.get(select);
                        a.setSuperscript(cur.getSuperscript());
                        a.setSubscript(cur.getSubscript());
                        root = a;
                    }
                    break;
                }
                case RIGHT:{
                    Atom p = dfsList.get(select).getParent();
                    if(p instanceof RowAtom){
                        ((RowAtom) p).insert(dfsList.get(select),a);

                    }
                    else if(p!=null){
                        RowAtom newRow = new RowAtom(dfsList.get(select));
                        newRow.insert(a);
                        p.replace(dfsList.get(select), newRow);
                    }
                    else{
                        Atom newRow = new RowAtom(root);
                        root = newRow;
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

}
