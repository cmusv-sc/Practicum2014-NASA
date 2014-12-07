var Network, RadialPlacement, activate, root;
var w = 600,
h = 600,
nodeCircles,
linkLines;
var vis1;
var node,link;
var SHOW_THRESHOLD = 2.5;
var width = 600,
height = 600;
var currentOffset = { x : 0, y : 0 };
root = typeof exports !== "undefined" && exports !== null ? exports : this;
/*** Configure zoom behaviour ***/
//The D3.js scales
var xScale = d3.scale.linear()
  .domain([0, w])
  .range([0, w]);
var yScale = d3.scale.linear()
  .domain([0, h])
  .range([0, h]);
var zoomScale = d3.scale.linear()
  .domain([1,6])
  .range([1,6])
  .clamp(true);
function zoom() {
    console.log("zoom", d3.event.translate, d3.event.scale);
    vis1.attr("transform", 
             "translate(" + d3.event.translate + ")" 
                + " scale(" + d3.event.scale + ")" );
}
/* --------------------------------------------------------------------- */
/* Move all graph elements to its new positions. Triggered:
   - on node repositioning (as result of a force-directed iteration)
   - on translations (user is panning)
   - on zoom changes (user is zooming)
   - on explicit node highlight (user clicks in a movie panel link)
   Set also the values keeping track of current offset & zoom values
*/
function repositionGraph( off, z, mode ) {
  console.log( "REPOS: off="+off, "zoom="+z, "mode="+mode );

  // do we want to do a transition?
  var doTr = (mode == 'move');

  // drag: translate to new offset
  if( off !== undefined &&
  (off.x != currentOffset.x || off.y != currentOffset.y ) ) {
g = d3.select('graph')
if( doTr )
  g = g.transition().duration(500);
g.attr("transform", function(d) { return "translate("+
				  off.x+","+off.y+")" } );
currentOffset.x = off.x;
currentOffset.y = off.y;
  }

  // zoom: get new value of zoom
  if( z === undefined ) {
if( mode != 'tick' )
  return;	// no zoom, no tick, we don't need to go further
z = currentZoom;
  }
  else
currentZoom = z;

  // move edges
  e = doTr ? link.transition().duration(500) : link;
  e
.attr("x1", function(d) { return z*(d.source.x); })
    .attr("y1", function(d) { return z*(d.source.y); })
    .attr("x2", function(d) { return z*(d.target.x); })
    .attr("y2", function(d) { return z*(d.target.y); });

  // move nodes
  n = doTr ? node.transition().duration(500) : node;
  n
.attr("transform", function(d) { return "translate("
				 +z*d.x+","+z*d.y+")" } );
  // move labels
 /* l = doTr ? graphLabels.transition().duration(500) : graphLabels;
  l
.attr("transform", function(d) { return "translate("
				 +z*d.x+","+z*d.y+")" } );*/
}
       

/* --------------------------------------------------------------------- */
/* Perform drag
 */
function dragmove(d) {
  console.log("DRAG",d3.event);
  offset = { x : currentOffset.x + d3.event.dx,
	 y : currentOffset.y + d3.event.dy };
  console.log("DRAGoffset",offset);
  repositionGraph( offset, undefined, 'drag' );
}
RadialPlacement = function() {
  var center, current, increment, place, placement, radialLocation, radius, setKeys, start, values;
  values = d3.map();
  increment = 2;
  radius = 1;
  center = {
    "x": 0,
    "y": 0
  };
  start = 2;
  current = start;
  radialLocation = function(center, angle, radius) {
    var x, y;
    x = center.x + radius * Math.cos(angle * Math.PI / 180);
    y = center.y + radius * Math.sin(angle * Math.PI / 180);
    return {
      "x": x,
      "y": y
    };
  };
  placement = function(key) {
    var value;
    value = values.get(key);
    if (!values.has(key)) {
      value = place(key);
    }
    return value;
  };
  place = function(key) {
    var value;
    value = radialLocation(center, current, radius);
    values.set(key, value);
    current += increment;
    return value;
  };
  setKeys = function(keys) {
    var firstCircleCount, firstCircleKeys, secondCircleKeys;
    values = d3.map();
    firstCircleCount = 360 / increment;
    if (keys.length < firstCircleCount) {
      increment = 360 / keys.length;
    }
    firstCircleKeys = keys.slice(0, firstCircleCount);
    firstCircleKeys.forEach(function(k) {
      return place(k);
    });
    secondCircleKeys = keys.slice(firstCircleCount);
    radius = radius + radius / 1.8;
    increment = 360 / secondCircleKeys.length;
    return secondCircleKeys.forEach(function(k) {
      return place(k);
    });
  };
  placement.keys = function(_) {
    if (!arguments.length) {
      return d3.keys(values);
    }
    setKeys(_);
    return placement;
  };
  placement.center = function(_) {
    if (!arguments.length) {
      return center;
    }
    center = _;
    return placement;
  };
  placement.radius = function(_) {
    if (!arguments.length) {
      return radius;
    }
    radius = _;
    return placement;
  };
  placement.start = function(_) {
    if (!arguments.length) {
      return start;
    }
    start = _;
    current = start;
    return placement;
  };
  placement.increment = function(_) {
    if (!arguments.length) {
      return increment;
    }
    increment = _;
    return placement;
  };
  return placement;
};

