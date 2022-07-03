package com.health.healthapp;

public class Person {
    private static int age;
    private static double activity;
    private static double helm;
    private static double weight;

    private static double diet;
    private static double bulk;

    private static String goal;

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Person.age = age;
    }

    public static double getActivity() {
        return activity;
    }

    public static void setActivity(double activity) {
        Person.activity = activity;
    }

    public static double getHelm() {
        return helm;
    }

    public static void setHelm(double helm) {
        Person.helm = helm;
    }

    public static double getWeight() {
        return weight;
    }

    public static void setWeight(double weight) {
        Person.weight = weight;
    }

    public static double getDiet() {
        return diet;
    }

    public static void setDiet(double diet) {
        Person.diet = diet;
    }

    public static double getBulk() {
        return bulk;
    }

    public static void setBulk(double bulk) {
        Person.bulk = bulk;
    }

    public static String getGoal() {
        return goal;
    }

    public static void setGoal(String goal) {
        Person.goal = goal;
    }




}
