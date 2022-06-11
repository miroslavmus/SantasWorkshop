package interfaces;

public interface Subscriber<T> {

    void objectChanged(T obj);
}
