package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//h2 테스트진행

@SpringBootTest
@Transactional  // 인서트 후 커밋 전 까지는 DB에 안들어감. 테스트 끝나고 록백하면 검증끝나고 db데이터 없앨 수 있다.
    //테스트 실행할 때 트랙젝션 실행하고, db에 데이터 insert 쿼리 후 테스트 끝나면 롤백한다.(데이터 반영을 안시킨다.  )
//@Commit
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //스프링컨테이너에게 멤버 서비스, 멤버 레파지토리 내놔! 해야됨

    //운영DB와 별개로 test DB를 돌린다.
    @Test
    void 회원가입() {
        //given 이 데이터를 기반으로 테스트
        Member member = new Member();
        member.setName("test1231");

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
