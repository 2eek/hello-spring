package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;


import static org.assertj.core.api.Assertions.*;

// 다른 곳에서 쓸 게 아니어서 public 안해도 됨
class MemoryMemberRepositoryTest {
//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //test메서드 끝나고 호출 됨. =콜백메서드
    public void afterEach(){
        //저장소를 지운다. 각 test가 서로 영향 안받는다. 서로 의존관계x
        repository.clearStore();

    }
    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");
        //저장
        repository.save(member);

        //자동으로 셋팅되는 아이디 값으로 회원 조회한다.
//        Optional<Member> byId = repository.findById(member.getId());
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result=" + (result == member));
        //기댓값인 member가  실제값인  result와 같아야됨
//        Assertions.assertEquals(member,result);

//        //option+enter static import
//        Assertions.assertThat(member).isEqualTo(result);
          assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);


    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
