package com.adrian.app.engine;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import com.adrian.app.terminal.MouseHandler;
import com.adrian.app.terminal.ScreenBuffer;
import com.adrian.app.util.TerminalUtil;

public class TuiEngine {
  
  private AtomicReference<String> mainUI = new AtomicReference<String>();
  private volatile boolean running;

  public void startTUI() throws IOException, InterruptedException {
    mainUI.set("Click somewhere in the terminal to show coordinates");
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
        int[] centerCords = TerminalUtil.centerDrawPosition(cols, rows, mainUI.get().length());
        int x = centerCords[0];
        int y = centerCords[1];
        screen.drawText(x, y, mainUI.get());
        screen.render();

        Thread.sleep(66);
      }
    } finally {
      TerminalUtil.exitRawMode();
      TerminalUtil.clearTerminalAndResetCursor();
      TerminalUtil.disableMouseReporting();
    }
  }

  private void startMouseCursorThread() throws IOException {
    MouseHandler mouseHandler = new MouseHandler();
    TerminalUtil.enableMouseReporting();
    Thread mouseThread = new Thread(() -> {
      try {
        while(true) {
          int[] mouseCords = mouseHandler.listenForMouseEvents(); 
          mainUI.set("x: " + mouseCords[0] + " | " + "y: " + mouseCords[1]);
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
