package helloadvanced.advenced.trace.template.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;

@Slf4j
public class Test {
    public int hello() {
        log.info("hello() 메서드");
        return 0;
    }

    public void world() {
        log.info("world() 메서드");
        return;
    }

    public String springboot() {
        log.info("springboot() 메서드");
        return "springboot";
    }

    public ResponseEntity<?> coffee() {
        return ResponseEntity.ok("coffee");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        examineMethod("hello");
        examineMethod("world");
        examineMethod("springboot");
        examineMethod("coffee");
    }

    private static void examineMethod(String name) throws NoSuchMethodException {
        Method m = Test.class.getMethod(name);
        Class<?> returnType = m.getReturnType();

        if (returnType.equals(Void.TYPE)) {
            System.out.println(name + "() : Void.TYPE 비교대상이 여기서 사용됨");
        }
        log.info("리턴타입 : {}\n", returnType.toGenericString());

    }
}
