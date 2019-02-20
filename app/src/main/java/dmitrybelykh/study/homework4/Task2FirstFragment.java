package dmitrybelykh.study.homework4;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.transition.ChangeBounds;
import androidx.transition.Slide;


/**
 * A simple {@link Fragment} subclass.
 */
public class Task2FirstFragment extends Fragment {


    AppCompatImageView imageView;
    AppCompatTextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task2_first, container, false);

        CardView cardView = rootView.findViewById(R.id.cardView);
        imageView = rootView.findViewById(R.id.picture);
        textView = rootView.findViewById(R.id.headline);
        Typeface typeface = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "Aquatico-Regular.otf");
        textView.setTypeface(typeface);
        cardView.setOnClickListener(click -> changeFragment());
        return rootView;
    }

    void changeFragment() {
        Slide slide = new Slide(Gravity.RIGHT);
        slide.setInterpolator(new OvershootInterpolator());
        slide.setDuration(1000);

        ChangeBounds changeBoundsEnter = new ChangeBounds();
        changeBoundsEnter.setDuration(1000);
        changeBoundsEnter.setInterpolator(new AnticipateOvershootInterpolator());

        ChangeBounds changeBoundsExit = new ChangeBounds();
        changeBoundsExit.setDuration(1000);
        changeBoundsExit.setInterpolator(new OvershootInterpolator());

        Task2SecondFragment secondFragment = new Task2SecondFragment();
        secondFragment.setSharedElementEnterTransition(changeBoundsEnter);
        secondFragment.setSharedElementReturnTransition(changeBoundsExit);
        secondFragment.setEnterTransition(slide);
        secondFragment.setAllowEnterTransitionOverlap(true);
        secondFragment.setAllowReturnTransitionOverlap(true);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, secondFragment, Task2SecondFragment.class.getSimpleName())
                .addToBackStack(null)
                .addSharedElement(imageView, "transitionImage")
                .addSharedElement(textView, "transitionHeadline")
                .commit();
    }

}
