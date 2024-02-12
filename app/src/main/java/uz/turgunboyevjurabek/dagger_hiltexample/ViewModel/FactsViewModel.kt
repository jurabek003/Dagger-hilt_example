package uz.turgunboyevjurabek.dagger_hiltexample.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.CatsFacts
import uz.turgunboyevjurabek.dagger_hiltexample.Model.repo.MyRepozitory
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.dagger_hiltexample.utils.Resource
import javax.inject.Inject


@HiltViewModel
class FactsViewModel @Inject constructor(private val myRepozitory: MyRepozitory):ViewModel(){

    private val getApi=MutableLiveData<Resource<CatsFacts>>()

    fun getFactsFromApi():MutableLiveData<Resource<CatsFacts>>{
        viewModelScope.launch {
            getApi.postValue(Resource.loading("Loading at FactsViewModel"))
            try {
                val getData=async {
                    myRepozitory.getFacts()
                }.await()
                getApi.postValue(Resource.success(getData))
            }catch (e:Exception){
                getApi.postValue(Resource.error(e.message))
            }
        }


        return getApi
    }

}