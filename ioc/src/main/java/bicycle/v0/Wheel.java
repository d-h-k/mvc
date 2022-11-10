package bicycle.v0;

public class Wheel {

    //attribute
    int size;

    //component
    Tire tire;

    public Wheel(int size) {
        this.tire = new Tire(TireGrade.COMPORT);
    }

    public int getSize() {
        return size;
    }
}
