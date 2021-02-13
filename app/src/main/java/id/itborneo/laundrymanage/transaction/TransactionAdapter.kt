package id.itborneo.laundrymanage.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.utils.toRupiah
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter(val clickListener: (TransactionModel) -> Unit) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    var list = listOf<TransactionModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: TransactionModel) {
            itemView.apply {
                tvIdTransaction.text = data.id.toString()
                tvProgressStatus.text = data.progress_status
                tvTotalPrice.text = data.totalPrice?.toRupiah()
                tvPaymentStatus.text = data.payment_status
                tvCustomerName.text = data.customer_name

                setOnClickListener {
                    clickListener(data)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


}