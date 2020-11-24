package com.udacity.shoestore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel(application: Application) : AndroidViewModel(application) {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> = _shoeList

    val nameText = MutableLiveData<String>()
    val sizeText = MutableLiveData<Double>()
    val companyText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    private val _navigateToShoeListFragment = MutableLiveData<Boolean>()
    val navigateToShoeListFragment: LiveData<Boolean> = _navigateToShoeListFragment

    private val _navigateToDetailFragment = MutableLiveData<Boolean>()
    val navigateToDetailFragment: LiveData<Boolean> = _navigateToDetailFragment

    private val _navigateToWelcomeFragment = MutableLiveData<Boolean>()
    val navigateToWelcomeFragment: LiveData<Boolean> = _navigateToWelcomeFragment

    private val _navigateToInstructionFragment = MutableLiveData<Boolean>()
    val navigateToInstructionFragment: LiveData<Boolean> = _navigateToInstructionFragment

    private val _showMissingFieldsToast = MutableLiveData<Boolean>()
    val showMissingFieldsToast: LiveData<Boolean> = _showMissingFieldsToast

    fun addNewShoe() {
        if (!nameText.value.isNullOrEmpty() && !companyText.value.isNullOrEmpty() &&
                sizeText.value != null && !descriptionText.value.isNullOrEmpty()) {

            val shoe = Shoe(nameText.value!!, sizeText.value!!, companyText.value!!, descriptionText.value!!)
            if (_shoeList.value != null) {
                _shoeList.value?.add(shoe)
            } else {
                _shoeList.value = mutableListOf(shoe)
            }
            _navigateToShoeListFragment.value = true
        } else {
            _showMissingFieldsToast.value = true
        }

    }

    fun goToShoeList() {
        _navigateToShoeListFragment.value = true
    }

    fun doneNavigatingToShoeList() {
        _navigateToShoeListFragment.value = false
    }

    fun goToDetail() {
        _navigateToDetailFragment.value = true
    }

    fun doneNavigatingToDetail() {
        _navigateToDetailFragment.value = false
    }

    fun goToWelcome() {
        _navigateToWelcomeFragment.value = true
    }

    fun doneNavigatingToWelcome() {
        _navigateToWelcomeFragment.value = false
    }

    fun goToInstructions() {
        _navigateToInstructionFragment.value = true
    }

    fun doneNavigatingToInstructions() {
        _navigateToInstructionFragment.value = false
    }

    fun shownToast() {
        _showMissingFieldsToast.value = false
    }

}