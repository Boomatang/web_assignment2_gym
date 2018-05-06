package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {

    public String name, email, password, address, gender;
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
    }

    public static Member findByEmail(String email){

        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public float currentBMI() {
//        TODO fill this out to return the correct result
        return 42.66f;
    }

    private String determineBMICategory() {
        return "SEVERELY OVERWEIGHT";
    }
}
