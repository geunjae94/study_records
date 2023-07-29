package solution;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// level 2 이모티콘 할인행사_2023 KAKAO BLIND RECRUITMENT
public class Solution9_2 {
    // 참고 가능한: brute force 알고리즘, 2차원 배열의 정렬(   ex) Arrays.sort(resultArr, new Comparator<int[]>() {})  )

    public static void main(String[] args) {
        int[][] ints = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900},
                {40, 3100}, {27, 9200}, {32, 6900}};
        int[] result = solution(ints, new int[]{1300, 1500, 1600, 4900});

        System.out.println(result[0] + "=" + result[1]);
    }

    public static int[] solution(int[][] users, int[] emoticons) {

        int emoLen = emoticons.length;

        int[] position = new int[((int) Math.pow(4, emoLen) * emoLen)];
        int[][] rateAndAmount = new int[emoLen * 4][2];
        int[][] resultArr = new int[(int) Math.pow(4, emoLen)][2];

        double discountPercent = 0.1;
        double dp = discountPercent;
        int joinCount = 0;
        int totalSales = 0;
        int save = 0;
        int index = 0;
        int count2 = 1;
        int count = 0;
        int index2 = 0;

    // 모든 경우의 수의 할인율 조합 index 초기화[(할인율의 가지수^이모티콘의 개수) * 이모티콘의 개수]
        //개선 필요(재귀)
        while (((int) Math.pow(4, emoLen) * emoLen) > count) {
            for (int i = 0; i <= 4 * emoLen - emoLen; i = i + emoLen) {
                if (emoLen == 1) {
                    position[count++] = i;
                } else if (emoLen == 2) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        position[count++] = i;
                        position[count++] = j;
                    }
                } else if (emoLen == 3) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        for (int k = 2; k <= 4 * emoLen - emoLen + 2; k = k + emoLen) {
                            position[count++] = i;
                            position[count++] = j;
                            position[count++] = k;
                        }
                    }
                } else if (emoLen == 4) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        for (int k = 2; k <= 4 * emoLen - emoLen + 2; k = k + emoLen) {
                            for (int l = 3; l <= 4 * emoLen - emoLen + 3; l = l + emoLen) {
                                position[count++] = i;
                                position[count++] = j;
                                position[count++] = k;
                                position[count++] = l;
                            }
                        }
                    }
                } else if (emoLen == 5) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        for (int k = 2; k <= 4 * emoLen - emoLen + 2; k = k + emoLen) {
                            for (int l = 3; l <= 4 * emoLen - emoLen + 3; l = l + emoLen) {
                                for (int m = 4; m <= 4 * emoLen - emoLen + 4; m = m + emoLen) {
                                    position[count++] = i;
                                    position[count++] = j;
                                    position[count++] = k;
                                    position[count++] = l;
                                    position[count++] = m;
                                }
                            }
                        }
                    }
                } else if (emoLen == 6) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        for (int k = 2; k <= 4 * emoLen - emoLen + 2; k = k + emoLen) {
                            for (int l = 3; l <= 4 * emoLen - emoLen + 3; l = l + emoLen) {
                                for (int m = 4; m <= 4 * emoLen - emoLen + 4; m = m + emoLen) {
                                    for (int n = 5; n <= 4 * emoLen - emoLen + 5; n = n + emoLen) {
                                        position[count++] = i;
                                        position[count++] = j;
                                        position[count++] = k;
                                        position[count++] = l;
                                        position[count++] = m;
                                        position[count++] = n;
                                    }
                                }
                            }
                        }
                    }
                } else if (emoLen == 7) {
                    for (int j = 1; j <= 4 * emoLen - emoLen + 1; j = j + emoLen) {
                        for (int k = 2; k <= 4 * emoLen - emoLen + 2; k = k + emoLen) {
                            for (int l = 3; l <= 4 * emoLen - emoLen + 3; l = l + emoLen) {
                                for (int m = 4; m <= 4 * emoLen - emoLen + 4; m = m + emoLen) {
                                    for (int n = 5; n <= 4 * emoLen - emoLen + 5; n = n + emoLen) {
                                        for (int o = 6; o <= 4 * emoLen - emoLen + 6; o = o + emoLen) {
                                            position[count++] = i;
                                            position[count++] = j;
                                            position[count++] = k;
                                            position[count++] = l;
                                            position[count++] = m;
                                            position[count++] = n;
                                            position[count++] = o;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    // 각각의 할인율이 적용된 이모티콘 조합에 대한 결과값의 전체 경우의 수
        for (int i = 1; i <= 4; i++) {
            for (int emoticon : emoticons) {
                rateAndAmount[index++] = new int[]{i * 10, emoticon - (int) (emoticon * dp)};
            }
            dp += 0.1;
        }

        while ((int) Math.pow(4, emoLen) >= count2) {
            for (int[] user : users) {
                int tempIndex = index2;
                for (int p = 0; p < emoLen; p++) {
                    if (rateAndAmount[position[index2]][0] >= user[0]) {
                        if (p == emoLen - 1) {
                            save += rateAndAmount[position[index2]][1];
                        } else {
                            save += rateAndAmount[position[index2++]][1];
                        }
                    } else {
                        if (p == emoLen - 1) {
                            continue;
                        }
                        index2++;
                    }
                }
                index2 = tempIndex;

                if (save >= user[1]) {
                    joinCount++;
                } else {
                    totalSales += save;
                }
                save = 0;
            }
            index2 += emoLen;

            resultArr[count2 - 1] = new int[]{joinCount, totalSales};

            joinCount = 0;
            totalSales = 0;
            count2++;
        }
    // 전체 경우의 수의 결과값 정렬(내림차순)
        //Arrays.sort(resultArr,(o1, o2) -> o1[0]-o2[0]);

        /*Arrays.sort(resultArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //return o1[0]-o2[0];
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]; //두 배열의 순서, 연산 결과값(양수, 음수)에 따라 정렬된다.
            }
        });*/
        Arrays.sort(resultArr, (o1, o2) -> o1[0]!=o2[0] ? o2[0]-o1[0] : o2[1]-o1[1]);

        return resultArr[0];

// --------------------------------------------------------------------------------

        //System.out.println((int) Math.pow(4, 7));

        /*for (int i = 1; i <= 4; i++) {
            map3.put(i * 10, new ArrayList<>());
            for (int emoticon : emoticons) {
                map3.get(i * 10).add(emoticon - (int) (emoticon * dp));
            }
            dp += 0.1;
        }

        map3.keySet().stream().forEach(key -> System.out.println(key + "=" + map3.get(key)));*/


        /*for (int[] result : resultArr) {
            System.out.println(result[0]+"="+result[1]);
        }*/

        //stream.sorted()
        //System.out.println(joinCount + ", " + totalSales);

        /*for (int[] anInt : rateAndAmount) {
            index = 0;
            if (anInt[index] != temp)
                System.out.println();
            temp = anInt[index];
            System.out.print(+anInt[index++] + "=" + anInt[index] + ", ");
        }*/


        //System.out.println(func(16));
    }

    //수정 필요
    public static int func(int val) {
        // ex)16x15x14x13 에 대한 재귀적 해법
        if (val > val - 3)
            return val * func(val - 1);
        else
            return 1;
    }
}
