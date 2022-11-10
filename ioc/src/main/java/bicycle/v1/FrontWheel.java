package bicycle.v1;


public class FrontWheel {
    //attribute
    int size;

    //component
    Tire tire;

    public FrontWheel(int size, Tire tire) {
        this.size = size;
        this.tire = tire;
    }
}
