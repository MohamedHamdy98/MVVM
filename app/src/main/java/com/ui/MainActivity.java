package com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.adapter.RecyclerViewFirebase;
import com.adapter.RecyclerViewPosts;
import com.adapter.RecyclerViewUsers;
import com.viewModel.ViewModelFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mh.mvp.R;
import com.mh.mvp.databinding.ActivityMainBinding;
import com.models.Peoples;
import com.viewModel.ViewModel;
import com.viewModel.ViewModelUsers;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewModel viewModel;
    private RecyclerViewPosts recyclerViewPosts;
    private ViewModelUsers viewModelUsers;
    private RecyclerViewUsers recyclerViewUsers;
    private Handler handler;
    private Peoples peoples;
    private ViewModelFirebase viewModelFirebase;
    private RecyclerViewFirebase recyclerViewFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // get Posts
//        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
//        viewModel.getPosts();
//        binding.recyclerView.setHasFixedSize(true);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerViewPosts = new RecyclerViewPosts();
//        binding.recyclerView.setAdapter(recyclerViewPosts);
//        viewModel.mutableLiveData.observe(this, new Observer<List<Posts>>() {
//            @Override
//            public void onChanged(List<Posts> posts) {
//                recyclerViewPosts.setList(posts);
//            }
//        });
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
        viewModelFirebase = ViewModelProviders.of(this).get(ViewModelFirebase.class);
        peoples = new Peoples();
        binding.setPeople(peoples);
        ViewModelFirebase.getINSTANCE().getData().observe(this,
                new Observer<List<Peoples>>() {
            @Override
            public void onChanged(List<Peoples> peoples) {
                recyclerViewFirebase = new RecyclerViewFirebase();
                binding.recyclerView.setHasFixedSize(true);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.recyclerView.setAdapter(recyclerViewFirebase);
                recyclerViewFirebase.setList(peoples);
            }
        });
        handler = new Handler(binding.editTextFirstName, binding.editTextLastName);
        binding.setOnClickMainHandler(handler);
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
