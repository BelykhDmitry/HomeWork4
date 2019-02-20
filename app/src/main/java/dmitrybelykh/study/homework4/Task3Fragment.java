package dmitrybelykh.study.homework4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.transition.ChangeBounds;
import androidx.transition.Explode;
import androidx.transition.Scene;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class Task3Fragment extends Fragment {

    private RadioGroup radioGroup;
    private ViewGroup viewGroup;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task3, container, false);

        viewGroup = rootView.findViewById(R.id.task3_container);
        radioGroup = rootView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            changeScene(checkedId);
        });
        scene1 = Scene.getSceneForLayout(viewGroup, R.layout.scene1, getActivity());
        scene2 = Scene.getSceneForLayout(viewGroup, R.layout.scene2, getActivity());
        scene3 = Scene.getSceneForLayout(viewGroup, R.layout.scene3, getActivity());
        TransitionManager.go(scene1);
        return rootView;
    }

    private void changeScene(int checkedId) {
        switch (checkedId) {
            case R.id.scene1:
                TransitionManager.go(scene1, new ChangeBounds());
                break;
            case R.id.scene2:
                TransitionManager.go(scene2, new Explode());
                break;
            case R.id.scene3:
                TransitionManager.go(scene3, new Slide());
                break;
        }
    }

}
