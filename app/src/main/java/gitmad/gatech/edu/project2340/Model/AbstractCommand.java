package gitmad.gatech.edu.project2340.Model;

public abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();
    public abstract boolean execute();
    public abstract boolean undo();
}
