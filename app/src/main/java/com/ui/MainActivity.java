package com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.adapter.RecyclerViewFirebase;
import com.adapter.RecyclerViewPosts;
import com.adapter.RecyclerViewUsers;
import com.models.Posts;
import com.viewModel.ViewModelFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mh.mvp.R;
import com.mh.mvp.databinding.ActivityMainBinding;
import com.models.Peoples;
import com.viewModel.ViewModel;
import com.viewModel.ViewModelUsers;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ViewModel viewModel;
    private RecyclerViewPosts recyclerViewPosts;
    private ViewModelUsers viewModelUsers;
    private RecyclerViewUsers recyclerViewUsers;
    private Handler handler;
    private Peoples peoples;
    private ViewModelFirebase viewModelFirebase;
    private RecyclerViewFirebase recyclerViewFirebase;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // get Posts
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getPosts();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPosts = new RecyclerViewPosts();
        binding.recyclerView.setAdapter(recyclerViewPosts);
        viewModel.mutableLiveData.observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                recyclerViewPosts.setList(posts);
            }
        });
        // get Users
//        viewModelUsers = ViewModelProviders.of(this).get(ViewModelUsers.class);
//        viewModelUsers.getUsers();
//        binding.recyclerView.setHasFixedSize(true);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerViewUsers = new RecyclerViewUsers();
//        binding.recyclerView.setAdapter(recyclerViewUsers);
//        viewModelUsers.mutableLiveData.observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//                recyclerViewUsers.setListUser(users);
//            }
//        });
//        viewModelFirebase = ViewModelProviders.of(this).get(ViewModelFirebase.class);
//        peoples = new Peoples();
//        binding.setPeople(peoples);
//        ViewModelFirebase.getINSTANCE().getData().observe(this,
//                new Observer<List<Peoples>>() {
//            @Override
//            public void onChanged(List<Peoples> peoples) {
//                recyclerViewFirebase = new RecyclerViewFirebase();
//                binding.recyclerView.setHasFixedSize(true);
//                binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                binding.recyclerView.setAdapter(recyclerViewFirebase);
//                recyclerViewFirebase.setList(peoples);
//            }
//        });
//        handler = new Handler(binding.editTextFirstName, binding.editTextLastName);
//        binding.setOnClickMainHandler(handler);

        // RX JAVA...............................................................................
//        PublishSubject<String> hot = PublishSubject.create();
//        hot.subscribe(i-> Log.d(TAG,"OnCreate: Students level one : "+i));
//        hot.onNext("A");
//        sleep(1000);
//        hot.onNext("B");
//        sleep(1000);
//        hot.onNext("C");
//        sleep(1000);
//        hot.subscribe(i-> Log.d(TAG,"OnCreate: Students level two : "+i));
//        hot.onNext("D");
//        sleep(1000);
//        hot.onNext("E");
//        Observable.just(1,2,3,4,5)
//                .doOnNext(i-> Log.d(TAG,"momo upStraem: "+i+"current thread "+ Thread.currentThread().getName()))
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation())
//                .subscribe(c->Log.d(TAG,"momo downStraem: "+c+"current thread "+ Thread.currentThread().getName()));
//        Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(ObservableEmitter<Object> e) throws Exception {
//               getDataApi(e);
//            }
//        }).doOnNext(i->Log.d(TAG,"momo upStraem:"+i))
//                .flatMap(new Function<Object, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Object o) throws Exception {
//                        return getDataApi(o.toString());
//                    }
//                })
//                .subscribe(c->{
//                    Log.d(TAG,"momo downStraem: "+c);
//                });

    }
//    private Observable getDataApi(Object o){
//        Observable observable = Observable.just("momo hahahah"+o);
//        observable.subscribe(x->{
//            Log.d(TAG,"momo api: "+x);
//            viewModel.getPosts();
//        });
//        return observable;
//    }

    private void sleep(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Handler {
        EditText editText_firstName, editText_lastName;

        public Handler(EditText editText_firstName, EditText editText_lastName) {
            this.editText_firstName = editText_firstName;
            this.editText_lastName = editText_lastName;
        }

        public void setData(View view) {
            String first_name = editText_firstName.getText().toString();
            String last_name = editText_lastName.getText().toString();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("users").child(first_name);
            peoples.setFirstName(first_name);
            peoples.setLastName(last_name);
            databaseReference.setValue(peoples);
        }
    }
}
