
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object login extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Application.Login],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[Application.Login]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.33*/(""" 

"""),_display_(Seq[Any](/*3.2*/main("Login")/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""    
    
    """),_display_(Seq[Any](/*5.6*/helper/*5.12*/.form(routes.Application.authenticate)/*5.50*/ {_display_(Seq[Any](format.raw/*5.52*/("""
        
        <h1>Please sign in below:</h1>
        
        """),_display_(Seq[Any](/*9.10*/if(form.hasGlobalErrors)/*9.34*/ {_display_(Seq[Any](format.raw/*9.36*/(""" 
            <p class="error">
                """),_display_(Seq[Any](/*11.18*/form/*11.22*/.globalError.message)),format.raw/*11.42*/("""
            </p>
        """)))})),format.raw/*13.10*/("""
        
        """),_display_(Seq[Any](/*15.10*/if(flash.contains("success"))/*15.39*/ {_display_(Seq[Any](format.raw/*15.41*/("""
            <p class="success">
                """),_display_(Seq[Any](/*17.18*/flash/*17.23*/.get("success"))),format.raw/*17.38*/("""
            </p>
        """)))})),format.raw/*19.10*/("""
        
        <p>
            <input type="email" name="email" placeholder="Email" value=""""),_display_(Seq[Any](/*22.74*/form("email")/*22.87*/.value)),format.raw/*22.93*/("""">
        </p>

        <p>
            <input type="password" name="password" placeholder="Password">
        </p>
        <p>
            <button type="submit" class="btn btn-success">Log in</button>
        </p>
        
    """)))})),format.raw/*32.6*/("""
            
""")))})),format.raw/*34.2*/("""
    

"""))}
    }
    
    def render(form:Form[Application.Login]): play.api.templates.HtmlFormat.Appendable = apply(form)
    
    def f:((Form[Application.Login]) => play.api.templates.HtmlFormat.Appendable) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Nov 14 16:20:49 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/login.scala.html
                    HASH: af45dc3be29b0231297b9630cd6c6fad70bc453f
                    MATRIX: 791->1|916->32|954->36|975->49|1014->51|1063->66|1077->72|1123->110|1162->112|1264->179|1296->203|1335->205|1420->254|1433->258|1475->278|1534->305|1589->324|1627->353|1667->355|1753->405|1767->410|1804->425|1863->452|1994->547|2016->560|2044->566|2305->796|2351->811
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|33->5|37->9|37->9|37->9|39->11|39->11|39->11|41->13|43->15|43->15|43->15|45->17|45->17|45->17|47->19|50->22|50->22|50->22|60->32|62->34
                    -- GENERATED --
                */
            