package com.adrian.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.adrian.app.enums.AnsiColor;

public class TerminalUtil {

  /*
   * Utility method to reset cursor position in terminal
   */
  public static void restCursor() {
    System.out.print("\u001b[H");
  }

  /*
   * Utility method that clears terminal content and resets
   * cursor position
   */
  public static void clearTerminalAndResetCursor() {
    System.out.print("\u001b[2J");
    System.out.print("\u001b[H");
  }

  /*
   * Utility method to clear terminal row of cursor position
   */
  public static void clearTerminalRow() {
    System.out.print("\u001b[K");
  }

  /*
   * Utility method to create new line in terminal
   */
  public static void newLine() {
    System.out.print("\n");
  }
  
  /*
   * Utility method that sets color of terminal text
   *
   * @param ansiColor: enum that contains color code string
   */
  public static void setTextColor(AnsiColor ansiColor) {
    System.out.print(ansiColor.getColorCode()); 
  }

  /*
   * Method starts terminal in raw mode to enable advanced
   * rendering possibilities. Uses ProcessBuilder to invoke
   * external unix processes
   *
   * @throws IOException/InterruptedException: throws error if error occours
   */
  public static void startRawMode() throws IOException, InterruptedException {
    new ProcessBuilder("sh", "-c", "stty -icanon -echo min 1 < /dev/tty")
      .inheritIO().start().waitFor();
  }

  /*
   * Method exits terminal raw mode to enable to revert terminal
   * to its original state
   *
   * @throws IOException/InterruptedException: throws exception if input/interrupt error occours
   */
  public static void exitRawMode() throws IOException, InterruptedException {
    new ProcessBuilder("sh", "-c", "stty sane < /dev/tty")
      .inheritIO().start().waitFor();
  }
 
  /*
   * Method gets the size of the terminal and returns it as a 
   * array of integers
   *
   * @return size: returns array of integers cotaining width & height
   * of terminal.
   * @throws IOException/InterruptedException: throws exception if input/interrupt error occours
   */
  public static int[] getTerminalSize() throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder(
      "sh", "-c", "stty size < /dev/tty"
    );
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();

    BufferedReader reader = new BufferedReader( new InputStreamReader(process.getInputStream()));
    String size = reader.readLine();
    process.waitFor();
    reader.close();

    if(size != null) {
      String[] sizes = size.trim().split("\\s+");
      if(sizes.length == 2) {
        int rows = Integer.parseInt(sizes[0]);
        int cols = Integer.parseInt(sizes[1]);
        return new int[]{rows, cols};
      }
    }
    return new int[]{24, 80};
  }
  
  /*
   * Utility method to center draw position in terminal
   *
   * @param cols: number of colomns in terminal
   * @param rows: number of rows in terminal
   * @param drawLength: contains length of ui to be drawn
   * @return int[]: contains cords of the center of terminal
   */
  public static int[] centerDrawPosition(int cols, int rows, int drawLength) {
    int x = (cols - drawLength) / 2;
    int y = rows / 2;
    return new int[]{x, y}; 
  }

  /*
   * Utility method to hide cursor in terminal
   */
  public static void hideCursor() {
    System.out.print("\033[?251");
  }

  /*
   * Utility method to show the cursor
   */
  public static void showCursor() {
    System.out.print("\033[?25h");
  }

  /*
   * Utility method to enable mouse reporting
   */
  public static void enableMouseReporting() throws IOException {
    System.out.println("\033[?1000h");
  }

  /*
   * Utility method to disable mouse reporting
   */
  public static void disableMouseReporting() throws IOException {
    System.out.println("\033[?10001");
  }

}
