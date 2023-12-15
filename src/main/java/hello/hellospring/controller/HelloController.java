package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    //겟방식으로 매칭됨 -> 메서드 실행
    @GetMapping("/toHello")
    //스프링이 모델 만들어서 넘겨줌
    public String hello(Model model) {
        //키: data-> 앞단에서 씀 밸류: hello
        //ajax에서처럼 키 값이 넘겨주는 곳에서 쓰임
        model.addAttribute("data", "hello!!");
        //model(data:hello!!)
        //템플릿 안에있는 hello로 보낸다. 랜더링한다.
        //컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.
        //스프링 부트 템플릿엔진 기본viewName 매핑
        //resources:templates/ + {ViewName} + .html
        return "hello";
    }



    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name1", name);
        return "hello-template";
    }

        @GetMapping("hello-input")
    public String helloStatic() {
        return "hello-input";
    }

    //form에 말아서 처리한다.
    @PostMapping("hello-mvc1")
    public String helloMvc1(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name1", name);
        return "hello-template";
    }


    //단순 문자를 반환한다. http통신 프로토콜의 body에 문자 담아서 반환한다.
    @GetMapping("hello-string")
    @ResponseBody // 응답의 body에 직접 return하겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;

    }
//API json 반환한다. {"키":"밸류"}
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        //객체를 반환한다.
        return hello;
    }

    static class Hello{
        private String name;
        //해당 객체의 name 속성 값을 읽을 수 있게 해줍니다.-> return
        public String getName() {
            return name;
        }
        ////해당 객체의 name 속성 값을 셋팅한다.
        public void setName(String name) {
            //현재 객체의 name 속성을 의미(hello 객체의 인스턴스 변수 name에 접근)
            this.name = name;
        }

    }
}

