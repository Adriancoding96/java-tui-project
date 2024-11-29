package com.adrian.app;

import java.io.IOException;

import com.adrian.app.engine.TuiEngine;

public class App {

  public static void main(String[] args) throws IOException, InterruptedException {
    TuiEngine engine = new TuiEngine();
    engine.startTUI();
    
  }
}

