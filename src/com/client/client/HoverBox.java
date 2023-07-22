package com.client.client;



public abstract class HoverBox
{
    public int x;
    public int y;
    public String text;
    public long duration;
    public long start;
    
    public HoverBox(final int x, final int y, final String text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }
    
    public HoverBox(final int x, final int y, final String text, final long duration) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.duration = duration;
        this.start = System.currentTimeMillis();
    }
    
    public abstract void render(final boolean p0, final GameClient p1);
}
