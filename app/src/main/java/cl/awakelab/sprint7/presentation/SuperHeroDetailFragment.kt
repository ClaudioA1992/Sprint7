package cl.awakelab.sprint7.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.sprint7.R
import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity
import cl.awakelab.sprint7.databinding.FragmentSuperHeroDetailBinding
import coil.load

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SuperHeroDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperHeroDetailFragment : Fragment() {

    lateinit var binding: FragmentSuperHeroDetailBinding
    private val superHeroesViewModel: SuperHeroesViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuperHeroDetailBinding.inflate(LayoutInflater.from(activity))
        initData()
        return binding.root
    }

    private fun initData() {
        superHeroesViewModel.getSuperheroDetail(param1!!.toInt(), requireContext())
        lateinit var detail: SuperHeroDetailEntity
        superHeroesViewModel.detailLiveData(param1!!.toInt()).observe(viewLifecycleOwner) {
            if(it!=null) {
                detail = it
                Log.d("Into live data", "Objeto: ${it}")
                binding.textViewDetailName.text = it.name
                binding.textViewColor.text = it.color
                binding.textViewDetailOrigin.text = it.origin
                binding.textViewDetailPower.text = it.power
                binding.textViewDetailCreationYear.text = it.creationYear.toString()
                binding.imageView.load(it.imageUrl)
                if(it.translation) {
                    binding.textViewTranslation.text = R.string.translation_true.toString()
                } else {
                    binding.textViewTranslation.text = R.string.translation_false.toString()
                }
            }
        }
        binding.buttonVote.setOnClickListener {
            Log.d("Button vote", "Button pressed")
            val intent = Intent(Intent.ACTION_SENDTO)
            val mail = arrayOf("comicconchile@hotmail.com")
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Votación ${detail.name} ")
            intent.putExtra(Intent.EXTRA_TEXT, "Hola\n" +
                    "Quiero que el siguiente super héroe ${detail.name} aparezca, en la nueva edición de\n" +
                    "biografías animadas.\n" +
                    "Número contacto: +56_________\n" +
                    "Gracias.")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SuperHeroDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SuperHeroDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}