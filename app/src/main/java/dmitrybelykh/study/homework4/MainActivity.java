package dmitrybelykh.study.homework4;

import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Task2SecondFragment.OnShowFragmentListener {
    private static final String LOG_TAG = MainActivity.class.getName();

    private int fragmentState = 1;

    private static final int OPTION1 = 1;
    private static final int OPTION2 = 2;
    private static final int OPTION3 = 3;
    private static final int OPTION4 = 4;

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.option1:
                    if (fragmentState != OPTION1)
                        openFragment(OPTION1);
                    return true;
                case R.id.option2:
                    if (fragmentState != OPTION2)
                        openFragment(OPTION2);
                    return true;
                case R.id.option3:
                    if (fragmentState != OPTION3)
                        openFragment(OPTION3);
                    return true;
                case R.id.option4:
                    if (fragmentState != OPTION4)
                        openFragment(OPTION4);
                    return true;
            }
            return false;
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new Task1Fragment(), Task1Fragment.class.getName())
                .commit();
    }

    private void openFragment(int fragmentNumber) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment newFragment = null;
        String tag = null;
        switch (fragmentNumber) {
            case OPTION1:
                newFragment = fragmentManager.findFragmentByTag(Task1Fragment.class.getName());
                if (newFragment == null)
                    newFragment = new Task1Fragment();
                tag = Task1Fragment.class.getName();
                fragmentState = OPTION1;
                break;
            case OPTION2:
                newFragment = fragmentManager.findFragmentByTag(Task2FirstFragment.class.getName());
                if (newFragment == null)
                    newFragment = new Task2FirstFragment();
                tag = Task2FirstFragment.class.getName();
                fragmentState = OPTION2;
                break;
            case OPTION3:
                newFragment = fragmentManager.findFragmentByTag(Task3Fragment.class.getName());
                if (newFragment == null)
                    newFragment = new Task3Fragment();
                tag = Task3Fragment.class.getName();
                fragmentState = OPTION3;
                break;
            case OPTION4:
                newFragment = fragmentManager.findFragmentByTag(Task6Fragment.class.getName());
                if (newFragment == null)
                    newFragment = new Task6Fragment();
                tag = Task6Fragment.class.getName();
                fragmentState = OPTION4;
                break;
            default:
                return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            // Смена выделения в меню в зависимости от стэка
        } else {
            // Установка первого пункта меню
        }
    }


    @Override
    public void onShowFragment() {
        hideMenu();
    }

    @Override
    public void onHideFragment() {
        openMenu();
    }

    private void hideMenu() {
        navigationView.animate().translationY(200f)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator());
    }

    private void openMenu() {
        navigationView.animate().translationY(0f)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator());
    }
}
