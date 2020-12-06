package com.hongzc.mhelloworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IsPlayListener extends ViewModel {
        //将“秒钟”这个字段用MutableLiveData包装起来
        private MutableLiveData<Boolean> isPlay;
        public LiveData<Boolean> getCurrentIsPlay()
        {
            if (isPlay == null)
            {
                isPlay = new MutableLiveData<>();
            }
            return isPlay;
        }
}
