package com.adrian.app;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import com.adrian.app.terminal.MouseHandler;
import com.adrian.app.terminal.ScreenBuffer;
import com.adrian.app.util.TerminalUtil;

public class App {

  private static AtomicReference<String> message = new AtomicReference<String>();
  private static volatile boolean running = true;

  public static void main(String[] args) throws IOException, InterruptedException {
    message.set("Click somewhere in the terminal to show coordinates");
    TerminalUtil.startRawMode();

    try {
      startMouseCursorThread(); 
    } catch(IOException e) {
      e.printStackTrace();
    }

    int[] terminalSize = TerminalUtil.getTerminalSize();
    int rows = terminalSize[0];
    int cols = terminalSize[1];

    ScreenBuffer screen = new ScreenBuffer(rows, cols);

    try {
      TerminalUtil.hideCursor();
      boolean isRunning = true;
      while(isRunning) {
        if(System.in.available() > 0) {
          int ch = System.in.read();
          if(ch == 'q') {
            isRunning = false;
            running = false;
          }
        }

        screen.clear();
        int[] centerCords = TerminalUtil.centerDrawPosition(cols, rows, message.get().length());
        int x = centerCords[0];
        int y = centerCords[1];
        screen.drawText(x, y, message.get());
        screen.render();

        Thread.sleep(66);
      }
    } finally {
      TerminalUtil.exitRawMode();
      TerminalUtil.clearTerminalAndResetCursor();
      TerminalUtil.disableMouseReporting();
    }
  }

  private static void startMouseCursorThread() throws IOException {
    MouseHandler mouseHandler = new MouseHandler();
    TerminalUtil.enableMouseReporting();
    Thread mouseThread = new Thread(() -> {
      try {
        while(true) {
          int[] mouseCords = mouseHandler.listenForMouseEvents(); 
          message.set("x: " + mouseCords[0] + " | " + "y: " + mouseCords[1]);
        }
      } catch(IOException e) {
        e.printStackTrace();
      } finally {
        try {
          TerminalUtil.disableMouseReporting();
        } catch (IOException err) {
          err.printStackTrace();
        }
      }
    });
    mouseThread.setDaemon(true);
    mouseThread.start();
  }
}
