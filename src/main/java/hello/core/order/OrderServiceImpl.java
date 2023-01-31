package hello.core.order;

import hello.core.discount.Discountpolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어주는 lombok 기능
public class OrderServiceImpl implements OrderService {

    //private final Discountpolicy discountpolicy = new FixDiscountPolicy();
    //private final Discountpolicy discountpolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final Discountpolicy discountpolicy;

//    lombok 자동 생성자(final 필드 존재 시) 기능 생성을 위한 주석 처리
//    @Autowired // 생성자가 한개라면 Autowired 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, Discountpolicy discountpolicy) {
//        this.memberRepository = memberRepository;
//        this.discountpolicy = discountpolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountpolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
