
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
object mapSchoolsLocation extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>

<html>
  <head>

    <title>Map Location</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*11.35*/routes/*11.41*/.Assets.at("stylesheets/bootstrap.css"))),format.raw/*11.80*/("""'>
    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*12.35*/routes/*12.41*/.Assets.at("stylesheets/navbar-fixed-top.css"))),format.raw/*12.87*/("""'>
    <link rel="stylesheet" href='"""),_display_(Seq[Any](/*13.35*/routes/*13.41*/.Assets.at("stylesheets/custom.css"))),format.raw/*13.77*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(Seq[Any](/*14.50*/routes/*14.56*/.Assets.at("stylesheets/main.css"))),format.raw/*14.90*/("""'>
    <link rel="shortcut icon" type="image/png" href='"""),_display_(Seq[Any](/*15.55*/routes/*15.61*/.Assets.at("images/favicon.png"))),format.raw/*15.93*/("""'>

    <link href='"""),_display_(Seq[Any](/*17.18*/routes/*17.24*/.Assets.at("stylesheets/bootstrap-editable.css"))),format.raw/*17.72*/("""' rel="stylesheet" />

    <script src='"""),_display_(Seq[Any](/*19.19*/routes/*19.25*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*19.70*/("""' type="text/javascript"></script>
    <script src='"""),_display_(Seq[Any](/*20.19*/routes/*20.25*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*20.67*/("""' type="text/javascript"></script>
    <script src='"""),_display_(Seq[Any](/*21.19*/routes/*21.25*/.Assets.at("javascripts/bootstrap-editable.min.js"))),format.raw/*21.76*/("""'></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src='"""),_display_(Seq[Any](/*24.19*/routes/*24.25*/.Assets.at("js/maplocation.js"))),format.raw/*24.56*/("""' type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*25.19*/controllers/*25.30*/.routes.Application.javascriptRoutes())),format.raw/*25.68*/("""" type="text/javascript"></script>

  </head>

  <body>
  
  	"""),_display_(Seq[Any](/*31.5*/header())),format.raw/*31.13*/("""

    <div class="container">
    	<div id="searchbar">
    	    <form id="searchbarform" action="">
    	    	<p> Topic <input type="text" class="text-input" id="searchtopic" value="" /><button type="button" id="buttonsearchtopic" class="btn btn-primary">Search</button></p>
    	    </form>
    	</div>
    	<div id="map3d" style="height: 400px; width: 600px;"></div>

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
</html>"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Dec 04 00:29:07 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/mapSchoolsLocation.scala.html
                    HASH: 002a92a032e713718632a32833a63027ab5bf558
                    MATRIX: 868->0|1165->261|1180->267|1241->306|1314->343|1329->349|1397->395|1470->432|1485->438|1543->474|1631->526|1646->532|1702->566|1795->623|1810->629|1864->661|1921->682|1936->688|2006->736|2083->777|2098->783|2165->828|2254->881|2269->887|2333->929|2422->982|2437->988|2510->1039|2732->1225|2747->1231|2800->1262|2889->1315|2909->1326|2969->1364|3067->1427|3097->1435
                    LINES: 29->1|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|45->17|45->17|45->17|47->19|47->19|47->19|48->20|48->20|48->20|49->21|49->21|49->21|52->24|52->24|52->24|53->25|53->25|53->25|59->31|59->31
                    -- GENERATED --
                */
            