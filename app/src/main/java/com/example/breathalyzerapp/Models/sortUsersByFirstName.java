package com.example.breathalyzerapp.Models;

import java.util.Comparator;

// Sorts users by name in ascending alphabetical order by firstname
public class sortUsersByFirstName implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user1.getFirstname().compareToIgnoreCase(user2.getFirstname());
    }
}
