package helloadvanced.advenced;

import helloadvanced.advenced.trace.logtrace.LogTrace;
import helloadvanced.advenced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Configuration 안에 컴포넌트스캔 있어서 자동으로 스프링 빈 등록이 됨
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        //return new FieldLogTrace();
        return new ThreadLocalLogTrace();
        // 이것이 스프링의 예술적인 부분, 클라이언트를 변경하지않고 OCP 를 지키면서 바꿀 수 있다!! 스프링 짱인듯
    }


}
