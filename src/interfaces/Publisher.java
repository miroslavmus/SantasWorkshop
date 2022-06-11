package interfaces;

public interface Publisher<T> {

    void addSubscriber(Subscriber<T> sub);
    void notifySubscribers(T obj);
}
