/*
 * $Id: ConsoleMachineFactory.java,v 1.0 2019/12/11 21:00 sblendorio Exp $
 * 
 * Created on 2019/12/11
 * Copyright 2019 Francesco Sblendorio, Roberto Manicardi
 *
 * This file is part of The Z-machine Preservation Project (ZMPP).
 *
 * ZMPP is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * ZMPP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ZMPP; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.zmpp.textbased;

import org.zmpp.textbased.cli.*;

import org.zmpp.io.IOSystem;
import org.zmpp.io.InputStream;
import org.zmpp.vm.*;

import org.zmpp.textbased.VirtualConsole;
import java.io.IOException;

/**
 * This class implements machine creation console application (stdin/stdout);
 * 
 * @author Francesco Sblendorio, Roberto Manicardi
 * @version 1.0
 */
public class ConsoleMachineFactory extends MachineFactory<VirtualConsole> {

  VirtualConsole console;
  IOSystem ioSystem;
  InputStream inputStream;
  StatusLine statusLine;
  ScreenModel screenModel;
  SaveGameDataStore saveGameDataStore;

  private byte[] byteArrayStory;

  public ConsoleMachineFactory(byte[] byteArrayStory) {
    this.byteArrayStory = byteArrayStory;
  }

  protected byte[] readStoryData() throws IOException {
      return byteArrayStory;
  }

  protected void reportInvalidStory() {
    console.reportInvalidStory();
  }

  protected VirtualConsole initUI(Machine machine) {
    CLIConsole cliConsole  = new CLIConsole(machine,false);
    console = cliConsole;
    saveGameDataStore = cliConsole;
    ioSystem = cliConsole;
    inputStream = cliConsole.getInputStream();
    screenModel = cliConsole.getScreenModel();
    statusLine = (StatusLine) cliConsole.getScreenModel();
    return console;
  }

  public VirtualConsole getUI() { return console; }

  protected IOSystem getIOSystem() { return ioSystem; }

  protected InputStream getKeyboardInputStream() { return inputStream; }

  protected StatusLine getStatusLine() { return statusLine; }

  protected ScreenModel getScreenModel() { return screenModel; }

  protected SaveGameDataStore getSaveGameDataStore() { return saveGameDataStore; }
}
