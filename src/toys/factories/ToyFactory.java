package toys.factories;

import magic.Command;
import toys.IToy;

public class ToyFactory {

    public static IToy make(Command command) {

        if (command instanceof Command.NeedBicycle) {
            return BicycleFactory.make();
        }
        else if (command instanceof Command.NeedDoll) {
            return DollFactory.make();
        }

        return null;
    }
}
