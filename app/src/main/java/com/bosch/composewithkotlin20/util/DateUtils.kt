package com.bosch.composewithkotlin20.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
	
	companion object {
		const val DATE_FORMAT = "d MMMM yyyy h:mm a"
		
		fun formattedDate(milliseconds: Long, format: String = DATE_FORMAT): String {
			val date = Date(milliseconds)
			val convertedDate = SimpleDateFormat(format, Locale.getDefault())
			return convertedDate.format(date)
		}
	}
}