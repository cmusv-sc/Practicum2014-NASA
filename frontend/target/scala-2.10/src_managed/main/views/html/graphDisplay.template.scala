
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
object graphDisplay extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import com.google.gson.Gson


Seq[Any](format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main("Graph Generation")/*6.26*/ {_display_(Seq[Any](format.raw/*6.28*/("""
<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>CoAuthor Networks: Interactive Network</title>
  <meta name="description" content="Interactive Network Visualization.">
  <meta name="author" content="">

  <meta name="viewport" content="width=device-width,initial-scale=1">

  <!-- CSS concatenated and minified via ant build script-->
  <link rel="stylesheet" href='"""),_display_(Seq[Any](/*23.33*/routes/*23.39*/.Assets.at("css/reset.css"))),format.raw/*23.66*/("""'>
  <link rel="stylesheet" href='"""),_display_(Seq[Any](/*24.33*/routes/*24.39*/.Assets.at("css/bootstrap.min.css"))),format.raw/*24.74*/("""'>
  <link rel="stylesheet" href='"""),_display_(Seq[Any](/*25.33*/routes/*25.39*/.Assets.at("css/style.css"))),format.raw/*25.66*/("""'>
  <!-- end CSS-->

  <script src='"""),_display_(Seq[Any](/*28.17*/routes/*28.23*/.Assets.at("js/libs/modernizr-2.0.6.min.js"))),format.raw/*28.67*/("""'></script>
  <script>
  $(document).ready(function() """),format.raw/*30.32*/("""{"""),format.raw/*30.33*/("""
	    function close_accordion_section() """),format.raw/*31.41*/("""{"""),format.raw/*31.42*/("""
	        $('.accordion .accordion-section-title').removeClass('active');
	        $('.accordion .accordion-section-content').slideUp(300).removeClass('open');
	    """),format.raw/*34.6*/("""}"""),format.raw/*34.7*/("""
	 
	    $('.accordion-section-title').click(function(e) """),format.raw/*36.54*/("""{"""),format.raw/*36.55*/("""
	        // Grab current anchor value
	        var currentAttrValue = $(this).attr('href');
	 
	        if($(e.target).is('.active')) """),format.raw/*40.40*/("""{"""),format.raw/*40.41*/("""
	            close_accordion_section();
	        """),format.raw/*42.10*/("""}"""),format.raw/*42.11*/("""else """),format.raw/*42.16*/("""{"""),format.raw/*42.17*/("""
	            close_accordion_section();
	 
	            // Add active class to section title
	            $(this).addClass('active');
	            // Open up the hidden content panel
	            $('.accordion ' + currentAttrValue).slideDown(300).addClass('open'); 
	        """),format.raw/*49.10*/("""}"""),format.raw/*49.11*/("""
	 
	        e.preventDefault();
	    """),format.raw/*52.6*/("""}"""),format.raw/*52.7*/(""");
	"""),format.raw/*53.2*/("""}"""),format.raw/*53.3*/(""");
  </script>
</head>

<body>

  <div id="container" class="container">
    <header>
    <h1>Scientific Collaboration Network</h1>
    <p>Researchers have been collaborating to make scientific breakthroughs. In this project, we aim to visualize and analyze 
    researchers' past collaboration history and base on that, help to recommend potential collaborators.(Below is a sample co-author graph for cloud computing)
    </p>
    </header>
    <div class="accordion">
    <div class="accordion-section">
        <a class="accordion-section-title" href="#accordion-1">Search Panel</a>
         
        <div id="accordion-1" class="accordion-section-content">
            
              <form name="searchmodal">
<input type="radio" name="search_by" value="Topic" checked onClick="toggleDiv('bytopic','byauthor')"><b><i>&nbsp;&nbsp;TOPIC</i></b>
<br>
<div name="topic_search"  id="bytopic" style = "float:right; width: 70%;">
	<p class="search_title">Enter Topics(Multiple topics separated by comma):  <input type="text" class="text-input" id="topictextbox" value="" /></p>
    <div id = "sortbytopic"><p>Sort by:
    <select id="sort_select_topic">
    	<option value="Publication">Publication Count</option>
        <option value="Citation">Citation Count</option>
    </select></p></div>
    <p>Limit to:
    <select id="limit_select_topic">
    	<option value="10">Less than 10</option>
        <option value="30">Less than 30</option>
        <option value="50">Less than 50</option>
        <option value="100">Less than 100</option>
    </select></p>
    <p id="trust_p"><input name="trust" type="checkbox">Add Trust Score</p>
</div>
      
<input type="radio" name="search_by" value="Author" onClick="toggleDiv('byauthor','bytopic')"><b><i>&nbsp;&nbsp;AUTHOR</i></b>
<div name="Author_search" style="display:none;float:right; width: 70%;" id="byauthor">
	<p class="search_title"> Enter Author Name:  <input type="text" class="text-input" id="author" value="" /></p>
    <p>Sort by:
    <select id="sort_select_author">
    	<option value="Publication">Publication Count</option>
        <option value="Citation">Citation Count</option>
    </select></p>
    <p>Limit to:
    	<select id="limit_select_author">
        	<option value="10">Less than 10</option>
        	<option value="30">Less than 30</option>
          	<option value="50">Less than 50</option>
          	<option value="100">Less than 100</option>
        </select>
    </p>
</div>
      
</form>
 <div class="searchfooter" style="float:right; width: 70%;">
       
        <button type="button" id="searchbtn" class="btn btn-primary">Search</button>
      </div>
 </div>
            
            </div><!--end .accordion-section-content-->
    </div><!--end .accordion-section-->
</div><!--end .accordion-->
<!--  <div class="modal-body">
      <form name="searchmodal">
<input type="radio" name="search_by" value="Topic" checked onClick="toggleDiv('bytopic','byauthor')"><b><i>&nbsp;&nbsp;TOPIC</i></b>
<br>
<div name="topic_search"  id="bytopic" style = "float:right; width: 70%;">
	<p class="search_title">Enter Topics(Multiple topics separated by comma):  <input type="text" class="text-input" id="topictextbox" value="" /></p>
    <div id = "sortbytopic"><p>Sort by:
    <select id="sort_select_topic">
    	<option value="Publication">Publication Count</option>
        <option value="Citation">Citation Count</option>
    </select></p></div>
    <p>Limit to:
    <select id="limit_select_topic">
    	<option value="10">Less than 10</option>
        <option value="30">Less than 30</option>
        <option value="50">Less than 50</option>
        <option value="100">Less than 100</option>
    </select></p>
    <p><input name="trust" type="checkbox">Add Trust Score</p>
</div>
      
<input type="radio" name="search_by" value="Author" onClick="toggleDiv('byauthor','bytopic')"><b><i>&nbsp;&nbsp;AUTHOR</i></b>
<div name="Author_search" style="display:none;float:right; width: 70%;" id="byauthor">
	<p class="search_title"> Enter Author Name:  <input type="text" class="text-input" id="author" value="" /></p>
    <p>Sort by:
    <select id="sort_select_author">
    	<option value="Publication">Publication Count</option>
        <option value="Citation">Citation Count</option>
    </select></p>
    <p>Limit to:
    	<select id="limit_select_author">
        	<option value="10">Less than 10</option>
        	<option value="30">Less than 30</option>
          	<option value="50">Less than 50</option>
          	<option value="100">Less than 100</option>
        </select>
    </p>
</div>
      
</form>
 <div class="searchfooter" style="float:right; width: 70%;">
       
        <button type="button" id="searchbtn" class="btn btn-primary">Search</button>
      </div>
 </div> -->
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<SCRIPT LANGUAGE="JavaScript">
function fullName(form) """),format.raw/*168.25*/("""{"""),format.raw/*168.26*/("""
    for (var i = 0; i < form.search_by.length; i++) """),format.raw/*169.53*/("""{"""),format.raw/*169.54*/("""
        if (form.search_by[i].checked) """),format.raw/*170.40*/("""{"""),format.raw/*170.41*/("""
            break
        """),format.raw/*172.9*/("""}"""),format.raw/*172.10*/("""
    """),format.raw/*173.5*/("""}"""),format.raw/*173.6*/("""
    alert("You chose " + form.search_by[i].value + ".")
    document.getElementById('bytopic').style.display = "block";
"""),format.raw/*176.1*/("""}"""),format.raw/*176.2*/("""
</SCRIPT>
      
     
      <!-- <div id="song_selection" class="control">
        <h3>Topic</h3>
        <select id="song_select">
          <option value="auth_cloud_computing.json">Big Data</option>
          <option value="auth_mach_learng.json">Machine Learning</option>
          <option value="Cloud Computing">Cloud Computing</option>
          <option value="All">All</option>
        </select>

      </div> -->
    <div id="controls">
      <!--  <div id="layouts" class="control">
        <h3>Layout</h3>
        <a id="force" class="active">Force Directed Network</a>
        <a id="radial">Authors</a>
      </div>
      <div id="filters" class="control">
        <h3>Filter</h3>
        <a id="all" class="active">All</a>
        <a id="popular">Expert</a>
        <a id="obscure">Amateur</a>
      </div>  -->
    
      <div id="coauthorgraphdiv" class="control">
        <h3>Type of Graph</h3>
        <a id="coauthor" class="active">Author Network</a>
      </div> 
      <div id="authorpublicationgraphdiv" class="control">
      <h3>.</h3>
        <a id="authorpublication">Author and Publication Network</a>
      </div>
     <!-- <div id="copublicationgraphdiv" class="control">
       <h3>.</h3>
        <a id="copublication">Publication Network</a>
      </div> -->
      <div id="search_section" class="control">
        <form id="search_form" action=""  method="post">
          <p class="search_title">Graph Node: <input type="text" class="text-input" id="search" value="" /></p>
        </form>
      </div> 
      <div id="mapview" class="control">
      <h3>Map View</h3>
        <a id="map" href="/mapSchoolsLocation">Map Institutes</a>
      </div>
    <div id="main"  role="main">
    	<div id="vis" style = "float:left; width: 50%;"></div>
      	<div id="loadingimagediv" style = "float:left; height: 5%; width: 5%;" hidden="true">
      		<img src='"""),_display_(Seq[Any](/*227.20*/routes/*227.26*/.Assets.at("images/loading.gif"))),format.raw/*227.58*/("""'>
      	</div>
        <div style = "float:right; width: 40%;" id="rightTopSidebar"></div >
    </div>
    
    
  </div> <!--! end of #container -->


  <!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> -->
  <script>window.jQuery || document.write("<script src='"""),_display_(Seq[Any](/*237.58*/routes/*237.64*/.Assets.at("js/libs/jquery-1.7.2.min.js"))),format.raw/*237.105*/("""'><\/script>")</script>


  <script defer src='"""),_display_(Seq[Any](/*240.23*/routes/*240.29*/.Assets.at("js/plugins.js"))),format.raw/*240.56*/("""'></script>
  <script defer src='"""),_display_(Seq[Any](/*241.23*/routes/*241.29*/.Assets.at("js/script.js"))),format.raw/*241.55*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*242.17*/routes/*242.23*/.Assets.at("js/libs/coffee-script.js"))),format.raw/*242.61*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*243.17*/routes/*243.23*/.Assets.at("js/libs/d3.v2.js"))),format.raw/*243.53*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*244.17*/routes/*244.23*/.Assets.at("js/Tooltip.js"))),format.raw/*244.50*/("""'></script>
  <script src='"""),_display_(Seq[Any](/*245.17*/routes/*245.23*/.Assets.at("js/vis.js"))),format.raw/*245.46*/("""'></script>
  <script src=""""),_display_(Seq[Any](/*246.17*/controllers/*246.28*/.routes.Application.javascriptRoutes())),format.raw/*246.66*/("""" type="text/javascript">
  <!--<script type="text/coffeescript" src="coffee/vis.coffee"></script>!-->

  <script> // Change UA-XXXXX-X to be your site's ID
    window._gaq = [['_setAccount','UAXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];
    Modernizr.load("""),format.raw/*251.20*/("""{"""),format.raw/*251.21*/("""
      load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
    """),format.raw/*253.5*/("""}"""),format.raw/*253.6*/(""");
  </script>


  <!--[if lt IE 7 ]>
    <script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
    <script>window.attachEvent('onload',function()"""),format.raw/*259.51*/("""{"""),format.raw/*259.52*/("""CFInstall.check("""),format.raw/*259.68*/("""{"""),format.raw/*259.69*/("""mode:'overlay'"""),format.raw/*259.83*/("""}"""),format.raw/*259.84*/(""")"""),format.raw/*259.85*/("""}"""),format.raw/*259.86*/(""")</script>
  <![endif]-->
  
</body>
</html>

""")))})),format.raw/*265.2*/("""
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Dec 04 05:17:22 PST 2014
                    SOURCE: /Users/ironstone/Documents/workspace/NasaPracticum/frontend/app/views/graphDisplay.scala.html
                    HASH: c25aaf06e6e93efc1d67dac6a280a69ee495ea1d
                    MATRIX: 907->48|943->50|975->74|1014->76|1780->806|1795->812|1844->839|1915->874|1930->880|1987->915|2058->950|2073->956|2122->983|2196->1021|2211->1027|2277->1071|2359->1125|2388->1126|2457->1167|2486->1168|2678->1333|2706->1334|2791->1391|2820->1392|2983->1527|3012->1528|3090->1578|3119->1579|3152->1584|3181->1585|3485->1861|3514->1862|3579->1900|3607->1901|3638->1905|3666->1906|8621->6832|8651->6833|8733->6886|8763->6887|8832->6927|8862->6928|8917->6955|8947->6956|8980->6961|9009->6962|9158->7083|9187->7084|11113->8973|11129->8979|11184->9011|11526->9316|11542->9322|11607->9363|11692->9411|11708->9417|11758->9444|11829->9478|11845->9484|11894->9510|11959->9538|11975->9544|12036->9582|12101->9610|12117->9616|12170->9646|12235->9674|12251->9680|12301->9707|12366->9735|12382->9741|12428->9764|12493->9792|12514->9803|12575->9841|12873->10110|12903->10111|13032->10212|13061->10213|13274->10397|13304->10398|13349->10414|13379->10415|13422->10429|13452->10430|13482->10431|13512->10432|13591->10479
                    LINES: 32->5|33->6|33->6|33->6|50->23|50->23|50->23|51->24|51->24|51->24|52->25|52->25|52->25|55->28|55->28|55->28|57->30|57->30|58->31|58->31|61->34|61->34|63->36|63->36|67->40|67->40|69->42|69->42|69->42|69->42|76->49|76->49|79->52|79->52|80->53|80->53|195->168|195->168|196->169|196->169|197->170|197->170|199->172|199->172|200->173|200->173|203->176|203->176|254->227|254->227|254->227|264->237|264->237|264->237|267->240|267->240|267->240|268->241|268->241|268->241|269->242|269->242|269->242|270->243|270->243|270->243|271->244|271->244|271->244|272->245|272->245|272->245|273->246|273->246|273->246|278->251|278->251|280->253|280->253|286->259|286->259|286->259|286->259|286->259|286->259|286->259|286->259|292->265
                    -- GENERATED --
                */
            