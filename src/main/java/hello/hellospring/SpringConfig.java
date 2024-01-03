package hello.hellospring;


//import hello.hellospring.aop.TimeTraceAop;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//->스프링빈으로 관리해준다.
public class SpringConfig {
    // memberService를 스프링빈에 등록해준다.


//    private  EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    private DataSource dataSource; //properties참고해서 스프링이 DataSource 만들어준다.
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private final MemberRepository memberRepository;

    @Autowired//생성자 하나일 때는 생략 가능함
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
            //인젝션 받아서 MemberService에 memberRepository등록해줌
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }


    //MemberRepository: 인스턴스!!!
    //MemoryMemberRepository(): 구현체
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();  //그냥 메모리에 저장
//        객체지향 설계의 좋은점. 인터페이스를 두고 구현체를 바꿔끼우기 가능.(다형성 활용)
//        return new JdbcMemberRepository(dataSource); //-> h2사용하기 위해
//        return new JdbcTrmplateMemberRepository(dataSource); //JDBC사용하기 위해
//        return new JpaMemberRepository(em);
//    }
}
