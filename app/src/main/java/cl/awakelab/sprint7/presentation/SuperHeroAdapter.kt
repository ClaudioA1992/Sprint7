package cl.awakelab.sprint7.presentation

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint7.R
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity
import cl.awakelab.sprint7.databinding.SuperheroItemBinding
import coil.load

class SuperHeroAdapter: RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {

    private lateinit var binding: SuperheroItemBinding
    private val superHeroList = mutableListOf<SuperHeroEntity>()

    class SuperHeroViewHolder(private val superHeroBinding: SuperheroItemBinding):
        RecyclerView.ViewHolder(superHeroBinding.root) {
        fun bind(superHero: SuperHeroEntity) {
            superHeroBinding.imageViewSuperHero.load(superHero.imageUrl)
            superHeroBinding.textViewName.text = superHeroBinding.root.getContext().getResources().getString(R.string.hero_name) + " " + superHero.name
            superHeroBinding.textViewOrigin.text = superHeroBinding.root.getContext().getResources().getString(R.string.origin) + " " + superHero.origin
            superHeroBinding.textViewPower.text = superHeroBinding.root.getContext().getResources().getString(R.string.power) + " " +  superHero.power
            superHeroBinding.textViewCreationYear.text = superHeroBinding.root.getContext().getResources().getString(R.string.creation_year) + " " + superHero.creationYear.toString()
            var bundle: Bundle = Bundle()
            bundle.putString("id", superHero.id.toString())
            superHeroBinding.constraintLayoutSuperHeroItem.setOnClickListener() {
                Navigation.findNavController(superHeroBinding.root).navigate(
                    R.id.action_superHeroesFragment_to_superHeroDetailFragment , bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        binding = SuperheroItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return SuperHeroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val superHero = superHeroList[position]
        holder.bind(superHero)
    }

    fun setData(superHeroes: List<SuperHeroEntity>) {
        this.superHeroList.clear()
        this.superHeroList.addAll(superHeroes)
        notifyDataSetChanged()
    }

}