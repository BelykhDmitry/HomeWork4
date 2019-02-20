package dmitrybelykh.study.homework4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Task1Fragment extends Fragment {

    private ImageView imageView;
    private boolean animationOn = false;
    private LottieAnimationView buttonPlayPause;
    private LottieAnimationView favoriteBlack;

    public Task1Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task1, container, false);
        imageView = rootView.findViewById(R.id.imageView);

        buttonPlayPause = rootView.findViewById(R.id.button_play_pause);
        buttonPlayPauseSetup();
        favoriteBlack = rootView.findViewById(R.id.favorite_black);
        favoriteBlackSetup();

        return rootView;
    }

    private void favoriteBlackSetup() {
        favoriteBlack.setAnimation("4371-swipe.json");
        favoriteBlack.setRepeatCount(LottieDrawable.INFINITE);
        favoriteBlack.setRepeatMode(LottieDrawable.RESTART);
        favoriteBlack.setProgress(0.f);
    }

    private void buttonPlayPauseSetup() {
        buttonPlayPause.setAnimation("Play-Pause.json");
        buttonPlayPause.setMinAndMaxProgress(0f, 0.5f);
        buttonPlayPause.setProgress(0f);
        buttonPlayPause.setOnClickListener(click -> {
            if (animationOn) {
                favoriteBlack.pauseAnimation();
                favoriteBlack.setProgress(0.f);
                buttonPlayPause.setSpeed(-1.5f);
                buttonPlayPause.playAnimation();
                DrawableUtils.animationStop(imageView);
                imageView.setImageResource(R.drawable.call);
                animationOn = false;
            } else {

                buttonPlayPause.setSpeed(1f);
                buttonPlayPause.playAnimation();
                imageView.setImageResource(R.drawable.call_animated);
                DrawableUtils.animate(imageView);
                animationOn = true;
                favoriteBlack.playAnimation();
            }
        });
    }

    @Override
    public void onResume() {
        buttonPlayPause.setProgress(0.f);
        favoriteBlack.setProgress(0.f);
        super.onResume();
    }

    @Override
    public void onPause() {
        DrawableUtils.animationStop(imageView);
        animationOn = false;
        buttonPlayPause.pauseAnimation();
        favoriteBlack.pauseAnimation();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
