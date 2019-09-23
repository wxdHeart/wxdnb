package com.example.moxtr;

public class MsgEvent {
    private long max;
    private long progress;

    public MsgEvent(long max, long progress) {
        this.max = max;
        this.progress = progress;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
