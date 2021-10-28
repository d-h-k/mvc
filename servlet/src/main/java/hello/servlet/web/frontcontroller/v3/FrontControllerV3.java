package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private static final String FORM = "/front-controller/v3/members/new-form";
    private static final String SAVE = "/front-controller/v3/members/save";
    private static final String LIST = "/front-controller/v3/members";
    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put(FORM, new MemberFormControllerV3());
        controllerMap.put(SAVE, new MemberSaveControllerV3());
        controllerMap.put(LIST, new MemberListControllerV3());
    }

    //해설 : 어딘가에서는 꼭 해야하만 하는 servlet 작업을 여기서 일괄 처리한다
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reueqtURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(reueqtURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);
        view.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
        // prefix = "/WEB-INF/views/"
        // postfix = ".jsp"
        // 위 두가지 prefix, postfix 를 이곳에서 공통으로 처리해줌으로써 소스코드 중복을 막는다
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames() //Enumeration<String> 타입이 넘어감
                .asIterator() //Iterator<String> 타입이 넘어감
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
                // Iterator인터페이스의 메서드, 내부 인자로는 Consumer인터페이스가 필요함(컨슈머 람다시그니쳐)
        return paramMap;
    }


}
