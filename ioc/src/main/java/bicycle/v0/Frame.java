package bicycle.v0;

public class Frame {

    //attribute
    String color;

    //component
    Wheel frontWheel;
    Wheel rearWheel;
    Handle handle;

    public Frame(String color) {
        this.color = color;
        this.frontWheel = new Wheel(16);
        this.rearWheel = new Wheel(16);
        this.handle = new Handle();
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "color='" + color + '\'' +
                ", frontWheel=" + frontWheel +
                ", rearWheel=" + rearWheel +
                ", handle=" + handle +
                '}';
    }
}
