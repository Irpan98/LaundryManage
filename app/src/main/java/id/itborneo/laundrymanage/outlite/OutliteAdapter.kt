package id.itborneo.laundrymanage.outlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.laundrymanage.R
import kotlinx.android.synthetic.main.item_outlite.view.*

class OutliteAdapter(val clickListener: (Outlite) -> Unit) :
    RecyclerView.Adapter<OutliteAdapter.ViewHolder>() {

    var list = listOf<Outlite>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(outlite: Outlite) {
            itemView.apply {
                tvName.text = outlite.nama
                tvAddress.text = outlite.alamat

                setOnClickListener {
                    clickListener(outlite)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_outlite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


}