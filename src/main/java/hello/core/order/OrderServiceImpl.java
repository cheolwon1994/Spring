package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    /**
     * 의존관계 주입 방법
     * 1. 생성자 주입 -> 프레임워크에 의존X, 순수한 자바 언어의 특징을 잘 살리는 방사
     * 2. 수정자 주입
     * 3. 필드 주입
     * 4. 일반 메서드 주입
     */

    /**
     * @Qualifier 탐색 순서
     * 1. @Qualifier 매칭
     * 2. 해당 이름의 스프링 빈을 추가로 찾는다
     * 3. 'NoSuchBeanDefinitionException' 발생
    */

    /**
     * @Primary
     * 중복된 타입의 빈이 있을 때, 자동주입시 @Primary가 붙은게 우선순위가 높다
     *
     * Question) @Qualifier와 @Primary가 동시에 쓰인다면 어떤것이 우선순위를 가질까?
     * Answer) 자동보단 수동이 우선권을 가지므로, @Qualifier가 우선순위가 높다. 단, @Qualifier의 단점으로는 모든 코드에 해당 어노테이션을 붙여주어야 한다징     *
     */
    /*3. 필드 주입 @Autowired 직접 선언
    / 단점: 1. 보기엔 편리해보이지만 외부에서 접근이 불가능하다. MemberRepository와 DiscountPolicy를 세팅해줄 set 함수가 추가로 필요하다. 즉, DI 프레임워크가 없으면 아무것도 할 수 없다
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    4. 일반 메서드 주입
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        @Autowired
        public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
    */


    //2. 수정자 주입
    /*@Autowired(required=false)      //기본동작) 주입할 대상이 없으면 오류 발생. 여기선 DiscountPolicy. 따라서, required=false를 붙인다
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    //1. 생성자 주입

    /*Lombok의 @RequiredArgsConstructor이 final로 선언된 bean들을 주입해준다
    @Autowired
    //생성자가 1개 일때는 자동적으로 @Autowired가 설정된다
    만약 타입이 같을 때 필드명, 파라미터 이름에 따라 매칭한다
    */
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
