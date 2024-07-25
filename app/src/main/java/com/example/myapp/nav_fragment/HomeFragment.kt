package com.example.myapp.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapp.R
import com.example.myapp.bottom_fragment.RealTimeArticlesFragment
import com.example.myapp.bottom_fragment.HomePageFragment
import com.example.myapp.bottom_fragment.BooksFragment
import com.example.myapp.bottom_fragment.PopularArticlesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Definição da classe HomeFragment, que herda de Fragment
class HomeFragment : Fragment() {

    // Método chamado para inflar o layout do fragmento e configurar a interface do usuário
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_menu_bottom, container, false)

        // Obtém a referência para a BottomNavigationView do layout do fragmento
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Define o listener para os itens da BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            // Verifica qual item foi selecionado e executa a ação correspondente
            when (menuItem.itemId) {
                R.id.bottom_home_page -> {
                    replaceFragment(HomePageFragment()) // Substitui o fragmento atual pelo HomePageFragment
                    activity?.title = "Home" // Define o título da atividade como "Category"
                }
                R.id.bottom_history -> {
                    replaceFragment(BooksFragment()) // Substitui o fragmento atual pelo BooksFragment
                    activity?.title = "History" // Define o título da atividade como "History"
                }
                R.id.bottom_notification -> {
                    replaceFragment(PopularArticlesFragment()) // Substitui o fragmento atual pelo PopularArticlesFragment
                    activity?.title = "Notification" // Define o título da atividade como "Notification"
                }
                R.id.bottom_camera -> {
                    replaceFragment(RealTimeArticlesFragment()) // Substitui o fragmento atual pelo RealTimeArticlesFragment
                    activity?.title = "Cart" // Define o título da atividade como "Cart"
                }
            }
            true // Retorna true para indicar que o item foi selecionado
        }

        // Substitui o fragmento atual pelo HomePageFragment por padrão
        replaceFragment(HomePageFragment())
        // Define o título da atividade como "Category"
        activity?.title = "Home"
        // Define o item da BottomNavigationView como selecionado
        bottomNavigationView.selectedItemId = R.id.bottom_home_page

        // Obtém a referência para o FloatingActionButton do layout do fragmento
        val addFab = view.findViewById<FloatingActionButton>(R.id.addFabBtn)
        // Define o listener para o FloatingActionButton
        addFab.setOnClickListener {
            // Exibe um Toast informando que o botão de adicionar foi clicado
            Toast.makeText(context, "Add Clicked", Toast.LENGTH_LONG).show()
        }
        // Retorna a view do fragmento
        return view
    }

    // Método para substituir um fragmento dentro do container do fragmento atual
    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment, fragment) // Substitui o fragmento atual pelo fragmento fornecido
            .commit()
    }
}
