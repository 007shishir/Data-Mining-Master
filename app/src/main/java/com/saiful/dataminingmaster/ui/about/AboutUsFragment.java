package com.saiful.dataminingmaster.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saiful.dataminingmaster.R;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AboutUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);

//        final TextView textView = root.findViewById(R.id.text_slideshow);
        WebView mWeb_aboutUs;
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        //WebView Settings
        String url = "file:///android_asset/index.html";
        mWeb_aboutUs = root.findViewById(R.id.mWeb_aboutUs);
        mWeb_aboutUs.getSettings().setBuiltInZoomControls(true);
        mWeb_aboutUs.clearCache(true);
        mWeb_aboutUs.clearHistory();
//        mWeb_aboutUs.getSettings().setJavaScriptEnabled(true);
        mWeb_aboutUs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWeb_aboutUs.loadUrl(url);
        return root;
    }
}