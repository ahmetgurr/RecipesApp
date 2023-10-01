package com.example.sisterslabprojectrecipes.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

//aynı baslık altında gecisYap fonksiyonunu oluşturdum, buradaki amaç ihtiyacımız doğrultusunda gerekli fonksiyonu kullanmak.

//fab click için
fun Navigation.gecisYap(id:Int, it: View) {
    findNavController(it).navigate(id)
}
//recyclerviewdeki item için
fun Navigation.gecisYap(it: View, id: NavDirections){
    findNavController(it).navigate(id)
}