package bicycle.v1;


public class RearWheel {
    //attribute
    int size;

    //component
    Tire tire;

    public RearWheel(int size, Tire tire) {
        this.size = size;
        this.tire = tire;
    }
}
