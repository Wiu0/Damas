import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenPiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GreenPiece extends Peca
{
    /**
     * Act - do whatever the GreenPiece wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        casasDisponiveis();
        /*if(Peca.c!=null){
            Peca.c.tiraCasa();
        }*/
    }
}