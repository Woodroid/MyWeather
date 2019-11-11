package example.com.myweather.contract

import example.com.myweather.retrofit.response.Location

interface WeatherAdapterContract {

    interface View{
        fun notifyAdapter()
    }
    interface Model{
        fun clearItems()
        fun addItems(locations: MutableList<Location>)
    }

}