package com.saiful.dataminingmaster.ui.syllabus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SyllabusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SyllabusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}