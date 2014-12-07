
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
object header extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*2.2*/():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*2.4*/("""
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" style="padding-bottom: 0;" href="/">
        <img src='"""),_display_(Seq[Any](/*7.20*/routes/*7.26*/.Assets.at("images/logo.png"))),format.raw/*7.55*/("""' style="height: 28px;">
      </a>
    </div>
    <div class="navbar-collapse collapse" style="height:">
      <ul class="nav navbar-nav navbar-right">

        <li><a href=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Application.index())),format.raw/*13.49*/("""">Home</a></li>

        """),_display_(Seq[Any](/*15.10*/if(!session.get("email"))/*15.35*/{_display_(Seq[Any](format.raw/*15.36*/("""
        <form class="navbar-form navbar-right" action=""""),_display_(Seq[Any](/*16.57*/routes/*16.63*/.Application.login())),format.raw/*16.83*/("""">
          <button type="submit" class="btn btn-link">Log in</button>
        </form>
        """)))}/*19.10*/else/*19.14*/{_display_(Seq[Any](format.raw/*19.15*/("""
          <form class="navbar-form navbar-right" action=""""),_display_(Seq[Any](/*20.59*/routes/*20.65*/.Application.logout())),format.raw/*20.86*/("""">
          <button type="submit" class="btn btn-link">Log out</button>
        </form>
        """)))})),format.raw/*23.10*/("""

      </ul>
    </div>
  </div>
</div>
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Dec 01 11:14:31 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/header.scala.html
                    HASH: 90c699494b867f74ac1705911bf685d4781add04
                    MATRIX: 768->2|863->4|1114->220|1128->226|1178->255|1390->431|1405->437|1447->457|1509->483|1543->508|1582->509|1675->566|1690->572|1732->592|1848->689|1861->693|1900->694|1995->753|2010->759|2053->780|2183->878
                    LINES: 26->2|29->2|34->7|34->7|34->7|40->13|40->13|40->13|42->15|42->15|42->15|43->16|43->16|43->16|46->19|46->19|46->19|47->20|47->20|47->20|50->23
                    -- GENERATED --
                */
            