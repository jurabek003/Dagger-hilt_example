package uz.turgunboyevjurabek.dagger_hiltexample.Model.madels

import com.google.gson.annotations.SerializedName

class CatsFacts(){
    @SerializedName("current_page")
    val currentPage: Int?=null
    @SerializedName("data")
    val `data`: List<Data>?=null
    @SerializedName("first_page_url")
    val firstPageUrl: String?=null
    @SerializedName("from")
    val from: Int?=null
    @SerializedName("last_page")
    val lastPage: Int?=null
    @SerializedName("last_page_url")
    val lastPageUrl: String?=null
    @SerializedName("links")
    val links: List<Link>?=null
    @SerializedName("next_page_url")
    val nextPageUrl: String?=null
    @SerializedName("path")
    val path: String?=null
    @SerializedName("per_page")
    val perPage: Int?=null
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?=null
    @SerializedName("to")
    val to: Int?=null
    @SerializedName("total")
    val total: Int?=null
}