package bicycle.v1;

import bicycle.v0.TireGrade;

import java.util.HashMap;
import java.util.Map;

public class Bicycle {
    public static void main(String[] args) {
        di();
    }

    private static void di() {
        
        Map<Class<?>, Object> container = new HashMap<>();
        
        Brake brake = new Brake();
        container.put(Brake.class, brake);


        (Brake) container.get(Brake.class)
        Handle handle = new Handle();
        
        Tire tire = new Tire(TireGrade.COMPORT);
        FrontWheel frontWheel = new FrontWheel(20,tire);
        RearWheel rearWheel = new RearWheel(20, tire);
        Frame frame = new Frame("red", frontWheel, rearWheel, handle);


        container.put(Tire.class, tire);
    }

}
