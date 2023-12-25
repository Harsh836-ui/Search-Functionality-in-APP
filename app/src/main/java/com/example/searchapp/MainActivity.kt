package com.example.searchapp

import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movies: MutableList<Movie> = getMockMovies().toMutableList()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter with an empty list
        adapter = MovieAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Set up the search view listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Optional: Handle submission if needed
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform live filtering and highlight as the user types
                filterMovies(newText.orEmpty())
                return true
            }
        })


        // Optional: Set a click listener to clear the search query when the close button is clicked
        binding.searchView.setOnCloseListener {
            clearSearch()
            false
        }

        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, R.drawable.divider))
    }

    private fun filterMovies(query: String) {
        val filteredMovies = getMockMovies().filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.releaseDate.contains(query, ignoreCase = true) ||
                    it.overview.contains(query, ignoreCase = true)
        }
        adapter.updateMovies(filteredMovies, query)

        // Optional: Show/hide the RecyclerView based on the filter results
        if (filteredMovies.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun clearSearch() {
        // Clear the search query and update the adapter with an empty list
        binding.searchView.setQuery("", false)
        adapter.updateMovies(emptyList(), "")
        binding.recyclerView.visibility = View.GONE
    }

    //list of my movies where you can search : currently static
    private fun getMockMovies(): List<Movie> {
        return listOf(
            Movie(
                "Inception",
                "2010-07-16",
                "A thief who enters the dreams of others to steal their secrets."
            ),
            Movie(
                "The Shawshank Redemption",
                "1994-09-23",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            ),
            Movie(
                "The Dark Knight",
                "2008-07-18",
                "When the menace known as The Joker emerges, Batman must confront him to bring justice."
            ),
            Movie(
                "Pulp Fiction",
                "1994-10-14",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
            ),
            Movie(
                "The Godfather",
                "1972-03-24",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."
            ),
            Movie(
                "Schindler's List",
                "1993-12-15",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis."
            ),
            Movie(
                "Forrest Gump",
                "1994-07-06",
                "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75."
            ),
            Movie(
                "The Matrix",
                "1999-03-31",
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."
            ),
            Movie(
                "Titanic",
                "1997-12-19",
                "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic."
            ),
            Movie(
                "The Lord of the Rings: The Return of the King",
                "2003-12-17",
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
            ),
            Movie(
                "The Silence of the Lambs",
                "1991-02-14",
                "A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer."
            ),
            Movie(
                "The Usual Suspects",
                "1995-08-16",
                "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat."
            ),
            Movie(
                "The Departed",
                "2006-10-06",
                "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston."
            ),
            Movie(
                "Fight Club",
                "1999-10-15",
                "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into much more."
            ),
            Movie(
                "The Social Network",
                "2010-09-24",
                "As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea, and by the co-founder who was later squeezed out of the business."
            ),
            Movie(
                "Eternal Sunshine of the Spotless Mind",
                "2004-03-19",
                "When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories."
            ),
            Movie(
                "Goodfellas",
                "1990-09-12",
                "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate."
            ),
            Movie(
                "The Green Mile",
                "1999-12-10",
                "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift."
            ),
            Movie(
                "The Shining",
                "1980-05-23",
                "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future."
            ),
            Movie(
                "Gladiator",
                "2000-05-01",
                "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery."
            ),
            Movie(
                "Toy Story",
                "1995-11-22",
                "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room."
            ),
            Movie(
                "Finding Nemo",
                "2003-05-30",
                "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home."
            ),
            Movie(
                "Shrek",
                "2001-04-22",
                "A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back."
            ),
            Movie(
                "Spirited Away",
                "2001-07-20",
                "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts."
            ),
            // Add more movies as needed
        )
    }
}