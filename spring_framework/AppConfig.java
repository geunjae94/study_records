package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
//@Configuration을 사용하지 않는 AppConfig 는 AppConfig@CGLIB가 되지않으므로 싱글턴을 보장하지 않는다.
//@Component - AnnotationConfigApplicationContext의 파라미터로 AppConfig.class로 넘겨주면 @ComponentScan의 대상이다.
//@Scope("singleton") - 생략 되어 있다 기본 Scope는 Singleton이기 때문에
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //예상 호출시 나열
     //call AppConfig.memberService
     //call AppConfig.memberRepository
     //call AppConfig.memberRepository
     //call AppConfig.orderService
     //call AppConfig.memberRepository

    //실제 호출시 나열
     //call AppConfig.memberService
     //call AppConfig.memberRepository
     //call AppConfig.orderService


    //스프링 컨테이너에서 AppConfig.class -> 오버라이딩된 Bean 메소드인 memberRepository()를 가지는 AppConfig@CGLIB(내부적으로 상속된 클래스) 생성
      //@Configuration을 사용할 때 - 오버라이딩 되는 기준은 @Bean이 설정된 메소드 내 new객체의 파라미터로 들어오는 메소드, 파라미터로 들어오는 메소드이므로 여러곳에서 호출될 가능성 고려해서 인가?
      //이후 컨테이너의 등록여부에 대한 분기를 나눠 동작
      //@Configuration을 사용하지 않는 경우 - @Bean이 설정된 메소드 내 new객체의 파라미터로 들어오는 메소드인 경우의 이유로 Bean 등록이 되지 않고 순수 자바 코드로만 호출 되는 것인가?

    //AppConfig@CGLIB 도 컨테이너에 Bean 객체로 등록됨. appConfig라는 이름으로
    //이후 BeanDefinition 과정을 거져 스프링 컨테이너에 각각의 Bean 객체로 등록된다.
    //Bean 객체 등록 과정에서 파라미터로 메소드 호출시 동적으로 스프링 컨테이너에 해당 Bean 객체를 등록 후 이후 호출시 그 하나를 공유(반환)하며 사용하게 된다.


    //@Autowired MemberRepository memberRepository; //추가적으로 컨테이너 Bean 객체로 MemoryMemberRepository 등록하고 주입하여 사용할 수도 있다.

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
        //return new MemberServiceImpl(memberRepository);
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return new OrderServiceImpl(memberRepository, discountPolicy());
        //return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
