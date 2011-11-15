package com.xmedic.troll.components;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.xmedic.troll.service.MapMath;

/**
 * Image view which image can be scrolled.
 * 
 * @author gytis
 */
public class ScrollableImageView extends ImageView {

    private float downX, downY;
    private int totalX, totalY;
    private int scrollByX, scrollByY;

    private int maxLeft;
    private int maxRight;
    private int maxTop;
    private int maxBottom;

    protected int maxX;
    protected int maxY;

    final Handler animationHandler = new Handler();

    public ScrollableImageView(final Context context, final AttributeSet set) {
        super(context, set);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        float currentX = 0, currentY = 0;

        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            downX = event.getX();
            downY = event.getY();
            break;

        case MotionEvent.ACTION_MOVE:
            currentX = event.getX();
            currentY = event.getY();
            scrollByX = (int) (downX - currentX);
            scrollByY = (int) (downY - currentY);

            // scrolling to left side of image (pic moving to the right)
            if (currentX > downX)
            {
                if (totalX == maxLeft)
                {
                    scrollByX = 0;
                }
                if (totalX > maxLeft)
                {
                    totalX = totalX + scrollByX;
                }
                if (totalX < maxLeft)
                {
                    scrollByX = maxLeft - (totalX - scrollByX);
                    totalX = maxLeft;
                }
            }

            // scrolling to right side of image (pic moving to the left)
            if (currentX < downX)
            {
                if (totalX == maxRight)
                {
                    scrollByX = 0;
                }
                if (totalX < maxRight)
                {
                    totalX = totalX + scrollByX;
                }
                if (totalX > maxRight)
                {
                    scrollByX = maxRight - (totalX - scrollByX);
                    totalX = maxRight;
                }
            }

            // scrolling to top of image (pic moving to the bottom)
            if (currentY > downY)
            {
                if (totalY == maxTop)
                {
                    scrollByY = 0;
                }
                if (totalY > maxTop)
                {
                    totalY = totalY + scrollByY;
                }
                if (totalY < maxTop)
                {
                    scrollByY = maxTop - (totalY - scrollByY);
                    totalY = maxTop;
                }
            }

            // scrolling to bottom of image (pic moving to the top)
            if (currentY < downY)
            {
                if (totalY == maxBottom)
                {
                    scrollByY = 0;
                }
                if (totalY < maxBottom)
                {
                    totalY = totalY + scrollByY;
                }
                if (totalY > maxBottom)
                {
                    scrollByY = maxBottom - (totalY - scrollByY);
                    totalY = maxBottom;
                }
            }

            scrollBy(scrollByX, scrollByY);
            downX = currentX;
            downY = currentY;
            break;

        }
        return true;
    }

    /**
     * Sets current scroll position.
     * 
     * @param x
     * @param y
     */
    public void setCurrent(final int x, final int y) {
        totalX = x;
        totalY = y;
    }

    public void setScreenSize(final int width, final int height) {
        maxX = ((MapMath.MAP_WIDTH / 2) - (width / 2)) + MapMath.OVERCOMPENSATION;
        maxY = ((MapMath.MAP_HEIGHT / 2) - (height / 2)) + MapMath.OVERCOMPENSATION;

        // set scroll limits
        maxLeft = (maxX * -1);
        maxRight = maxX;
        maxTop = (maxY * -1);
        maxBottom = maxY;

    }

    /**
     * Moves image to given position.
     * 
     * @param point to move to.
     */
    public void moveTo(final Point point) {

        final int yCoeficient = (getScrollY() > point.y) ? -1 : 1;
        final int xCoeficient = (getScrollX() > point.x) ? -1 : 1;
        Thread t = new Thread() {
            @Override
            public void run() {
                int y = getScrollY();
                int x = getScrollX();
                while (y != point.y || x != point.x) {
                    final int X = x;
                    final int Y = y;
                    animationHandler.post(new Runnable() {
                        public void run() {
                            scrollTo(X, Y);
                        }
                    });
                    if (y != point.y) {
                        y = y + yCoeficient;
                    }
                    if (x != point.x) {
                        x = x + xCoeficient;
                    }
                    try {
                        sleep(1000 / 350);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();

        setCurrent(point.x, point.y);
    }
}
