package helloadvanced.advenced;

import helloadvanced.advenced.trace.logtrace.FieldLogTrace;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Configuration 안에 컴포넌트스캔 있어서 자동으로 스프링 빈 등록이 됨
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }

}
