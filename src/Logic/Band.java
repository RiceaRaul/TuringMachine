package Logic;

import Models.Enums.Movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Band {
    private int cursor;
    public List<String> band;

    public void moveCursor(Movement movement){
        switch (movement){
            case L -> {
                if(cursor-1 >= 0) {
                    cursor = cursor - 1;
                }
            }
            case R -> {
                if(cursor + 1 <= band.size() - 1){
                    cursor = cursor + 1;
                }
            }
            case S -> {
            }
        }
    }

    public String getCursorValue(){
        return band.get(cursor);
    }

    public void setCursorValue(String value){
        band.set(cursor,value);
    }

    public void setBand(String band){
        this.band = new ArrayList<>(Arrays.stream(band.split("")).toList());
    }
}
