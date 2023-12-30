package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@NoRepositoryBean
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //인터페이스만 있지만. 스프링데이터JPA가 구현체를 자동으로 만들어줘서 자동으로 빈을 등록해준다.
    //JPQL  select m from Member as m where m.name=? -> SQL로 번역돼서 실행된다.
    @Override
    Optional<Member> findByName(String name);
}
