package raq.lop.io.marvelkotlinmvvm.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.time.Duration

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG){

    Toast.makeText(
        requireContext(),
        message,
        duration
    ).show()

}

fun View.show(){
    visibility = View.VISIBLE
}


fun View.hide(){
    visibility = View.INVISIBLE
}

fun String.limitText(characters: Int): String{
    if(this.length>characters){
        val firstChar = 0
        return "${this.substring(firstChar, characters)}..."
    }
    return this
}