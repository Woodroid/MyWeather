package example.com.myweather

import android.util.Log
import retrofit2.Call

class MainModel(val presenter: MainPresenter) {

    var locations: MutableList<Location> = ArrayList()

    var i = 0

    // query = se 데이터 요청
    fun getLocationSearch() {
        // 갱신시 locations 값 초기화
        locations.clear()
        // 갱신시 i값 초기화
        i = 0

        WeatherApi.getApi()
            .getLocationSearch()
            .enqueue(object : WeatherApi.Callback<List<LocationSearch>>() {
                override fun onResponseImpl(
                    call: Call<List<LocationSearch>>,
                    body: List<LocationSearch>?
                ) {
                    if (null != body && body.isNotEmpty()) {
                        // 각각의 woeid에 맞는 날씨 정보를 요청.
                        // 일괄처리를 위한 index값 저장
                        i = body.size
                        for (locationSearch: LocationSearch in body) {
                            getLocation(locationSearch.woeid)
                        }
                    }

                }
            })
    }

    // 날씨정보 요청
    fun getLocation(woeid: String) {
        WeatherApi.getApi()
            .getLocation(woeid)
            .enqueue(object : WeatherApi.Callback<Location>() {
                override fun onResponseImpl(call: Call<Location>, body: Location?) {
                    if (null != body) {
                        locations.add(body)
                        // 지역 날씨 정보 값이 검색한 지역 개수와 동일할 경우 리스트를 갱신
                        if (locations.size == i) {
                            presenter.addWeather(locations)
                        }
                    }
                }
            })
    }

}