package com.sanchezsergio.newsapp.view.activity.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sanchezsergio.newsapp.persistence.repository.TopHeadlinesITopHeadlinesRepository

class HomeViewModel @ViewModelInject constructor(
	repository: TopHeadlinesITopHeadlinesRepository
	) : ViewModel() {
	val flowRemoteAndDb = repository.getTopHeadlines()
}