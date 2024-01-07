package id.utdi.putridwioktaviani.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation( //merupakan data class yang digunakan dalam aplikasi, dipanggil dengan cara menggunakan variabelnya untuk memanggil data
    @StringRes val stringResourceId: Int,
    @StringRes val stringDetailResourceId: Int,
    @StringRes val stringCreatorResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
