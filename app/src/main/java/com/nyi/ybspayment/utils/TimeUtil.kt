package com.nyi.ybspayment.utils

import java.text.ParseException
import java.util.*
import java.text.SimpleDateFormat


class TimeUtil{
    companion object {
        val dateTimeFormat = SimpleDateFormat("dd.MM.yy hh:mm aaa")
        val dateFormat = SimpleDateFormat("dd.MM.yy")
        val timeFormat = SimpleDateFormat("hh:mm aaa")

        fun getCureenTime() : String{

            val calendar = Calendar.getInstance()

            val date = dateTimeFormat.format(calendar.time)

            return  date
        }

        fun getDateFromString(dateTimeString: String) : String{
            var date : Date = Date()
            try {
                date = dateTimeFormat.parse(dateTimeString)
            }catch (e : ParseException){
                e.printStackTrace()
            }
            val dateString = dateFormat.format(date)
            return dateString
        }

        fun getTimeFromString(dateTimeString: String) : String{
            var date : Date = Date()
            try {
                date = dateTimeFormat.parse(dateTimeString)
            }catch (e : ParseException){
                e.printStackTrace()
            }
            val timeString = timeFormat.format(date)
            return timeString
        }
    }
}