package example.com.myweather.contract

import example.com.myweather.model.MainModel
import example.com.myweather.retrofit.response.Location

interface MainContract {

    interface View {
        fun showLoadingBar()
        fun hideLoadingBar()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun initModel(model: MainModel)
        fun initAdapterView(adapterView: WeatherAdapterContract.View)
        fun initAdapterModel(adapterModel: WeatherAdapterContract.Model)
        fun getLocationSearch(isSwipe: Boolean)
        fun addWeather(locations: MutableList<Location>)
    }

}