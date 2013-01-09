package com.danieru.miraie.nds;

/*
Copyright (C) 2012 Jeffrey Quesnelle

This file is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.

This file is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with the this software.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.util.Log;

class EmulatorThread extends Thread {
	
	public EmulatorThread(EmulateActivity activity) {
		super("EmulatorThread");
		this.activity = activity;
	}
	
	public void setCurrentActivity(EmulateActivity activity) {
		this.activity = activity;
	}
	
	boolean soundPaused = true;
	
	long lastDraw = 0;
	final AtomicBoolean finished = new AtomicBoolean(false);
	final AtomicBoolean paused = new AtomicBoolean(false);
	String pendingRomLoad = null;
	Integer pending3DChange = null;
	Integer pendingSoundChange = null;
	boolean pendingAutosave = false;
	
	public void loadRom(String path) {
		pendingRomLoad = path;
		synchronized(dormant) {
			dormant.notifyAll();
		}
	}
	
	public void change3D(int set) {
		pending3DChange = set;
	}
	
	public void changeSound(int set) {
		pendingSoundChange = set;
	}
	
	public void setCancel(boolean set) {
		finished.set(set);
		synchronized(dormant) {
			dormant.notifyAll();
		}
	}
	
	public void setPause(boolean set) {
		paused.set(set);
		if(DeSmuME.inited) {
			DeSmuME.setSoundPaused(set ? 1 : 0);
			DeSmuME.setMicPaused(set ? 1 : 0);
			soundPaused = set;
		}
		synchronized(dormant) {
			dormant.notifyAll();
		}
	}
	
	public void sheduleAutosave() {
		pendingAutosave = true;
	}
	
	Object dormant = new Object();
	
	public Lock inFrameLock = new ReentrantLock();
	int fps = 1;
	EmulateActivity activity = null;
	long frameCounter = 0;
	
	@Override
	public void run() {
		
		while(!finished.get()) {
			
			if(!DeSmuME.inited) {
				DeSmuME.context = activity;
				DeSmuME.load();
				
				Filespace.prepFolders();
				DeSmuME.setWorkingDir(Filespace.getMainFolder() + '/', Filespace.getTempFolder() + '/');
				
				DeSmuME.init();
				DeSmuME.inited = true;
			}
			if(pendingRomLoad != null) {
				activity.msgHandler.sendEmptyMessage(EmulateActivity.LOADING_START);
				if(DeSmuME.romLoaded)
					DeSmuME.closeRom();
				if(!DeSmuME.loadRom(pendingRomLoad)) {
					activity.msgHandler.sendEmptyMessage(EmulateActivity.LOADING_END);
					activity.msgHandler.sendEmptyMessage(EmulateActivity.ROM_ERROR);
					DeSmuME.romLoaded = false;
				}
				else {
					activity.msgHandler.sendEmptyMessage(EmulateActivity.LOADING_END);
					DeSmuME.romLoaded = true;
					setPause(false);
				}
				pendingRomLoad = null;
			}
			if(pending3DChange != null) {
				DeSmuME.change3D(pending3DChange.intValue());
				pending3DChange = null;
			}
			if(pendingSoundChange != null) {
				DeSmuME.changeSound(pendingSoundChange.intValue());
				pendingSoundChange = null;
			}
			if(pendingAutosave) {
				//Log.i(ANDSemuApplication.TAG, "Autosaving!");
				//DeSmuME.saveState(11);
				pendingAutosave = false;
			}
			
			if(!paused.get()) {
				
				if(soundPaused) {
					DeSmuME.setSoundPaused(0);
					DeSmuME.setMicPaused(0);
					soundPaused = false;
				}
				
				inFrameLock.lock();
				DeSmuME.runCore();
				inFrameLock.unlock();
				fps = DeSmuME.runOther();

				activity.msgHandler.sendEmptyMessage(EmulateActivity.DRAW_SCREEN);
		
				
			} 
			else {
				//hacky, but keeps thread alive so we don't lose contexts
				try {
					synchronized(dormant) {
						dormant.wait();
					}
				} 
				catch (InterruptedException e) {
				} 
			}
		}
	}
}