package codetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class CodeTest {
    
    void codeTest() {

        List<Integer> integerList = Arrays.asList(1,2,3);
        //Arrays.asList(new Integer[]{new Integer(?,?,?)});는 내부 Arrays 클래스의 내부 ArrayList(추가,삭제 불가)

        Collection<int[]> ints = Arrays.asList(new int[]{1,2,3,4});
        //파라미터의 타입 Collection 모든 하위 객체를 대입받기 위한, 다형성의 의미보단
        List ints1 = (List) ints; //이후 원래대로 형변환이 이루어져 다형성이 정상적으로 작동된다.

        B[] bs = new B[2]; //B->A
        List<B> bs1 = Arrays.asList(bs);
        Collection<A> arrayList = new ArrayList<>(bs1);
        //ArrayList 생성자 파라미터의 타입의 제네릭 타입이 ? extends E 이기 때문에 가능
        //내부 E나 T ..에서도 클래스 타입 처럼 상속 또는 인터페이스 타입까지로 제한 되어 사용됨
        //<E> : element, 내부적으로 배열처리 ex)List
        //<T> : 내부적으로 객체의 참조형으로 처리


        List<Integer> integerList2 = Arrays.asList(new Integer[]{new Integer(1),2,3});
        ArrayList<Integer> arrayList2 = new ArrayList<>(integerList2);
        //Object[] elementData에 파라미터내의 값 배열로 변경후 얕은 복사
        //생성자의 파라미터로 지정되는 제네릭 타입이 ArrayList의 제네릭타입으로 지정됨
        //
        //public E get(int index) {
        //        Objects.checkIndex(index, size);
        //        return elementData(index);
        //    }
        //
        //E elementData(int index) {
        //    return (E) elementData[index];
        //}
        //
        //의 이유들로 인해


        Integer[] integers = new Integer[]{1,2,3,4};
        //Integer객체 하나 씩이 들어갈 수 있는 4개 배열 저장 공간 객체 생성, 그것의 맨 앞의 주소 넘김
        //타입 자료형은 핸들링 가능한 기능을 하면서, 배열 저장 공간 객체의 기능 사용 범위를 제한 할 뿐이다.
        //integer4. 는 사용할 수 있는 메서드가 제한적, integer4[1]는 C에서의 *(integer+1)와 동일 하다고 볼 수 있다.
        Integer[] integers2 = (Integer[]) integers;
        //Integer와 Integer[]는 서로 다른 클래스, int[]도 클래스

        Integer integer3 = new Integer(1);

    }
}
