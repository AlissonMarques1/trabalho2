package com.example.myapp.bottom_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp.R


// TODO: Renomeie os parâmetros de argumentos, escolha nomes que correspondam
// aos parâmetros de inicialização do fragmento, por exemplo, ARG_ITEM_NUMBER
// Constantes para os argumentos do fragmento
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento de notificação.
 * Um simples [Fragment] que exibe notificações.
 * Use o método de fábrica [ProfileFragment.newInstance] para
 * criar uma instância deste fragmento.
 */
class RealTimeArticlesFragment : Fragment() {
    // Variáveis para os parâmetros do fragmento
    private var param1: String? = null
    private var param2: String? = null

    // Método onCreate onde os argumentos são inicializados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se existirem argumentos, atribui-os às variáveis locais
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Método onCreateView onde a UI do fragmento é inflada
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Infla o layout do fragmento
        return inflater.inflate(R.layout.real_time_articles_fragment, container, false)
    }

    // Companion object para fornecer um método de fábrica para criar uma instância deste fragmento
    companion object {
        /**
         * Método de fábrica para criar uma nova instância deste fragmento usando os parâmetros fornecidos.
         *
         * @param param1 Parâmetro 1.
         * @param param2 Parâmetro 2.
         * @return Uma nova instância do fragmento PopularArticlesFragment.
         */
        // TODO: Renomeie e mude os tipos e número de parâmetros
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopularArticlesFragment().apply {
                // Cria um Bundle e insere os argumentos
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
