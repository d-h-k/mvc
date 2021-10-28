package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
// 의미 : 서블릿과는 전혀 관계없도록, 서블릿과(내부구현기술) 컨트롤러(역할) 사이의 분리
