package id.utdi.putridwioktaviani.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import id.utdi.putridwioktaviani.R
import id.utdi.putridwioktaviani.model.Affirmation

class DetailViewModel : ViewModel() {
    val title = mutableStateOf<Int>(0)
    val description = mutableStateOf<Int>(0)
    val imageResourceId = mutableStateOf<Int>(0)

    // Fungsi untuk mengatur data berdasarkan itemId
    fun setData(affirmation: Affirmation?) {
        affirmation?.let {
            title.value = it.stringResourceId
            description.value = it.stringDetailResourceId
            imageResourceId.value = it.imageResourceId
        }
    }
}