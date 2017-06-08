package character;

import hud.Hud;
import map.Map;
import org.newdawn.slick.*;

import java.util.Iterator;
import java.util.LinkedList;

public class Player extends Character {

    private LinkedList<Hud> hudList = new LinkedList<>();
    protected Animation[] animationsAtk = new Animation[4];
    private String spriteAtk;

    private Player(Map map, float x, float y, String sprite, String spriteAtk) {
        super(map,x,y,sprite);
        this.spriteAtk = spriteAtk;
    }

    private final static Player instance = new Player(Map.getInstance(), 500,500,"src/ressources/sprites/character.png", "src/ressources/sprites/attaque.png");

    public static Player getInstance() {
        return instance;
    }

    @Override
    public void init() throws SlickException {
        super.init();

        SpriteSheet spriteSheet = new SpriteSheet(this.spriteAtk, 64, 64);
        this.animationsAtk[0] = loadAnimation(spriteSheet, 0, 5, 0);
        this.animationsAtk[1] = loadAnimation(spriteSheet, 0, 5, 1);
        this.animationsAtk[2] = loadAnimation(spriteSheet, 0, 5, 2);
        this.animationsAtk[3] = loadAnimation(spriteSheet, 0, 5, 3);

        Hud barreDeVie = new Hud("barreDeVie", new Image("src/ressources/hud/barreDeVie.png"),10,10);
        hudList.add(barreDeVie);

        Image sacImage = new Image("src/ressources/hud/sac.png");
        Hud sac = new Hud("sac", sacImage, 640 - sacImage.getWidth() - 10,10);
        sac.setVisible(false);
        hudList.add(sac);
    }

    public void renderHudList(Graphics g) throws SlickException {
        Iterator<Hud> iterator = hudList.iterator();
        while (iterator.hasNext()) {
            iterator.next().render(g);
        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        if (this.nameMap.equals(Map.getInstance().getNameMap())) {
            g.setColor(new Color(0, 0, 0, .5f));
            g.fillOval(x - 16, y - 8, 32, 16);
            if (!atkable)
                g.drawAnimation(animationsWalk[direction + (moving ? 4 : 0)], x - 32, y - 60);
            else {
                g.drawAnimation(animationsAtk[direction], x - 32, y - 60);
                if (animationsAtk[direction].getFrame() == animationsAtk[direction].getFrameCount()-1) {
                    this.atkable = false;
                    animationsAtk[direction].restart();
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (player)";
    }

}
