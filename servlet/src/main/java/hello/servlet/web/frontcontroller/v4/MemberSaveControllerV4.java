package hello.servlet.web.frontcontroller.v4;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4{

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member); // 페이지에 표시될 정보를 model에 담아 넘겨줌
        return "save-result"; //제공할 리소스(뷰의 프레임이랄까..)
    }
}
