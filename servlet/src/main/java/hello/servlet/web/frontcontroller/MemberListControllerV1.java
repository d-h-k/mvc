package hello.servlet.web.frontcontroller;

import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

class MemberListControllerV1 implements ControllerV1 {

    static final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";
        //final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";
        final String VIEW_PATH = "/WEB-INF/views/members.jsp";

        List<Member> members = memberRepository.findAll();
        //한글깨짐 문제 해결
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //
        request.setAttribute("members",members); // 모델어트리븃은 요청(request)쪽에
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH); //
        dispatcher.forward(request, response);
    }
}
