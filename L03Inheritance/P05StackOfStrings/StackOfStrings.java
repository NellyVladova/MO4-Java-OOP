package L03Inheritance.P05StackOfStrings;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item){
        this.data.add(item);
    }

    public String pop(){
        return data.remove(data.size() - 1);
    }

    public String peek(){
        return data.get(data.size() - 1);
    }

    public boolean isEmpty(){
        if(data.size() == 0){
            return true;
        }
        return false;
    }
}
