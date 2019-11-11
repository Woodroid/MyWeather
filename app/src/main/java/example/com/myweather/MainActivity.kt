package example.com.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    var presenter: MainPresenter = MainPresenter()
    var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
        presenter.initModel(MainModel(presenter))
        adapter = WeatherAdapter(this)
        presenter.initAdapterView(adapter!!)
        presenter.initAdapterModel(adapter!!)

        weather_rv.adapter = adapter
        weather_rv.layoutManager = LinearLayoutManager(this)

        presenter.getLocationSearch(false)

        weather_srl.setOnRefreshListener {
            presenter.getLocationSearch(true)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoadingBar() {
        loading_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        loading_bar.visibility = View.GONE
        weather_srl.isRefreshing = false
    }

}
