package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns ="/front-controller/v2/*" )
public class FrontControllerServletV2 extends HttpServlet {
    private final Map<String, ControllerV2> router;
    static private final String FORM = "/front-controller/v2/members/new-form";
    static private final String SAVE = "/front-controller/v2/members/save";
    static private final String LIST = "/front-controller/v2/members";

    public FrontControllerServletV2() {
        router = new HashMap<>();
        router.put(FORM, new MemberFormControllerV2());
        router.put(SAVE, new MemberSaveControllerV2());
        router.put(LIST, new MemberListControllerV2());
        System.out.println("DONG : FrontControllerServletV2.FrontControllerServletV2");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV2 controller = router.get(requestURI);
        request.setCharacterEncoding("utf-8");

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404 error
            return;
        }

        MyView myView= controller.process(request, response);
        myView.render(request, response);
    }
/* 앗 나의 실수
    static private final String FORM = "WEB-INF/views/new-form";
    static private final String SAVE_REQ = "WEB-INF/views/save";
    //static private final String SAVE_RES = "";
    static private final String LIST = "WEB-INF/views";
     */

}
