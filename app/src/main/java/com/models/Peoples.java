package com.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mh.mvp.BR;

public class Peoples extends BaseObservable {
    private String firstName;
    private String lastName;

    public Peoples() {
    }

    public Peoples(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
