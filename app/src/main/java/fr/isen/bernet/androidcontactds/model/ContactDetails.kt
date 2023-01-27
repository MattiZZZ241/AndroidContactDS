package fr.isen.bernet.androidcontactds

import com.google.gson.annotations.SerializedName


data class ContactDetails (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

): java.io.Serializable