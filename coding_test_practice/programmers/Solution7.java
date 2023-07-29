package solution;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// level 1 신고 결과 받기_2022 KAKAO BLIND RECRUITMENT
class Solution7 {
    // 참고 가능한: Map<String, List<String>>, new LinkedHashMap<>(), ~~.mapToInt(Integer::intValue), ~~.boxed().toArray(Integer[]::new)

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Integer> mailCount = new LinkedHashMap<>(); //유저당 받은 결과 메일 수(배열로 변환전 최종 결과값 저장)
        Map<String, List<String>> reporters = new LinkedHashMap<>(); //신고당한 유저에 대한 신고한 유저의 집합들 ex) {frodo=[muzi, apeach], ...}
        Map<String, Integer> reportedIdCount = new LinkedHashMap<>();//신고당한 유저의 신고당한 횟수 ex) {muzi=1, frodo=2, ...}

        for (String s : id_list) { //map 초기화: {muzi=0, frodo=0, apeach=0, neo=0}
            reportedIdCount.put(s,0);
            mailCount.put(s,0);
        }

        for (String s : report) { //key: String, value: List 추가, 해당 key에 대한 값 추가, 신고당한 횟수 증가
            String[] s1 = s.split(" ");
            if (reporters.containsKey(s1[1]) && reporters.get(s1[1]).contains(s1[0])) { //report 중복 값 제외
                continue;
            } else if (reporters.containsKey(s1[1]) && !reporters.get(s1[1]).contains(s1[0])) { //중복 값 존재 하지 않으면
                reporters.get(s1[1]).add(s1[0]);
                reportedIdCount.put(s1[1], (reportedIdCount.get(s1[1])) + 1);
            } else { //최초 값 존재 하지 않으면
                List<String> list = new ArrayList<>(); //범위 내 for loop시 새 list추가
                reporters.put(s1[1], list);
                reporters.get(s1[1]).add(s1[0]);
                reportedIdCount.put(s1[1], (reportedIdCount.get(s1[1])) + 1);
            }
        }

        reportedIdCount.keySet().forEach(key-> {
            int valueToKey = reportedIdCount.get(key);
                    if (valueToKey>=k) { //k횟수 이상 신고당한 유저를 기준으로
                        List<String> listVal = reporters.get(key); //해당 유저를 신고한 유저의 집합중
                        listVal.forEach(value->{ //key: 신고한 유저, value: 신고한 유저에 대한 수 추가, 중복은 그 수만큼 증가
                            mailCount.put(value, mailCount.get(value)+1);
                        });
                    }
                }
        );

        /*System.out.println(reporters);
        System.out.println(reportedIdCount);
        System.out.println(mailCount);*/

        int[] answer = mailCount.values().stream().mapToInt(Integer::intValue).toArray(); //mapToInt(i -> i.intValue()) -> mapToInt(Integer::intValue)
                                                                                          //(추측) Integer값->int로 변환 -> int[]에 전부 대입 -> 해당 int[]를 IntStream의 int[]에 대입
                                                                                          //(추측) 해당 IntStream 반환     *Stream<int[]>
        /*int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);
        int[] ints1 = stream.toArray();
        Stream<Integer> boxed = stream.boxed();
        boxed.toArray(Integer[]::new);*/

        return answer;
    }
}
