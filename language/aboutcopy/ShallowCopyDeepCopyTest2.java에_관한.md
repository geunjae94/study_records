TestB testB = new TestB(99, "zaza");

`얕은 복사(shallow copy)` TestA testA = new TestA(20, "kuma", testB);
* 내부적으로 동일 메모리의 필드, 동일 필드 값을 가지는 동일 클래스의 객체를 참조

`깊은 복사(deep copy)` TestA clone = testA.clone(); 
* 내부적으로 다른 메모리의 필드, 동일 필드 값을 가지는 동일 클래스의 객체를 참조
  
 >　> 동일 필드값 이지만 primitive type의 경우 필드 자체에는 다른 메모리가 할당 된 듯하다.
  >>　> 근거는 다른 주소를 가지는 clone객체의 내부 primitive type의 변수 값 변경시</br>
    　　원본 primitive type의 변수 값이 변경되지 않음
 
 >　> 동일 필드값 이지만 reference type(String에 대한)의 경우 필드 자체에는 다른 메모리가 할당 된 듯하다.
  >>　> 근거는 마찬가지로 복제된 객체의 주소가 원본 객체와는 다른 주소를 가지게 되면서,</br>
    　　그 과정에서 내부적으로 필드에 원본과는 다른 별개의 메모리가 할당되어 생성된 듯하고</br>
    　　그렇기에, 그 String 필드 값은 원본과 동일한 String 객체에 대한 주소를 가지지만</br>
    　　해당 필드를 다른 String 객체의 주소로 변경하여도</br>
    　　원본 객체 내 String 필드 값은 변경되지 않는다.</br>
    　　반대도 동일하다고 볼 수 있다.(원본 객체에서의 변경)
 
　　따라서 `깊은 복사`로 볼 수 있다.

　　그렇지만 reference type이 String과 같은 immutable객체가 아닌 mutable객체라면</br>
　　필드 값인 주소 자체를 변경하는 것이 아니라 참조하는 값에 대한 변경이 이루어지는 경우에는</br>
　　원본 객체내 reference type(TestB에 대한)의 참조하는 값도 같이 변경이 이루어지기에 `얕은 복사`로 볼 수 있지 않을까 생각된다.