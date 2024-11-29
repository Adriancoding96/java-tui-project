package com.adrian.app.terminal;

import java.util.Arrays;

import com.adrian.app.util.TerminalUtil;
/*
 * Class to draw ui on the Screen
 */
public class ScreenBuffer {
  
  private char[][] buffer;
  private int rows;
  private int cols;

  public ScreenBuffer(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    buffer = new char[rows][cols];
    clear();
  }

  public void clear() {
    for(int i = 0; i < rows; i++) {
      Arrays.fill(buffer[i], ' ');
    }
  }

  public void drawText(int x, int y, String text) {
    for(int i = 0, n = text.length(); i < n; i++) {
      buffer[y][x + i] = text.charAt(i);
    }
  }

  public void render() {
    TerminalUtil.restCursor();
    for(int i = 0; i < rows; i++) {
      System.out.print(buffer[i]);
      TerminalUtil.clearTerminalRow();
      TerminalUtil.newLine();
    }
  } 

}
