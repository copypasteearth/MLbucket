package jacs.apps.mlbucket.ui.text

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jacs.apps.kotlinwiki.KotlinWiki
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        viewModelScope.launch(Dispatchers.IO){
            val wikiSearch = KotlinWiki()
            val response = wikiSearch.searchResponse("hello world")
            val page = wikiSearch.getPage(response.query?.pages!![0].title!!)
            viewModelScope.launch(Dispatchers.Main){
                value = wikiSearch.getSimplyParsedWikiText()
            }

        }
        //value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}