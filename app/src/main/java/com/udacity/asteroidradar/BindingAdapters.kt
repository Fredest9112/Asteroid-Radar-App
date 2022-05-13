package com.udacity.asteroidradar

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.main.PictureApiStatus

@BindingAdapter("pictureOfTheDay")
fun bindPictureOfTheDayImage(imageView: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty()) {
            Log.i("bindPicture", "$url")
            Picasso.get().load(it).fit().into(imageView)
        }
    }
}

@BindingAdapter("dailyPicture_Status")
fun bindDailyPictureStatus(statusImageView: ImageView, status: PictureApiStatus) {
    statusImageView.bringToFront()
    when (status) {
        PictureApiStatus.LOADING -> {
            Log.i("image", "Loading")
            statusImageView.visibility = View.VISIBLE

            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PictureApiStatus.DONE -> {
            Log.i("image", "Done")
            statusImageView.visibility = View.GONE
        }
        PictureApiStatus.ERROR -> {
            Log.i("image", "Error")
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.signal_cellular_connected_no_internet)
        }
    }
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
