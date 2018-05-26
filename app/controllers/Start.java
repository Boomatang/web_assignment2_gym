package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Start extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Start");

    Member member = Accounts.getLoggedInMember();

    if (member.isTrainer()) {
      Logger.info("There is a trainer logged in");
    }

    render("start.html", member);
  }
}
