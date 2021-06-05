package com.example.footballleagueapplication.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.footballleagueapplication.R


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}



fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(getCircularProgressDrawable(this.rootView.context))
        .into(this)
}


fun getCircularProgressDrawable(view: Context): CircularProgressDrawable? {
    val circularProgressDrawable = CircularProgressDrawable(view)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            circularProgressDrawable.setColorSchemeColors(context.getColor(R.color.colorAccent));
//        }
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun RecyclerView.animateItems() {
    layoutAnimation = AnimationUtils.loadLayoutAnimation(
        this.context,
        R.anim.layout_animation_fall_down
    ) as LayoutAnimationController
}
fun AppCompatActivity.scrollable() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    )
}

fun Context.goTo(dest: AppCompatActivity, name: String = "", value: String = "") {
    startActivity(Intent(this, dest::class.java).apply {
        putExtra(name, value)
    })



    fun Context.CgotoUrl1(url: String) {

        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun Context.share(message: String = "") {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(
            Intent.EXTRA_TEXT, ""
        )
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, ""))
    }


}

