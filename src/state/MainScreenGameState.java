package state;

import controller.MainScreenController;
import hud.MainScreenHud;
import org.newdawn.slick.*;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

    public static final int ID = 1;
    private Image background;
    private StateBasedGame game;
    private Music music;
    private MainScreenHud hud;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image("src/ressources/background/snow.png");
        this.music = new Music("src/ressources/sound/OveMelaaDarkBlue.ogg");

        InputProvider provider = new InputProvider(container.getInput());
        MainScreenController controller = new MainScreenController(game, container);
        provider.addListener(controller);
        this.hud = new MainScreenHud(controller);
        this.hud.init(container,game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        this.hud.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void keyReleased(int key, char c) {

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        music.loop(1,0);
        music.fade(1500,0.8f,false);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        music.stop();
    }



}
