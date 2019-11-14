package com.saiful.dataminingmaster.ui.share;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saiful.dataminingmaster.R;

import java.util.Objects;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    String fb_GROUP_Url, fb_PAGE_Url;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        fb_GROUP_Url = "https://www.facebook.com/groups/353790045198315/";
        fb_PAGE_Url = "https://www.facebook.com/AVTutorial/";

        final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button mBtnFBgroup = root.findViewById(R.id.mBtnFBgroup);
        Button mBtnFBpage = root.findViewById(R.id.mBtnFBpage);

        mBtnFBgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = newFacebookIntent(Objects.requireNonNull(getContext()).getPackageManager(),fb_GROUP_Url);
                startActivity(fbIntent);
            }
        });

        mBtnFBpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = newFacebookIntent(Objects.requireNonNull(getContext()).getPackageManager(),fb_PAGE_Url);
                startActivity(fbIntent);
            }
        });

        return root;
    }

    //This method is for opening facebook in app or in browser!
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}