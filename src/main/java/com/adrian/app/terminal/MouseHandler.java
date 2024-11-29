package com.adrian.app.terminal;

import java.io.IOException;
import java.io.InputStream;

public class MouseHandler {
 
  private InputStream inputStream;

  public MouseHandler() {
    this.inputStream = System.in;
  }


  /*
   * Method litsens for a mouse click in the terminal,
   * when mouse click is registered it returns an integer
   * array containing x and y coordinates
   *
   * @return int[]: returns integer array containing x, y coordinates
   * @throws IOException: throws exception if IO error occours
   */
  public int[] listenForMouseEvents() throws IOException {
    while(true) {
      if(inputStream.available() > 0) { //Checks if bytes are available
        int byteRead = inputStream.read();
        //Lots of mysterious if statements ..
        if (byteRead == 0x1B) { //Checks if first byte is correct control sequence prefix
          if(inputStream.read() == 0x5B) { //Checks if byte start control sequence 
            if(inputStream.read() == 0x4D) { //Checks if byte is mouse event
              //int event = inputStream.read();
              int y = inputStream.read() - 32;
              int x = inputStream.read() - 32;
              return new int[]{x, y}; 
            }
          }
        }
      }
      try {
        Thread.sleep(50);
      } catch(InterruptedException e){
        Thread.currentThread().interrupt();
        return null;  
      }
    }
  }
}
