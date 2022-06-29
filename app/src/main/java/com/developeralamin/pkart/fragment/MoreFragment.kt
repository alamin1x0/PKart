package com.developeralamin.pkart.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.developeralamin.pkart.R
import com.developeralamin.pkart.adapter.AllOrderAdapter
import com.developeralamin.pkart.databinding.FragmentHomeBinding
import com.developeralamin.pkart.databinding.FragmentMoreBinding
import com.developeralamin.pkart.model.AllOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MoreFragment : Fragment() {

    private lateinit var binding: FragmentMoreBinding

    private lateinit var list: ArrayList<AllOrderModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMoreBinding.inflate(layoutInflater)

        list = ArrayList()

        val preferences =
            requireContext().getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)

        Firebase.firestore.collection("allOrders")
            .whereEqualTo("userId", preferences.getString("number", "")!!)
            .get().addOnSuccessListener {
                list.clear()

                for (doc in it) {
                    val data = doc.toObject(AllOrderModel::class.java)
                    list.add(data)
                }
                binding.recyclerView.adapter = AllOrderAdapter(list, requireContext())
            }

        return binding.root
    }
}