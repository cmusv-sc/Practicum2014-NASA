/*
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ if you have any 
 * questions.
 * 
 * */
package util;

import play.libs.Json;
import play.libs.WS;
import play.libs.F.Function;
import play.libs.F.Promise;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class APICall {
  public static enum ResponseType {
    SUCCESS, GETERROR, SAVEERROR, DELETEERROR, RESOLVEERROR, TIMEOUT, CONVERSIONERROR, UNKNOWN
  }

  public static JsonNode callAPI(String apiString) {
    Promise<WS.Response> responsePromise = WS
      .url(apiString).get();

    final Promise<JsonNode> bodyPromise = responsePromise
      .map(new Function<WS.Response, JsonNode>() {
        @Override
        public JsonNode apply(WS.Response response)
        throws Throwable {
        if (response.getStatus() == 200
          || response.getStatus() == 201) {
          return response.asJson();
        } else { // no response from the server
          return createResponse(ResponseType.GETERROR);
        }
        }
      });

    try {
      return bodyPromise.get(300000L);
    } catch (Exception e) {
      return createResponse(ResponseType.TIMEOUT);
    }

  }

  public static JsonNode postAPI(String apiString, JsonNode jsonData) {
    Promise<WS.Response> responsePromise = WS.url(apiString).post(jsonData);
    final Promise<JsonNode> bodyPromise = responsePromise
      .map(new Function<WS.Response, JsonNode>() {
        @Override
        public JsonNode apply(WS.Response response)
        throws Throwable {
        if ((response.getStatus() == 201 || response
            .getStatus() == 200)
          && !response.getBody().contains("not")) {
          return createResponse(ResponseType.SUCCESS);
        } else { // other response status from the server
          return createResponse(ResponseType.SAVEERROR);
        }
        }
      });
    try {
      return bodyPromise.get(10000L);
    } catch (Exception e) {
      return createResponse(ResponseType.TIMEOUT);
    }
  }

  public static JsonNode putAPI(String apiString, JsonNode jsonData) {
    Promise<WS.Response> responsePromise = WS.url(apiString).put(jsonData);
    final Promise<JsonNode> bodyPromise = responsePromise
      .map(new Function<WS.Response, JsonNode>() {
        @Override
        public JsonNode apply(WS.Response response)
        throws Throwable {
        if ((response.getStatus() == 201 || response
            .getStatus() == 200)
          && !response.getBody().contains("not")) {
          return createResponse(ResponseType.SUCCESS);
        } else { // other response status from the server
          return createResponse(ResponseType.SAVEERROR);
        }
        }
      });
    try {
      return bodyPromise.get(10000L);
    } catch (Exception e) {
      return createResponse(ResponseType.TIMEOUT);
    }
  }

  public static JsonNode deleteAPI(String apiString) {
    Promise<WS.Response> responsePromise = WS.url(apiString).delete();
    final Promise<JsonNode> bodyPromise = responsePromise
      .map(new Function<WS.Response, JsonNode>() {
        @Override
        public JsonNode apply(WS.Response response)
        throws Throwable {
        if ((response.getStatus() == 200 || response
            .getStatus() == 201)
          && !response.getBody().contains("not")) {
          return createResponse(ResponseType.SUCCESS);
        } else { // no response from the server
          return createResponse(ResponseType.DELETEERROR);
        }
        }
      });
    try {
      return bodyPromise.get(10000L);
    } catch (Exception e) {
      return createResponse(ResponseType.TIMEOUT);
    }

  }

  public static JsonNode createResponse(ResponseType type) {
    ObjectNode jsonData = Json.newObject();
    switch (type) {
      case SUCCESS:
        jsonData.put("success", "Success!");
        break;
      case GETERROR:
        jsonData.put("error", "Cannot get data from server");
        break;
      case SAVEERROR:
        jsonData.put("error", "Cannot be saved. The data must be invalid!");
        break;
      case DELETEERROR:
        jsonData.put("error", "Cannot be deleted on server");
        break;
      case RESOLVEERROR:
        jsonData.put("error", "Cannot be resolved on server");
        break;
      case TIMEOUT:
        jsonData.put("error", "No response/Timeout from server");
        break;
      case CONVERSIONERROR:
        jsonData.put("error", "Conversion error");
        break;
      default:
        jsonData.put("error", "Unknown errors");
        break;
    }
    return jsonData;
  }
}
