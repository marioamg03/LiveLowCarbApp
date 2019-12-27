package ve.com.mariomendoza.livelowcarb.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodsModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("calories")
    @Expose
    public Double calories;
    @SerializedName("fat")
    @Expose
    public Double fat;
    @SerializedName("carbs")
    @Expose
    public Double carbs;
    @SerializedName("protein")
    @Expose
    public Double protein;

    public FoodsModel(Integer id, String name, Double calories, Double fat, Double carbs, Double protein) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public FoodsModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "FoodsModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}';
    }


}
