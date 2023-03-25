package Models;

import Models.Enums.Movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transition {
    private String initialState;
    private List<String> words;
    private List<String> replace;
    private List<Movement> movement;
    private String nextState;

    public Transition(String initialState, String[] bands, String nextState) {
        words = new ArrayList<>();
        replace = new ArrayList<>();
        movement = new ArrayList<>();

        this.initialState = initialState;
        this.nextState = nextState;
        Arrays.stream(bands)
                .map(band -> band.split(","))
                .forEach(bandData -> {
                    words.add(bandData[0]);
                    replace.add(bandData[1]);
                    movement.add(Movement.valueOf(bandData[2]));
                });
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getReplace() {
        return replace;
    }

    public void setReplace(List<String> replace) {
        this.replace = replace;
    }

    public List<Movement> getMovement() {
        return movement;
    }

    public void setMovement(List<Movement> movement) {
        this.movement = movement;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}
