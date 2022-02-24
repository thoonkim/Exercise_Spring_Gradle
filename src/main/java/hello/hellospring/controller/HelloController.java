package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*컨트롤러는 스프링의 첫번째 진입점이다. 반드시 클래스명 위에 @Controller를 붙여야 한다.*/
@Controller
public class HelloController {

    /*웹 어플리케이션에서 /hello로 요청하면 이 메소드로 진행 한다.*/
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";//이 리턴 값은 뷰리졸버가 templates의 리턴값과같은이름.html에 자동 화면을 찾아 처리한다.
    }
}
