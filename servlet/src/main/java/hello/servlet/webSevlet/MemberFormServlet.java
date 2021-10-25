package hello.servlet.webSevlet;

import hello.servlet.domain.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    private static final String HTML =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "   <meta charset=\"UTF-8\">\n" +
                    "   <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +

                    //---- body start
                    "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                    "   username: <input type=\"text\" name=\"username\" />\n" +
                    "   age: <input type=\"text\" name=\"age\" />\n" +
                    "   <button type=\"submit\">전송</button>\n" +
                    "</form>\n" +
                    //---- body end

                    "</body>\n" +
                    "</html>";


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(HTML);
        //http://localhost:8080/servlet/members/new-form
    }
}


