package com.nazar.sobatmasjid.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.nazar.sobatmasjid.utils.extensions.setImageFromUrl

class ImageSliderAdapter(
    private val context: Context,
    private val images: List<String>
) : PagerAdapter() {
    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1
    override fun getCount(): Int = images.size
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        (container as ViewPager).removeView(`object` as View)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageFromUrl(images[position])
        container.addView(imageView)
        return imageView
    }
}