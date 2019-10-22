//import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Casa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Casa extends Peca
{
    /**
     * Act - do whatever the Casa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       
    }
    /**
     * pegaCasa() - método que passa o objeto do tipo Casa clicado para a classe Peca
     */
    public void tiraCasa(){
        getWorld().removeObjects(getWorld().getObjects(Casa.class));
    }
}
