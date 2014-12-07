// @SOURCE:/Users/ironstone/Documents/workspace/NasaPracticum/backend/conf/routes
// @HASH:e4d3b289f049e49d9d74235001d9f561e96b9fd5
// @DATE:Thu Dec 04 05:17:42 PST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:18
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:15
class ReverseSchoolMap {
    

// @LINE:15
def GetSchoolsByTopic(topic:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getSchoolsByTopic/" + implicitly[PathBindable[String]].unbind("topic", dynamicString(topic)))
}
                                                
    
}
                          

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseGraphReturnObject {
    

// @LINE:10
def getAuthorPublicationGraphDataByTopic(topic:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAuthorPublicationGraphDataByTopic/" + implicitly[PathBindable[String]].unbind("topic", dynamicString(topic)))
}
                                                

// @LINE:11
def getCoAuthorGraphDataByAuthor(author:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoAuthorGraphDataByAuthor/" + implicitly[PathBindable[String]].unbind("author", dynamicString(author)))
}
                                                

// @LINE:12
def getAuthorPublicationGraphDataByAuthor(author:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAuthorPublicationGraphDataByAuthor/" + implicitly[PathBindable[String]].unbind("author", dynamicString(author)))
}
                                                

// @LINE:9
def getCoAuthorGraphDataByTopic(topic:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoAuthorGraphDataByTopic/" + implicitly[PathBindable[String]].unbind("topic", dynamicString(topic)))
}
                                                
    
}
                          

// @LINE:18
class ReverseAssets {
    

// @LINE:18
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:18
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:15
class ReverseSchoolMap {
    

// @LINE:15
def GetSchoolsByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SchoolMap.GetSchoolsByTopic",
   """
      function(topic) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getSchoolsByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("topic", encodeURIComponent(topic))})
      }
   """
)
                        
    
}
              

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseGraphReturnObject {
    

// @LINE:10
def getAuthorPublicationGraphDataByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphReturnObject.getAuthorPublicationGraphDataByTopic",
   """
      function(topic) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAuthorPublicationGraphDataByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("topic", encodeURIComponent(topic))})
      }
   """
)
                        

// @LINE:11
def getCoAuthorGraphDataByAuthor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphReturnObject.getCoAuthorGraphDataByAuthor",
   """
      function(author) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoAuthorGraphDataByAuthor/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("author", encodeURIComponent(author))})
      }
   """
)
                        

// @LINE:12
def getAuthorPublicationGraphDataByAuthor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphReturnObject.getAuthorPublicationGraphDataByAuthor",
   """
      function(author) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAuthorPublicationGraphDataByAuthor/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("author", encodeURIComponent(author))})
      }
   """
)
                        

// @LINE:9
def getCoAuthorGraphDataByTopic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GraphReturnObject.getCoAuthorGraphDataByTopic",
   """
      function(topic) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoAuthorGraphDataByTopic/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("topic", encodeURIComponent(topic))})
      }
   """
)
                        
    
}
              

// @LINE:18
class ReverseAssets {
    

// @LINE:18
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:18
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:15
class ReverseSchoolMap {
    

// @LINE:15
def GetSchoolsByTopic(topic:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SchoolMap.GetSchoolsByTopic(topic), HandlerDef(this, "controllers.SchoolMap", "GetSchoolsByTopic", Seq(classOf[String]), "GET", """ Map Schools""", _prefix + """getSchoolsByTopic/$topic<[^/]+>""")
)
                      
    
}
                          

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseGraphReturnObject {
    

// @LINE:10
def getAuthorPublicationGraphDataByTopic(topic:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphReturnObject.getAuthorPublicationGraphDataByTopic(topic), HandlerDef(this, "controllers.GraphReturnObject", "getAuthorPublicationGraphDataByTopic", Seq(classOf[String]), "GET", """""", _prefix + """getAuthorPublicationGraphDataByTopic/$topic<[^/]+>""")
)
                      

// @LINE:11
def getCoAuthorGraphDataByAuthor(author:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphReturnObject.getCoAuthorGraphDataByAuthor(author), HandlerDef(this, "controllers.GraphReturnObject", "getCoAuthorGraphDataByAuthor", Seq(classOf[String]), "GET", """""", _prefix + """getCoAuthorGraphDataByAuthor/$author<[^/]+>""")
)
                      

// @LINE:12
def getAuthorPublicationGraphDataByAuthor(author:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphReturnObject.getAuthorPublicationGraphDataByAuthor(author), HandlerDef(this, "controllers.GraphReturnObject", "getAuthorPublicationGraphDataByAuthor", Seq(classOf[String]), "GET", """""", _prefix + """getAuthorPublicationGraphDataByAuthor/$author<[^/]+>""")
)
                      

// @LINE:9
def getCoAuthorGraphDataByTopic(topic:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GraphReturnObject.getCoAuthorGraphDataByTopic(topic), HandlerDef(this, "controllers.GraphReturnObject", "getCoAuthorGraphDataByTopic", Seq(classOf[String]), "GET", """ GraphReturnObject""", _prefix + """getCoAuthorGraphDataByTopic/$topic<[^/]+>""")
)
                      
    
}
                          

// @LINE:18
class ReverseAssets {
    

// @LINE:18
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    