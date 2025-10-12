package com;

public class University {
    private String name;
    private int establishedYear;

    public University(String name) {
        this.name = name;
    }

    public int getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(int establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getName() {
        return name;
    }

    public void info() {
        System.out.println(name + " was established in " + establishedYear + ".");
    }
}
