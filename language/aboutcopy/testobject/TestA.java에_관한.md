```java
@Override   //protected native Object clone() throws CloneNotSupportedException;
public TestA clone() throws CloneNotSupportedException {
    return (TestA) super.clone();
}
```
* protected: 다른 패키지(package)의 클래스(class)에서 접근(상속)은 받을 수 있되 오버라이딩(overriding)을 하지 않으면 호출은 불가한 상태
 >　> 따라서, clone()의 오버라이딩이 필수인 이유: 1. A 패키지 범위에 속한 clone()을 B 패키지 범위(현재 클래스의)로,</br>
 >　　　　　　　　　　　　　　　　　선택 사항: 2. public 설정이 가능하도록, 3. 내부적으로 다운캐스팅(downcasting)을 하여 리턴
  >>　> public 설정의 이유: C 패키지에서의 사용도 고려
 
　　protected의 존재 의의 or 사용 의도에 대한 약간의 의문점, protected 키워드와 관련된 어렴풋한 느낌</br></br>

* clone()의 오버라이딩은 cloneable interface를 명시적으로 구현하지 않으면 CloneNotSupportedException 예외 발생