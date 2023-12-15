package hello.hellospring;


import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration//->스프링빈으로 관리해준다.
public class SpringConfig {
    // memberService를 스프링빈에 등록해준다.

    private DataSource dataSource; //properties참고해서 스프링이 DataSource 만들어준다.

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }


    //MemberRepository: 인스턴스
    //MemoryMemberRepository(): 구현체
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();  //그냥 메모리에 저장
        //객체지향 설계의 좋은점. 인터페이스를 두고 구현체를 바꿔끼우기 가능.(다형성 활용)
        return new JdbcMemberRepository(dataSource); //-> h2사용하기 위해
    }
}
