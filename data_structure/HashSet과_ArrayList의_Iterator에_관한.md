### HashSet의 내부

___
HashSet의 내부는 **HashMap**이다.

MVC패턴의 Service와 Repository사이의 느낌으로 볼 수도 있는데 

HashSet으로 부터 Iterator객체를 만드는 경우에는<br/>
HashMap으로부터 keySet객체를 리턴하여 거기로 부터 다시 Iterator객체를 만들어
이것이 리턴된다.
```java
public Iterator<E> iterator() { return map.keySet().iterator();}
```
<br/><br/>
### ArrayList의 Iterator

---
ArrayList의 Iterator는 내장클래스인 **Itr**로 부터 객체를 만들어서 리턴한다.

특징 :
1. 얕은 복사 - 주소 복사를 하기 때문에 Iterator객체의 remove()를 호출 하게 되면 외부의 List 값도 영향을 받는다.
따라서 Iterator는 List, Set의 표준화된 접근을 보장하는것 뿐이지 깊은 복사를 하는 것은 아니다.
   #### * Collection의 특징
    >Java의 Collection은 기본적으로 얕은 복사(shallow copy)는 제공하나 깊은 복사기능은
   제공하지 않는다. 즉, 원본 목록과 복제된 목록에 저장된 객체가 동일하고 그렇다는것은
   Java 힙 공간에서 동일한 메모리 위치를 가리켜 문제가 될 수 있다.


2. 일회성 객체 - index 위치를 가리키는 cursor가 null 까지 움직이기 때문에
<br/><br/>
```java
public Iterator<E> iterator() {
return new Itr();
}
    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData; //얕은 복사
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
        
        ...
```
