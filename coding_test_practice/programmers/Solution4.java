package solution;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// level 1 기사단원의 무기
class Solution4 {
    // 요약:
    //1 ~ number에 해당하는 숫자까지의 약수의 개수를 구한뒤 모두 합한 결과 구하기

    // 알고리즘:
    //1. 지정된 숫자의 제곱근 까지 나누어 떨어지는 수들의 개수 Count
    //2. 구한 나누어 떨어지는 수들을 지정된 숫자와 나누어 나오는 값중 중복되지 않은 값만 Count

    // 참고: HashSet<> -> iterator<>

    // 문제점:
    //expectedModCount != modCount(서로 불일치 문제) - ConcurrentModificationException 발생 에 관한

    int solution(int number, int limit, int power) {
        int answer = 0;
        int[] ints = new int[number];
        Set<Integer> integers = new HashSet<>();


        for (int i = 1; i <= number; i++) {

            int sqrt = (int)(Math.sqrt(i));
            for (int j = 1; j <= sqrt; j++) {
                if (i % j == 0) {
                    integers.add(j); //ex) i: 9, modCount: ex) 2
                    ints[i-1]++;
                }
            }

            /*Iterator<Integer> iter = integers.iterator(); //expectedModCount = modCount
            while(iter.hasNext()){
                if (integers.add(i / iter.next())) { //add 하게 되면 HashSet의 add().put().putVal() 속의 modCount: ex) 2 -> 3
                                                     //while 속 다음번 next()의 checkForComodification()에서 expectedModCount != modCount(서로 불일치 문제) - ConcurrentModificationException 발생
                                                     //integer.remove()에서도 동일한 문제

                                                     //단, iter.remove()는 변경된 modCount를 expectedModCount로 갱신하기 때문에 문제 발생 X
                                                     //그렇다면 remove는 해결되지만 add는?

                                                     //ArrayList도 동일한 처리방식, 내부구조에 대해서는 어느정도 차이가 있다.
                    ints[i - 1]++;
                }
            }*/

            Iterator<Integer> iter = integers.iterator();
            while(iter.hasNext()){
                if (!integers.contains(i / iter.next())){
                    ints[i - 1]++;
                }
            }

            if (ints[i-1]>limit) {
                ints[i-1]=power;
            }

            integers.clear();
        }

        for (int anInt : ints) {
            answer+=anInt;
        }

        return answer;
    }
}


