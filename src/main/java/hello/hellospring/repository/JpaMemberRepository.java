package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    //엔티티매니저있어야 jpa사용 가능.  스프링부트가 만듦.  엔티티매니저: 데이터베이스 커넥션 정보 가지고 있음.
    private final EntityManager em;

    //생성자
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {//jpql쿼리어 작성
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //jpql쿼리언어. 객체를 대상으로 쿼리를 날린다->sql로 번역이된다.
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