Network = function() {
  var allData, charge, curLinksData, curNodesData, filter, filterLinks, filterNodes, force, forceTick, groupCenters, height, hideDetails, layout, linkedByIndex, linksG, mapNodes, moveToRadialLayout, neighboring, network, nodeColors, nodeCounts, nodesG, radialTick, setFilter, setLayout, setSort, setupData, showDetails, showlinkDetails, sort, sortedArtists, strokeFor, tooltip, update, updateCenters, updateLinks, updateNodes, width;
  width = 600;
  height = 600;
  allData = [];
  curLinksData = [];
  curNodesData = [];
  linkedByIndex = {};
  nodesG = null;
  linksG = null;
  node = null;
  link = null;
  layout = "force";
  filter = "all";
  sort = "songs";
  groupCenters = null;
  force = d3.layout.force();
  nodeColors = d3.scale.category20();
  tooltip = Tooltip("vis-tooltip", 230);
  charge = function(node) {
    return -Math.pow(node.radius, 2.0) / 2;
  };
  network = function(selection, data) {
  //  var vis1;
  //  var zoomer = d3.behavior.zoom()
   // .scaleExtent([0.1,10])
//allow 10 times zoom in or out
   // .on("zoom", zoom);
//define the event handler function
    allData = setupData(data);
    vis1 = d3.select(selection).append("svg").attr("width", width).attr("height", height).append("g")
    .attr("class", "graph");
   // .call(zoomer); //Attach zoom behaviour.
    linksG = vis1.append("g").attr("id", "links");
    nodesG = vis1.append("g").attr("id", "nodes");
    var text = d3.select(selection).append("text")
    /*.attr("x", 20)
    .attr("dy", 0)*/
   // .style("text-anchor","end") 
    .attr("startOffset","-300%")
    .text('Publication list: ')
    .attr("font-family", "sans-serif")
    .attr("font-size", "40px")
    .attr("fill","blue");
    force.size([width, height]);
    setLayout("force");
    setFilter("all");
 // Add a transparent background rectangle to catch
 // mouse events for the zoom behaviour.
 // Note that the rectangle must be inside the element (graph)
 // which has the zoom behaviour attached, but must be *outside*
 // the group that is going to be transformed.
    vis1.call( d3.behavior.zoom()
  	      .x(xScale)
  	      .y(yScale)
  	      .scaleExtent([1, 6])
  	      .on("zoom", zoom) );
    vis1.call( d3.behavior.drag()
    	      .on("drag",dragmove) );
 /*var rect = vis1.append("rect")
     .attr("width", width)
     .attr("height", height)
     .style("fill", "none") 
         //make transparent (vs black if commented-out)
     .style("pointer-events", "all");  
         //respond to mouse, even when transparent

  vis = vis1.append("svg:g")
             .attr("class", "plotting-area");
*/ //create a group that will hold all the content to be zoomed
    return update();
    /*
     * added
     */
   
  };
  
  update = function() {
    var artists;
    curNodesData = filterNodes(allData.nodes);
    curLinksData = filterLinks(allData.links, curNodesData);
    if (layout === "radial") {
      artists = sortedArtists(curNodesData, curLinksData);
      updateCenters(artists);
    }
    force.nodes(curNodesData);
    updateNodes();
    if (layout === "force") {
      force.links(curLinksData);
      updateLinks();
    } else {
      force.links([]);
      if (link) {
        link.data([]).exit().remove();
        link = null;
      }
    }
    return force.start();
  };
  network.toggleLayout = function(newLayout) {
    force.stop();
    setLayout(newLayout);
    return update();
  };
  network.toggleFilter = function(newFilter) {
    force.stop();
    setFilter(newFilter);
    return update();
  };
  network.toggleSort = function(newSort) {
    force.stop();
    setSort(newSort);
    return update();
  };
  network.updateSearch = function(searchTerm) {
    var searchRegEx;
    searchRegEx = new RegExp(searchTerm.toLowerCase());
    return node.each(function(d) {
      var element, match;
      element = d3.select(this);
      match = d.artist.toLowerCase().search(searchRegEx);
      if (searchTerm.length > 0 && match >= 0) {
        element.style("fill", "#F38630").style("stroke-width", 2.0).style("stroke", "#555");
        return d.searched = true;
      } else {
        d.searched = false;
        return element.style("fill", function(d) {
          return nodeColors(d.group);
        }).style("stroke-width", 1.0);
      }
    });
  };
  network.updateData = function(newData) {
    allData = setupData(newData);
    link.remove();
    node.remove();
    return update();
  };
  setupData = function(data) {
    var circleRadius, countExtent, nodesMap;
    countExtent = d3.extent(data.nodes, function(d) {
      return d.playcount;
    });
    circleRadius = d3.scale.sqrt().range([3, 12]).domain(countExtent);
    data.nodes.forEach(function(n) {
    
	    
      var randomnumber;
      n.x = randomnumber = Math.floor(Math.random() * width);
      n.y = randomnumber = Math.floor(Math.random() * height);
      return n.radius = circleRadius(n.playcount);
    });
    
    
    nodesMap = mapNodes(data.nodes);
    data.links.forEach(function(l) {
      l.source = nodesMap.get(l.source);
      l.target = nodesMap.get(l.target);
      return linkedByIndex["" + l.source.id + "," + l.target.id] = 1;
    });
    
    
    
    return data;
  };
  mapNodes = function(nodes) {
    var nodesMap;
    nodesMap = d3.map();
    nodes.forEach(function(n) {
      return nodesMap.set(n.id, n);
    });
    return nodesMap;
  };
  nodeCounts = function(nodes, attr) {
    var counts;
    counts = {};
    nodes.forEach(function(d) {
      var _name;
      if (counts[_name = d[attr]] == null) {
        counts[_name] = 0;
      }
      return counts[d[attr]] += 1;
    });
    return counts;
  };
  neighboring = function(a, b) {
    return linkedByIndex[a.id + "," + b.id] || linkedByIndex[b.id + "," + a.id];
  };
  filterNodes = function(allNodes) {
    var cutoff, filteredNodes, playcounts;
    filteredNodes = allNodes;
    if (filter === "popular" || filter === "obscure") {
      playcounts = allNodes.map(function(d) {
        return d.playcount;
      }).sort(d3.ascending);
      cutoff = d3.quantile(playcounts, 0.5);
      filteredNodes = allNodes.filter(function(n) {
        if (filter === "popular") {
          return n.playcount > cutoff;
        } else if (filter === "obscure") {
          return n.playcount <= cutoff;
        }
      });
    }
    return filteredNodes;
  };
  sortedArtists = function(nodes, links) {
    var artists, counts;
    artists = [];
    if (sort === "links") {
      counts = {};
      links.forEach(function(l) {
        var _name, _name1;
        if (counts[_name = l.source.artist] == null) {
          counts[_name] = 0;
        }
        counts[l.source.artist] += 1;
        if (counts[_name1 = l.target.artist] == null) {
          counts[_name1] = 0;
        }
        return counts[l.target.artist] += 1;
      });
      nodes.forEach(function(n) {
        var _name;
        return counts[_name = n.artist] != null ? counts[_name] : counts[_name] = 0;
      });
      artists = d3.entries(counts).sort(function(a, b) {
        return b.value - a.value;
      });
      artists = artists.map(function(v) {
        return v.key;
      });
    } else {
      counts = nodeCounts(nodes, "artist");
      artists = d3.entries(counts).sort(function(a, b) {
        return b.value - a.value;
      });
      artists = artists.map(function(v) {
        return v.key;
      });
    }
    return artists;
  };
  updateCenters = function(artists) {
    if (layout === "radial") {
      return groupCenters = RadialPlacement().center({
        "x": width / 2,
        "y": height / 2 - 100
      }).radius(300).increment(18).keys(artists);
    }
  };
  filterLinks = function(allLinks, curNodes) {
    curNodes = mapNodes(curNodes);
    return allLinks.filter(function(l) {
      return curNodes.get(l.source.id) && curNodes.get(l.target.id);
    });
  };
  updateNodes = function() {
    node = nodesG.selectAll(".node").data(curNodesData, function(d) {
      return d.id;
    });
    
    node.enter().append("circle").attr("class", "node").attr("cx", function(d) {
      return d.x;
    }).attr("cy", function(d) {
      return d.y;
    }).attr("r", function(d) {
      return d.radius;
    }).style("fill", function(d) {
      return nodeColors(d.group);
    }).style("stroke", function(d) {
      return strokeFor(d);
    }).style("stroke-width", 1.0);
    /*
    node.append("text").attr("dx", ".12").attr("dy", ".35").text(function(d) { return d.name; });*/
    node.on("mouseover", showDetails).on("mouseout", hideDetails).on("click",function(d){d3.select("text").text(d.detail);});
    
    
    return node.exit().remove();
  };
  updateLinks = function() {
    link = linksG.selectAll(".link").data(curLinksData, function(d) {
      return "" + d.source.id + "_" + d.target.id;
    });
    /*  
    link = link
    .data(graph.links)
  .enter().append("path")
    .attr("class", "link")
    .attr("d", function(d) {
 
		 var dx = d.target.x - d.source.x,
	         dy = d.target.y - d.source.y,
	         dr = Math.sqrt(dx * dx + dy * dy);
	         return 	"M" + d.source.x + "," 
	    			+ d.source.y 
	    			+ "A" + dr + "," 
	    			+ dr + " 0 0,1 " 
	    			+ d.target.x + "," 
	    			+ d.target.y;
	});
	http://www.is.kau.se/julioangulo/angulo/blog/?p=157568737
  */
    link.enter().append("line").attr("class", "link").attr("stroke", "#ddd").style("stroke-width", function(d) { return Math.sqrt(d.value); }).attr("stroke-opacity", 0.8).attr("x1", function(d) {
      return d.source.x;
    }).attr("y1", function(d) {
      return d.source.y;
    }).attr("x2", function(d) {
      return d.target.x;
    }).attr("y2", function(d) {
      return d.target.y;
    });
    return link.exit().remove();
  };
  setLayout = function(newLayout) {
    layout = newLayout;
    if (layout === "force") {
      return force.on("tick", forceTick).charge(-200).linkDistance(50);
    } else if (layout === "radial") {
      return force.on("tick", radialTick).charge(charge);
    }
  };
  setFilter = function(newFilter) {
    return filter = newFilter;
  };
  setSort = function(newSort) {
    return sort = newSort;
  };
  forceTick = function(e) {
    node.attr("cx", function(d) {
      return d.x;
    }).attr("cy", function(d) {
      return d.y;
    });
    /*added----
    text.attr("cx", function(d) {
        return d.x;
      }).attr("cy", function(d) {
        return d.y;
      });*/
    
    return link.attr("x1", function(d) {
      return d.source.x;
    }).attr("y1", function(d) {
      return d.source.y;
    }).attr("x2", function(d) {
      return d.target.x;
    }).attr("y2", function(d) {
      return d.target.y;
    });
  };
  radialTick = function(e) {
    node.each(moveToRadialLayout(e.alpha));
    node.attr("cx", function(d) {
      return d.x;
    }).attr("cy", function(d) {
      return d.y;
    });
    if (e.alpha < 0.03) {
      force.stop();
      return updateLinks();
    }
  };
  moveToRadialLayout = function(alpha) {
    var k;
    k = alpha * 0.1;
    return function(d) {
      var centerNode;
      centerNode = groupCenters(d.artist);
      d.x += (centerNode.x - d.x) * k;
      return d.y += (centerNode.y - d.y) * k;
    };
  };
  strokeFor = function(d) {
    return d3.rgb(nodeColors(d.artist)).darker().toString();
  };
  showlinkDetails = function(d) {
    return d3.select(this).style("stroke", "blue").style("stroke-width", 2.0).append("title");
  };
  showDetails = function(d, i) {
    var content;
    content = '<p class="main">' + d.group + ':</span></p>';
    content += '<hr class="tooltip-hr">';
    content += '<p class="main">' + d.artist + '</span></p>';
    content += '<hr class="tooltip-hr">';
    content += '<p class="main">' + d.name + '</span></p>';
    tooltip.showTooltip(content, d3.event);
    if (link) {
      link.attr("stroke", function(l) {
        if (l.source === d || l.target === d) {
          return "#555";
        } else {
          return "#ddd";
        }
      }).attr("stroke-opacity", function(l) {
        if (l.source === d || l.target === d) {
          return 1.0;
        } else {
          return 0.5;
        }
      });
    }
    link.each(function(l) {
      if (l.source === d || l.target === d) {
        l.attr("stroke", "red");
        return content = '<p class="main">' + d.name + '</span></p>';
      }
    });
    content += '<hr class="tooltip-hr">';
    content += '<p class="main">' + d.artist + '</span></p>';
    tooltip.showTooltip(content, d3.event);
    node.style("stroke", function(n) {
      if (n.searched || neighboring(d, n)) {
        return "#555";
      } else {
        return strokeFor(n);
      }
    }).style("stroke-width", function(n) {
      if (n.searched || neighboring(d, n)) {
        return 2.0;
      } else {
        return 1.0;
      }
    });
    
    return d3.select(this).style("stroke", "black").style("stroke-width", 2.0);
  };
  
  hideDetails = function(d, i) {
    tooltip.hideTooltip();
    node.style("stroke", function(n) {
      if (!n.searched) {
        return strokeFor(n);
      } else {
        return "#555";
      }
    }).style("stroke-width", function(n) {
      if (!n.searched) {
        return 1.0;
      } else {
        return 2.0;
      }
    });
    if (link) {
      return link.attr("stroke", "#ddd").attr("stroke-opacity", 0.8);
    }
  };
  return network;
};

