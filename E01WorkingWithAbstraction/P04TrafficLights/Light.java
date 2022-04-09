package E01WorkingWithAbstraction.P04TrafficLights;

public class Light {
    private Color color;

    public Light(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void changeColor(){
        if(this.color == Color.RED){
            setColor(Color.GREEN);
        }else if(this.color == Color.GREEN){
            setColor(Color.YELLOW);
        }else if(this.color == Color.YELLOW){
            setColor(Color.RED);
        }
    }
}

