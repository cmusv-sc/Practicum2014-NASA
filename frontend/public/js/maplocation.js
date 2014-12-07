/**
 * File to initialize the google earth api.
 * Contains the google earth object globally along with the geocoder object
 * Contains methods to react to user inputs to the text box to query the backend for data 
 */
var ge;
var geocoder = new google.maps.Geocoder();
google.load("earth", "1", {"other_params":"sensor=false"});

function init() {
	google.earth.createInstance('map3d', initCB, failureCB);
}

function initCB(instance) {
	ge = instance;
	ge.getWindow().setVisibility(true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_BUILDINGS, true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_TERRAIN, true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_TREES, true);
	ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);
}

function failureCB(errorCode) {
}

google.setOnLoadCallback(init);

$(function() {
	$("#buttonsearchtopic").on("click", function(e) {
	    var topic = $("#searchtopic").val();
	    if(topic!="")
	    	{
	    		jsRoutes.controllers.GraphDisplay.getSchoolsByTopic(encodeURIComponent(topic)).ajax({
	    			success : function(data) {
	    					for(var i = 0; i < data.length; i++) {
	    						var obj = data[i];
	    						codeAddress(obj.schoolName, i);
	    					}
	    				}
	    		});
	    	}
	});
	});

function codeAddress(name, focus) {
   geocoder.geocode( { 'address': name}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
    	  var lat = results[0].geometry.location.lat();
    	  var long = results[0].geometry.location.lng();
    	  
    	  var placemark = ge.createPlacemark('');
    	  placemark.setName(name);

    	  // Set the placemark's location.  
    	  var point = ge.createPoint('');
    	  point.setLatitude(lat);
    	  point.setLongitude(long);
    	  placemark.setGeometry(point);
    	  ge.getFeatures().appendChild(placemark);
    	  
    	  if(focus == 0){
    		  setDefaultLocation(lat, long);
    	  }
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

function setDefaultLocation(lat, long) {
	// Get the current view.
	  var lookAt = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);

	  // Set new latitude and longitude values.
	  lookAt.setLatitude(lat);
	  lookAt.setLongitude(long);

	  // Zoom in
	  lookAt.setRange(lookAt.getRange() * 0.025);

	  // Update the view in Google Earth.
	  ge.getView().setAbstractView(lookAt);
}