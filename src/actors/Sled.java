package actors;

import interfaces.Subscriber;
import toys.IToy;

import java.util.ArrayList;
import java.util.List;

public class Sled implements Subscriber<IToy> {

    private List<IToy> toys;

    private static Sled instance;

    public static Sled shared() {

        if (instance == null) {

            synchronized (Sled.class) {

                if (instance == null) {

                    instance = new Sled();
                }
            }
        }

        return instance;
    }

    private Sled() {

        this.toys = new ArrayList<>();
    }

    public List<IToy> getToys() {

        return toys;
    }

    @Override
    public void objectChanged(IToy obj) {

        if (toys != null) {

            this.toys.add(obj);
        }
    }
}