activate = function(group, link) {
  d3.selectAll("#" + group + " a").classed("active", false);
  return d3.select("#" + group + " #" + link).classed("active", true);
};

$(function() {
  var myNetwork;
  myNetwork = Network();
  d3.selectAll("#layouts a").on("click", function(d) {
    var newLayout;
    newLayout = d3.select(this).attr("id");
    activate("layouts", newLayout);
    return myNetwork.toggleLayout(newLayout);
  });
  d3.selectAll("#filters a").on("click", function(d) {
    var newFilter;
    newFilter = d3.select(this).attr("id");
    activate("filters", newFilter);
    return myNetwork.toggleFilter(newFilter);
  });
  d3.selectAll("#sorts a").on("click", function(d) {
    /*var newSort;
    newSort = d3.select(this).attr("id");
    activate("sorts", newSort);
    return myNetwork.toggleSort(newSort);*/
	  return d3.json("assets/data/publications.json", function(json) {
	      return myNetwork.updateData(json);
	  });    
  });
  d3.selectAll("#both a").on("click", function(d) {
	//  json=JSON.parse(data);
		  return d3.json("assets/data/authorandpublication.json", function(json) {
			  
		      return myNetwork.updateData(json);
		  });    
	  
  });
  d3.selectAll("#dynamic a").on("click", function(d) {
	  json=JSON.parse(data);
		 // return d3.json("assets/data/authorandpublication.json", function(json) {
			  
		      return myNetwork.updateData(json);
		 // });    
	  
  });
  $("#song_select").on("change", function(e) {
    var topic;
    topic = $(this).val();
    if (topic=="Cloud Computing" || topic=="All") {
    //Dynamic data load by directly calling the backend which supplies the data in json format itself. Uses Play's javascriptrouter.
    	jsRoutes.controllers.GraphDisplay.getCoAuthorGraphData(encodeURIComponent(topic)).ajax({
    		success : function(data) {
    			return myNetwork.updateData(data);}});
      }
    else{
    return d3.json("assets/data/" + topic, function(json) {
      return myNetwork.updateData(json);
    });
    }
  });
  $("#search").keyup(function() {
    var searchTerm;
    searchTerm = $(this).val();
    return myNetwork.updateSearch(searchTerm);
  });
  //return d3.json("assets/data/auth_cloud_computing.json", function(json) {
    //return myNetwork("#vis", json);
  //});
  		//json=JSON.parse(data);
  return d3.json("assets/data/auth_cloud_computing.json", function(json) {
	    return myNetwork("#vis", json);
	  });
});

