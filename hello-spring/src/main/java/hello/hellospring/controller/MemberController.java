package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //해당 애노테이션을 이용하면 스프링이 처음 뜰때 스프링컨테이너라는 통이 생기는데, 해당 컨트롤러 객체를 생성해 스프링에 넣어두고 관리를 해준다.
//즉 빈을 생성해서 스프링이 관리해주는것이다.
public class MemberController {

    //필드주입 방법 - 안좋음. 스프링뜰때 넣어주기만 하고 중간에 바꿀수 있는 방법이 없다.
//    @Autowired private MemberService memberService;

    //setter주입방법 - 누군가가 MemberController를 호출했을때 public void setMemberService이 public으로 열려있어야 한다.
    //하지만 한번 셋팅이 되고나면 바꿀일이 없는데, 노출이 되어버린다. 애플리케이션 조립시점에만 열려있으면 되기 때문에 퍼블릭으로 열려있을 필요가 없다.
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    //생성자주입 방법 - 요즘 사용하는 방법 -조립시점에 생성자주입으로 한번만 실행되도록 작업.
    private final MemberService memberService;

    @Autowired //DI
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
