package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    /**
     * 의존관계 주입 방법
     * 1. 생성자 주입
     * 2. 수정자 주입
     * 3. 필드 주입
     * 4. 일반 메서드 주입
     */

    //3. 필드 주입 @Autowired 직접 선언
    // 단점: 1. 보기엔 편리해보이지만 외부에서 접근이 불가능하다. MemberRepository와 DiscountPolicy를 세팅해줄 set 함수가 추가로 필요하다. 즉, DI 프레임워크가 없으면 아무것도 할 수 없
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

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

    /*
    2. 수정자 주입
    @Autowired(required=false)      //기본동작) 주입할 대상이 없으면 오류 발생. 여기선 DiscountPolicy. 따라서, required=false를 붙인다
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*
    1. 생성자 주입
    @Autowired      //생성자가 1개 일때는 자동적으로 @Autowired가 설정된다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

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
