package com.leadsphere.crm.patterns;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QuadTree {
  Rect boundary;
  int capacity;
  boolean divided;
  Map<Integer, Point> points;
  QuadTree northwest;
  QuadTree northeast;
  QuadTree southwest;
  QuadTree southeast;

  QuadTree(Rect boundary, int capacity) {
    this.boundary = boundary;
    this.capacity = capacity;
    this.divided = false;
    this.points = new HashMap<>();
    this.northwest = null;
    this.northeast = null;
    this.southwest = null;
    this.southeast = null;
  }

  void insert(Point p) {
    if (this.boundary.contains(p)) {
      if (this.points.size() < this.capacity) {
        points.put(p.id, p);
      } else {
        if (!this.divided) {
          this.divide();
        }
        if (this.northwest.boundary.contains(p)) {
          this.northwest.insert(p);
        } else if (this.northeast.boundary.contains(p)) {
          this.northeast.insert(p);
        } else if (this.southwest.boundary.contains(p)) {
          this.southwest.insert(p);
        } else if (this.southeast.boundary.contains(p)) {
          this.southeast.insert(p);
        }
      }
    }
  }

  void divide() {
    var x = this.boundary.coordinateX;
    var y = this.boundary.coordinateY;
    var width = this.boundary.width;
    var height = this.boundary.height;
    var nw = new Rect(x - width / 4, y + height / 4, width / 2, height / 2);
    this.northwest = new QuadTree(nw, this.capacity);
    var ne = new Rect(x + width / 4, y + height / 4, width / 2, height / 2);
    this.northeast = new QuadTree(ne, this.capacity);
    var sw = new Rect(x - width / 4, y - height / 4, width / 2, height / 2);
    this.southwest = new QuadTree(sw, this.capacity);
    var se = new Rect(x + width / 4, y - height / 4, width / 2, height / 2);
    this.southeast = new QuadTree(se, this.capacity);
    this.divided = true;
  }

  Collection<Point> query(Rect r, Collection<Point> relevantPoints) {
    // could also be a circle instead of a rectangle
    if (this.boundary.intersects(r)) {
      this.points.values().stream().filter(r::contains).forEach(relevantPoints::add);
      if (this.divided) {
        this.northwest.query(r, relevantPoints);
        this.northeast.query(r, relevantPoints);
        this.southwest.query(r, relevantPoints);
        this.southeast.query(r, relevantPoints);
      }
    }
    return relevantPoints;
  }
}
