import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Peca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Peca extends Actor
{
    static Casa c1, c2;
    static Peca p, pecaComida;
    static int x, y, xInicialPeca, yInicialPeca;
    static boolean temPeca = false;
    static boolean moveu = false;
    /**
     * Act - do whatever the Peca wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    /**
     * casasDisponiveis - Método que lê a peça que foi clicada e adiciona dois  
     * objetos do tipo Casa no tabuleiro, indicando as casa que a peça consegue alcança
     */
    public void casasDisponiveis(){
        MouseInfo m = Greenfoot.getMouseInfo();
        World w = getWorld();
        if(m!=null){
            if(Greenfoot.mouseClicked(this)){
                setCasa();
            }
            mover();
        }
    }
    public void setCasa(){
        MouseInfo m = Greenfoot.getMouseInfo();
        World w = getWorld();
        if(w.getObjects(Casa.class)!=null){
            w.removeObjects(w.getObjects(Casa.class));
        }
        this.x = getX();
        this.y = getY();
        p = (Peca)m.getActor();
        if(p.getClass() == GreenPiece.class){
            this.x+=50; this.y-=50;
        }else if(p.getClass() == BluePiece.class){
            this.x+=50; this.y+=50;
        }
        if(x<450){
            Casa c2 = new Casa();
            w.addObject(c2, this.x, this.y);
            this.c2 = c2;
            pecaNoCaminhoGreen("c2");
            pecaNoCaminhoBlue("c2");
        }
        x-=100;
        if(x>=50){
            Casa c1 = new Casa();
            w.addObject(c1, this.x, this.y);
            this.c1 = c1;
            pecaNoCaminhoGreen("c1");
            pecaNoCaminhoBlue("c1");
        }
    }
    public void mover(){
        World w = getWorld();
        if(Greenfoot.mousePressed(this.c1)){
            if(this.c1 != null){
                int pecaAntesDeMover = p.getX();
                p.setLocation(this.c1.getX(), this.c1.getY());
                comer(this.c1.getX(), pecaAntesDeMover);
            }
        }
        if(Greenfoot.mousePressed(this.c2)){
            if(this.c2 != null){
                int pecaAntesDeMover = p.getX();;
                p.setLocation(this.c2.getX(), this.c2.getY());
                comer(this.c2.getX(), pecaAntesDeMover);
            }
        }
    }
    public void pecaNoCaminhoGreen(String casa){
        World w = getWorld();
        if(p.getClass() == GreenPiece.class){
            if(w.getObjectsAt(this.x, this.y, GreenPiece.class)!=null){
                for(GreenPiece gp : w.getObjectsAt(this.x, this.y, GreenPiece.class)){
                    if("c1".equals(casa)){
                        w.removeObject(gp.getOneIntersectingObject(Casa.class));
                    }else if("c2".equals(casa)){
                        w.removeObject(gp.getOneIntersectingObject(Casa.class));
                    }
                }
            }
            if(w.getObjectsAt(this.x, this.y, BluePiece.class)!=null){
                for(BluePiece bp : w.getObjectsAt(this.x, this.y, BluePiece.class)){
                    if("c1".equals(casa)){
                        bp.getOneIntersectingObject(Casa.class).setLocation(this.x-50, this.y-50);
                        this.pecaComida = bp;
                        for(BluePiece bp2 : w.getObjectsAt(this.x-50, this.y-50, BluePiece.class)){
                            w.removeObject(bp2.getOneIntersectingObject(Casa.class));
                        }
                        for(GreenPiece gp2 : w.getObjectsAt(this.x-50, this.y-50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                        }
                    }else if("c2".equals(casa)){
                        bp.getOneIntersectingObject(Casa.class).setLocation(this.x+50, this.y-50);
                        this.pecaComida = bp;
                        for(BluePiece bp2 : w.getObjectsAt(this.x+50, this.y-50, BluePiece.class)){
                            w.removeObject(bp2.getOneIntersectingObject(Casa.class));
                        }
                        for(GreenPiece gp2 : w.getObjectsAt(this.x+50, this.y-50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                        }
                    }
                }
            }
        }
    }
    public void pecaNoCaminhoBlue(String casa){
        World w = getWorld();
        if(p.getClass() == BluePiece.class){
            if(w.getObjectsAt(this.x, this.y, BluePiece.class)!=null){
                for(BluePiece bp : w.getObjectsAt(this.x, this.y, BluePiece.class)){
                    if("c1".equals(casa)){
                        w.removeObject(bp.getOneIntersectingObject(Casa.class));
                    }else if("c2".equals(casa)){
                        w.removeObject(bp.getOneIntersectingObject(Casa.class));
                    }
                }
            }
            if(w.getObjectsAt(this.x, this.y, GreenPiece.class)!=null){
                for(GreenPiece gp : w.getObjectsAt(this.x, this.y, GreenPiece.class)){
                    if("c1".equals(casa)){
                        gp.getOneIntersectingObject(Casa.class).setLocation(this.x-50, this.y+50);
                        this.pecaComida = gp;
                        for(GreenPiece gp2 : w.getObjectsAt(this.x-50, this.y+50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                        }
                        for(BluePiece bp : w.getObjectsAt(this.x-50, this.y+50, BluePiece.class)){
                            w.removeObject(bp.getOneIntersectingObject(Casa.class));
                        }
                    }else if("c2".equals(casa)){
                        gp.getOneIntersectingObject(Casa.class).setLocation(this.x+50, this.y+50);
                        this.pecaComida = gp;
                        for(GreenPiece gp2 : w.getObjectsAt(this.x+50, this.y+50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                        }
                        for(BluePiece bp : w.getObjectsAt(this.x+50, this.y+50, BluePiece.class)){
                            w.removeObject(bp.getOneIntersectingObject(Casa.class));
                        }
                    }
                }
            }
        }
    }
    public void tiraCasa(){
        MouseInfo m = Greenfoot.getMouseInfo();
        World w = getWorld();
        if(m!=null){
            if(m.getButton()==3){
                if(w.getObjects(Casa.class)!=null){
                    w.removeObjects(w.getObjects(Casa.class));
                }
            }
        }
    }
    public void comer(int c, int p){
        World w = getWorld();
        if(c==p+100 || c==p-100){
            w.removeObject(this.pecaComida);
        }
    }
}