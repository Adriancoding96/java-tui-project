package com.adrian.app.enums;

import com.adrian.app.ui.component.Border;

public enum BorderType {

  SIMPLE(new Border("|", "_", "*")),
  FILLED(new Border("|*|", "=", "(*)"));

  private final Border border; 

  private BorderType(Border border) {
    this.border = border;
  }

  public Border getBorder() {
    return border;
  }
  
}