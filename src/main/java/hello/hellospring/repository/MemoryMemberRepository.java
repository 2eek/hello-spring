package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    //동시성 문제 고려 안함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

// store라는 HashMap은 실제 데이터를 저장하는 메모리 공간
    @Override
    public Member save(Member member) {
        //member에 id 셋팅
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//null이라도 감싸서 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    //실무에서 루프 돌기 편해서 List 많이쓴다.
    public List<Member> findAll() {
        //store에 있는 values(=member)들이 반환된다
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
