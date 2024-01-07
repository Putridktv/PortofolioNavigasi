package id.utdi.putridwioktaviani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.utdi.putridwioktaviani.data.Datasource
import id.utdi.putridwioktaviani.model.Affirmation
import id.utdi.putridwioktaviani.ui.DetailScreen
import id.utdi.putridwioktaviani.ui.DetailViewModel
import id.utdi.putridwioktaviani.ui.theme.PortofolioNavigasiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortofolioNavigasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortofolioNavigasiApp()
                }
            }
        }
    }
}

enum class PortofolioScreen(@StringRes val title: Int) { //enum class digunakan untuk membuat judul dalam header tiap halaman
    Start(title = R.string.main),
    Detail(title = R.string.detail),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortofolioNavigasiApp(
    detailViewModel: DetailViewModel = viewModel(), //menjalankan class OrderViewModel dalam composable CupcakeApp
    navController: NavHostController = rememberNavController() //membuat variabel navController yang akan digunakan untuk NavHost
) { //Membuat fungsi ProjekPortofolioApp
    var selectedItem by remember { mutableStateOf<Affirmation?>(null) } // Menambahkan state untuk item card list portofolio yang dipilih

    Scaffold(
        topBar = {  //digunakan untuk membuat bagian header dengan teks Portofolio/ top bar
            CenterAlignedTopAppBar(
                title = {
                    Text( //bagian untuk membuat teks top bar
                        text = "Portofolio",
                        fontWeight = FontWeight.Bold // agar font bold
                    )
                }, //bagian modifier untuuk mengatur tampilan top bar
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn( //menggunakan lazycolumn untuk menampilkan scrollable list kebawah
                modifier = Modifier.padding(innerPadding)
            ) {
                items(Datasource().loadAffirmations()) { affirmation ->
                    AffirmationCard( //memanggil fungsi AffirmationCard agar menjadi scrollable menggunakan lazycolumn
                        affirmation = affirmation,
                        onCardClick = { affirmation -> //berfungsi untuk merujuk data yang akan ditampilkan setelah meng-klik card item list berdasarkan data affirmation
                            selectedItem = affirmation
                        }
                    )
                }
            }
            selectedItem?.let { item ->
                // Gunakan NavHost untuk navigasi
                NavHost(navController = navController, startDestination = "main") {
                    // Halaman utama (main)
                    composable("main") {
                        LazyColumn(
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            items(Datasource().loadAffirmations()) { affirmation ->
                                AffirmationCard(
                                    affirmation = affirmation,
                                    onCardClick = { affirmation ->
                                        // Navigasi ke halaman detail dengan membawa Affirmation sebagai argumen
                                        navController.navigate("detail/${affirmation.stringResourceId}")
                                    }
                                )
                            }
                        }
                    }
                    // Halaman detail (detail/{itemId})
                    composable("detail/{itemId}") { backStackEntry ->
                        // Ambil itemId dari argumen
                        val itemId = backStackEntry.arguments?.getString("itemId")
                        // Konversi itemId menjadi Int (sebagai contoh, berdasarkan stringResourceId)
                        val affirmationId = itemId?.toIntOrNull()
                        // Ambil Affirmation dari Datasource berdasarkan itemId
                        val affirmation = Datasource().loadAffirmations().find { it.stringResourceId == affirmationId }

                        // Set data ke ViewModel berdasarkan Affirmation
                        detailViewModel.setData(affirmation)

                        // Tampilkan DetailScreen
                        DetailScreen(affirmation = affirmation, onClose = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable //fungsi untuk membuat tampilan tiap item card
fun AffirmationCard(affirmation: Affirmation, onCardClick: (Affirmation) -> Unit) {
    Card( //menggunakan card agar mempercepat pembuatan shape column
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick(affirmation) }
    ) {
        Row {//menggunakan row agar gambar dengan teks sejajar
            Image(
                painter = painterResource(affirmation.imageResourceId), //memanggil gambar berdasarkan imageResourceId dalam Datasource
                contentDescription = stringResource(affirmation.stringResourceId), //memanggil teks berdasarkan stringResourceId dalam Datasource
                modifier = Modifier //menggunakna modifier unntuk mengatur tampilan image
                    .size(100.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.FillHeight
            )
            Column ( //menggunakan column agar teks dapat urut kebawah
                modifier = Modifier
                    .align(Alignment.CenterVertically) // Memusatkan teks secara center /tengah vertikal
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(affirmation.stringResourceId), //memanggil teks berdasarkan stringResourceId dalam datasource
                    style = MaterialTheme.typography.headlineSmall, //mengatur ukuran teks
                    textAlign = TextAlign.Center //mengatur teks agar rata tengah
                )

                Text(
                    text = stringResource(affirmation.stringCreatorResourceId), //memanggil teks berdasarkan stringResourceId dalam datasource
                    style = MaterialTheme.typography.bodyLarge, //mengatur ukuran teks
                    textAlign = TextAlign.Center //mengatur teks agar rata tengah
                )
            }
        }
    }
}