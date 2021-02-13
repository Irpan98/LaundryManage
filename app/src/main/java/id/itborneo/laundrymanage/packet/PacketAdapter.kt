package id.itborneo.laundrymanage.packet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.laundrymanage.R
import kotlinx.android.synthetic.main.item_packet.view.*

class PacketAdapter(val clickListener: (PacketModel) -> Unit) :
    RecyclerView.Adapter<PacketAdapter.ViewHolder>() {

    var list = listOf<PacketModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(Packet: PacketModel) {
            itemView.apply {
                tvName.text = Packet.name
                tvPrice.text = Packet.price
                tvNote.text = Packet.note

                setOnClickListener {
                    clickListener(Packet)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_packet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


}