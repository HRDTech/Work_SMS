package com.solucioneshr.soft.work_sms.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import androidx.recyclerview.widget.LinearLayoutManager
import com.solucioneshr.soft.work_sms.controller.AdapterNumberSms
import com.solucioneshr.soft.work_sms.controller.AdapterReadSMS
import com.solucioneshr.soft.work_sms.databinding.ActivityNumberSmsBinding
import com.solucioneshr.soft.work_sms.model.SmsModel

private lateinit var binding: ActivityNumberSmsBinding

class NumberSmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberSmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listItems = ArrayList<SmsModel>()

        binding.recyclerNumberSms.layoutManager = LinearLayoutManager(this)
        binding.recyclerNumberSms.setHasFixedSize(true)

        val numberCol = Telephony.TextBasedSmsColumns.ADDRESS
        val textCol = Telephony.TextBasedSmsColumns.BODY
        val dateCol = Telephony.TextBasedSmsColumns.DATE

        val projection = arrayOf(numberCol, textCol, dateCol)

        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            projection, null, null, null
        )

        val numberColIdx = cursor!!.getColumnIndex(numberCol)
        val textColIdx = cursor.getColumnIndex(textCol)
        val dateColIdx = cursor.getColumnIndex(dateCol)

        while (cursor.moveToNext()) {
            listItems.add(
                SmsModel(
                    cursor.getString(numberColIdx),
                    cursor.getString(textColIdx),
                    cursor.getString(dateColIdx)
                )
            )
        }

        cursor.close()

        val listener = object : AdapterNumberSms.OnItemClickListener{
            override fun onSelectSms(data: SmsModel) {
                val intent = Intent(this@NumberSmsActivity, ReadSmsActivity::class.java)
                intent.putExtra("phoneNumber", data.numberPhone)
                startActivity(intent)
            }

        }
        val adapter = AdapterNumberSms(listener, listItems)
        binding.recyclerNumberSms.adapter = adapter

    }
}