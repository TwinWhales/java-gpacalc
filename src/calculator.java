import java.util.Scanner;
public class calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //전공과 교양 한줄로 입력받기
        System.out.println("전공 과목명과 이수학점, 평점을 입력해주세요(예시: 프로그래밍언어론-3-A+,소프트웨어공학-3-B+):");
        String coreStr = scanner.nextLine();

        System.out.println("교양 과목명과 이수학점, 평점을 입력해주세요(예시: 선형대수학-3-C0,인간관계와자기성장-3-P):");
        String subStr = scanner.nextLine();


        //전공과목 저장
        String[] core = coreStr.split(",");

        String[][] coreMem = new String[core.length][];  //전공과목 저장을 위한 배열(문자열 형태)

        for(int i=0; i<core.length; i++) {
            coreMem[i] = core[i].split("-");
        }

        //교양 저장
        String[] sub = subStr.split(",");

        String[][] subMem = new String[sub.length][];  //교양과목 저장을 위한 배열(문자열 형태)

        for(int i=0; i<sub.length; i++) {
            subMem[i] = sub[i].split("-");
        }

        /*
        이제 coreMem[][] 과 subMem[][] 에 입력받는것에 따라 크기가 변하는
        배열로 점수를 저장하는 것까지 구현했다.
        이제 남은것은
        1. 요구사항대로 입력받지 않았으면 프로그램을 종료시키는 조건을 거는것과
        2. 학점계산 후 출력
        */

        //에러 판별
        String checkStr = "          ";
        int check=0;

        String[] coreName= new String[core.length]; //전공 이름
        String[] subName= new String[sub.length]; //교양 이름

        int[] coreNum = new int[core.length]; //전공 학점
        int[] subNum = new int[sub.length]; //교양 학점

        String[] coreScore= new String[core.length]; //전공 점수
        String[] subScore= new String[sub.length]; //교양 점수

        //과목명
        for(int i=0; i<core.length; i++){
            //공백으로 채워져있는지 검사
            if(coreMem[i][0].equals(checkStr)){
                check++;
            }
            //글자가 10개 넘는지 검사
            if(coreMem[i][0].length()>10){
                check++;
            }
            coreName[i] = coreMem[i][0]; //전공 복사
        }

        for(int i=0; i<sub.length; i++){
            //공백으로 채워져있는지 검사
            if(subMem[i][0].equals(checkStr)){
                check++;
            }
            //글자가 10개 넘는지 검사
            if(subMem[i][0].length()>10){
                check++;
            }
            subName[i] = subMem[i][0]; //교양 복사
        }

        //학점
        for(int i=0; i<core.length; i++){
            coreNum[i] = Integer.parseInt(coreMem[i][1]);
            if(coreNum[i]>4 || coreNum[i]<1){
                check++;
            }
        }

        for(int i=0; i<sub.length; i++){
            subNum[i] = Integer.parseInt(subMem[i][1]);
            if(subNum[i]>4 || subNum[i]<1){
                check++;
            }
        }

        if(check!=0){
            int error = checkError(check);
            System.out.println(error);
            System.exit(0);
        }

        //성적
        for(int i=0; i<core.length; i++) {
            coreScore[i] = coreMem[i][2];
        }

        for(int i=0; i<sub.length; i++) {
            subScore[i] = subMem[i][2];
        }

        //계산
        int point=0; //취득학점
        double allAverage; //평점평균(전공, 교양 과목 모두 포함)
        double coreAverage; //전공 평점평균(전공 과목만 포함)
        int gradePoint=0; //과목학점 총합
        double allGrade=0.0; //과목성적가중치 총합
        int corePoint=0; //전공과목학점
        double coreGrade=0.0; //전공성적가중치

        //취득학점
        for(int i=0; i<core.length; i++) {
            if(!(coreScore[i].equals("F")||coreScore[i].equals("NP"))){
                point+=coreNum[i];
            }
        }
        for(int i=0; i<sub.length; i++) {
            if(!(subScore[i].equals("F")||subScore[i].equals("NP"))){
                point+=subNum[i];
            }
        }

        //평점평균
        for(int i=0; i<core.length; i++) {
            double score=0;
            if(!(coreScore[i].equals("P")||coreScore[i].equals("NP"))){
                if(coreScore[i].equals("A+")){
                    score=4.5;
                }else if(coreScore[i].equals("A0")){
                    score=4.0;
                }else if(coreScore[i].equals("B+")){
                    score=3.5;
                }else if(coreScore[i].equals("B0")){
                    score=3.0;
                }else if(coreScore[i].equals("C+")){
                    score=2.5;
                }else if(coreScore[i].equals("C0")){
                    score=2.0;
                }else if(coreScore[i].equals("D+")){
                    score=1.5;
                }else if(coreScore[i].equals("D0")){
                    score=1.0;
                }else if(coreScore[i].equals("F")){
                    score=0.0;
                }
                allGrade += (score * coreNum[i]);
                coreGrade += (score * coreNum[i]);
                gradePoint += coreNum[i];
                corePoint += coreNum[i];
            }
        }

        for(int i=0; i<sub.length; i++) {
            double score=0;
            if(!(subScore[i].equals("P")||subScore[i].equals("NP"))){
                if(subScore[i].equals("A+")){
                    score=4.5;
                }else if(subScore[i].equals("A0")){
                    score=4.0;
                }else if(subScore[i].equals("B+")){
                    score=3.5;
                }else if(subScore[i].equals("B0")){
                    score=3.0;
                }else if(subScore[i].equals("C+")){
                    score=2.5;
                }else if(subScore[i].equals("C0")){
                    score=2.0;
                }else if(subScore[i].equals("D+")){
                    score=1.5;
                }else if(subScore[i].equals("D0")){
                    score=1.0;
                }else if(subScore[i].equals("F")){
                    score=0.0;
                }
                allGrade += (score * subNum[i]);
                gradePoint += subNum[i];
            }
        }
        allAverage= allGrade / gradePoint;
        coreAverage= coreGrade / corePoint;

        //출력
        System.out.println("<과목 목록>");
        for(int i=0; i<core.length; i++) {
            System.out.println("[전공] " + coreName[i] + "," + coreNum[i] + "," + coreScore[i]);
        }
        for(int i=0; i<sub.length; i++) {
            System.out.println("[교양] " + subName[i] + "," + subNum[i] + "," + subScore[i]);
        }
        System.out.println("\n<취득학점>");
        System.out.println(point + "학점");

        System.out.println("\n<평점평균>");
        System.out.println(String.format("%.2f", allAverage) + " / 4.5");

        System.out.println("\n<전공 평점평균>");
        System.out.println(String.format("%.2f", coreAverage) + " / 4.5");

    }

    public static int checkError(int i) throws IllegalArgumentException{
        throw new IllegalArgumentException("입력이 잘못되었습니다.");
    }
}