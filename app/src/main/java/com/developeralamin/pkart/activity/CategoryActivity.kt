package com.developeralamin.pkart.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developeralamin.pkart.R
import com.developeralamin.pkart.adapter.CategoryProductAdapter
import com.developeralamin.pkart.databinding.ActivityCategoryBinding
import com.developeralamin.pkart.model.AddProductModel
import com.developeralamin.pkartadmin.adaper.ProductAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductes(intent.getStringExtra("cate"))

    }

    private fun getProductes(category: String?) {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products").whereEqualTo("productCategoryImg",category)
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.recyclerView.adapter = CategoryProductAdapter(this, list)
            }
    }
}