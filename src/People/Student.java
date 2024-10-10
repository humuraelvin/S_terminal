package People;

import java.util.Date;

public class Student extends Person{


    public Student(int id, String firstName, String lastName, Date dateOfBirth) {
        super(id, firstName, lastName, dateOfBirth);
    }



    private String school;
    private String combination;
    private int level;

    public Student(int id, String firstName, String lastName, Date dateOfBirth, String school, String combination, int level){
        super(id, firstName, lastName, dateOfBirth);

        this.school = school;
        this.combination = combination;
        this.level = level;

    }

    public Student() {
        super();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}