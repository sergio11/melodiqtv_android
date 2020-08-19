package com.sanchezsergio.newsapp.view.activity.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sanchezsergio.newsapp.R

class DashboardFragment : Fragment() {
	
	private val dashboardViewModel by lazy {
		ViewModelProvider(this).get(DashboardViewModel::class.java)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
		val textView: TextView = root.findViewById(R.id.text_dashboard)
		dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}


}