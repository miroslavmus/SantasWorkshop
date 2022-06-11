package actors;

import interfaces.ObservedObject;
import interfaces.Observer;
import interfaces.Subscriber;
import magic.Command;

import java.util.ArrayList;
import java.util.List;

public class MagicBoard implements Subscriber<Command>, ObservedObject {

    private List<Command> commands;

    private List<Observer> observers;

    public MagicBoard() {

        this.commands = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Command fulfillCommand() {

        if (commands != null && !commands.isEmpty()) {

            var cmd = commands.get(0);
            commands.remove(cmd);
            return cmd;
        }

        return null;
    }

    @Override
    public void objectChanged(Command obj) {

        if (commands != null) {

            this.commands.add(obj);
            notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer obj) {

        if (observers != null) {

            this.observers.add(obj);
        }
    }

    @Override
    public void notifyObservers() {

        if (observers != null) {

            for (var observer : observers) {

                if (observer != null) {

                    observer.objectDidChange(this);
                }
            }
        }
    }
}
