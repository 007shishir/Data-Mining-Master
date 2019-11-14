package com.saiful.dataminingmaster.ui.privacy;

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

public class PrivacyPolicyFragment extends Fragment {

    private PrivacyPolicyModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(PrivacyPolicyModel.class);
        View root = inflater.inflate(R.layout.fragment_privacy, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        String url = "file:///android_asset/privacy_policy.html";
        WebView mWeb_privacy = root.findViewById(R.id.mWeb_privacy);
        mWeb_privacy.getSettings().setBuiltInZoomControls(true);
        mWeb_privacy.clearCache(true);
        mWeb_privacy.clearHistory();

        mWeb_privacy.loadUrl(url);

        return root;
    }
}