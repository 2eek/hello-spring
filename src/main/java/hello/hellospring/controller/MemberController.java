package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {

  public final MemberService memberService;

    //MemberService라는 의존성을 생성자를 통해 주입받는다.
    //@Autowired 어노테이션이 있는 생성자 public MemberController(MemberService memberService)는
    // 스프링에게 해당 클래스가 MemberService 빈을 필요로 한다는 것을 알려준다.
    //생성자 주입방식

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = "+ memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " +member.getName());

        memberService.join(member);
        return "redirect:/";

    }

    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}