package dmitrybelykh.study.homework4;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Task6Fragment extends Fragment {

    AppCompatImageView imageView;
    OnPointPositionChangeListener mDrawableListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task6, container, false);

        imageView = rootView.findViewById(R.id.shadow_layer);
        TriangleCropShadowDrawable drawable = new TriangleCropShadowDrawable(Color.parseColor("#41494141"));
        mDrawableListener = drawable;
        imageView.setImageDrawable(drawable);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDrawableListener.onPointPositionChange(event.getX(), event.getY());
                imageView.postInvalidate();
                return false;
            }
        });
        return rootView;
    }

    interface OnPointPositionChangeListener {
        void onPointPositionChange(float x, float y);
    }

}
