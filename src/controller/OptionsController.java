package controller;

import command.GameCommand;
import command.OptionsCommand;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.StateBasedGame;
import state.Options;
import state.StartPageState;

/**
 * Class for the options control
 */
public class OptionsController implements InputProviderListener {

    private StateBasedGame game;
    private OptionsCommand mode = OptionsCommand.NONE;

    public OptionsController(StateBasedGame game) {
        this.game = game;
    }

    @Override
    public void controlPressed(Command command) {
        this.mode = (OptionsCommand) command;
        switch (mode) {
            case STARTPAGE:
                game.enterState(StartPageState.ID);
                break;
            case DOWNSOUND:
                Options.downSoundLevel();
                break;
            case UPSOUND:
                Options.upSoundLevel();
                break;
        }
    }

    @Override
    public void controlReleased(Command command) {
    }

}
