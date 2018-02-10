package com.stoyan.tunein.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stoyan.tunein.R;
import com.stoyan.tunein.fragments.CategoriesFragment;
import com.stoyan.tunein.fragments.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            changeFragment(CategoriesFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) { //if not last fragment
            super.onBackPressed();
        } else {
            //If last fragment, finish the activity
            finish();
        }
    }

    public void addCategoryFrag(String categoryUrl){
        changeFragment(CategoryFragment.newInstance(categoryUrl));
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_base_container, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

}