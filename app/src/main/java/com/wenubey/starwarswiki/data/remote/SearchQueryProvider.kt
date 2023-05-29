package com.wenubey.starwarswiki.data.remote


interface SearchQueryProvider {
    fun getSearchQuery(): String

    fun setSearchQuery(query: String)
}

class SearchQueryProviderImpl() : SearchQueryProvider {

    private var searchQuery: String = ""
    override fun getSearchQuery(): String {
        return searchQuery
    }

    override fun setSearchQuery(query: String) {
        searchQuery = query
    }
}