import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Latex {
    public Atom root;
    final int fontSize = 100;
    ArrayList<Atom> dfsList = new ArrayList<>();
    public String generateFormula(){
        if(root!=null) {
            return root.generate();
        }
        else{
            return "";
        }
    }

    ArrayList<Atom> getDfsList(){
        ArrayList<Atom> l = new ArrayList<>();
        if(root !=null) {
            root.dfs(l);
        }
        return l;
    }
    Graphics gr;
    BufferedImage image;
    final int topx = 200;
    final int topy = 200;
    public String draw(Graphics g){
        gr=g;
        Box.Inset = new LinkedList<>();
        String latexFormula = generateFormula();
        TeXFormula formula = new TeXFormula(latexFormula);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, fontSize);
        image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        icon.paintIcon(jl, g2, 0, 0);
        g.drawImage(image,topx,topy,null);
        dfsList = getDfsList();
        /*for(Rectangle2D.Float i:Box.Inset){
            if(i.height==0){
                i.y-= 0.5f;
                i.height = 0.5f;
            }
            g.drawRect((int)(i.x*fontSize),(int)(i.y*fontSize),(int)(i.width*fontSize),(int)(i.height*fontSize));
        }*/
        return latexFormula;
    }

    public enum AtomPos{TOP_RIGHT, RIGHT, BOTTOM_RIGHT,CENTER,NONE};

    int distSquare(int x1,int y1,int x2,int y2){
        int t1 = x2-x1;
        int t2 = y2-y1;
        return t1*t1+t2*t2;
    }

    boolean addAtom(Atom a,int x,int y){
        if(root == null){
            root = a;
            return true;
        }
        int select = -1;
        AtomPos pos = AtomPos.NONE;
        for(int i=0;i<Box.Inset.size()&&i<dfsList.size();i++){
            Rectangle2D.Float rect = Box.Inset.get(i);
            if(rect.height==0) {
                rect.y-=0.5;
                rect.height=0.5f;
            }

            int centerx = topx+(int)(rect.x*fontSize + rect.width*fontSize/2);
            int centery = topy+(int)(rect.y*fontSize + rect.height*fontSize/2);
            int toprightx = topx+(int)(rect.x*fontSize+rect.width*fontSize);
            int toprighty = topy+(int)(rect.y*fontSize) - (int)(rect.height*fontSize*0.4f);
            int bottomrightx = topx+(int)(rect.x*fontSize+rect.width*fontSize) - (int)(rect.width*fontSize*0.2);
            int bottomrighty = topy+(int)(rect.y*fontSize+rect.height*fontSize) + (int)(rect.height*fontSize*0.3);
            int rightx = topx+(int)(rect.x*fontSize+rect.width*fontSize) + (int)(rect.width*fontSize*0.15);
            int righty = topy+(int)(rect.y*fontSize + rect.height*fontSize/2);

            int range = Math.min((int)(rect.height*fontSize*rect.height*fontSize*0.25),800);
            //System.out.printf("topright dist: %d\n",distSquare(x,y,toprightx,toprighty));
            //System.out.printf("bottomright dist :%d\n",distSquare(x,y,bottomrightx,bottomrighty));
            //System.out.printf("center dist :%d\n",distSquare(x,y,centerx,centery));

            if(dfsList.get(i).hasPos(AtomPos.RIGHT)){
                gr.drawOval(rightx-(int)(Math.sqrt(range)),righty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
                if(distSquare(x,y,rightx,righty)<range) {
                    pos = AtomPos.RIGHT;
                    select = i;
                }
            }
            if(dfsList.get(i).hasPos(AtomPos.CENTER)){
                gr.drawOval(centerx-(int)(Math.sqrt(range)),centery-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
                if(distSquare(x,y,centerx,centery)<range) {
                    pos = AtomPos.CENTER;
                    select = i;
                }
            }

            if(dfsList.get(i).hasPos(AtomPos.BOTTOM_RIGHT)){
                gr.drawOval(bottomrightx-(int)(Math.sqrt(range)),bottomrighty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
                if(distSquare(x,y,bottomrightx,bottomrighty)<range) {
                    pos = AtomPos.BOTTOM_RIGHT;
                    select = i;
                }
            }
            if(dfsList.get(i).hasPos(AtomPos.TOP_RIGHT)){
                gr.drawOval(toprightx-(int)(Math.sqrt(range)),toprighty-(int)(Math.sqrt(range)),(int)(Math.sqrt(range))*2,(int)(Math.sqrt(range))*2);
                if(distSquare(x,y,toprightx,toprighty)<range) {
                    pos = AtomPos.TOP_RIGHT;
                    select = i;
                }
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
                    if(!(dfsList.get(select) instanceof SqrtAtom)) {
                        Atom p = dfsList.get(select).getParent();
                        if (p != null) {
                            p.replace(dfsList.get(select), a);
                        }
                        else {
                            Atom cur = dfsList.get(select);
                            a.setSuperscript(cur.getSuperscript());
                            a.setSubscript(cur.getSubscript());
                            root = a;
                        }
                    }
                    else{
                        return false;
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
                        RowAtom newRow = new RowAtom(root);
                        root = newRow;
                        newRow.insert(a);
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    public void download() {
        if(image == null) {
            return;
        }
        try {
            File outputFile = new File("saved.png");
            ImageIO.write(image, "png", outputFile);
        }
        catch (IOException e) {
            System.out.println("Error writing file");
        }
    }
}
