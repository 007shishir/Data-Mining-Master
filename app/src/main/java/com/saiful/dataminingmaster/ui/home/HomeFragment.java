package com.saiful.dataminingmaster.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.saiful.dataminingmaster.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        Button mBtn_mcq = root.findViewById(R.id.mBtn_mcq);
        Button mBtn_mem = root.findViewById(R.id.mBtn_mem);
        TextView mTxt_details = root.findViewById(R.id.mTxt_details);
        TextView mTxt_privacyPolicy = root.findViewById(R.id.mTxt_privacyPolicy);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        mBtn_mcq.setOnClickListener(this);
        mBtn_mem.setOnClickListener(this);
        mTxt_details.setOnClickListener(this);
        mTxt_privacyPolicy.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Bundle child_name;
        switch (v.getId()) {
            case R.id.mBtn_mcq:
                child_name = new Bundle();
                child_name.putString("child_name", getResources().getString(R.string.child_mcq));
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_mcq_rec_view, child_name);
                break;
            case R.id.mBtn_mem:
                child_name = new Bundle();
                child_name.putString("child_name", getResources().getString(R.string.child_memorize));
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_memorise_rec_view, child_name);
                break;
            case R.id.mTxt_details:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_syllabus);
                break;
            case R.id.mTxt_privacyPolicy:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_privacy_policy);
                break;
            default:
                Toast.makeText(getContext(),
                        "Please make sure you have active internet connection",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}