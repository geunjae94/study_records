package solution;

// level 1 햄버거 만들기 - 배열, 문자열 사용
class Solution5_3 {
    // 참고 가능한: StringBuffer

    // 문제점:
    //500000개 이상의 배열의 요소가 있을시 시간 복잡도
    //점점 많은 양의 데이터를 가진 새로운 String객체를 만드는 것이 문제
    //해결1. StringBuffer의
    //contains역할을 하는 것이 있으면 될 것
    //최종해결: buffer의 일부분만 자른 배열 값의 String객체를 만들어 이용
    //StringBuffer -> .subSequence(CharSequence) -> String

    public int solution(int[] ingredient) {
        StringBuffer buffer = new StringBuffer();
        int answer = 0;
        int index = 0;

        for (int i = 0; i < ingredient.length; i++) {
            /*temp = buffer.append(ingredient[i]).toString(); //이 부분이 문제(append 이후)
            if (temp.contains("1231")){
                index=buffer.indexOf("1231");
                buffer.delete(index, index + 4);
                buffer.s
                answer++;
            }
            temp=null;*/
            buffer.append(ingredient[i]);
            if(buffer.length()>3){
                CharSequence charSequence = buffer.subSequence(buffer.length()-4, buffer.length());
                String temp = charSequence.toString();
                if (temp.contains("1231")){
                    buffer.delete(buffer.length()-4, buffer.length());
                    answer++;
                }
            }
        }
        //System.out.println(buffer);
        return answer;
    }
}
