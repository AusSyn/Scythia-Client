package com.client.client;



public class DecoratedHoverBox extends HoverBox
{
    private boolean center;
    private boolean headerLine;
    
    public DecoratedHoverBox(final int x, final int y, final String text) {
        super(x, y, text);
        this.center = true;
        this.headerLine = false;
    }
    
    public DecoratedHoverBox(final int x, final int y, final String text, final boolean headerLine) {
        super(x, y, text);
        this.center = true;
        this.headerLine = false;
        this.headerLine = headerLine;
    }
    
    public DecoratedHoverBox(final int x, final int y, final String text, final boolean headerLine, final boolean center) {
        super(x, y, text);
        this.center = true;
        this.headerLine = false;
        this.headerLine = headerLine;
        this.center = center;
    }
    
    public DecoratedHoverBox(final int x, final int y, final String text, final long duration) {
        super(x, y, text, duration);
        this.center = true;
        this.headerLine = false;
    }
    
    public DecoratedHoverBox(final int x, final int y, final String text, final long duration, final boolean headerLine) {
        super(x, y, text, duration);
        this.center = true;
        this.headerLine = false;
        this.headerLine = headerLine;
    }
    
    public DecoratedHoverBox(final int x, final int y, final String text, final long duration, final boolean headerLine, final boolean center) {
        super(x, y, text, duration);
        this.center = true;
        this.headerLine = false;
        this.headerLine = headerLine;
        this.center = center;
    }
    
    public int getWidth(final GameClient c) {
        int width = 0;
        int max = 0;
        final String[] results = this.text.split("\n");
        TextDrawingArea tda = c.normalFont;
        for (int i = 0; i < results.length; ++i) {
            if (i == 0 && this.headerLine) {
                tda = c.boldFont;
            }
            else {
                tda = c.normalFont;
            }
            final int tw = tda.getTextWidth(results[i]);
            if (tw > max) {
                max = tw;
            }
        }
        width = max;
        width /= 15;
        width *= 15;
        width += 15;
        return width;
    }
    
    public void render(final boolean posMatters, final GameClient c) {
        if (this.text == null) {
            return;
        }
        final int ox = this.x;
        final int oy = this.y;
        boolean straight = false;
        boolean isExp = false;
        TextDrawingArea tda = c.normalFont;
        int alpha = 240;
        if (this.duration > 0L) {
            final long d = System.currentTimeMillis() - this.start;
            long d2 = this.duration - d;
            if (d2 < 390L) {
                d2 = 390L;
            }
            else {
                c.hoverBoxesAdd.add(this);
            }
            if (d2 <= 1000L) {
                alpha = (int)d2 / 5;
            }
        }
        if (this.text.contains(" exp: ")) {
            isExp = true;
            straight = true;
            tda = c.boldFont;
            final String[] splits = this.text.split(" ");
            final int myExp = Integer.parseInt(splits[2]);
            final int myLevel = c.getLevelForXP(myExp);
            if (myLevel < 99) {
                final int nextLevel = c.getXPForLevel(myLevel + 1);
                this.text = splits[0] + " exp: " + String.format("%,d", myExp) + "\nNext level at: " + String.format("%,d", nextLevel) + "\nRemaining: " + String.format("%,d", nextLevel - myExp);
            }
            else {
                this.text = splits[0] + " exp: " + String.format("%,d", myExp);
            }
        }
        if (GameClient.clientSize != 0) {}
        final String[] results = this.text.split("\n");
        int height = results.length * 13;
        height /= 15;
        height *= 15;
        height += 15;
        int width = 0;
        int max = 0;
        for (int i = 0; i < results.length; ++i) {
            if (i == 0 && this.headerLine) {
                tda = c.boldFont;
            }
            else {
                tda = c.normalFont;
            }
            final int tw = tda.getTextWidth(results[i]);
            if (tw > max) {
                max = tw;
            }
        }
        width = max;
        width /= 15;
        width *= 15;
        width += 15;
        if (this.center) {
            this.x -= width / 2;
        }
        if (posMatters) {
            if ( GameClient.clientSize == 0 || isExp) {}
            if (GameClient.clientSize == 0 && this.x + width + 16 > GameClient.clientWidth) {
                this.x = GameClient.clientWidth - width - 16;
            }
            else if (GameClient.clientSize != 0) {}
        }
        final int border = 1;
     //   c.drawBorderBox(this.x, this.y, width, height, alpha, c.borders15);
        this.y += 16;
        final int color = 16101975;
        for (int j = 0; j < results.length; ++j) {
            int plus = 13;
            if (j == 0 && this.headerLine) {
                tda = c.normalFont;
                plus = 16;
            }
            else {
                tda = c.normalFont;
            }
            if (!straight || results.length <= 1) {
                tda.drawTextShadowCenter(results[j], this.x + width / 2 + 10, color, this.y + 4);
            }
            else {
                tda.drawTextShadow2(results[j], this.x + 10, color, this.y + 4);
            }
            this.y += plus;
        }
        this.x = ox;
        this.y = oy;
    }
}
