package example.com.myweather

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("consolidated_weather") var consolidatedWeathers: MutableList<ConsolidatedWeather>,
    @SerializedName("time") var time: String,
    @SerializedName("sun_rise") var sunRise: String,
    @SerializedName("sun_set") var sunSet: String,
    @SerializedName("timezone_name") var timezoneName: String,
    @SerializedName("parent") var parent: Parent,
    @SerializedName("sources") var sources: MutableList<Source>,
    @SerializedName("title") var title: String,
    @SerializedName("location_type") var locationType: String,
    @SerializedName("woeid") var woeid: Long,
    @SerializedName("latt_long") var lattLong: String,
    @SerializedName("timezone") var timezone: String
) {

    data class ConsolidatedWeather(
        @SerializedName("id") var id: String,
        @SerializedName("weather_state_name") var weatherStateName: String,
        @SerializedName("weather_state_abbr") var weatherStateAbbr: String,
        @SerializedName("wind_direction_compass") var windDirectionCompass: String,
        @SerializedName("created") var created: String,
        @SerializedName("applicable_date") var applicableDate: String,
        @SerializedName("min_temp") var minTemp: Double,
        @SerializedName("max_temp") var maxTemp: Double,
        @SerializedName("the_temp") var theTemp: Double,
        @SerializedName("wind_speed") var windSpeed: Double,
        @SerializedName("wind_direction") var windDirection: Double,
        @SerializedName("air_pressure") var airPressure: Float,
        @SerializedName("humidity") var humidity: Int,
        @SerializedName("visibility") var visibility: Double,
        @SerializedName("predictability") var predictability: Int
    )

    data class Parent(
        @SerializedName("title") var title: String,
        @SerializedName("location_type") var locationType: String,
        @SerializedName("woeid") var woeid: Long,
        @SerializedName("latt_long") var lattLong: String
    )

    data class Source(
        @SerializedName("title") var title: String,
        @SerializedName("slug") var slug: String,
        @SerializedName("url") var url: String,
        @SerializedName("crawl_rate") var crawl_rate: Int
    )

}