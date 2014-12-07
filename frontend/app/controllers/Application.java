package controllers;

import play.Routes;
import play.mvc.*;
import play.data.*;
import views.html.*;

public class Application extends Controller {

  public static Result index() {
    return ok(index.render());
  }
  
  public static class Login {
    public String email;
    public String pwd;
     
    public String validate() {
      //if(User.authenticate(email, password) == null) 
      if(!email.equals("admin@admin.com"))
        return "Invalid user or password";
      return null;
    }
  }

  public static Result login() {
    return ok(login.render(Form.form(Login.class)));
  }

  public static Result logout() {
    session().clear();
    flash("success", "You have been logged out");
    return redirect(routes.Application.login());
  }

  public static Result authenticate() {
    Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    if (loginForm.hasErrors()) {
      return badRequest(login.render(loginForm));
    } else {
      session().clear();
      session("email", loginForm.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  
  // Method which generates a javascript dynamically which holds the routes which are called through AJAX.
  // Uses Play's javascriptrouter method. 1st argument is the global variable name which contains the routes 
  // and can be called from any javascript. 
  public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("jsRoutes",
	            controllers.routes.javascript.GraphDisplay.getCoAuthorGraphDataByTopic(),
	            controllers.routes.javascript.GraphDisplay.getAuthorPublicationGraphDataByTopic(),
	            controllers.routes.javascript.GraphDisplay.getCoPublicationGraphDataByTopic(),
	            controllers.routes.javascript.GraphDisplay.getCoAuthorGraphDataByAuthor(),
	            controllers.routes.javascript.GraphDisplay.getAuthorPublicationGraphDataByAuthor(),
	            controllers.routes.javascript.GraphDisplay.getSchoolsByTopic()
	        )
	    );
	}
}
