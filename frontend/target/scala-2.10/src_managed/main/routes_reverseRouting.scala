// @SOURCE:/Users/ironstone/Documents/workspace/NasaPracticum/frontend/conf/routes
// @HASH:fea213d5e9fed34e0c544acaf4062bc6b61a54d2
// @DATE:Mon Dec 01 11:14:30 PST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:29
class ReverseAssets {
    

// @LINE:29
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseGraphDisplay {
    

// @LINE:17
def getCoPublicationGraphDataByTopic(parameter:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoPublicationGraphDataByTopic/" + implicitly[PathBindable[String]].unbind("parameter", dynamicString(parameter)))
}
                                                

// @LINE:23
def mapSchoolsLocation(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "mapSchoolsLocation")
}
                                                

// @LINE:18
def getCoAuthorGraphDataByAuthor(parameter:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoAuthorGraphDataByAuthor/" + implicitly[PathBindable[String]].unbind("parameter", dynamicString(parameter)))
}
                                                

// @LINE:15
def getCoAuthorGraphDataByTopic(parameter:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoAuthorGraphDataByTopic/" + implicitly[PathBindable[String]].unbind("parameter", dynamicString(parameter)))
}
                                                

// @LINE:22
def getSchoolsByTopic(topic:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getSchoolsByTopic/" + implicitly[PathBindable[String]].unbind("topic", dynamicString(topic)))
}
                                                

// @LINE:14
def graphDisplay(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "graphDisplay")
}
                                                

// @LINE:16
def getAuthorPublicationGraphDataByTopic(parameter:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAuthorPublicationGraphDataByTopic/" + implicitly[PathBindable[String]].unbind("parameter", dynamicString(parameter)))
}
                                                

// @LINE:19
def getAuthorPublicationGraphDataByAuthor(parameter:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAuthorPublicationGraphDataByAuthor/" + implicitly[PathBindable[String]].unbind("parameter", dynamicString(parameter)))
}
                                                
    
}
                          

// @LINE:26
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:10
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:26
def javascriptRoutes(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/js/routes")
}
                                                

// @LINE:9
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          
}
                  


// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:29
class ReverseAssets {
    

// @LINE:29
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseGraphDisplay {
    

// @LINE:17
def getCoPublicationGraphDataByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getCoPublicationGraphDataByTopic",
   """
      function(parameter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoPublicationGraphDataByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parameter", encodeURIComponent(parameter))})
      }
   """
)
                        

// @LINE:23
def mapSchoolsLocation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.mapSchoolsLocation",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mapSchoolsLocation"})
      }
   """
)
                        

// @LINE:18
def getCoAuthorGraphDataByAuthor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getCoAuthorGraphDataByAuthor",
   """
      function(parameter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoAuthorGraphDataByAuthor/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parameter", encodeURIComponent(parameter))})
      }
   """
)
                        

// @LINE:15
def getCoAuthorGraphDataByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getCoAuthorGraphDataByTopic",
   """
      function(parameter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoAuthorGraphDataByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parameter", encodeURIComponent(parameter))})
      }
   """
)
                        

// @LINE:22
def getSchoolsByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getSchoolsByTopic",
   """
      function(topic) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getSchoolsByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("topic", encodeURIComponent(topic))})
      }
   """
)
                        

// @LINE:14
def graphDisplay : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.graphDisplay",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "graphDisplay"})
      }
   """
)
                        

// @LINE:16
def getAuthorPublicationGraphDataByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getAuthorPublicationGraphDataByTopic",
   """
      function(parameter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAuthorPublicationGraphDataByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parameter", encodeURIComponent(parameter))})
      }
   """
)
                        

// @LINE:19
def getAuthorPublicationGraphDataByAuthor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphDisplay.getAuthorPublicationGraphDataByAuthor",
   """
      function(parameter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAuthorPublicationGraphDataByAuthor/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parameter", encodeURIComponent(parameter))})
      }
   """
)
                        
    
}
              

// @LINE:26
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:10
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:26
def javascriptRoutes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.javascriptRoutes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/js/routes"})
      }
   """
)
                        

// @LINE:9
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:29
class ReverseAssets {
    

// @LINE:29
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
class ReverseGraphDisplay {
    

// @LINE:17
def getCoPublicationGraphDataByTopic(parameter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getCoPublicationGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoPublicationGraphDataByTopic", Seq(classOf[String]), "GET", """""", _prefix + """getCoPublicationGraphDataByTopic/$parameter<[^/]+>""")
)
                      

// @LINE:23
def mapSchoolsLocation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.mapSchoolsLocation(), HandlerDef(this, "controllers.GraphDisplay", "mapSchoolsLocation", Seq(), "GET", """""", _prefix + """mapSchoolsLocation""")
)
                      

// @LINE:18
def getCoAuthorGraphDataByAuthor(parameter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getCoAuthorGraphDataByAuthor(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoAuthorGraphDataByAuthor", Seq(classOf[String]), "GET", """""", _prefix + """getCoAuthorGraphDataByAuthor/$parameter<[^/]+>""")
)
                      

// @LINE:15
def getCoAuthorGraphDataByTopic(parameter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getCoAuthorGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getCoAuthorGraphDataByTopic", Seq(classOf[String]), "GET", """""", _prefix + """getCoAuthorGraphDataByTopic/$parameter<[^/]+>""")
)
                      

// @LINE:22
def getSchoolsByTopic(topic:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getSchoolsByTopic(topic), HandlerDef(this, "controllers.GraphDisplay", "getSchoolsByTopic", Seq(classOf[String]), "GET", """ Map Schools""", _prefix + """getSchoolsByTopic/$topic<[^/]+>""")
)
                      

// @LINE:14
def graphDisplay(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.graphDisplay(), HandlerDef(this, "controllers.GraphDisplay", "graphDisplay", Seq(), "GET", """ Graph Display""", _prefix + """graphDisplay""")
)
                      

// @LINE:16
def getAuthorPublicationGraphDataByTopic(parameter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getAuthorPublicationGraphDataByTopic(parameter), HandlerDef(this, "controllers.GraphDisplay", "getAuthorPublicationGraphDataByTopic", Seq(classOf[String]), "GET", """""", _prefix + """getAuthorPublicationGraphDataByTopic/$parameter<[^/]+>""")
)
                      

// @LINE:19
def getAuthorPublicationGraphDataByAuthor(parameter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphDisplay.getAuthorPublicationGraphDataByAuthor(parameter), HandlerDef(this, "controllers.GraphDisplay", "getAuthorPublicationGraphDataByAuthor", Seq(classOf[String]), "GET", """""", _prefix + """getAuthorPublicationGraphDataByAuthor/$parameter<[^/]+>""")
)
                      
    
}
                          

// @LINE:26
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:10
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:26
def javascriptRoutes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.javascriptRoutes(), HandlerDef(this, "controllers.Application", "javascriptRoutes", Seq(), "GET", """ Map route to the javascript which contains the routes used by the AJAX calls""", _prefix + """assets/js/routes""")
)
                      

// @LINE:9
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """ Login and authentication""", _prefix + """login""")
)
                      
    
}
                          
}
        
    