package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class ParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("전체 파라미터 조회");
        request.getParameterNames().asIterator().forEachRemaining(
                paramName -> System.out.println("req.getParameter(paramName = " + request.getParameter(paramName))
        );

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);


        System.out.println("이름이 같은 여러개의 파라미터");
        String[] values = request.getParameterValues("username");
        if(values != null) {
            for (String value : values) {
                System.out.println("value = " + value);
                ;
            }
        }

        response.getWriter().write("OK");

    }
}
