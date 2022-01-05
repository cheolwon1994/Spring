package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    /**
     * Q. 자동 Bean 등록 vs 자동 Bean 등록일 때
     * A. ConflictingBeanDefinitionException 예외 발생
     *
     * Q. 수동 Bean 등록 vs 자동 Bean 등록일 때
     * A. 수동 Bean이 자동 Bean을 오버라이딩 해버린다.
     *
     * 수동 빈 등록시 남는 LOG
     * Overriding bean definition for bean 'memoryMemberRepository' with a different definition
     * definition: replacing
     *
     * [But SpringBoot에서는 스프링 부트 에러 발생]
     * -> The bean 'memoryMemberRepository', defined in class path resource [hello/core/AutoAppConfig.class], could not be registered.
     * A bean with that name has already been defined in file...
     */
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

    }
}
