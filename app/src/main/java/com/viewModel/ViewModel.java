package com.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.data.RetrofitClient;
import com.models.Posts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private static final String TAG = "ViewModel";
    public MutableLiveData<List<Posts>> mutableLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void getPosts() {
        Single<List<Posts>> observable = RetrofitClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o->mutableLiveData.setValue(o),e->Log.d(TAG,"Error:"+e)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
