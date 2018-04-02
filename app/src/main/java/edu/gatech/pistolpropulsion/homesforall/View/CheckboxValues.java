package edu.gatech.pistolpropulsion.homesforall.View;

/**
 * Created by Yuan Zhang on 3/7/2018.
 * An thing used by adapter and other stuff? i honestly don't recall writing this lol
 */
class CheckboxValues<T> {
    private T value;
    private boolean selected;

    /**
     * get value of selected
     * @return whatever was in the checkbox
     */
    public T getValue() {
        return value;
    }

    /**
     * sets the value to parameter
     * @param value value that was selected
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * if checkbox was selected or not
     * @return boolean selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * sets the boolean to selected parameter
     * @param selected sets selected to true or false
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
