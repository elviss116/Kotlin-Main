package com.example.kotlin_main.listar_mvvm.model

import androidx.lifecycle.MutableLiveData
import com.example.kotlin_main.ListarRetrofit.ApiInterface
import com.example.kotlin_main.listar_mvvm.mObject.Usuario3Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Usuario3ListRepository {

    var usuarioResponse = MutableLiveData<Usuario3Response>()

    fun getData(id: String) : MutableLiveData<Usuario3Response> {
        var api = ApiInterface.create().getUserMvvm(id)
        api.enqueue( object : Callback<Usuario3Response>{
            override fun onFailure(call: Call<Usuario3Response>, t: Throwable) {
                usuarioResponse.value = Usuario3Response(null, Exception(t.message))
            }

            override fun onResponse(
                call: Call<Usuario3Response>,
                response: Response<Usuario3Response>
            ) {
                //var p = Usuario3Response(response.body()!!.lista,null)
                //usuarioResponse.value = p
                usuarioResponse.value = Usuario3Response(response.body()!!.record,null)
            }
        })

        return usuarioResponse
    }

}