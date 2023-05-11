package solution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

// level 1 성격 유형 검사하기
class Solution6 {
    // 참고: LinkedHashMap<>, StringBuffer, ~~stream().toArray(String[]::new), Char -> ASCII

    //ex)
    /*
    RT 0 3
    CF 1 0
    JM 0 2
    AN 1 1

    input: ["AN", "CF", "MJ", "RT", "NA"]
    input: [5, 3, 2, 7, 5]

    N1 C1 M2 T3 A1

    result: TCMA

--------------------------------

    RT 6 1 (T R)
    CF 0 0 (F C)
    JM 0 0 (M J)
    AN 0 0 (N A)

    input: ["TR", "RT", "TR"]
    input: [7, 1, 3]

    R3 R3 T1 -> R6 T1

    result: RCJA
    */

    public String solution(String[] survey, int[] choices) {
        String[] initStr = {"RT", "CF", "JM", "AN"}; //표본 유형별 문자
        //HashMap 저장 순서 보장x
        Map<String, Integer> map = new LinkedHashMap<>(); //표본 유형별 점수 초기화 공간

        Map<String, Integer> map2 = new LinkedHashMap<>(); //choices 값에 대한 {유형과 점수} 치환 저장 공간

        String s="", s1="", s2=""; // temp 공간(문자열)

        StringBuffer sb = new StringBuffer(); //최종 반환 문자 공간

        for (int i = 0; i < initStr.length; i++) { //표본 유형별 점수 초기화(value: 0)
            s = initStr[i];

            s1 = String.valueOf(s.charAt(0));
            map.put(s1, 0);
            s1 = String.valueOf(s.charAt(1));
            map.put(s1, 0);
        }
        //map.keySet().iterator().forEachRemaining(val->System.out.println(val));

        for (int i = 0; i < choices.length; i++) { //choices 값에 대한 {유형과 점수} 치환
            s = survey[i];
            s1 = String.valueOf(s.charAt(0));
            s2 = String.valueOf(s.charAt(1));

            switch (choices[i]){
                case 1:
                    if(!map2.containsKey(s1))
                        map2.put(s1, 3);
                    else
                        map2.replace(s1, map2.get(s1)+3); break;
                case 2:
                    if(!map2.containsKey(s1))
                        map2.put(s1, 2);
                    else
                        map2.replace(s1, map2.get(s1)+2); break;
                case 3:
                    if(!map2.containsKey(s1))
                        map2.put(s1, 1);
                    else
                        map2.replace(s1, map2.get(s1)+1); break;
                case 4: map2.put(null,0); break;
                case 5:
                    if(!map2.containsKey(s2))
                        map2.put(s2, 1);
                    else
                        map2.replace(s2, map2.get(s2)+1); break;
                case 6:
                    if(!map2.containsKey(s2))
                        map2.put(s2, 2);
                    else
                        map2.replace(s2, map2.get(s2)+2); break;
                case 7:
                    if(!map2.containsKey(s2))
                        map2.put(s2, 3);
                    else
                        map2.replace(s2, map2.get(s2)+3); break;
            }
        }

        String[] strs = map.keySet().stream().toArray(String[]::new); //표본 유형별 점수 -> 배열
        String[] strs2 = map2.keySet().stream().toArray(String[]::new); //choices 값에 대한 {유형과 점수} 치환값 -> 배열
        //Object[] objects = map.keySet().toArray();
        //String object = (String) objects[i];

        for (int i = 0; i < strs2.length; i++) { // map2 -> map 덮어쓰기
            for (int j = 0; j < strs.length; j++) {
                if(strs[j].equals(strs2[i])){
                    map.replace(strs[j],map2.get(strs2[i]));
                    break;
                }
            }
        }

        for (int i = 0; i < strs.length; i++) { //묶인 두개의 유형의 값 비교후 반환 문자열 추가
            if(i%2==0 && map.get(strs[i]) > map.get(strs[i+1]))
                sb.append(strs[i]);
            else if(i%2==0 && map.get(strs[i]) < map.get(strs[i+1]))
                sb.append(strs[i+1]);
            else if(i%2==0 && map.get(strs[i]) == map.get(strs[i+1])) {
                int asVal = ((int) strs[i].charAt(0)) - 64;
                int asVal2 = ((int) strs[i+1].charAt(0)) - 64;
                if(asVal<asVal2)
                    sb.append(strs[i]);
                else
                    sb.append(strs[i+1]);
            }
        }

        return sb.toString();
    }
}
