package com.developeralamin.pkart.model


data class AddProductModel(
    val productName: String? = "",
    val productDescription: String? = "",
    val productCoverImg: String? = "",
    val productCategoryImg: String? = "",
    val productId: String? = "",
    val productMrp: String? = "",
    val productSp: String? = "",
    val productImages: ArrayList<String> = ArrayList()
)
