package id.utdi.putridwioktaviani.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.utdi.putridwioktaviani.model.Affirmation

@Composable
fun DetailScreen(affirmation: Affirmation?, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp)
    ) {
        // Konten berada di dalam Column yang ditempatkan di bawah TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(top = AppBarDefaults.TopAppBarHeight)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            affirmation?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Gambar
                    Image(
                        painter = painterResource(id = it.imageResourceId),
                        contentDescription = "Detail Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Judul
                    Text(
                        text = stringResource(id = it.stringResourceId),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Deskripsi
                    Text(
                        text = stringResource(id = it.stringDetailResourceId),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Pembuat
                    Text(
                        text = stringResource(id = it.stringCreatorResourceId),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )

                    // Tombol untuk menutup halaman
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { onClose() }) {
                        Text("Close")
                    }
                }
            } ?: run {
                // Tampilan jika data Affirmation null
                Text("Detail not available")
            }
        }
    }
}