package example.com.myweather.adapter.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.myweather.R

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var localTv: TextView = itemView.findViewById(R.id.local_tv)!!
    var todayStateIv: ImageView = itemView.findViewById(R.id.today_state_iv)!!
    var todayStateTv: TextView = itemView.findViewById(R.id.today_state_tv)!!
    var todayTempTv: TextView = itemView.findViewById(R.id.today_temp_tv)!!
    var todayHumidityTv: TextView = itemView.findViewById(R.id.today_humidity_tv)!!
    var tomorrowStateIv: ImageView = itemView.findViewById(R.id.tomorrow_state_iv)!!
    var tomorrowStateTv: TextView = itemView.findViewById(R.id.tomorrow_state_tv)!!
    var tomorrowTempTv: TextView = itemView.findViewById(R.id.tomorrow_temp_tv)!!
    var tomorrowHumidityTv: TextView = itemView.findViewById(R.id.tomorrow_humidity_tv)!!

}