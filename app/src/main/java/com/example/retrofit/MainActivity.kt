package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.remote.PokemonEntry
import com.example.retrofit.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create()

        val response = retrofit.getPokemonById("27")


        response.enqueue(object : Callback<PokemonEntry>{

            override fun onResponse(call: Call<PokemonEntry>, response : Response<PokemonEntry>){

                Log.i("retrofitResponse", "res: ${response.body()}" )

                val stats = response.body()?.stats

                if (stats != null){
                    for (stat in stats){
                        Log.i("retrofitResponse", "${stat.stat.stat_value} : ${stat.base_stat}" )

                    }
                }

                val ty = response.body()?.types



                if(ty != null){

                         for (type in  ty){

                        Log.i("retrofitResponse", "name  : ${type.type.name}" )
                    }
                }



                      var fr = response.body()?.sprites

                              if (fr != null) {
                                    Log.i("retrofitResponse", "front_default : ${fr.front_default}")
                }
            }

             override fun onFailure(call: Call<PokemonEntry>, t: Throwable){
                  Log.e("retrofitResponse", "error: ${t.message}")
            }
        }



        )


    }
}