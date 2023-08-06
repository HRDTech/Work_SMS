package com.solucioneshr.soft.work_sms.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solucioneshr.soft.work_sms.R
import com.solucioneshr.soft.work_sms.databinding.LayoutItemSmsNumberBinding
import com.solucioneshr.soft.work_sms.model.SmsModel
import com.solucioneshr.soft.work_sms.util.DateConverters

class AdapterNumberSms(private var listener: OnItemClickListener, private var listItems: ArrayList<SmsModel>): RecyclerView.Adapter<AdapterNumberSms.ViewHolderNumberSms>() {

    interface OnItemClickListener{
        fun onSelectSms(data: SmsModel)
    }

    class ViewHolderNumberSms(view: View): RecyclerView.ViewHolder(view){
        private val binding = LayoutItemSmsNumberBinding.bind(view)

        fun bind(item: SmsModel, listener: OnItemClickListener){
            binding.textPhoneNumberSMS.text = item.numberPhone
            binding.textDateNumberSMS.text = DateConverters().fromTimestamp(item.date.toLong())

            binding.root.setOnClickListener {
                listener.onSelectSms(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNumberSms {
        return ViewHolderNumberSms(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_sms_number, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolderNumberSms, position: Int) {
        val item = listItems[position]
        holder.bind(item, listener)
    }
}