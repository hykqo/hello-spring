package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //스프링 부트가 자동으로  데이터베이스랑 연결을 해서 EntityManager라는걸 생성해줌.
    //우리는 그걸 인젝션만 하면 됨.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist : 저장하다, 영속화하다
        //이렇게만 하면 jpa가 인서트쿼리생성해서 집어넣어주고 반환까지 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //pk가 아닌 파리미터로 조회를 할때에는 쿼리를 작성해주어야 한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //객체(entity)를 대상으로 쿼리를 날린다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }
}
