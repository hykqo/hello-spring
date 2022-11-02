package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//서비스는 비즈니스에 의존적으로 설계할것.
@Transactional //jpa는 데이터를 저장하거나 변경할때에는 트랜잭션 계층안에서 실행이 되어야 한다.
public class MemberService {

    private  final MemberRepository memberRepository;
    //DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        long start = System.currentTimeMillis();
        try{
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("MemberService.join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                //Optional로 감싸면 ifPresent같은 메서드를 쓸 수 있다.(값이 있다면.)
            ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        } finally {
        long finish = System.currentTimeMillis();
        long timeMs = finish - start;
        System.out.println("MemberService.findMembers = " + timeMs + "ms");
    }
    }

    public Optional<Member> findOne(Long id){
        long start = System.currentTimeMillis();
        try{
        return memberRepository.findById(id);
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("MemberService.findOne = " + timeMs + "ms");
        }
    }
}
