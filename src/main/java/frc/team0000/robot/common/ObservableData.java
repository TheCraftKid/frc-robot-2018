package frc.team0000.robot.common;

import java.util.ArrayList;
import java.util.List;

public class ObservableData<T> {

    private T data;

    private List<Callback<T>> callbacks = new ArrayList<>();

    public T getValue() {
        return data;
    }

    public void setValue(T newData) {
        data = newData;
        for (Callback<T> callback : callbacks) {
            callback.onChange(newData);
        }
    }

    public void addObserver(Callback<T> callback) {
        callbacks.add(callback);
    }

    public void removeObserver(Callback<T> callback) {
        callbacks.remove(callback);
    }

    public interface Callback<T> {

        void onChange(T data);
    }
}
