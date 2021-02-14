package id.itborneo.laundrymanage.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.laundrymanage.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val clickListener: (UsersModel) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var list = listOf<UsersModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: UsersModel) {
            itemView.apply {
                tvName.text = data.nama
                tvEmail.text = data.email
                tvRole.text = data.role

                setOnClickListener {
                    clickListener(data)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


}