package helloadvanced.advenced;

import helloadvanced.advenced.trace.logtrace.FieldLogTrace;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }

}
