package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4{
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
        // 여기서는 아주 단순하게 논리이름만 반환하게 된다
        // 왜냐하면 단순히 정적 리소스(여기서는 JSP이지만 HTML이 될 수도 있다)만을 넘겨주기 때문
        // 쉽게말해서 페이지뷰를 보여주는거 말고는 로직을 처리할 일이 없다, 다음에 만들 save,list에서는 그래도 뭔가 좀 한다
    }
}
