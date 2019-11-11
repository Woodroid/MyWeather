package example.com.myweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import example.com.myweather.R
import example.com.myweather.adapter.viewHolder.WeatherHeaderViewHolder
import example.com.myweather.adapter.viewHolder.WeatherViewHolder
import example.com.myweather.contract.WeatherAdapterContract
import example.com.myweather.retrofit.WeatherApi
import example.com.myweather.retrofit.response.Location

class WeatherAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    WeatherAdapterContract.View, WeatherAdapterContract.Model {

    private val HEADER_SIZE = 1
    private val TYPE_HEADER = 0
    private val TYPE_BODY = 1

    var locations: MutableList<Location> = ArrayList()
    var glide = Glide.with(context)

    // degree symbol 0x00B0
    val degree: Char = 0x00B0.toChar()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_BODY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            WeatherHeaderViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.weather_list_header,
                    parent, false
                )
            )
        } else {
            WeatherViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.weather_list_item,
                    parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return if (locations.isNotEmpty()) {
            locations.size + HEADER_SIZE
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != TYPE_HEADER) {
            val itemHolder = holder as WeatherViewHolder
            val location: Location = locations[position - HEADER_SIZE]

            itemHolder.localTv.text = location.parent.title

            // 오늘
            val today: Location.ConsolidatedWeather? = location.consolidatedWeathers[0]
            itemHolder.todayStateTv.text = today?.weatherStateName
            glide.asBitmap()
                .load(getImageUrl(today?.weatherStateAbbr))
                .into(itemHolder.todayStateIv)
            val sb: StringBuilder = StringBuilder()
            sb.append(today?.theTemp?.toInt()).append(degree).append("C")
            itemHolder.todayTempTv.text = sb
            itemHolder.todayHumidityTv.text = "${today?.humidity}%"

            // 내일
            val tomorrow: Location.ConsolidatedWeather? = location.consolidatedWeathers[1]
            itemHolder.tomorrowStateTv.text = tomorrow?.weatherStateName
            glide.asBitmap()
                .load(getImageUrl(tomorrow?.weatherStateAbbr))
                .into(itemHolder.tomorrowStateIv)
            val sb1 = StringBuilder()
            sb1.append(tomorrow?.theTemp?.toInt()).append(degree).append("C")
            itemHolder.tomorrowTempTv.text = sb1
            itemHolder.tomorrowHumidityTv.text = "${tomorrow?.humidity}%"

        }
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(locations: MutableList<Location>) {
        this.locations.addAll(locations)
    }

    override fun clearItems() {
        this.locations.clear()
    }

    fun getImageUrl(weatherStateAbbr: String?): String {
        return WeatherApi.IMAGE_URL + "${weatherStateAbbr}.png"
    }

}