package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
    public static void signup() {
        Logger.info("New user signed up.");
        render("signup.html");
    }

    public static void login() {
        Logger.info("User logged in.");
        render("login.html");
    }

    public static void logout() {
        Logger.info("User logged out");
        session.clear();
        redirect("/");
    }

    public static void register(String name, String gender, String email, String password, String address, String height, String weight) {
        Member member = new Member(name, email, password, address, gender, Float.valueOf(height), Float.valueOf(weight));
        member.save();

        redirect("/login");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
