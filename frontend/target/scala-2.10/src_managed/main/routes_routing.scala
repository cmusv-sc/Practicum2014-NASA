// @SOURCE:/Users/ironstone/Documents/workspace/NasaPracticum/frontend/conf/routes
// @HASH:fea213d5e9fed34e0c544acaf4062bc6b61a54d2
// @DATE:Mon Dec 01 11:14:30 PST 2014


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
private[this] lazy val controllers_Application_login1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:10
private[this] lazy val controllers_Application_authenticate2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:11
private[this] lazy val controllers_Application_logout3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:14
private[this] lazy val controllers_GraphDisplay_graphDisplay4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("graphDisplay"))))
        

// @LINE:15
private[this] lazy val controllers_GraphDisplay_getCoAuthorGraphDataByTopic5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorGraphDataByTopic/"),DynamicPart("parameter", """[^/]+""",true))))
        

// @LINE:16
private[this] lazy val controllers_GraphDisplay_getAuthorPublicationGraphDataByTopic6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAuthorPublicationGraphDataByTopic/"),DynamicPart("parameter", """[^/]+""",true))))
        

// @LINE:17
private[this] lazy val controllers_GraphDisplay_getCoPublicationGraphDataByTopic7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoPublicationGraphDataByTopic/"),DynamicPart("parameter", """[^/]+""",true))))
        

// @LINE:18
private[this] lazy val controllers_GraphDisplay_getCoAuthorGraphDataByAuthor8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorGraphDataByAuthor/"),DynamicPart("parameter", """[^/]+""",true))))
        

// @LINE:19
private[this] lazy val controllers_GraphDisplay_getAuthorPublicationGraphDataByAuthor9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAuthorPublicationGraphDataByAuthor/"),DynamicPart("parameter", """[^/]+""",true))))
        

// @LINE:22
private[this] lazy val controllers_GraphDisplay_getSchoolsByTopic10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getSchoolsByTopic/"),DynamicPart("topic", """[^/]+""",true))))
        

// @LINE:23
private[this] lazy val controllers_GraphDisplay_mapSchoolsLocation11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("mapSchoolsLocation"))))
        

// @LINE:26
private[this] lazy val controllers_Application_javascriptRoutes12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/js/routes"))))
        

// @LINE:29
private[this] lazy val controllers_Assets_at13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """graphDisplay""","""controllers.GraphDisplay.graphDisplay()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorGraphDataByTopic/$parameter<[^/]+>""","""controllers.GraphDisplay.getCoAuthorGraphDataByTopic(parameter:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAuthorPublicationGraphDataByTopic/$parameter<[^/]+>""","""controllers.GraphDisplay.getAuthorPublicationGraphDataByTopic(parameter:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoPublicationGraphDataByTopic/$parameter<[^/]+>""","""controllers.GraphDisplay.getCoPublicationGraphDataByTopic(parameter:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorGraphDataByAuthor/$parameter<[^/]+>""","""controllers.GraphDisplay.getCoAuthorGraphDataByAuthor(parameter:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAuthorPublicationGraphDataByAuthor/$parameter<[^/]+>""","""controllers.GraphDisplay.getAuthorPublicationGraphDataByAuthor(parameter:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getSchoolsByTopic/$topic<[^/]+>""","""controllers.GraphDisplay.getSchoolsByTopic(topic:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """mapSchoolsLocation""","""controllers.GraphDisplay.mapSchoolsLocation()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/js/routes""","""controllers.Application.javascriptRoutes()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"GET", """ Login and authentication""", Routes.prefix + """login"""))
   }
}
        

// @LINE:10
case controllers_Application_authenticate2(params) => {
   call { 
        invokeHandler(controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Nil,"POST", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:11
case controllers_Application_logout3(params) => {
   call { 
        invokeHandler(controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:14
case controllers_GraphDisplay_graphDisplay4(params) => {
   call { 
        invokeHandler(controllers.GraphDisplay.graphDisplay(), HandlerDef(this, "controllers.GraphDisplay", "graphDisplay", Nil,"GET", """ Graph Display""", Routes.prefix + """graphDisplay"""))
   }
}
        

// @LINE:15
case controllers_GraphDisplay_getCoAuthorGraphDataByTopic5(params) => {
   call(params.fromPath[String]("parameter", None)) { (parameter) =>
        invokeHandler(controllers.GraphDisplay.getCoAuthorGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoAuthorGraphDataByTopic", Seq(classOf[String]),"GET", """""", Routes.prefix + """getCoAuthorGraphDataByTopic/$parameter<[^/]+>"""))
   }
}
        

// @LINE:16
case controllers_GraphDisplay_getAuthorPublicationGraphDataByTopic6(params) => {
   call(params.fromPath[String]("parameter", None)) { (parameter) =>
        invokeHandler(controllers.GraphDisplay.getAuthorPublicationGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getAuthorPublicationGraphDataByTopic", Seq(classOf[String]),"GET", """""", Routes.prefix + """getAuthorPublicationGraphDataByTopic/$parameter<[^/]+>"""))
   }
}
        

// @LINE:17
case controllers_GraphDisplay_getCoPublicationGraphDataByTopic7(params) => {
   call(params.fromPath[String]("parameter", None)) { (parameter) =>
        invokeHandler(controllers.GraphDisplay.getCoPublicationGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoPublicationGraphDataByTopic", Seq(classOf[String]),"GET", """""", Routes.prefix + """getCoPublicationGraphDataByTopic/$parameter<[^/]+>"""))
   }
}
        

// @LINE:18
case controllers_GraphDisplay_getCoAuthorGraphDataByAuthor8(params) => {
   call(params.fromPath[String]("parameter", None)) { (parameter) =>
        invokeHandler(controllers.GraphDisplay.getCoAuthorGraphDataByAuthor(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoAuthorGraphDataByAuthor", Seq(classOf[String]),"GET", """""", Routes.prefix + """getCoAuthorGraphDataByAuthor/$parameter<[^/]+>"""))
   }
}
        

// @LINE:19
case controllers_GraphDisplay_getAuthorPublicationGraphDataByAuthor9(params) => {
   call(params.fromPath[String]("parameter", None)) { (parameter) =>
        invokeHandler(controllers.GraphDisplay.getAuthorPublicationGraphDataByAuthor(parameter), HandlerDef(this, "controllers.GraphDisplay", "getAuthorPublicationGraphDataByAuthor", Seq(classOf[String]),"GET", """""", Routes.prefix + """getAuthorPublicationGraphDataByAuthor/$parameter<[^/]+>"""))
   }
}
        

// @LINE:22
case controllers_GraphDisplay_getSchoolsByTopic10(params) => {
   call(params.fromPath[String]("topic", None)) { (topic) =>
        invokeHandler(controllers.GraphDisplay.getSchoolsByTopic(topic), HandlerDef(this, "controllers.GraphDisplay", "getSchoolsByTopic", Seq(classOf[String]),"GET", """ Map Schools""", Routes.prefix + """getSchoolsByTopic/$topic<[^/]+>"""))
   }
}
        

// @LINE:23
case controllers_GraphDisplay_mapSchoolsLocation11(params) => {
   call { 
        invokeHandler(controllers.GraphDisplay.mapSchoolsLocation(), HandlerDef(this, "controllers.GraphDisplay", "mapSchoolsLocation", Nil,"GET", """""", Routes.prefix + """mapSchoolsLocation"""))
   }
}
        

// @LINE:26
case controllers_Application_javascriptRoutes12(params) => {
   call { 
        invokeHandler(controllers.Application.javascriptRoutes(), HandlerDef(this, "controllers.Application", "javascriptRoutes", Nil,"GET", """ Map route to the javascript which contains the routes used by the AJAX calls""", Routes.prefix + """assets/js/routes"""))
   }
}
        

// @LINE:29
case controllers_Assets_at13(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     