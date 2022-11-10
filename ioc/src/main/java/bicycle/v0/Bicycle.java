package bicycle.v0;

// TODO: 2022/11/02 롬복사용을 위해 스프링

public class Bicycle {
    public static void main(String[] args) {
        Frame frame = new Frame("red");
        System.out.println(frame.getColor());
        System.out.println(frame.toString());
    }
}
