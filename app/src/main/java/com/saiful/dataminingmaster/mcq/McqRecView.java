package com.saiful.dataminingmaster.mcq;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.AudienceNetworkAds;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saiful.dataminingmaster.Parameter;
import com.saiful.dataminingmaster.R;
import com.facebook.ads.*;

import java.util.Objects;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class McqRecView extends Fragment {
//recycler view and database referrence are here. The child name is
//came from previous fragment
    private RecyclerView mRecycler_Memorize;
    private DatabaseReference mDatabase;
    String childName_fromFragment;

    //To check internet connection
    private ConnectivityManager connectivityManager;
    private AdView adView;

//    private FormulaViewViewModel mViewModel;

    public static McqRecView newInstance() {
        return new McqRecView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mcq_rec_view, container, false);

        connectivityManager = (ConnectivityManager) Objects.
                requireNonNull(getActivity()).getSystemService(CONNECTIVITY_SERVICE);

        // Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(Objects.requireNonNull(getContext()));
        adView = new AdView(getContext(), getResources().getString(R.string.facebook_banner_90),
                AdSize.BANNER_HEIGHT_90);
        // Find the Ad Container
        LinearLayout adContainer = root.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();

        assert getArguments() != null;
        childName_fromFragment = getArguments().getString("child_name");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(childName_fromFragment);
        mDatabase.keepSynced(false);
        mRecycler_Memorize = root.findViewById(R.id.mRecycler_Memorize);
        mRecycler_Memorize.setHasFixedSize(true);
        mRecycler_Memorize.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FormulaViewViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Parameter, ParameterViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Parameter, ParameterViewHolder>
                        (Parameter.class, R.layout.option_recycler_view_list, ParameterViewHolder.class, mDatabase) {
                    @Override
                    protected void populateViewHolder(ParameterViewHolder viewHolder, Parameter model, int position) {
                        final String post_key = getRef(position).getKey();
                        viewHolder.setSource(model.getSource());
                        viewHolder.setTopic(model.getTopic());
                        viewHolder.setSum(model.getSum());
                        viewHolder.setTotal(model.getTotal());
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                                if (networkInfo != null && networkInfo.isConnected()){
                                    Intent intent = new Intent(getActivity(), McqVersion1.class);
                                    intent.putExtra("key_name", post_key);
                                    intent.putExtra("childName", childName_fromFragment);
                                    Toast.makeText(getContext(), "Please make sure you turn off the rotation of your device",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }else {
                                    Snackbar.make(Objects.requireNonNull(getActivity()).
                                            findViewById(R.id.drawer_layout), "No Network Connection...",
                                            Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                };
        mRecycler_Memorize.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ParameterViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public ParameterViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }
        public void setSource(String source)
        {
            TextView post_source = mView.findViewById(R.id.mTxt_source);
            post_source.setText(source);
        }
        public void setTopic(String topic)
        {
            TextView post_topic = mView.findViewById(R.id.mTxt_topic);
            post_topic.setText(topic);
        }
        public void setSum(String sum)
        {
            TextView post_sum = mView.findViewById(R.id.mTxt_sum);
            post_sum.setText(sum);
        }
        public void setTotal(String total)
        {
            TextView post_total = mView.findViewById(R.id.mTxt_total);
            post_total.setText(total);
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
