package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스를 받을때에는 extends를 사용하자
//JpaRepository 받고 있으면 구현체를 자동으로 만들고 스프링 빈으로 자동으로 등록을 해준다.
//프록시라는 기술을 사용하는데, 다음 강의에서 자세히 알아보자.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
