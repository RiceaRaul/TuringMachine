package Logic;

import Models.Enums.Movement;
import Models.Transition;

import java.util.List;

public class TransitionList {
    private final List<Transition> transitionList;
    private final List<Band> bandList;

    public TransitionList(List<Transition> transitionList, List<Band> bandList) {
        this.transitionList = transitionList;
        this.bandList = bandList;
    }

    public Transition FindTransition(String state){
        Transition find = transitionList.stream()
                .filter(x -> x.getInitialState().equals(state))
                .filter(transition -> {
                    int countBandEqual = 0;
                    for (int i = 0; i < bandList.size(); i++) {
                        String word = bandList.get(i).getCursorValue();
                        if (transition.getWords().get(i).equals(word)) {
                            countBandEqual++;
                        } else {
                            countBandEqual = 0;
                            break;
                        }
                    }
                    return countBandEqual == bandList.size();
                })
                .findFirst()
                .orElse(null);

        return find;
    }

    public void setInputValue(String value){
        value = "B"+value+"B";
        String finalValue = value;
        this.bandList.forEach(i -> {
            if (bandList.indexOf(i) == 0) {
                i.setBand(finalValue);
            } else {
                i.setBand(finalValue.replaceAll("[a-zA-Z]","B"));
            }
        });
    }

    public boolean ValidateBandCondition(Transition transition){
        boolean validate = false;
        for (int i = 0; i < bandList.size(); i++) {
            String word = transition.getWords().get(i);
            Band band = bandList.get(i);
            if(band.getCursorValue().equals(word)){
                validate = true;
            }
        }
        return validate;
    }

    public void operations(Transition transition){
        for (int i = 0; i < bandList.size(); i++) {
            Band band = bandList.get(i);
            String replace = transition.getReplace().get(i);
            Movement movement = transition.getMovement().get(i);
            band.setCursorValue(replace);
            band.moveCursor(movement);
        }
    }
}
