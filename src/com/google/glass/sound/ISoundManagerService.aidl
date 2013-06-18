package com.google.glass.sound;

interface ISoundManagerService {
	int playSound(String name);
	void stopSound(int requestId);
	void setMuted(boolean muted);
	boolean isMuted();
}
