package com.example.personmicroservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String name;
    @NonNull private String location;

    public Person(@NonNull String name, @NonNull String location) {
        this.name = name;
        this.location = location;
    }

}