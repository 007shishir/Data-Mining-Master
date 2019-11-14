package com.saiful.dataminingmaster.ui.syllabus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saiful.dataminingmaster.R;

public class SyllabusFragment extends Fragment {

    private SyllabusViewModel syllabusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        syllabusViewModel =
                ViewModelProviders.of(this).get(SyllabusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_syllabus, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
        WebView mWeb_syllabus;
        syllabusViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        //WebView Settings
        String url = "file:///android_asset/syllabus.html";
        mWeb_syllabus = root.findViewById(R.id.mWeb_syllabus);
        mWeb_syllabus.getSettings().setBuiltInZoomControls(true);
        mWeb_syllabus.clearCache(true);
        mWeb_syllabus.clearHistory();

        mWeb_syllabus.loadUrl(url);
        return root;
    }
}