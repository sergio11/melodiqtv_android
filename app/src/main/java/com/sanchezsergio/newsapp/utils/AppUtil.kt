package com.sanchezsergio.newsapp.utils

import com.sanchezsergio.newsapp.R

fun getItemStatusDrawable(status:String):Int{
	return when(status){
		"Alive" ->  R.drawable.ic_baseline_brightness_green_1_4
		
		"Dead" ->  R.drawable.ic_baseline_brightness_red_1_4
		
		else ->  R.drawable.ic_baseline_brightness_unknown_1_4
	}
}

fun getItemDashString(status: String?,species:String?):String{
	return if(status.isNullOrBlank() || species.isNullOrBlank()){
		""
	}else{
		"-"
	}
}