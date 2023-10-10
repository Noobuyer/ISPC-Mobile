package com.ispc.gymapp.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ispc.gymapp.R;
import com.ispc.gymapp.views.adapter.ExercisesAdapter;
import com.ispc.gymapp.views.fragments.ExercisesAdvancedFragment;
import com.ispc.gymapp.views.fragments.ExercisesBeginnerFragment;
import com.ispc.gymapp.views.fragments.ExercisesIntermediateFragment;
import com.ispc.gymapp.views.fragments.MiPerfilFragment;

public class ExerciseList extends AppCompatActivity {

    private ExercisesAdapter exercisesAdapter;
    private ViewPager2 viewPager2;

    public final static String EXTRA_EXERCISE_TYPE = "com.ispc.gymapp.extra.Exercise_Type";

    private String exerciseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        viewPager2 = findViewById(R.id.viewPagerExercises);
        exercisesAdapter = new ExercisesAdapter(getSupportFragmentManager(), getLifecycle());
        // Add fragments
        exercisesAdapter.add(new ExercisesBeginnerFragment());
        exercisesAdapter.add(new ExercisesIntermediateFragment());
        exercisesAdapter.add(new ExercisesAdvancedFragment());

        viewPager2.setAdapter(exercisesAdapter);

        TabLayout exercisesTabLayout = findViewById(R.id.exercises_difficult_tabs);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_Navigator);
        bottomNavigationView.setSelectedItemId(R.id.title_activity_exercise);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.title_activity_exercise) {
                    return true;
                }

                if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                if (id == R.id.shopItem) {
                    startActivity(new Intent(getApplicationContext(), Ecommerce.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                if (id == R.id.accountItem) {
                    startActivity(new Intent(getApplicationContext(), MiPerfilFragment.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;

            }


        });






        // Sync clic with tab
        new TabLayoutMediator(exercisesTabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0 -> tab.setText("Principiante");
                case 1 -> tab.setText("Intermedio");
                case 2 -> tab.setText("Avanzado");
            }
        }).attach();
    }

    public void returnToHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    public void goToDescription(View view) {
        TextView textView = (TextView) view;

        Intent exercise = new Intent(this, ExercisesDescription.class);

        exerciseType = (String) textView.getText();

        exercise.putExtra(EXTRA_EXERCISE_TYPE, exerciseType);

        startActivity(exercise);
    }



}




