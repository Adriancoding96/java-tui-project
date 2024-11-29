package com.adrian.app.ui.component;

public class Border {

  private String verticalBorder;
  
  private String horizontalBorder;

  private String corner;

  public Border(String verticalBorder, String horizontalBorder, String corner) {
    this.verticalBorder = verticalBorder;
    this.horizontalBorder = horizontalBorder;
    this.corner = corner;
  }

  public String getVerticalBorder() {
    return verticalBorder;
  }

  public String getHorizontalBorder() {
    return horizontalBorder;
  }

  public String getCorner() {
    return corner;
  }
  
  public void setVerticalBorder(String verticalBorder) {
    this.verticalBorder = verticalBorder;
  }

  public void setHorizontalBorder(String horizontalBorder) {
    this.horizontalBorder = horizontalBorder;
  }

  public void setCorner(String corner) {
    this.corner = corner;
  }
}
