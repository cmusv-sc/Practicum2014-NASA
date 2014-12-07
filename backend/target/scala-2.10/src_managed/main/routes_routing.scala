// @SOURCE:/Users/ironstone/Documents/workspace/NasaPracticum/backend/conf/routes
// @HASH:e4d3b289f049e49d9d74235001d9f561e96b9fd5
// @DATE:Thu Dec 04 05:17:42 PST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_GraphReturnObject_getCoAuthorGraphDataByTopic1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorGraphDataByTopic/"),DynamicPart("topic", """[^/]+""",true))))
        

// @LINE:10
private[this] lazy val controllers_GraphReturnObject_getAuthorPublicationGraphDataByTopic2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAuthorPublicationGraphDataByTopic/"),DynamicPart("topic", """[^/]+""",true))))
        

// @LINE:11
private[this] lazy val controllers_GraphReturnObject_getCoAuthorGraphDataByAuthor3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorGraphDataByAuthor/"),DynamicPart("author", """[^/]+""",true))))
        

// @LINE:12
private[this] lazy val controllers_GraphReturnObject_getAuthorPublicationGraphDataByAuthor4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAuthorPublicationGraphDataByAuthor/"),DynamicPart("author", """[^/]+""",true))))
        

// @LINE:15
private[this] lazy val controllers_SchoolMap_GetSchoolsByTopic5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getSchoolsByTopic/"),DynamicPart("topic", """[^/]+""",true))))
        

// @LINE:18
private[this] lazy val controllers_Assets_at6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorGraphDataByTopic/$topic<[^/]+>""","""controllers.GraphReturnObject.getCoAuthorGraphDataByTopic(topic:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAuthorPublicationGraphDataByTopic/$topic<[^/]+>""","""controllers.GraphReturnObject.getAuthorPublicationGraphDataByTopic(topic:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorGraphDataByAuthor/$author<[^/]+>""","""controllers.GraphReturnObject.getCoAuthorGraphDataByAuthor(author:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAuthorPublicationGraphDataByAuthor/$author<[^/]+>""","""controllers.GraphReturnObject.getAuthorPublicationGraphDataByAuthor(author:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getSchoolsByTopic/$topic<[^/]+>""","""controllers.SchoolMap.GetSchoolsByTopic(topic:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_GraphReturnObject_getCoAuthorGraphDataByTopic1(params) => {
   call(params.fromPath[String]("topic", None)) { (topic) =>
        invokeHandler(controllers.GraphReturnObject.getCoAuthorGraphDataByTopic(topic), HandlerDef(this, "controllers.GraphReturnObject", "getCoAuthorGraphDataByTopic", Seq(classOf[String]),"GET", """ GraphReturnObject""", Routes.prefix + """getCoAuthorGraphDataByTopic/$topic<[^/]+>"""))
   }
}
        

// @LINE:10
case controllers_GraphReturnObject_getAuthorPublicationGraphDataByTopic2(params) => {
   call(params.fromPath[String]("topic", None)) { (topic) =>
        invokeHandler(controllers.GraphReturnObject.getAuthorPublicationGraphDataByTopic(topic), HandlerDef(this, "controllers.GraphReturnObject", "getAuthorPublicationGraphDataByTopic", Seq(classOf[String]),"GET", """""", Routes.prefix + """getAuthorPublicationGraphDataByTopic/$topic<[^/]+>"""))
   }
}
        

// @LINE:11
case controllers_GraphReturnObject_getCoAuthorGraphDataByAuthor3(params) => {
   call(params.fromPath[String]("author", None)) { (author) =>
        invokeHandler(controllers.GraphReturnObject.getCoAuthorGraphDataByAuthor(author), HandlerDef(this, "controllers.GraphReturnObject", "getCoAuthorGraphDataByAuthor", Seq(classOf[String]),"GET", """""", Routes.prefix + """getCoAuthorGraphDataByAuthor/$author<[^/]+>"""))
   }
}
        

// @LINE:12
case controllers_GraphReturnObject_getAuthorPublicationGraphDataByAuthor4(params) => {
   call(params.fromPath[String]("author", None)) { (author) =>
        invokeHandler(controllers.GraphReturnObject.getAuthorPublicationGraphDataByAuthor(author), HandlerDef(this, "controllers.GraphReturnObject", "getAuthorPublicationGraphDataByAuthor", Seq(classOf[String]),"GET", """""", Routes.prefix + """getAuthorPublicationGraphDataByAuthor/$author<[^/]+>"""))
   }
}
        

// @LINE:15
case controllers_SchoolMap_GetSchoolsByTopic5(params) => {
   call(params.fromPath[String]("topic", None)) { (topic) =>
        invokeHandler(controllers.SchoolMap.GetSchoolsByTopic(topic), HandlerDef(this, "controllers.SchoolMap", "GetSchoolsByTopic", Seq(classOf[String]),"GET", """ Map Schools""", Routes.prefix + """getSchoolsByTopic/$topic<[^/]+>"""))
   }
}
        

// @LINE:18
case controllers_Assets_at6(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     