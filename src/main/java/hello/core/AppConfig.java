package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * @Configuration에 대한 설명
     * 상황 예시
     * 1. @Bean memberService -> new MemoryMemberRepository()
     * 2. @Bean orderService -> new MemoryMemberRepository()
     * -> new 호출 2번으로 인해 Sinlgeton이 깨지는것 아닌가?
     *
     * 호출 순서는 다를 수 있다. 단, Singleton이라면 memberRepository가 3번 호출되야 한다(TestCode에서)
     * [예상]
     * call AppConfig.memberService
     * call AppConfig.memberRepository
     * call AppConfig.memberRepository
     * call AppConfig.orderService
     * call AppConfig.memberRepository
     *
     * [실제]
     * call AppConfig.memberService
     * call AppConfig.memberRepository
     * call AppConfig.orderService
     *
     * 왜?
     * @Configuration or @Autowired를 사용할 때 SpringContainer의 경우, memberRepository를 상속하는 memberRepository@CGLIB~를 Bean으로 등록하여 싱글톤이 보장되도록 한다
     */

    /**
     * 빈 이름은 메서드 이름을 사용
     * 법@Bean(name="memberSer")과 같이 빈 이름 직접 설정 가능
     */
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        //return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }
}
