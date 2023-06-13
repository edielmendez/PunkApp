package mx.com.ediel.mv.punkapp.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64

class PAUtilities {
    companion object{
        fun base64Encode(value: String): String{
            return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Base64.getEncoder().encodeToString(value.toByteArray())
            }else{
                android.util.Base64.encodeToString(value.toByteArray(), android.util.Base64.DEFAULT)
            }

        }

        fun base64Decode(base64: String): String{
            return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String(Base64.getDecoder().decode(base64))
            }else{
                String(android.util.Base64.decode(base64, android.util.Base64.DEFAULT))
            }
        }
    }
}