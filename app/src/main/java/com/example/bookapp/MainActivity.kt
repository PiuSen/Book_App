package com.example.bookapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.bookapp.ui.theme.BookAppTheme
import com.example.bookapp.ui.theme.Detailed

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopAppBar(title = {
                        Text("BookApp")
                })
                    BookList(bookList, this@MainActivity)
            }
        }
    }
}
val bookList: List<Book> = listOf(Book(R.drawable.anny_frank, "The Diary of A Young Girl", "Anne Frank"),
    Book(R.drawable.alexander_the_great, "Alexander the Great", "Jacob Abbott"),
    Book(R.drawable.autobiography_of_a_yogi, "Autobiography of a Yogi", "Paramahansa Yogananda"),
    Book(R.drawable.fluffy_and_me, "Fluffy and Me", "Anita Krishan"),
    Book(R.drawable.my_inventions, "My Inventions", "Nikola Tesla"),
    Book(R.drawable.the_enchanted_castle, "The Enchanted Castle", "E. Nesbit"),
    Book(R.drawable.the_secret_garden, "The Secret Garden", " Frances Hodgson Burnett"))

@Parcelize
data class Book(
    @DrawableRes val imageResource: Int,
    val bookTitle: String,
    val bookAuthor: String
) : Parcelable
@Composable
fun BookList(books: List<Book>, context: Context) {

    LazyColumn() {
        items(books) { book ->
            BookListItem(book, context)
        }
    }
}
@Composable
fun BookListItem(book: Book, context: Context) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                val intent = Intent(context, Detailed::class.java)
                intent.putExtra("bookData", book)
                startActivity(context, intent, null)

            }
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            BookImage(book)
            Column(
                modifier = Modifier
                    .weight(6f, true)
                    .padding(20.dp, 0.dp, 0.dp, 0.dp),
            ) {
                Text(
                    text = book.bookTitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red
                )
                Text(
                    text = "by ${book.bookAuthor}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                )

            }
        }
    }
}
@Composable
fun BookImage(book: Book?) {
    //used to display an image.
    Image(
        painter = painterResource(book!!.imageResource),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun bookListPreview() {
    BookAppTheme{
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = {
                Text("BookApp")
            })
            BookList(
                bookList,
                LocalContext.current
            )
        }
    }

}
}

