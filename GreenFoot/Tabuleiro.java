import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tabuleiro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tabuleiro extends World
{
    /**
     * Constructor for objects of class Tabuleiro.
     * 
     */
    public Tabuleiro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(452, 452, 1);
        addBP();
        addGP();
    }
    /**
     * addBP() - Método que adiciona todas as peças azuis na posição inicial
     */
    public void addBP(){
        int i = 0, x = 50, y = 50;
        while(i<12){
            addObject(new BluePiece(),x,y);
            x+=100;
            if(i==3){
                x=100; y+=50;
            }else if(i==7){
                x=50; y+=50;
            }
            i++;
        }
    }
    /**
     * addGP() - Método que adiciona todas as peças verdes na posição inicial
     */
    public void addGP(){
        int i = 0, x = 100, y = 400;
        while(i<12){
            addObject(new GreenPiece(),x,y);
            x+=100;
            if(i==3){
                x=50; y-=50;
            }else if(i==7){
                x=100; y-=50;
            }
            i++;
        }
    }
}
