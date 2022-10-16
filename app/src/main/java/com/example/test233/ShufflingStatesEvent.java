package com.example.test233;

/**
 * @author anyu
 * @date 2022/5/23-11:28
 * @desc
 */
public class ShufflingStatesEvent {

    private int from;

    private boolean state;

    public boolean isState () {
        return state;
    }

    public void setState (boolean state) {
        this.state = state;
    }

    public ShufflingStatesEvent (int froms) {
        from = froms;
    }

    public int getFrom () {
        return from;
    }
}
