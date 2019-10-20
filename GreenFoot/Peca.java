import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Peca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Peca extends Actor
{
    static Casa c;
    static Peca p;
    static int x, y;
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
        if(Greenfoot.mouseClicked(this)){
            if(m!=null){
                if(getWorld().getObjects(Casa.class)!=null){
                    getWorld().removeObjects(getWorld().getObjects(Casa.class));
                }
                this.x = getX();
                this.y = getY();
                p = (Peca)m.getActor();
                if(p.getClass() == GreenPiece.class){
                    x+=50; y-=50;
                }else if(p.getClass() == BluePiece.class){
                    x+=50; y+=50;
                }
                if(x<450){
                    Casa c1 = new Casa();
                    getWorld().addObject(c1, x, y);
                }
                x-=100;
                if(x>=50){
                    Casa c2 = new Casa();
                    getWorld().addObject(c2, x, y);
                }
            }
        }
    }
    /**
     * mover() - metodo que muda a localização da peça selecionada anteriormente 
     * para a localização do objeto Casa que foi clicado posteriormente
     */
    public void mover(){
        if(this.c != null){
            p.setLocation(this.c.getX(), this.c.getY());
        }
    }
}

