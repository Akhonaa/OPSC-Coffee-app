package com.example.coffeeappv3.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeappv3.Model.CategoryModel
import com.example.coffeeappv3.Model.itemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    private  val firebaseDatabase=FirebaseDatabase.getInstance()

    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    private val _popular=MutableLiveData<MutableList<itemsModel>>()
    private val _offer=MutableLiveData<MutableList<itemsModel>>()


    val category: LiveData<MutableList<CategoryModel>> = _category
    val popular: LiveData<MutableList<itemsModel>> = _popular
    val offer: LiveData<MutableList<itemsModel>> = _offer



    fun loadCategory(){
        val Ref = firebaseDatabase.getReference(    "Category")
        Ref.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }

                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }

    fun loadPopular(){
        val Ref=firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val lists =  mutableListOf<itemsModel>()

                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(itemsModel::class.java)

                    if(list!=null){
                        lists.add(list)
                    }
                }

                _popular.value = lists

            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }

    fun loadOffer(){
        val Ref=firebaseDatabase.getReference("Offers")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val lists =  mutableListOf<itemsModel>()

                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(itemsModel::class.java)

                    if(list!=null){
                        lists.add(list)
                    }
                }

                _offer.value = lists

            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }

}