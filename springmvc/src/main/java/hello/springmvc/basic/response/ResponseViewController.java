package hello.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data","hello : VIEW-V1");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello : VIEW-V2");
        return "response/hello";
    }


    @RequestMapping("/response/hello")
    public void responseView_anti(Model model) {
        log.info("@RequestMapping(\"/response/hello\")\n 이 있을때 요청 경로(req url) 와 논리적 뷰의 이름이 완벽히 일치하면 void return 시에도 view 가 불러진다");
        log.info("이 방식은 너무 불분명하고 명시성이 부족해서 이런방식의 코딩은 권장하지 않는다., 실용성도 떨어짐");
        log.info("컨트롤러 메서드의 리턴타입이 void 인경우 : @Controller 사용하고, HTTP message body 를 처리하는 파라미터가 없으면 논리 뷰의 이름으로 사용");
        //HTTP message body 를 처리하는 파라미터 :  HttpServletResponse, OutputStream(Writer)
        model.addAttribute("data", "hello : anti-pattern");
    }

}
