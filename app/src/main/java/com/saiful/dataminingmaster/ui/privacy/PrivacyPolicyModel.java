package com.saiful.dataminingmaster.ui.privacy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyPolicyModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrivacyPolicyModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}