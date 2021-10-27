package hello.servlet.web.sevlet;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("before save member = " + member);
        member = memberRepository.save(member);
        System.out.println("after save member = " + member);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter w = response.getWriter();

        final String HTML =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "   <meta charset=\"UTF-8\">\n" +
                        "   <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +

                        //---- body start
                        "성공\n" +
                        "<ul>\n" +
                        "   <li>id=" + member.getId() + "</li>\n" +
                        "   <li>username=" + member.getUsername() + "</li>\n" +
                        "   <li>age=" + member.getAge() + "</li>\n" +
                        "</ul>\n" +
                        "<a href=\"/index.html\">메인</a>\n" +

                        //---- body end

                        "</body>\n" +
                        "</html>";

        w.write(HTML);
    }
}
