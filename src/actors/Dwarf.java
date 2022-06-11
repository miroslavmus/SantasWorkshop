package actors;

import interfaces.Observer;
import interfaces.Publisher;
import interfaces.Subscriber;
import toys.IToy;
import toys.factories.ToyFactory;

import java.util.ArrayList;
import java.util.List;

public class Dwarf implements Observer, Publisher<IToy> {

    private List<Subscriber<IToy>> subscribers;

    public Dwarf() {

        subscribers = new ArrayList<>();
    }

    @Override
    public void objectDidChange(Object observedObject) {

        if (observedObject instanceof MagicBoard) {

            notifySubscribers(
                    ToyFactory.make(
                            ((MagicBoard) observedObject).fulfillCommand()
                    )
            );
        }
    }

    @Override
    public void addSubscriber(Subscriber<IToy> sub) {

        if (subscribers != null) {

            subscribers.add(sub);
        }
    }

    @Override
    public void notifySubscribers(IToy obj) {

        if (obj == null) { return; }

        if (subscribers != null) {

            for (var sub : subscribers) {

                sub.objectChanged(obj);
            }
        }
    }
}
