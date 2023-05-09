### HashSet과 HashMap 

---
HashSet과 HashMap의 데이터 **저장** 및 **조작**은 동일하다.<br/>
그 이유는 내부적인 동작을 살펴보면 HashSet 의 .add()의내부는 HashMap의 .put() 이기 때문이다.<br/>
```java
private transient HashMap<E,Object> map;
...
public boolean add(E e) {return map.put(e, PRESENT)==null;}
```
( HashSet 의 .add(element) -> .put(element(key), value) )<br/>* Object 참조 변수의 값은 Dummy value data로 PRESENT를 주입, 일종에 자리수 맞추기용 

즉, HashSet의 element가 map에서는 key값으로 된 후 그 key값은 hashing 과정을 거쳐 HashTable배열의 index로 지정된다.
이후 index로 된 key값 다시말해 최초의 element값은 그 해당 index의 위치에 저장된다.<br/>
만약 해당 자리에 이미 값이 존재하여 충돌이 일어나면 이후부터는 연결리스트로 확장해가며 값을 저장하게 된다.
<br/>
<br/>
HashSet은 key와 value나 index와 element같은 자료 저장 구조가 아니기 때문에 따로 get 메서드가 없음, Iterator객체를 사용하여 저장된 값의 조회가 가능하다.<br/>
HashMap에서는 최초부터 key와 value로 들어온다. 과정은 위와 동일하며 저장되는 데이터는 key와 value 한쌍으로 저장된다.
<br/>
<br/>
List와 Set은 공통조상인 Collection을 상속한다. 그렇지만 Map은 Collection을 상속하지 않는다.<br/>
Set과 Map은 Iterator를 통해 값(쌍, 집합) 단위의 조회가 가능하다.<br/>
List와 Map은 index와 key를 가지고 있기 때문에 get()이 가능하다.
<br/>
<br/>
Set이나 Map이 순서를 유지하지 않는 구조는
일관된 순서의 index에 값을 저장하고 찾아오는게 아닌
hashing을 통해 나온 index에 값을 저장하고 찾아오는 방식이기 때문에
<br/>
<br/>
---
<br/>
HashSet - add(element), ...<br/>
ArrayList - add(index, element), element get(index), ...<br/>
HashMap - put(key, value), value get(key), Set entrySet(), Set keySet(), Collection values(), ...