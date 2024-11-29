package com.adrian.app.ui;

import java.util.ArrayList;
/*
 * Abstract super class that contains coordinate coordinates
 */
public abstract class UiElement {

  private ArrayList<Coordinates> coordinates;

  public UiElement(ArrayList<Coordinates> coordinates) {
    this.coordinates = new ArrayList<Coordinates>();
  }

  public void addCoordinates(ArrayList<Coordinates> newCoordinates) {
    coordinates.addAll(newCoordinates);
  }

  public void removeCoordinates(ArrayList<Coordinates> nullCoordinates) {
    coordinates.removeAll(nullCoordinates);
  }

  public ArrayList<Coordinates> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(ArrayList<Coordinates> coordinates) {
    this.coordinates = coordinates;
  }

}
