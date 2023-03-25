package Logic;

import Models.Transition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MT {

    private final String ER_state = "((q[0-9])|(q[1-9][0-9]*))";
    private String initial_state;
    private List<String> final_states;

    private final TransitionList _transitionList;
    public MT() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("D:\\Facultate\\Masina Touring\\src\\source2.txt"));
        String tmp = br.readLine();
        if(tmp.matches(this.ER_state)) {
            this.initial_state=tmp;
        }
        else{
            throw new Exception("Invalid State 1");
        }
        final_states = Arrays.stream(br.readLine().split(" ")).toList();
        List<Transition> transitionList = new ArrayList<>();
        List<Band> bandList = new ArrayList<>();
        while (true){
            String line = br.readLine();
            if(line == null) {
                break;
            }
            String[] states = line.split(" ");
            String[] bands = states[1].split(";");
            Transition transition = new Transition(states[0],bands,states[2]);
            transitionList.add(transition);
            bandList = new ArrayList<>(bands.length);
            for (int i = 0; i < bands.length; i++) {
                bandList.add(new Band());
            }
        }
        _transitionList = new TransitionList(transitionList,bandList);
        Validate();
    }

    public void Validate() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String validateText = reader.readLine();
        _transitionList.setInputValue(validateText);
        Transition transition = _transitionList.FindTransition(initial_state);
        while (transition != null){
            if(_transitionList.ValidateBandCondition(transition)){
                _transitionList.operations(transition);
                transition = _transitionList.FindTransition(transition.getNextState());
                if(transition == null){
                    break;
                }
                initial_state = transition.getNextState();
            }
        }
        if(final_states.contains(initial_state)){
            System.out.println("Ok");
        }else{
            System.out.println("Nu este ok");
        }
    }
}
