package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2{
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
    //todo : 기억해줘
    //이제 각 컨트롤러는 복잡한 dispatcher.forward() 를 직접 생성해서 호출하지 않는다
    //MyView 객체를 생성하고 거기에 뷰 이름만 넣고 반환하면 MyView에서 일괄처리해줌
    //ControllerV1 대비 이 부분의 중복 제거됨
}
