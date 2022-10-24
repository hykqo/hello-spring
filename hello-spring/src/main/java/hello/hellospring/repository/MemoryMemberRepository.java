package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository{

    //db가 없으므로 우선 맵에다 저장을 해보자.
    //실무에서는 동시성 문제떄문에 공유되는 변수일떄는 ConcurrentHashMap을 써야 한다.
    private static Map<Long, Member> store = new HashMap<>();

    //시퀀스 키값을 생성해주는 역할을 줄것이다.
    //동시성 문제떄문에 실무에서는 AtomicLong을 써야 한다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //Optional.ofNullable() -> null일경우 에러가 나오지 않고 null이 반환되도록 처리.
       return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //findAny -> 같은 결과가 하나라도 있으면 반환. 없으면 Optional에 null이 포함되서 반환.
    }

    @Override
    public List<Member> findAll() {
        //map -> List로 변환해서 반환.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
