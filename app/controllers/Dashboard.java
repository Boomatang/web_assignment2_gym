package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    render("dashboard.html", member);
  }

  public static void addAssessment(String weight, String chest, String thigh, String upperArm, String waist, String hips) {

    Assessment assessment = new Assessment(Float.valueOf(weight),
            Float.valueOf(chest),
            Float.valueOf(thigh),
            Float.valueOf(upperArm),
            Float.valueOf(waist),
            Float.valueOf(hips));
    Member member = Accounts.getLoggedInMember();
    member.assessmentList.add(assessment);
    member.save();
    Logger.info("New assessment added");
    redirect("/dashboard");
  }

}
