package hello.core.singleton;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class SingletonService {
    // Summary:
    //private 생성자로 외부에서 인스턴스 객체 생성 막음
    //static 객체(static 참조변수 값) 생성 - 공유 객체 싱글턴
    //static 메소드로 리턴되는 단일의 객체만 참조 가능

    private static final SingletonService singletonService = new SingletonService();
    //static 메모리에 필드 영역 정의
    //heap 에서 객체 생성후 static의 필드로 리턴 되어 등록
    //static 참조변수를 static 객체라고 할 수 있는가?

    public static SingletonService getInstance() {
        return singletonService;
    }
    //static 메모리에 메소드 정의
    //클래스 단위로 호출해서 외부에서 단일의 객체만 참조

    private SingletonService(){
    }
    //private 로 선언된 생성자이므로 외부에서 객체 생성 불가 내부에서만 생성 가능
    //이걸로 싱글톤 유지, 그렇지만 스프링 컨테이너에서의 싱글턴과는 차이가 있다.
    //컨테이너의 bean 객체는 생성자의 private 설정이 아닌, 단지 하나의 bean 객체를 컨테이너에 추가 해놓고 그것을 공유하는 방식

    public void logic() {
        System.out.println("싱글턴 객체 호출");
    }

//----------------------------------------------------------------------------------------------------------------------------------

    /*public static class InnerClass{
        void clear() {
            System.out.println("clear");
        }
    }*/

    //외부 클래스는 static 이 생략되어있다. static 메모리상 하나의 클래스정보

    //내부 클래스는 static 을 사용할 용도이면 따로 명시해야 한다. 명시할 시 그것에 대한 기능적 동작은
    //1.외부 클래스 안의 내부 클래스로 static 메모리에 올라가 개별적인 instance 를 생성할 수 있게 된다.
    //ex) SingletonService.InnerClass ssi = new SingletonService.InnerClass();
    //2.내부 클래스가 외부참조를 하지 않는 경우에, static 으로 인해 객체 생성 단계에서 생성자의 parameter 로 외부 필드가 삽입되지 않는다.(GC 관련)
    //단, 접근지정자를 명시하였을때 static 이 있기때문에 외부참조에 관한것은 어떻게?
    //외부 클래스의 경우는 생성자로 외부 참조가 수행되지 않기에 접근지정자를 명시해도 상관이 없는 것이라고 생각 되어진다.

    //static 을 명시하지 않는다면 외부 클래스를 인스턴스화했을때 그 인스턴스 내에 클래스의 정보가 있는것을 토대로 인스턴스를 생성 가능
    //ex) SingletonService.InnerClass ssi = new SingletonService().new InnerClass();

}
