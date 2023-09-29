package com.ispc.gymapp.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ispc.gymapp.R;
import com.ispc.gymapp.views.adapter.ExercisesAdapter;
import com.ispc.gymapp.views.fragments.ExercisesAdvancedFragment;
import com.ispc.gymapp.views.fragments.ExercisesBeginnerFragment;
import com.ispc.gymapp.views.fragments.ExercisesIntermediateFragment;

public class ExerciseList extends AppCompatActivity {

    private ExercisesAdapter exercisesAdapter;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        viewPager2 = findViewById(R.id.viewPagerExercises);
        exercisesAdapter = new ExercisesAdapter(getSupportFragmentManager(),getLifecycle());
        // Add fragments
        exercisesAdapter.add(new ExercisesBeginnerFragment());
        exercisesAdapter.add(new ExercisesIntermediateFragment());
        exercisesAdapter.add(new ExercisesAdvancedFragment());

        viewPager2.setAdapter(exercisesAdapter);

        TabLayout exercisesTabLayout = findViewById(R.id.exercises_difficult_tabs);

        // Sync clic with tab
        new TabLayoutMediator(exercisesTabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0 -> tab.setText("Principiante");
                    case 1 -> tab.setText("Intermedio");
                    case 2 -> tab.setText("Avanzado");
                }
            }
        }).attach();
    }
}