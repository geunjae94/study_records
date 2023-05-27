package solution;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// level 1 신고 결과 받기_2022 KAKAO BLIND RECRUITMENT
class Solution7 {
    // 참고: Map<String, List<String>>, new LinkedHashMap<>(), .mapToInt(i -> i.intValue()), .boxed().toArray(Integer[]::new)

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Integer> resultSave = new LinkedHashMap<>(); //배열로 변환전 최종 결과값 저장(유저당 받은 결과 메일 수)
        Map<String, List<String>> reportedUsersMap = new LinkedHashMap<>(); //신고당한 유저에 대한 신고한 유저의 집합들 ex) {frodo=[muzi, apeach], ...}
        Map<String, Integer> reportedIdCountMap = new LinkedHashMap<>();//신고당한 유저의 신고당한 횟수 ex) {muzi=1, frodo=2, ...}

        for (String s : id_list) { //map 초기화: {muzi=0, frodo=0, apeach=0, neo=0}
            reportedIdCountMap.put(s,0);
            resultSave.put(s,0);
        }

        for (String s : report) { //key: String, value: List 추가, 해당 key에 대한 값 추가, 신고당한 횟수 증가
            String[] s1 = s.split(" ");
            if (reportedUsersMap.containsKey(s1[1]) && reportedUsersMap.get(s1[1]).contains(s1[0])) { //report 중복 값 제외
                continue;
            } else if (reportedUsersMap.containsKey(s1[1]) && !reportedUsersMap.get(s1[1]).contains(s1[0])) { //중복 값 존재 하지 않으면
                reportedUsersMap.get(s1[1]).add(s1[0]);
                reportedIdCountMap.put(s1[1], (reportedIdCountMap.get(s1[1])) + 1);
            } else { //최초 값 존재 하지 않으면
                List<String> list = new ArrayList<>(); //범위 내 for loop시 새 list추가
                reportedUsersMap.put(s1[1], list);
                reportedUsersMap.get(s1[1]).add(s1[0]);
                reportedIdCountMap.put(s1[1], (reportedIdCountMap.get(s1[1])) + 1);
            }
        }

        reportedIdCountMap.keySet().forEach(key-> {
            int valueToKey = reportedIdCountMap.get(key);
                    if (valueToKey>=k) { //k횟수 이상 신고당한 유저를 기준으로
                        List<String> listVal = reportedUsersMap.get(key); //해당 유저를 신고한 유저의 집합중
                        listVal.forEach(value->{ //key: 신고한 유저, value: 신고한 유저에 대한 수 추가, 중복은 그 수만큼 증가
                            resultSave.put(value,resultSave.get(value)+1);
                        });
                    }
                }
        );

        /*System.out.println(reportedUsersMap);
        System.out.println(reportedIdCountMap);
        System.out.println(resultSave);*/

        int[] answer = resultSave.values().stream().mapToInt(i -> i.intValue()).toArray(); //mapToInt(i -> i.intValue()): Stream<int[]>또는 IntStream일 것

        /*int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);
        int[] ints1 = stream.toArray();
        Stream<Integer> boxed = stream.boxed();
        boxed.toArray(Integer[]::new);*/

        return answer;
    }
}
