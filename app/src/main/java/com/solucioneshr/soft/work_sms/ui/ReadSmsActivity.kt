package com.solucioneshr.soft.work_sms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.solucioneshr.soft.work_sms.R
import com.solucioneshr.soft.work_sms.controller.AdapterReadSMS
import com.solucioneshr.soft.work_sms.databinding.ActivityReadSmsBinding
import com.solucioneshr.soft.work_sms.model.SmsModel

class ReadSmsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadSmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadSmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneNumber = intent.getStringExtra("phoneNumber")

        if (!phoneNumber.isNullOrEmpty()) {
            val listItems = ArrayList<SmsModel>()

            binding.recyclerSms.layoutManager = LinearLayoutManager(this)
            binding.recyclerSms.setHasFixedSize(true)

            val numberCol = Telephony.TextBasedSmsColumns.ADDRESS
            val textCol = Telephony.TextBasedSmsColumns.BODY
            val dateCol = Telephony.TextBasedSmsColumns.DATE

            val projection = arrayOf(numberCol, textCol, dateCol)

            val cursor = contentResolver.query(
                Telephony.Sms.CONTENT_URI,
                projection, Telephony.Sms.ADDRESS + "='" + phoneNumber + "'", null, null
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

            val adapter = AdapterReadSMS(listItems)
            binding.recyclerSms.adapter = adapter

        } else{
            Snackbar.make(binding.recyclerSms, getString(R.string.errorData), Snackbar.LENGTH_LONG).show()
        }
    }
}