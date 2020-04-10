package com.android.coolwinks.flickr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.coolwings.R
import com.android.coolwinks.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_flickr_image.*

/**
 * A simple [Fragment] subclass.
 */
class FlickrImageFragment : BaseFragment() {
    private val args: FlickrImageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flickr_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = args.imageUrl
        imgThumbnail.isZoomEnabled = true

        //Circular progress placeholder
        val circularProgressDrawable = CircularProgressDrawable(appCompatActivity)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(appCompatActivity,R.color.white))
        circularProgressDrawable.start()

        Glide.with(appCompatActivity)
            .load(imageUrl)
            .fitCenter()
            .error(R.drawable.ic_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(circularProgressDrawable)
            .into(imgThumbnail)
    }

}
