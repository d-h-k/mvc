package bicycle.v1;

public class Frame {

    //attribute
    String color;

    //component
    FrontWheel frontWheel;
    RearWheel rearWheel;
    Handle handle;

    public Frame(String color, FrontWheel frontWheel, RearWheel rearWheel, Handle handle) {
        this.color = color;
        this.frontWheel = frontWheel;
        this.rearWheel = rearWheel;
        this.handle = handle;
    }
}
