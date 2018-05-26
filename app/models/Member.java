package models;

import controllers.Status;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

@Entity
public class Member extends Model {

    public String name, email, password, address, gender, status;
    public float height, startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentList = new ArrayList<>();

    public Member(String name, String email, String password, String address, String gender, float height, float startingWeight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingWeight = startingWeight;
        status = Status.MEMBER;
    }

    public static Member findByEmail(String email){

        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public float currentBMI() {
        double output;

        if (assessmentList.size() == 0){
            output = (startingWeight / (pow(height/100, 2)));
         } else {
            Assessment assessment = assessmentList.get(assessmentList.size() -1);
            output = (assessment.weight / (pow(height/100, 2)));
        }

        return Math.round(output);
    }

    private String determineBMICategory() {
        String massage;
        double bmiValue = currentBMI();

        if (bmiValue < 16) {
            massage = "SEVERELY UNDERWEIGHT";
        } else if (bmiValue < 18.5) {
            massage = "UNDERWEIGHT";
        } else if (bmiValue < 25) {
            massage = "NORMAL";
        } else if (bmiValue < 30) {
            massage = "OVERWEIGHT";
        } else if (bmiValue < 35) {
            massage = "MODERATELY OVERWEIGHT";
        } else {
            massage = "SEVERELY OVERWEIGHT";
        }

        return massage;
    }

    public String weightStatus() {
        String massage;
        double bmiValue = currentBMI();

        if (bmiValue < 16) {
            massage = "red";
        } else if (bmiValue < 18.5) {
            massage = "yellow";
        } else if (bmiValue < 25) {
            massage = "green";
        } else if (bmiValue < 30) {
            massage = "blue";
        } else if (bmiValue < 35) {
            massage = "yellow";
        } else {
            massage = "red";
        }

        return massage;
    }

    public boolean isTrainer() {
        return status.equals(Status.TRAINER);
    }
}
