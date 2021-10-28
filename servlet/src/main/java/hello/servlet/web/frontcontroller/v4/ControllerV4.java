package hello.servlet.web.frontcontroller.v4;

import java.util.Map;


public interface ControllerV4 {
    /**
     * @param paramMap 파라미터 모델
     * @param model 모델
     * @reuturn viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
    // 해설 : ModelView 클래스가 포함되지 않았다
    // 대신 model 객체에 파라미터로 전달되기 때문에 그냥 사용하면 됨
    // 리턴 : 어쨋든 viewName(String) 뷰의 이름만 반환해주면 됨
}
