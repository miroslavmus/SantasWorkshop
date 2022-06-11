package actors;

import interfaces.Publisher;
import interfaces.Subscriber;
import magic.Command;

import java.util.ArrayList;
import java.util.List;

public class Santa implements Publisher<Command> {

    private static Santa instance = null;

    public static Santa shared() {

        if (instance == null) {

            synchronized (Santa.class) {

                if (instance == null) {

                    instance = new Santa();
                }
            }
        }

        return instance;
    }

    private List<Subscriber<Command>> subscribers = null;

    private Santa() {

        this.subscribers = new ArrayList<>();
    }

    public void utterForgottenMagic(Command command) {

        notifySubscribers(command);
    }

    @Override
    public void notifySubscribers(Command obj) {

        for (var subscriber : subscribers) {

            if (subscriber != null) {

                subscriber.objectChanged(obj);
            }
        }
    }

    @Override
    public void addSubscriber(Subscriber<Command> sub) {

        if (subscribers != null) {

            this.subscribers.add(sub);
        }
    }
}
