package com.adrian.app.ui.impl;

import com.adrian.app.ui.Coordinate;
import com.adrian.app.enums.BorderType;

public class Box {
 
  private BorderType borderType;  
  private Coordinate[] coordinates;

  /*
   * Constructor calculates top left coordinates based on
   * center coordinate, width and height. Calls initBox
   * to construct the object
   *
   * @param center: coordinates of the box center
   * @param width: width of the box
   * @param height: height of the box
   */
  public Box(Coordinate center, int width, int height, BorderType borderType) {
    this.borderType = borderType; 

    int topLeftX = center.getX() - width / 2;
    int topLeftY = center.getY() - height / 2;

    //Adjusts topleft coordinates if width/height is even
    if(width % 2 == 0) topLeftX +=1;
    if(height % 2 == 0) topLeftY +=1;
    
    initBox(topLeftX, topLeftY, width, height);

  }

  /*
   * Constructs a rectangular box by defining the borders
   * storing them in a array of coordinates.
   *
   * @param topLeftX: the x coordinate of the top left corner
   * @param topLeftY: the y coordinate of the top left corner
   * @param width: the width of the box
   * @param height: the height of the box
   */
  public void initBox(int topLeftX, int topLeftY, int width, int height) {
    coordinates = new Coordinate[2 * (width + height)];
    int index = 0;

    for(int x = topLeftX + 1; x < topLeftX + width; x++) {
      coordinates[index++] = new Coordinate(x, topLeftY);
      coordinates[index++] = new Coordinate(x, topLeftY + height);
    }

    for(int y = topLeftY + 1; y < topLeftY + height; y++) {
      coordinates[index++] = new Coordinate(topLeftX, y);
      coordinates[index++] = new Coordinate(topLeftX + width, y);
    }
  }

  public Coordinate[] getCoordinates() {
    return coordinates;
  }

  public BorderType getBorderType() {
    return borderType;
  }

  public void setBorderType(BorderType borderType) {
    this.borderType = borderType;
  }
}
