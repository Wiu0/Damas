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
    // recebe as casas qdo criadas
    static Peca p, pecaPuladaC1, pecaPuladaC2, dama;
    // recebem, respectivamente, a peça clicada, a peça que vai ser comida, a peça que vai ser promovida e a dama qdo for clicada
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
            // remove as casas ao clicar em outra peça
        }
        this.x = getX();
        // x recebe o valor do eixo x da peça clicada
        this.y = getY();
        // y recebe o valor do eixo y da peça clicada
        p = (Peca)m.getActor();
        //p recebe o ator que foi clicado que é convertido em peça
        if(p.getClass() == GreenPiece.class){
            this.x+=50; this.y-=50;
            // se a peça for verde, x recebe mais 50, e y menos 50
        }else if(p.getClass() == BluePiece.class){
            this.x+=50; this.y+=50;
            // senão, se for azul, x e y recebem mais 50
        }
        // os valores de x e y, agora, se referem a posição da casa disponivel a direita, chamada c2
        if(x<450){
            Casa c2 = new Casa();
            w.addObject(c2, this.x, this.y);
            this.c2 = c2;
            // se x for menor que 450, adiciona a casa c2, e a variavel estatica c2 recebe c2
            pecaNoCaminhoGreen("c2");
            pecaNoCaminhoBlue("c2");
        }
        x-=100;
        if(x>=50){
            Casa c1 = new Casa();
            w.addObject(c1, this.x, this.y);
            this.c1 = c1;
            // se x for maior ou igual que 50, adiciona a casa c1, e a variavel estatica c1 recebe c1
            pecaNoCaminhoGreen("c1");
            pecaNoCaminhoBlue("c1");
        }
    }
    public void mover(){
        World w = getWorld();
        if(Greenfoot.mousePressed(this.c1) && this.c1 != null){
            // se c1 for pressionado e não nulo
            int pecaAntesDeMover = p.getX();
            // pecaAntesDeMover recebe o eixo x da peça
            p.setLocation(this.c1.getX(), this.c1.getY());
            // localização da peça alterada para a posição da casa clicada
            comer(this.c1.getX(), pecaAntesDeMover);
            invocarGreenQueen();
            invocarBlueQueen();
        }
        if(Greenfoot.mousePressed(this.c2) && this.c2 != null){
            // se c2 for pressionado e não nulo
            int pecaAntesDeMover = p.getX();
            // pecaAntesDeMover recebe o eixo x da peça
            p.setLocation(this.c2.getX(), this.c2.getY());
            // localização da peça alterada para a posição da casa clicada
            comer(this.c2.getX(), pecaAntesDeMover);
            invocarGreenQueen();
            invocarBlueQueen();
        }
    }
    public void pecaNoCaminhoGreen(String casa){
        World w = getWorld();
        if(p.getClass() == GreenPiece.class && w.getObjectsAt(this.x, this.y, GreenPiece.class)!=null){
            // se a peça clicada for verde e existir um objeto do tipo GreenPiece no eixo x e y (posição de uma das casas)
            for(GreenPiece gp : w.getObjectsAt(this.x, this.y, GreenPiece.class)){
                if("c1".equals(casa)){
                    w.removeObject(gp.getOneIntersectingObject(Casa.class));
                    // para cada peça verde na lista de peças verdes na posição de umas das casas, se chamado pela casa c1
                    // remove a casa
                    // em outras palavra, ve se possui uma peça verde na frente da peça verde clicada, não vai aparecer uma casa
                }else if("c2".equals(casa)){
                     w.removeObject(gp.getOneIntersectingObject(Casa.class));
                     // igualmente para qdo o metodo é chamado pela casa c2
                }
            }
            if(w.getObjectsAt(this.x, this.y, BluePiece.class)!=null){
                for(BluePiece bp : w.getObjectsAt(this.x, this.y, BluePiece.class)){
                    if("c1".equals(casa)){
                        bp.getOneIntersectingObject(Casa.class).setLocation(bp.getX()-50, bp.getY()-50);
                        // agr caso não tenha uma peça verde e sim uma azul, qdo chamado pela casa c1, este metodo altera
                        // a localização da casa c1, pulando a casa com a peça azul
                        this.pecaPuladaC1 = bp;
                        // variavel estatica recebe a peça a ser removida
                        for(BluePiece bp2 : w.getObjectsAt(this.x-50, this.y-50, BluePiece.class)){
                            w.removeObject(bp2.getOneIntersectingObject(Casa.class));
                            // se c1 muda para uma casa que tambem esta ocupado por uma peça azul, ele remove a casa
                        }
                        for(GreenPiece gp2 : w.getObjectsAt(this.x-50, this.y-50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                            // se c1 muda para uma casa que esta ocupado por uma peça verde, ele remove a casa
                        }
                    }else if("c2".equals(casa)){
                        // aqui a ideia se repete, mas com o chamado qdo é feito pela casa c2
                        //if(this.x+50<=400 && this.y-50>=50){
                        bp.getOneIntersectingObject(Casa.class).setLocation(bp.getX()+50, bp.getY()-50);
                        this.pecaPuladaC2 = bp;
                        //}
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
        // aqui a ideia é a mesma do metodo pecaNoCaminhoGreen, mas agr para as peças azuis
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
                        this.pecaPuladaC1 = gp;
                        for(GreenPiece gp2 : w.getObjectsAt(this.x-50, this.y+50, GreenPiece.class)){
                            w.removeObject(gp2.getOneIntersectingObject(Casa.class));
                        }
                        for(BluePiece bp : w.getObjectsAt(this.x-50, this.y+50, BluePiece.class)){
                            w.removeObject(bp.getOneIntersectingObject(Casa.class));
                        }
                    }else if("c2".equals(casa)){
                        gp.getOneIntersectingObject(Casa.class).setLocation(this.x+50, this.y+50);
                        this.pecaPuladaC2 = gp;
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
        if(m!=null && m.getButton()==3 && w.getObjects(Casa.class)!=null){
            w.removeObjects(w.getObjects(Casa.class));
            // se minha informação do mouse não tiver vazia E p botão esquerdo do mouse for pressionado E exisir objetos do tipo Casa
            // no munfo, essa casas serão removidas ao clicar com o botão esquerdo
        }
    }
    public void comer(int c, int p){
        World w = getWorld();
        if(c==p+100){
            // se a posição da casa, no eixo x, estiver a 100px tanto para a direita quanto para a esquerda
            // remove a peça a ser comida
            w.removeObject(this.pecaPuladaC2);
        }else if(c==p-100){
            w.removeObject(this.pecaPuladaC1);
        }
    }
    public void invocarGreenQueen(){
        World w = getWorld();
        if(p!=null && p.getY()==50){
            GreenQueen gq = new GreenQueen();
            w.addObject(gq, p.getX(), p.getY());
            this.dama = gq;
        }
    }
    public void invocarBlueQueen(){
        World w = getWorld();
        if(p!=null && p.getY()==400){
            BlueQueen bq = new BlueQueen();
            w.addObject(bq, p.getX(), p.getY());
            this.dama = bq;
        }
    }
}



