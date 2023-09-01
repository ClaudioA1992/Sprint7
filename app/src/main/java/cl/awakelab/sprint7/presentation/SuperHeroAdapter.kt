package cl.awakelab.sprint7.presentation

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity
import cl.awakelab.sprint7.databinding.SuperheroItemBinding
import coil.load

class SuperHeroAdapter: RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {

    private lateinit var binding: SuperheroItemBinding

    class SuperHeroViewHolder(private val superHeroBinding: SuperheroItemBinding):
        RecyclerView.ViewHolder(superHeroBinding.root) {
        fun bind(superHero: SuperHeroEntity) {
            superHeroBinding.imageViewSuperHero.load(superHero.imageUrl)
            superHeroBinding.textViewName.text = superHero.name
            superHeroBinding.textViewOrigin.text = superHero.origin
            superHeroBinding.textViewPower.text = superHero.power
            superHeroBinding.textViewCreationYear.text = superHero.creationYear.toString()
            var bundle: Bundle = Bundle()
            superHeroBinding.constraintLayoutSuperHeroItem.setOnClickListener() {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}