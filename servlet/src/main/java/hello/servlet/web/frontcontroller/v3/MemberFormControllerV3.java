package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3{
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
        // 의미 : 여기서는 논리적인 페이지뷰의 이름을 지정
        // 실제 리턴해줄 리소스(JSP파일 여기선)는 다른데서 맵핑
    }
}
