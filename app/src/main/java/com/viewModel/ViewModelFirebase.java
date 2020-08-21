package com.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.models.Peoples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewModelFirebase extends ViewModel {

    final MutableLiveData<List<Peoples>> mutableLiveData = new MutableLiveData<>();
    private static ViewModelFirebase INSTANCE;
    private List<Peoples> peoples = new ArrayList<>();

    public static ViewModelFirebase getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ViewModelFirebase();
        }
        return INSTANCE;
    }

    public MutableLiveData<List<Peoples>> getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    peoples.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Peoples people = dataSnapshot.getValue(Peoples.class);
                        peoples.add(people);
                        mutableLiveData.setValue(peoples);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return mutableLiveData;
    }
}
