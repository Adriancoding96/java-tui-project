package com.adrian.app.ui.impl;

import java.util.ArrayList;

import com.adrian.app.enums.BorderType;
import com.adrian.app.ui.Coordinates;
import com.adrian.app.ui.UiElement;

public class Box extends UiElement {

  private int height;
  private int width;
  private BorderType borderType;

  public Box(ArrayList<Coordinates> coordinates, int height, int width, BorderType borderType) {
    super(coordinates);
    this.height = height;
    this.width = width;
    this.borderType = borderType;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public BorderType getBorderType() {
    return borderType;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setBorderType(BorderType borderType) {
    this.borderType = borderType;
  }
  
}
