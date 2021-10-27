package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
    // [뷰jsp페이지 파일]이름과 [뷰클래스]를 랜더링할때 연결하려고 필요한 model 이라는 이름의 맵퍼를 가지고있다

}

