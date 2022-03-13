package hello.hellospring.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*컨트롤러는 스프링의 첫번째 진입점이다. 반드시 클래스명 위에 @Controller를 붙여야 한다.*/
@Controller
public class HelloController {

    /*웹 어플리케이션에서 /hello로 요청하면 이 메소드로 진행 한다.*/
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";//이 리턴 값은 뷰리졸버가 templates의 리턴값과같은이름.html에 자동 화면을 찾아 처리한다.
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //@RequestParam : 요청 파라미터 값을 가져오기위한 애너테이션
                           //@RequestParam("값")에 Ctrl+p를누르면 옵션이 설정이 가능 중요!! 굉장히 많이씀
        model.addAttribute("name", name); //파라미터에서 넘어온 name을 넘긴다.
        //기본으로 넘겨준 값이 없어 http://localhost:8090/hello-mvc?name=spring!!!!!!!!!! 와 같이 name을 지정하고 넘겨주어야 한다.
        return "hello-template";
    }
    /*API 방식 1*/
    @GetMapping("hello-string")
    @ResponseBody //html이 아닌 http의 바디에 이 데이터를 직접 넣어 주겠다는 것
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //위의 name에 spring으로 바꾸면 이 리턴이 hello spring으로 바뀐다.
        //이 방식과 템플릿 엔진과의 방식의 차이는 뷰 같은 것이 없고 이 문자 자체가 그대로 리턴된다.
        /*쓸일은 별로없다*/
    }
    /*API 방식 2 (중요!!)*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ //Hello 객체 사용
        Hello hello = new Hello(); //Ctrl+Shift+enter치면 가로와 마침이 자동 완성됌
        hello.setName(name);
        return hello; //문자가아닌 객체를 hello라는 객체를 넘긴것 (중요!!)
        /*JSON 반환 법*/
        //동작 방식 = 웹브라우저에서 요청(hello-api)이 오면 톰켓 서버가 hello-api가 있네 하면서 스프링 컨테이너로 던진다.
        //근데 @ResponseBody가 붙어있는 것을 보고 스프링은 Http 응답에 그대로 데이터를 넘겨야 겠구나 하는데 객체일 경우
        //HttpMessageConverter가 JsonConverter로 넘기고 변환되어
        //JSON으로 데이터를 넘긴다.
    }

    static class Hello {
        private String name;
        /*게터 세터는 자바빈규약 또는 프로퍼티접근 방식이라 한다.*/
        public String getName() { //Alt+insert = 게터세터 추가
            return name;
            //꺼낼 때
        }

        public void setName(String name) {
            this.name = name;
            //넣을 때
        }
    }

}
