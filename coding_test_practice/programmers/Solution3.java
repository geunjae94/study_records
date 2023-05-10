package solution;

import java.util.Arrays;
import java.util.Collections;

// level 1 과일 장수(최종)
class Solution3 {
    // 참고: int[] -> ...(내림차순 정렬) -> Integer[]
    
    public int solution(int k, int m, int[] score) {
        /*IntStream stream = Arrays.stream(score);
        Stream<Integer> boxed = stream.boxed();*/

        /*Integer[] integers = boxed.toArray(new IntFunction<Integer[]>() {
            @Override
            public Integer[] apply(int value) {
                return new Integer[value];
            }
        });
        Integer[] integers1 = boxed.toArray(value -> new Integer[value]);*/
        //-------------------------------------------------------------------------

        int j = 0;
        int highest = m;
        int answer = 0;
        int[] resultArr = new int[score.length / m];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = 10;
        }

        Integer[] scoreToInteger = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(scoreToInteger, Collections.reverseOrder());
        int[] scoreToInt = Arrays.stream(scoreToInteger).mapToInt(Integer::intValue).toArray();

        //int[] -> IntStream -> Stream<Integer> -> Integer[]
        // 내림차순 정렬(Integer[]) -> int[]
        //Integer[] -> Stream<Integer> -> IntStream -> int[]

        //Stream<Integer>의 멤버 변수인 Integer[]의 값 [1],[2]..들을 [1].intValue 하여 int[] 속에 전부 넣고
        //int[]를 가져간 IntStream 을 생성하여 반환
        //이후 IntStream의 껍데기를 벗긴후 int[]만 반환

        for (int i = 0; i < resultArr.length; i++) {
            for (; j < highest; j++) {
                if(resultArr[i] > scoreToInt[j]){
                    resultArr[i] = scoreToInt[j];
                }
            }
            j=highest;
            highest+=m;
        }

        for (int i : resultArr) {
            answer+= i * m;
        }

        return answer;

        //-------------------------------------------------------------------------------------
        /*List<String> as = new ArrayList<>();
        Stream<String> stream1 = as.stream();


        Integer[] integers = new Integer[]{1,2};
        Stream<Integer> stream2 = Arrays.stream(integers);
        Stream<Integer> integers1 = Stream.of(integers);
        IntStream stream3 = Arrays.stream(new int[]{1, 2, 3});//박싱 필요
        Stream<int[]> stream4 = Stream.of(new int[]{1, 2, 3});



        Function<Integer, int[]> s = new Function<>(){
            @Override
            public int[] apply(Integer x) {
                int i = x.intValue();
                return new int[i];
            }
        };

        Function<Integer, int[]> s2 = (Integer x) -> {
                int i = x.intValue();
                return new int[i];
        };

        int[] apply = s.apply(3);*/
    }
}
