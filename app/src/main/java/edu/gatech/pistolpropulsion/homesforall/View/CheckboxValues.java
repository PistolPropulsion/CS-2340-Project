package edu.gatech.pistolpropulsion.homesforall.View;

/**
 * Created by Yuan Zhang on 3/7/2018.
 */

public class CheckboxValues<T> {
    private T value;
    private boolean selected;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
