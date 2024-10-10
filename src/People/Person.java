package People;

import java.util.Date;

public class Person {

    private int id;
    private String firstName;
    String lastName;
    private Date dateOfBirth;


    public Person(int id, String firstName, String lastName, Date dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String firstName, String lastName, Date dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String lastName, Date dateOfBirth){
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(int id) {
        return this.id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }


}
