package example.com.myweather

class MainPresenter : MainContract.Presenter {

    var view: MainContract.View? = null
    var model: MainModel? = null
    var adapterView: WeatherAdapterContract.View? = null
    var adapterModel: WeatherAdapterContract.Model? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun initModel(model: MainModel) {
        this.model = model
    }

    override fun initAdapterView(adapterView: WeatherAdapterContract.View) {
        this.adapterView = adapterView
    }

    override fun initAdapterModel(adapterModel: WeatherAdapterContract.Model) {
        this.adapterModel = adapterModel
    }

    override fun getLocationSearch(isSwipe: Boolean) {
        if (!isSwipe) {
            view?.showLoadingBar()
        }
        adapterModel?.clearItems()
        model?.getLocationSearch()
    }

    override fun addWeather(locations: MutableList<Location>) {
        adapterModel?.addItems(locations)
        adapterView?.notifyAdapter()

        view?.hideLoadingBar()
    }
}