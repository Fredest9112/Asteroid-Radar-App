package com.ar.asteroidradar.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ar.asteroidradar.R
import com.squareup.picasso.Picasso
import com.ar.asteroidradar.data.Asteroid
import com.ar.asteroidradar.data.PictureOfDay
import com.ar.asteroidradar.main.AsteroidAdapter
import com.ar.asteroidradar.model.PictureApiStatus

@BindingAdapter("isHazardous")
fun bindIsAsteroidHazardous(imageView: ImageView, isHazardous: Boolean){
    if(isHazardous){
        imageView.contentDescription = imageView.resources.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.contentDescription = imageView.resources.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("pictureOfDayDC")
fun bindPictureOfTheDayContentDesc(imageView: ImageView, pictureOfDay: PictureOfDay?){
    pictureOfDay.let {
        imageView.contentDescription = "${imageView.resources.getString(R.string.image_day)} ${pictureOfDay?.title}"
    }
}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, asteroids: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(asteroids)
}

@BindingAdapter("pictureOfTheDay")
fun bindPictureOfTheDayImage(imageView: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty()) {
            Picasso.get().load(it).fit().into(imageView)
        }
    }
}

@BindingAdapter("dailyPicture_Status")
fun bindDailyPictureStatus(statusImageView: ImageView, status: PictureApiStatus) {
    statusImageView.bringToFront()
    when (status) {
        PictureApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PictureApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PictureApiStatus.ERROR -> {
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
