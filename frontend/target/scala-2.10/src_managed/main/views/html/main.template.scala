
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.32*/(""" 

<!DOCTYPE html>

<html>
  <head>

    <title>"""),_display_(Seq[Any](/*8.13*/title)),format.raw/*8.18*/("""</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*13.35*/routes/*13.41*/.Assets.at("stylesheets/bootstrap.css"))),format.raw/*13.80*/("""'>
    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*14.35*/routes/*14.41*/.Assets.at("stylesheets/navbar-fixed-top.css"))),format.raw/*14.87*/("""'>
    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*15.35*/routes/*15.41*/.Assets.at("stylesheets/custom.css"))),format.raw/*15.77*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(Seq[Any](/*16.50*/routes/*16.56*/.Assets.at("stylesheets/main.css"))),format.raw/*16.90*/("""'>
    <link rel="shortcut icon" type="image/png" href='"""),_display_(Seq[Any](/*17.55*/routes/*17.61*/.Assets.at("images/favicon.png"))),format.raw/*17.93*/("""'>

    <link href='"""),_display_(Seq[Any](/*19.18*/routes/*19.24*/.Assets.at("stylesheets/bootstrap-editable.css"))),format.raw/*19.72*/("""' rel="stylesheet" />

    <script src='"""),_display_(Seq[Any](/*21.19*/routes/*21.25*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*21.70*/("""' type="text/javascript"></script>
    <script src='"""),_display_(Seq[Any](/*22.19*/routes/*22.25*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*22.67*/("""' type="text/javascript"></script>
    <script src='"""),_display_(Seq[Any](/*23.19*/routes/*23.25*/.Assets.at("javascripts/bootstrap-editable.min.js"))),format.raw/*23.76*/("""'></script>

  </head>

  <body>

    """),_display_(Seq[Any](/*29.6*/header())),format.raw/*29.14*/("""

    <div class="container">

      """),_display_(Seq[Any](/*33.8*/content)),format.raw/*33.15*/("""

      <!-- Footer -->
      <div class="footer">
        <div class="row">
          <div class="col-lg-4 col-lg-offset-8">
            <p class="text-muted" style="text-align: right; font-size: 14px;">&copy; Carnegie Mellon University. Silicon Valley. 2014</p>
          </div>
        </div>
      </div>

    </div>

  </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Nov 14 16:20:49 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/main.scala.html
                    HASH: e81e9987ee7c0b277db84af5cb2d8578f3c4ee9b
                    MATRIX: 778->1|902->31|986->80|1012->85|1252->289|1267->295|1328->334|1401->371|1416->377|1484->423|1557->460|1572->466|1630->502|1718->554|1733->560|1789->594|1882->651|1897->657|1951->689|2008->710|2023->716|2093->764|2170->805|2185->811|2252->856|2341->909|2356->915|2420->957|2509->1010|2524->1016|2597->1067|2671->1106|2701->1114|2774->1152|2803->1159
                    LINES: 26->1|29->1|36->8|36->8|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|47->19|47->19|47->19|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|57->29|57->29|61->33|61->33
                    -- GENERATED --
                */
            