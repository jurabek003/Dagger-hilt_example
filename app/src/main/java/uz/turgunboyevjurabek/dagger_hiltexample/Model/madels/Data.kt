package uz.turgunboyevjurabek.dagger_hiltexample.Model.madels


import com.google.gson.annotations.SerializedName

 class Data(){
     val fact: String?=null
     val length: Int?=null
     override fun toString(): String {
         return "Data(fact=$fact, length=$length)"
     }

 }