package example.com.myweather

interface WeatherAdapterContract {

    interface View{
        fun notifyAdapter()
    }
    interface Model{
        fun clearItems()
        fun addItems(locations: MutableList<Location>)
    }

}