package com.eofelx.eofel.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.eofelx.eofel.R;
import com.eofelx.eofel.utils.Preferences;
import com.eofelx.eofel.views.home.MakeAdActivity;
import com.eofelx.eofel.views.home.MartActivity;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.SliderHomeAdapter;
import com.eofelx.eofel.adapters.SpecialAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.Posts;
import com.eofelx.eofel.models.SliderItem;
import com.eofelx.eofel.models.SpecialModel;
import com.eofelx.eofel.views.home.PosActivity;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeViews extends BaseViews implements BaseViews.OnBackPress {


    private RequestQueue queue;


    RecyclerView recyclerView;
    LinearLayout mart, ads, promo, pos;

    private Posts content;

    //private UnifiedNativeAd nativeAd;
    private static HomeViews views;

    public static HomeViews getInstance() {
        if (views == null) {
            views = new HomeViews();
        }
        return views;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_home_design, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();// getActivity();
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        queue = Volley.newRequestQueue(requireContext().getApplicationContext());
        /*recyclerView = view.findViewById(R.id.home_recycler_view);
        linearLayout = view.findViewById(R.id.layout_loading);
        content = new Posts();*/
        recyclerView = view.findViewById(R.id.design_recycler);

        SliderView sliderView = view.findViewById(R.id.sliderView);
        int[] url = new int[] {
          R.raw.slider1, R.raw.slider2, R.raw.slider3, R.raw.slider4
        };
        List<SliderItem> items = new ArrayList<>();
        for (int j : url) {
            items.add(new SliderItem(j));
        }
        sliderView.setSliderAdapter(new SliderHomeAdapter(items, new SliderHomeAdapter.OnItemClickListener() {
            @Override
            public void onClick(SliderItem item) {
            }
        }));
        sliderView.startAutoCycle();
        int[] urls = new int[] {
                R.raw.slider1, R.raw.slider2, R.raw.slider3, R.raw.slider4,
                R.raw.one, R.raw.two, R.raw.three
        };
        List<SpecialModel> models = new ArrayList<>();
        for (int i : urls) {
            models.add(new SpecialModel(i, getResources().getString(R.string.special_title)));
        }
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new SpecialAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {
            }
        }));

       // getAllPost(40);

        mart = view.findViewById(R.id.home_mart);
        ads = view.findViewById(R.id.home_ads);
        promo = view.findViewById(R.id.home_promo);
        pos = view.findViewById(R.id.home_pos);

        System.out.println(Preferences.getToken(requireContext()));

        generateClick();
    }

    private void generateClick() {
        mart.setOnClickListener(v -> startActivity(new Intent(requireActivity(), MartActivity.class)));
        ads.setOnClickListener(v -> startActivity(new Intent(requireActivity(), MakeAdActivity.class)));
        pos.setOnClickListener(v -> startActivity(new Intent(requireActivity(), PosActivity.class)));
    }

    /* private void refreshAd(View view) {
         AdLoader.Builder builder = new AdLoader.Builder(getContext(), ADMOB_AD_UNIT_ID);
         builder.forUnifiedNativeAd(
                 new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                     // OnUnifiedNativeAdLoadedListener implementation.
                     @Override
                     public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                         // If this callback occurs after the activity is destroyed, you must call
                         // destroy and return or you may get a memory leak.
                         boolean isDestroyed;
                         isDestroyed = getActivity().isDestroyed();
                         if (isDestroyed || getActivity().isFinishing() || getActivity().isChangingConfigurations()) {
                             unifiedNativeAd.destroy();
                             return;
                         }
                         // You must call destroy on old ads when you are done with them,
                         // otherwise you will have a memory leak.
                         if (nativeAd != null) {
                             nativeAd.destroy();
                         }
                         nativeAd = unifiedNativeAd;
                         FrameLayout frameLayout = view.findViewById(R.id.fl_adplaceholder);
                         UnifiedNativeAdView adView =
                                 (UnifiedNativeAdView) getLayoutInflater()
                                         .inflate(R.layout.ad_native, null);
                         populateUnifiedNativeAdView(unifiedNativeAd, adView);
                         frameLayout.removeAllViews();
                         frameLayout.addView(adView);
                     }
                 });

         VideoOptions videoOptions =
                 new VideoOptions.Builder().setStartMuted(false).build();

         NativeAdOptions adOptions =
                 new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

         builder.withNativeAdOptions(adOptions);

         AdLoader adLoader =
                 builder
                         .withAdListener(
                                 new AdListener() {
                                     @Override
                                     public void onAdFailedToLoad(LoadAdError loadAdError) {
                                         @SuppressLint("DefaultLocale") String error =
                                                 String.format(
                                                         "domain: %s, code: %d, message: %s",
                                                         loadAdError.getDomain(),
                                                         loadAdError.getCode(),
                                                         loadAdError.getMessage());
                                         Toast.makeText(
                                                 requireActivity(),
                                                 "Failed to load native ad with error " + error,
                                                 Toast.LENGTH_SHORT)
                                                 .show();
                                     }
                                 })
                         .build();

         adLoader.loadAd(new AdRequest.Builder().build());
     }


     private void populateUnifiedNativeAdView(@NonNull UnifiedNativeAd nativeAd, @NonNull UnifiedNativeAdView adView) {
         // Set the media view.
         adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

         // Set other ad assets.
         adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
         adView.setBodyView(adView.findViewById(R.id.ad_body));
        *//* adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));*//*
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

       *//* if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }*//*

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        *//*if (vc.hasVideoContent()) {
            videoStatus.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 video asset.",
                    vc.getAspectRatio()));

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    refresh.setEnabled(true);
                    videoStatus.setText("Video status: Video playback has ended.");
                    super.onVideoEnd();
                }
            });
        } else {
            videoStatus.setText("Video status: Ad does not contain a video asset.");
            refresh.setEnabled(true);
        }*//*
    }
*/
   /* public void getAllPost(int page) {
        List<Posts> posts = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Query.get().getAllPosts(page), null, response -> {
                    linearLayout.setVisibility(View.GONE);
                    try {
                        for (int x = 0; x < response.length(); x++) {
                            JSONObject object = response.getJSONObject(x);

                            content.setId(object.getString("id"));
                            content.setUrl(object.getString("link"));
                            content.setDate(object.getString("date"));
                            content.setMediaFeatured(object.getString("featured_media"));

                            JSONObject title = object.getJSONObject("title");
                            for (int i = 0; i < title.length(); i++) {
                                content.setTitle(title.getString("rendered"));
                            }

                            JSONObject excerpt = object.getJSONObject("excerpt");
                            for (int i = 0; i < excerpt.length() ; i++) {
                                content.setExcerpt(excerpt.getString("rendered"));
                            }

                            JSONObject cont = object.getJSONObject("content");
                            for (int i = 0; i < cont.length(); i++) {
                                content.setContent(cont.getString("rendered"));
                            }

                            content.setCategory(object.getString("categories"));

                            content.setReplies(Query.get().getCommentsUrls(content.getId()));

                            posts.add(new Posts(
                                    content.getId(),
                                    content.getUrl(),
                                    content.getDate(),
                                    content.getTitle(),
                                    content.getMediaFeatured(),
                                    content.getContent(),
                                    content.getExcerpt(),
                                    content.getCategory(),
                                    content.getReplies()
                            ));
                        }
                        System.out.println(posts.size());
                        recyclerView.setAdapter(new PostAdapter(posts, new Adapter.OnPostsClickListener() {
                            @Override
                            public void onClick(Posts post) {
                                System.out.println("Content: " +post.getId());
                                PostViews postViews = new PostViews();
                                Bundle bundle = new Bundle();
                                bundle.putString("id", post.getId());
                                bundle.putString("title", post.getTitle());
                                bundle.putString("content", post.getContent());
                                bundle.putString("category", post.getCategory());
                                bundle.putString("replies", post.getReplies());
                                postViews.setArguments(bundle);
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.main_fragment, postViews)
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                        .addToBackStack(HomeViews.class.getSimpleName())
                                        .commit();
                            }
                        }));
                        LinearLayoutManager manager = new LinearLayoutManager(getContext().getApplicationContext());
                        manager.setOrientation(RecyclerView.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e("Error request: ", error.getMessage()));
        queue.add(request);
    }*/
    @Override
    public void backPress() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
