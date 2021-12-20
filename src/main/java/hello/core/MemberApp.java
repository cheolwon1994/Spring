package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig =  new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * 스프링 컨테이너 생성 <빈 이름, 빈 객체> 형태의 Map을 만든다
         * 빈 이름은 항상 다른 이름을 부여. 같은 이름 사용시, 다른 빈이 무시되거나 기존 빈을 덮거나 설정에 따라 오류 발생
         * AppConfig에 있는 Bean 전부 등록처리
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+member.getName());
        System.out.println("find member = "+findMember.getName());
    }
}
