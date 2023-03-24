package com.danisbana.danisbanaapp.core.util

import android.app.Activity
import android.content.Context
import android.util.Log
import com.danisbana.danisbanaapp.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdmobHelper(val context: Context) {
    private var rewardedAd: RewardedAd? = null
    private var successCallBack: OnUserEarnedRewardListener = OnUserEarnedRewardListener {}
    private var errorCallBack: ()->Unit =  {}
    private var adLoadingState: (Boolean) -> Unit =  {}

    private var isLoading: Boolean = false

    fun setOnSuccess(callBack : (RewardItem)->Unit) {
        this.successCallBack = OnUserEarnedRewardListener { callBack.invoke(it) }
    }

    fun setOnFailure(callBack : ()->Unit) {
        this.errorCallBack = callBack
    }

    fun setOnAdLoading(callBack : (Boolean)->Unit) {
        this.adLoadingState = callBack
    }

    fun loadRewardedAds() {
        if (isLoading) return
        isLoading = true
        adLoadingState.invoke(isLoading)
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            context,
            AD_UNIT_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    rewardedAd = null
                    isLoading = false
                    adLoadingState.invoke(isLoading)
                    Log.e("TAG", "onAdFailedToLoad: $adError", )
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                    isLoading = false
                    adLoadingState.invoke(isLoading)
                    rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent()
                            errorCallBack.invoke()
                        }
                    }
                    rewardedAd?.show(
                        context as Activity,
                        successCallBack
                    )

                }
            }
        )
    }

    companion object {
        private const val AD_UNIT_ID = BuildConfig.admob_test_app_id
    }
}