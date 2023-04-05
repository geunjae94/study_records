package hello.core.genericmethod;


import org.junit.jupiter.api.Test;

import java.util.Optional;

class GenericMethodTest {

    @Test
    void genericMethodTest() {

        // 참조변수 선언 먼저
        Optional<String> s1 = Optional.ofNullable(null); //..able(String ? = null);
        Optional<String> s2 = Optional.<String>ofNullable(null);
        //<T> Optional<T> ofNullable(T value) -> <String> Optional<String> ofNullable(String ? = null);

        // 객체 선언 먼저
        Optional<Object> o1 = Optional.ofNullable(null); //..able(Object ? = null);
        Optional<Object> o2 = Optional.<Object>ofNullable(null); //위와 방식 동일(String 에서의..)


        Optional<? extends Object> o3 = Optional.ofNullable(null);
        //와일드 카드 타입 선언시 제네릭 메소드의 결정 되는 제네릭 타입은?


        //static 메서드, static 필드는 클래스의 제네릭 타입을 받아 사용하는 것이 불가하다.
        //해당 클래스가 인스턴스화 하면서 제네릭 타입이 결정되는 것이 아닌 실행시 곧 바로 static 메모리에 올라가기 때문에
        //그래서 static 메서드로 제네릭을 사용하려면 제네릭 메서드로 선언한다.
        //static 이기에 외부의 클래스 제네릭 타입과는 별개이며 그 메소드에서만 동작하는 제네릭 이다.

   //-------------------------------------------------------------------------------------------------------------


        Optional<Object> empty1 = Optional.<Object>ofNullable(null);
        System.out.println("empty1 = " + empty1);

        Optional<Object> empty2 = Optional.<Object>empty();
        System.out.println("empty2 = " + empty2);

        // toString()으로 부터의 출력값
        //오버라이딩 되어있으므로 null 제외의 객체의 값은 Optional[객체의 주소]로 나옴
        //null 값을 가지고 있는 empty()로부터의 Optional 객체는 Optional[null 주소]가 아닌 Optional.empty 라는 문자열로 나옴

        // empty1.get(), empty2.get()으로 부터의 출력값
        //실제로 내부의 객체를 반환하는 것이기 때문에 NoSuchElementException: No value present 가 발생한다.
        //다만, null 값을 수용하는 Optional 객체이므로 NullPointerException 는 발생하지 않는다.
    }
}
