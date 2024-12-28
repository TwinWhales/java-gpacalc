package gpacalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subject {
    private String name; // 과목명
    private int number; // 학점수
    private double score; //  받은 점수
    private String field; // 전공, 교양

    public Subject() {
        this.name = "";
        this.number = 0;
        this.score = 0.0;
        this.field = "";
    }

    public Subject(String field, String name, int number, double score) {
        this.name = name;
        this.number = number;
        this.score = score;
        this.field = field;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}

    public double getScore() {return score;}
    public void setScore(double score) {this.score = score;}

    public String getField() {return field;}
    public void setField(String field) {this.field = field;}

    @Override
    public String toString() {
        return "[" + this.getField() + "] " + this.getName() + "," + this.getNumber() + "," + this.getScore();
    }
}

public class SubjectManager {
    Scanner scanner;
    private ArrayList<Subject> subjectlist;

    public SubjectManager() {
        this.subjectlist = new ArrayList<Subject>();
        this.scanner = new Scanner(System.in);
    }

    public void AddSubject() {
        System.out.println("전공 과목명과 이수학점, 평점을 입력해주세요(예시: 프로그래밍언어론-3-A+,소프트웨어공학-3-B+):");
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile(",");

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String[] result = matcher.group(1).split("-");
            Subject subject = new Subject("전공", result[0], Integer.parseInt(result[1]),Double.parseDouble(result[2]));
            this.subjectlist.add(subject);
        }

        System.out.println("교양 과목명과 이수학점, 평점을 입력해주세요(예시: 선형대수학-3-C0,인간관계와자기성장-3-P):");
        input = scanner.nextLine();
        pattern = Pattern.compile(",");

        matcher = pattern.matcher(input);

        while (matcher.find()) {
            String[] result = matcher.group(1).split("-");
            Subject subject = new Subject("교양", result[0], Integer.parseInt(result[1]), Double.parseDouble(result[2]));
            this.subjectlist.add(subject);
        }
    }

    public void printList() {
        System.out.println("<과목 목록>");
        for (Subject subject : subjectlist) {
            System.out.println(subject.toString());
        }
    }

    void SubjectCalculator(List<Subject> subjects) {
        double Major_subjects = 0;
        double Liberal_arts_courses = 0;
        int total_number = 0;

        for(Subject subject : subjects) {
            if(subject.getField() ==  "전공") {
                Major_subjects += subject.getScore();
            }
            else {
                Liberal_arts_courses += subject.getScore();
            }
            total_number += subject.getNumber();
        }

        System.out.println("<취득학점>");
        System.out.println(total_number + "학점");

        System.out.println("<평점평균>");
        System.out.println((Major_subjects + Liberal_arts_courses) + " / 4.5");

        System.out.println("전공 평점평균");
        System.out.println(Major_subjects + " / 4.5");
    }
}

public class Application {
    public static void main(String[] args) {




    }
}
