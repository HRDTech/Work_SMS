package com.solucioneshr.soft.work_sms.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solucioneshr.soft.work_sms.R
import com.solucioneshr.soft.work_sms.databinding.LayoutItemSmsBinding
import com.solucioneshr.soft.work_sms.model.SmsModel

class AdapterReadSMS(private var listItems: ArrayList<SmsModel>):
    RecyclerView.Adapter<AdapterReadSMS.ViewHolderReadSms>() {


    class ViewHolderReadSms(view: View): RecyclerView.ViewHolder(view){
        private val binding = LayoutItemSmsBinding.bind(view)

        fun bind(item: SmsModel){
            binding.textNumberSMS.text = item.numberPhone
            binding.messageSMS.text = item.message

            binding.root.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReadSms {
        return ViewHolderReadSms(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_sms, parent, false))
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolderReadSms, position: Int) {
        val item = listItems[position]
        holder.bind(item)
    }
}