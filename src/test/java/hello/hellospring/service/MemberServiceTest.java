package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach //test메서드 끝나고 호출 됨. =콜백메서드
    public void afterEach() {
        //저장소를 지운다. 각 test가 서로 영향 안받는다. 서로 의존관계x
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 이 데이터를 기반으로 테스트
        Member member = new Member();
        member.setName("hello");

        //when 검증하는 것
        Long saveId = memberService.join(member);

        //then 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
   public void 중복_회원_예외() {
    //given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
//    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//    assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
}




    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
