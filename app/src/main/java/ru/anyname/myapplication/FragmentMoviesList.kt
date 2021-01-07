package ru.anyname.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.android.synthetic.main.fragment_movies_list.*

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bg_movie.setOnClickListener {
            findNavController().navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
        }
    }
}

//class Fragment1 : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        // получаем ссылку на макет фрагмента
//        val fragmentLayout = inflater.inflate(R.layout.fragment_fragment1, container, false)
//
//        // получаем ссылку на NavController (навигационный контроллер)
//        val navController = NavHostFragment.findNavController(this)

//        // слушатели кнопок, которые передают адрес навигационному контроллеру
//        fragmentLayout.button2.setOnClickListener { navController.navigate(R.id.fragment2) }
//        fragmentLayout.button3.setOnClickListener { navController.navigate(R.id.fragment3) }
//        fragmentLayout.button4.setOnClickListener { navController.navigate(R.id.fragment4) }
//
//        // возвращаем макет фрагмента
//        return fragmentLayout
//    }
//
//}