package com.xbadut.pokedex.repository

import com.xbadut.pokedex.data.remote.PokemonApi
import com.xbadut.pokedex.data.remote.response.Pokemon
import com.xbadut.pokedex.data.remote.response.PokemonList
import com.xbadut.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokemonApi,
){
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit = limit, offset = offset)

        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getPokemon(
        name: String
    ) : Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(name = name)

        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}