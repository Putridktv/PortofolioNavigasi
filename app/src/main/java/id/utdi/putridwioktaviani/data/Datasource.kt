package id.utdi.putridwioktaviani.data

import id.utdi.putridwioktaviani.R
import id.utdi.putridwioktaviani.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> { //merupakan data teks yang diambil dari Strings.xml dan gambar yang ditampung dalam class Datasource yang digunakan dalam main activity
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.string.porto_1_desc, R.string.creator, R.drawable.image_1),
            Affirmation(R.string.affirmation2, R.string.porto_2_desc, R.string.creator, R.drawable.image_2),
            Affirmation(R.string.affirmation3, R.string.porto_3_desc, R.string.creator, R.drawable.image_3),
            Affirmation(R.string.affirmation4, R.string.porto_4_desc, R.string.creator, R.drawable.image_4),
            Affirmation(R.string.affirmation5, R.string.porto_5_desc, R.string.creator, R.drawable.image_5),
            Affirmation(R.string.affirmation6, R.string.porto_6_desc, R.string.creator, R.drawable.image_6),
            Affirmation(R.string.affirmation7, R.string.porto_7_desc, R.string.creator, R.drawable.image_7),
            Affirmation(R.string.affirmation8, R.string.porto_8_desc, R.string.creator, R.drawable.image_8),
            Affirmation(R.string.affirmation9, R.string.porto_9_desc, R.string.creator, R.drawable.image_9),
            Affirmation(R.string.affirmation10, R.string.porto_10_desc, R.string.creator, R.drawable.image_10)
        )
    }
}