package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    Member member = Accounts.getLoggedInMember();

    if (member.isTrainer()) {
      Logger.info("Rendering Admin");
      List<Member> memberlist = Member.findAll();
      render("admin.html", memberlist);
    } else {
      Logger.info("Un-trainer tried to access admin pages");
      redirect("/dashboard");
    }
  }

  public static void userDashboard(Long id) {
    Member trainer = Accounts.getLoggedInMember();

    if(trainer.isTrainer()) {
      Member member = Member.findById(id);

      render("adminUserDashboard.html", member);

    } else {
      Logger.info("None trainer tried to access information");
      redirect("/");
    }
  }

  public static void addComment(Long id, String comment){
    Member trainer = Accounts.getLoggedInMember();

    if(trainer.isTrainer()) {
      Assessment assessment = Assessment.findById(id);
      assessment.comment = comment;
      assessment.save();

      Logger.info(comment);
      redirect("/admin");

    } else {
      Logger.info("None trainer tried to access information");
      redirect("/");
    }
  }

}
