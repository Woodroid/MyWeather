package example.com.myweather.retrofit.response

import com.google.gson.annotations.SerializedName

data class LocationSearch(@SerializedName("title") var title: String,
                          @SerializedName("location_type") var locationType: String,
                          @SerializedName("woeid") var woeid: String,
                          @SerializedName("latt_long") var lattLong: String)