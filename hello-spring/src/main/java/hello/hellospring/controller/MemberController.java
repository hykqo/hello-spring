package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //해당 애노테이션을 이용하면 스프링이 처음 뜰때 스프링컨테이너라는 통이 생기는데, 해당 컨트롤러 객체를 생성해 스프링에 넣어두고 관리를 해준다.
//즉 빈을 생성해서 스프링이 관리해주는것이다.
public class MemberController {

    private final MemberService memberService;

    @Autowired //DI
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
