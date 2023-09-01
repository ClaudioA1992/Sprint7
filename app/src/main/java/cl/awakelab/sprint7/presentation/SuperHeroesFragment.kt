package cl.awakelab.sprint7.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.sprint7.R
import cl.awakelab.sprint7.databinding.FragmentSuperHeroesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SuperHeroesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperHeroesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSuperHeroesBinding
    val superHeroesViewModel: SuperHeroesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuperHeroesBinding.inflate(layoutInflater, container, false)
        superHeroesViewModel.getAllSuperheroes(requireContext())
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = SuperHeroAdapter()
        superHeroesViewModel.superHeroesLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        binding.recyclerView.adapter = adapter
    }


}

