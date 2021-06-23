package jacs.apps.mlbucket.ui.image_classification

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.skydoves.progressview.ProgressView
import com.skydoves.progressview.progressView
import jacs.apps.mlbucket.R
import jacs.apps.mlbucket.databinding.ProgressViewBinding

import org.tensorflow.lite.support.label.Category

class ResultAdapter (private val context: Context,
                     private val dataSource: ArrayList<Category>) : BaseAdapter(){
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Category {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val category = getItem(position)

        val root = ProgressViewBinding.inflate(LayoutInflater.from(context))
        var progress = root.progressView1
        progress.progress = category.score
        progress.labelText = category.label + " :" + category.score.toString() + "%"


        return root.root
    }
}