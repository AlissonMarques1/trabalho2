package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.myapp.nav_fragment.HomeFragment
import com.example.myapp.nav_fragment.ProfileFragment
import com.example.myapp.nav_fragment.SettingFragment
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar

// Classe principal da aplicação que herda de AppCompatActivity e implementa NavigationView.OnNavigationItemSelectedListener
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Variáveis para o layout da gaveta e a barra de ferramentas
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar

    // Método onCreate chamado quando a atividade é iniciada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Define o layout XML para a atividade
        initViews() // Chama o método para inicializar as visualizações
    }

    // Método para inicializar as visualizações e configurações da UI
    private fun initViews() {
        toolbar = findViewById<Toolbar>(R.id.toolbar) // Encontra a Toolbar definida no layout XML
        setSupportActionBar(toolbar) // Define a Toolbar como a ActionBar da atividade
        drawerLayout = findViewById(R.id.drawerLayout) // Encontra o DrawerLayout definido no layout XML
        setupActionBar() // Configura a ActionBar com o DrawerToggle
        setupNavigationView() // Configura o NavigationView
        replaceFragment(HomeFragment()) // Inicia com o HomeFragment como conteúdo principal
    }

    // Configura a ActionBar com o DrawerToggle para abrir e fechar a gaveta
    private fun setupActionBar() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer
        ) // Cria um ActionBarDrawerToggle com os textos de abertura e fechamento
        drawerLayout.addDrawerListener(toggle) // Adiciona o toggle como um listener para o estado da gaveta
        toggle.syncState() // Sincroniza o ícone do toggle com o estado da gaveta
    }

    // Configura o NavigationView e define o listener para os itens do menu
    private fun setupNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView) // Encontra o NavigationView no layout XML
        navigationView.setNavigationItemSelectedListener(this) // Define esta classe como o listener para os itens selecionados
    }

    // Substitui o fragmento atual pelo fragmento fornecido
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navFragment, fragment) // Define o container e o novo fragmento
            .commit() // Realiza a transação de substituição
    }

    // Método chamado quando um item do menu é selecionado
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { // Verifica o ID do item selecionado
            R.id.nav_home -> replaceFragment(HomeFragment()) // Substitui pelo HomeFragment
            R.id.nav_profile -> replaceFragment(ProfileFragment()).also { title = "Profile" } // Substitui pelo ProfileFragment e atualiza o título
            R.id.nav_setting -> replaceFragment(SettingFragment()).also { title = "Setting" } // Substitui pelo SettingFragment e atualiza o título
            R.id.nav_share -> showToast("Share Clicked") // Mostra um Toast para o item "Share"
            R.id.nav_logout -> showToast("Logout Clicked") // Mostra um Toast para o item "Logout"
        }
        drawerLayout.closeDrawer(GravityCompat.START) // Fecha a gaveta após a seleção
        return true // Retorna verdadeiro para indicar que o evento foi tratado
    }

    // Exibe um Toast com a mensagem fornecida
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show() // Cria e mostra o Toast
    }
}