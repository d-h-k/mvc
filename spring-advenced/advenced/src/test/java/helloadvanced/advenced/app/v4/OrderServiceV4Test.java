package helloadvanced.advenced.app.v4;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.reflect.Method;
import java.util.Arrays;

//import static helloadvanced.advenced.app.v4.DongSample.examineMethod;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class OrderServiceV4Test {

    @Test
    @DisplayName("Void 클래스를 설명하기 위한 학습용 테스트코드, 에러나서 동작안함")
    public void ClassVoidStudy() {

        DongSample sample = new DongSample();

        try {
            sample.examineMethod("foo");
            sample.examineMethod("bar");

        }catch (NoSuchMethodException noSuchMethodException) {
            log.error(Arrays.toString(noSuchMethodException.getStackTrace()));
            log.error(noSuchMethodException.getMessage());
            log.error("이게 왜 에러가 나서 동작안하는지 모르겠음 리플렉션 나중에 공부해야겠다는 생각이 듭니당...");
            //fail();
        }


    }

}


final class DongSample {
    public int foo() { return 0; }

    public void bar() {}

    /*
    public static void main(String[] args) throws NoSuchMethodException {
        examineMethod("foo");
        examineMethod("bar");
    }

     */

    public void examineMethod(String name) throws NoSuchMethodException {
        Method m = Test.class.getMethod(name);
        Class<?> returnType = m.getReturnType();

        if (returnType.equals(Void.TYPE))
            System.out.println(name + " does not return anything");
        else
            System.out.println(name + " returns a " + returnType);
    }
}
