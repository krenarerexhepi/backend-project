package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder fragment containing a simple view.
 * This fragment will display the data for the free version of application
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    // We use progress bar to display the spinner where
    // the data will be retrived from library
    ProgressBar spinner;

    InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeBtn = (Button) root.findViewById(R.id.btnTellJoke);

        // Initialize the InterstitialAd and set the unit Id
         mInterstitialAd = new InterstitialAd(getContext());
         mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad));

        spinner = (ProgressBar) root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        // We load the add to get the data because when we click the button to display the add
        LoadAdd();
        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    CallTask();
                }
            }

        });
        DispalyAd(root);
        return root;
    }
    private void LoadAdd() {

        //Set listener to know that we need to request new Intertitial
        mInterstitialAd.setAdListener(new AdListener() {
             @Override
              public void onAdClosed() {
               requestNewInterstitial();
                CallTask();
            }
         });
        requestNewInterstitial();

    }

    //with this method we call our new ad to be dispalyed
     private void requestNewInterstitial() {
         AdRequest adRequest = new AdRequest.Builder()
                 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                 .build();
         mInterstitialAd.loadAd(adRequest);
     }
// This method call our EndpointsAsyncTaskJoke class which call our library
    private void CallTask() {
        spinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTaskJoke().execute(new Pair<>(getContext(), ""));

    }

    private void DispalyAd(View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
