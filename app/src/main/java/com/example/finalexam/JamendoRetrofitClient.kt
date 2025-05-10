import com.example.finalexam.data.api.JamendoApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object JamendoRetrofitClient {
    private const val BASE_URL = "https://api.jamendo.com/v3.0/"

    val jamendoApi: JamendoApiService by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Thời gian kết nối tối đa
            .readTimeout(30, TimeUnit.SECONDS)     // Thời gian đọc dữ liệu tối đa
            .writeTimeout(30, TimeUnit.SECONDS)    // Thời gian ghi dữ liệu tối đa
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Cung cấp client cho Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JamendoApiService::class.java)
    }
}
