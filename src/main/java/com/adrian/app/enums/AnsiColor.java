package com.adrian.app.enums;   

/*
 * Enum to define terminal color codes
 * as types 
 */
public enum AnsiColor {
  ANSI_RESET("\u001B[0m"),
  ANSI_BLACK("\u001B[30m"),
  ANSI_RED("\001B[31m"),
  ANSI_GREEN("\001B[32m"),
  ANSI_YELLOW("\001B[33m"),
  ANSI_BLUE("\001B[34m"),
  ANSI_PURPLE("\001B[35m"),
  ANSI_CYAN("\001B[36m"),
  ANSI_WHITE("\001B[37m");

  private final String colorCode;

  private AnsiColor(String colorCode) {
    this.colorCode = colorCode;
  }

  public String getColorCode() {
    return colorCode;
  }
}
