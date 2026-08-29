package com.leadsphere.crm.patterns;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.iluwatar.typeobject.Candy.Type;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class JsonParser {
  Hashtable<String, Candy> candies;

  JsonParser() {
    this.candies = new Hashtable<>();
  }

  void parse() throws JsonParseException {
    var is = this.getClass().getClassLoader().getResourceAsStream("candy.json");
    var reader = new InputStreamReader(is);
    var json = (JsonObject) com.google.gson.JsonParser.parseReader(reader);
    var array = (JsonArray) json.get("candies");
    for (var item : array) {
      var candy = (JsonObject) item;
      var name = candy.get("name").getAsString();
      var parentName = candy.get("parent").getAsString();
      var t = candy.get("type").getAsString();
      var type = Type.CRUSHABLE_CANDY;
      if (t.equals("rewardFruit")) {
        type = Type.REWARD_FRUIT;
      }
      var points = candy.get("points").getAsInt();
      var c = new Candy(name, parentName, type, points);
      this.candies.put(name, c);
    }
    setParentAndPoints();
  }

  void setParentAndPoints() {
    for (var e = this.candies.keys(); e.hasMoreElements(); ) {
      var c = this.candies.get(e.nextElement());
      if (c.parentName == null) {
        c.parent = null;
      } else {
        c.parent = this.candies.get(c.parentName);
      }
      if (c.getPoints() == 0 && c.parent != null) {
        c.setPoints(c.parent.getPoints());
      }
    }
  }
}
