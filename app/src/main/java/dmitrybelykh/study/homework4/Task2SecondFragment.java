package dmitrybelykh.study.homework4;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Task2SecondFragment extends Fragment {

    private BottomNavigationView menu;
    private OnShowFragmentListener mListener;

    public Task2SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task2_second, container, false);

        TextView textView = rootView.findViewById(R.id.headline);
        Typeface typeface = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "Aquatico-Regular.otf");
        textView.setTypeface(typeface);
        menu = rootView.findViewById(R.id.navigation);
        mListener = (OnShowFragmentListener) getActivity();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onShowFragment();
    }

    @Override
    public void onPause() {
        mListener.onHideFragment();
        super.onPause();
    }

    interface OnShowFragmentListener {
        void onShowFragment();

        void onHideFragment();
    }
}
