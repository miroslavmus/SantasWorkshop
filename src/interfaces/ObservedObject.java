package interfaces;

public interface ObservedObject {

    void notifyObservers();
    void addObserver(Observer obj);
}
