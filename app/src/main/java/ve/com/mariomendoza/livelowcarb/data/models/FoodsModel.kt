package ve.com.mariomendoza.livelowcarb.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodsModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("calories")
    @Expose
    var calories: Double? = null
    @SerializedName("fat")
    @Expose
    var fat: Double? = null
    @SerializedName("carbs")
    @Expose
    var carbs: Double? = null
    @SerializedName("protein")
    @Expose
    var protein: Double? = null

    constructor(id: Int?, name: String?, calories: Double?, fat: Double?, carbs: Double?, protein: Double?) {
        this.id = id
        this.name = name
        this.calories = calories
        this.fat = fat
        this.carbs = carbs
        this.protein = protein
    }

    constructor() {}

    override fun toString(): String {
        return "FoodsModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}'
    }
}