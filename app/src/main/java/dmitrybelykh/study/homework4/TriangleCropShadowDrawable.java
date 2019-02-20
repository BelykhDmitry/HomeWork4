package dmitrybelykh.study.homework4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;

public class TriangleCropShadowDrawable extends ColorDrawable implements Task6Fragment.OnPointPositionChangeListener {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    /**
     * Create TriangleCropShadowDrawable.
     *
     * @param color - Shadow color.
     */
    public TriangleCropShadowDrawable(int color) {
        super(color);
        mPaint.setShader(getShader(color));
    }

    @Override
    public void draw(Canvas canvas) {
        if (!mPath.isEmpty()) {
            canvas.clipPath(mPath, Region.Op.DIFFERENCE);
            // Попытка нарисовать размытие. Пока безуспешно
            canvas.drawPath(mPath, mPaint);
        }
        super.draw(canvas);
    }

    private float mWidth;
    private float mHeight;

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mHeight = bounds.height();
        mWidth = bounds.width();
        calculatePath();
    }

    private Shader getShader(int color) {
        LinearGradient shader = new LinearGradient(0f, 0f, 0f, mHeight,
                Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR);
        return shader;
    }

    private float mBaseX = -1;
    private float mBaseY = -1;

    /**
     * Calculate crop triangle for new coordinates:
     */
    private void calculatePath() {
        mPath.reset();
        if (mBaseX != -1 && mBaseY != -1) {
            mPath.moveTo(0.f, 0.f);
            mPath.lineTo(mBaseX, mBaseY);
            mPath.lineTo(mWidth, 0f);
            mPath.lineTo(0.f, 0.f);
            mPath.addCircle(mBaseX, mBaseY, 25f, Path.Direction.CCW);
        }
    }

    /**
     * Calls, when base Vertex Position changes
     *
     * @param x - new Vertex X
     * @param y - new Vertex Y
     */
    @Override
    public void onPointPositionChange(float x, float y) {
        mBaseX = x;
        mBaseY = y;
        calculatePath();
    }
}
