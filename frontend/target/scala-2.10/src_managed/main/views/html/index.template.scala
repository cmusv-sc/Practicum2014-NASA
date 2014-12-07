
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](_display_(Seq[Any](/*2.2*/main("DblpFrontend")/*2.22*/{_display_(Seq[Any](format.raw/*2.23*/("""

  <div class="jumbotron">
    <div class="row">
      <div class="col-lg-6">

        <img src='"""),_display_(Seq[Any](/*8.20*/routes/*8.26*/.Assets.at("images/network.png"))),format.raw/*8.58*/("""' style="padding-bottom: 0;">

      </div>
      <div class="col-lg-6">
        <h1>Research Community Service Platform</h1>
        <p>A data driven service to recommend papers and network collaborators for the research community</p>
        <!-- TODO -->
        <a class="btn btn-lg btn-default" href="/">Get Started &raquo;</a>
        <p> Generate Scientific Collaboration Network </p>
        <a class="btn btn-lg btn-default" href="/graphDisplay"> Collaboration Network &raquo;</a>
        <!-- Button trigger modal -->
 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" style="display:none;"data-target="#myModal">
   Search Options 
</button>
      </div>
    </div>
  </div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Search By</h4>
      </div>
      <div class="modal-body">
      <form name="searchmodal">
<input type="radio" name="search_by" value="Topic" checked onClick="toggleDiv('bytopic','byauthor')"><b><i>&nbsp;&nbsp;TOPIC</i></b>
<br>
<div name="topic_search"  id="bytopic">
        <p class="search_title">Enter Topics(Multiple topics separated by comma): <input type="text" class="text-input" id="topictextbox_modal" value="" /></p>
       <p>Sort by:
        <select id="sort_select_topic_modal">
          <option value="Publication">Publication Count</option>
          <option value="Citation">Citation Count</option>
        </select></p>
      <p>Limit to:
        <select id="limit_select_topic_modal">
        <option value="10">Less than 10</option>
        <option value="30">Less than 30</option>
          <option value="50">Less than 50</option>
          <option value="100">Less than 100</option>
        </select></p>
         <p><input name="trust_modal" type="checkbox" onclick="toggleCheckbox('trustbyauthor')">Add Trust Score
         <div name="Author_trustsearch" style="display:none;" id="trustbyauthor">
         Enter Author Name:<input type="text" class="text-input" id="author" value=""></p> 
      </div>
      </div>
<input type="radio" name="search_by" value="Author" onClick="toggleDiv('byauthor','bytopic')"><b><i>&nbsp;&nbsp;AUTHOR</i></b>
<div name="Author_search" style="display:none;" id="byauthor">
        <p class="search_title"> Enter Author Name:<input type="text" class="text-input" id="author_modal" value="" /></p>
        <p>Sort by:
        <select id="sort_select_author_modal">
          <option value="Publication">Publication Count</option>
          <option value="Citation">Citation Count</option>
        </select></p>
      <p>Limit to:
        <select id="limit_select_author_modal">
        <option value="10">Less than 10</option>
        <option value="30">Less than 30</option>
          <option value="50">Less than 50</option>
          <option value="100">Less than 100</option>
        </select></p>
         <p><input name="trustauthor" type="checkbox" onclick="toggleCheckboxauthor('trustbyauthor1')">Add Trust Score
         <div name="Author_trustsearch" style="display:none;" id="trustbyauthor1">
         Enter Author Name:<input type="text" class="text-input" id="author" value=""></p> 
      </div>
      </div>
      
</form>
      
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="modalsearch" >Search</button>
      </div>
    </div>
  </div>
</div>
 <script defer src='"""),_display_(Seq[Any](/*90.22*/routes/*90.28*/.Assets.at("js/plugins.js"))),format.raw/*90.55*/("""'></script>
  <script defer src='"""),_display_(Seq[Any](/*91.23*/routes/*91.29*/.Assets.at("js/script.js"))),format.raw/*91.55*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*92.17*/routes/*92.23*/.Assets.at("js/libs/coffee-script.js"))),format.raw/*92.61*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*93.17*/routes/*93.23*/.Assets.at("js/libs/d3.v2.js"))),format.raw/*93.53*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*94.17*/routes/*94.23*/.Assets.at("js/Tooltip.js"))),format.raw/*94.50*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*95.17*/routes/*95.23*/.Assets.at("js/vis.js"))),format.raw/*95.46*/("""'></script>
 <script src=""""),_display_(Seq[Any](/*96.16*/controllers/*96.27*/.routes.Application.javascriptRoutes())),format.raw/*96.65*/("""" type="text/javascript">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
function toggleDiv(divId1,divId2) """),format.raw/*99.35*/("""{"""),format.raw/*99.36*/("""
	   $("#"+divId1).show();
	   $("#"+divId2).hide();
	   	
	"""),format.raw/*103.2*/("""}"""),format.raw/*103.3*/("""
	

	
/* document.getElementById("modalsearch").onclick = function () """),format.raw/*107.65*/("""{"""),format.raw/*107.66*/("""
    location.href = "/graphDisplay";
"""),format.raw/*109.1*/("""}"""),format.raw/*109.2*/("""; */
</script>
<SCRIPT LANGUAGE="JavaScript">
function fullName(form) """),format.raw/*112.25*/("""{"""),format.raw/*112.26*/("""
    for (var i = 0; i < form.search_by.length; i++) """),format.raw/*113.53*/("""{"""),format.raw/*113.54*/("""
        if (form.search_by[i].checked) """),format.raw/*114.40*/("""{"""),format.raw/*114.41*/("""
            break
        """),format.raw/*116.9*/("""}"""),format.raw/*116.10*/("""
    """),format.raw/*117.5*/("""}"""),format.raw/*117.6*/("""
    alert("You chose " + form.search_by[i].value + ".")
    document.getElementById('bytopic').style.display = "block";
"""),format.raw/*120.1*/("""}"""),format.raw/*120.2*/("""
function toggleCheckbox(element)
"""),format.raw/*122.1*/("""{"""),format.raw/*122.2*/("""
	if (searchmodal.trust.checked) 
		$("#"+element).hide();
	else
		$("#"+element).hide();
"""),format.raw/*127.1*/("""}"""),format.raw/*127.2*/("""
function toggleCheckboxauthor(element)
"""),format.raw/*129.1*/("""{"""),format.raw/*129.2*/("""
	if (searchmodal.trustauthor.checked) 
		$("#"+element).hide();
	else
		$("#"+element).hide();
"""),format.raw/*134.1*/("""}"""),format.raw/*134.2*/("""
</SCRIPT>
""")))})),format.raw/*136.2*/("""
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Dec 04 05:17:23 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/index.scala.html
                    HASH: 5eb07b91ffebbb50cb5a6492f5de0b4811680dc3
                    MATRIX: 864->2|892->22|930->23|1064->122|1078->128|1131->160|4994->3987|5009->3993|5058->4020|5128->4054|5143->4060|5191->4086|5255->4114|5270->4120|5330->4158|5394->4186|5409->4192|5461->4222|5525->4250|5540->4256|5589->4283|5653->4311|5668->4317|5713->4340|5776->4367|5796->4378|5856->4416|6042->4574|6071->4575|6159->4635|6188->4636|6287->4706|6317->4707|6383->4745|6412->4746|6511->4816|6541->4817|6623->4870|6653->4871|6722->4911|6752->4912|6807->4939|6837->4940|6870->4945|6899->4946|7048->5067|7077->5068|7139->5102|7168->5103|7286->5193|7315->5194|7383->5234|7412->5235|7536->5331|7565->5332|7609->5344
                    LINES: 29->2|29->2|29->2|35->8|35->8|35->8|117->90|117->90|117->90|118->91|118->91|118->91|119->92|119->92|119->92|120->93|120->93|120->93|121->94|121->94|121->94|122->95|122->95|122->95|123->96|123->96|123->96|126->99|126->99|130->103|130->103|134->107|134->107|136->109|136->109|139->112|139->112|140->113|140->113|141->114|141->114|143->116|143->116|144->117|144->117|147->120|147->120|149->122|149->122|154->127|154->127|156->129|156->129|161->134|161->134|163->136
                    -- GENERATED --
                */
            