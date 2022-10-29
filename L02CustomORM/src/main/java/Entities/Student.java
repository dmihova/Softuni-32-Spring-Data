package Entities;

import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

@Entity(name ="students")
public class Student {
    @Id
    private int id;

    @Column(name="first_name")
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
