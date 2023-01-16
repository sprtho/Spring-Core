package hello.core.order;

import hello.core.discount.Discountpolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //private final Discountpolicy discountpolicy = new FixDiscountPolicy();
    //private final Discountpolicy discountpolicy = new RateDiscountPolicy();
    private final Discountpolicy discountpolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, Discountpolicy discountpolicy) {
        this.memberRepository = memberRepository;
        this.discountpolicy = discountpolicy;
    }

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
