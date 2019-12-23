package com.pani.tictactoe.restclient


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pani.tictactoe.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

abstract class BaseServiceRxWrapper(
) {
    protected val retrofit: Retrofit
    val ACCEPT_HEADER = "accept"
    val CONTENT_TYPE_HEADER = "content-type"
    val APPLICATION_JSON_VALUE = "application/json"
    val AUTHORIZATION_HEADER = "Authorization"
    val DEPRECATION_DIALOG_TITLE = "Notice"
    val DEPRECATED_DIALOG_ACTION_BUTTON_TITLE = "Upgrade"
    companion object{
        val SOMETHING_WENT_WRONG_ERROR_MESSAGE = "Something Went Wrong"
        val NO_INTERNET_CONNECTION = "No internet connection"
    }

    init {
        val requestInterceptor = Interceptor { chain ->
            val request = chain.request()
            val originalHttpUrl = request.url()


            var requestBuilder: Request.Builder

            val url: HttpUrl

            url = originalHttpUrl.newBuilder().build()

            requestBuilder = request.newBuilder()
                .addHeader(ACCEPT_HEADER, APPLICATION_JSON_VALUE)
                .addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON_VALUE)
                .url(url)

            val overridingRequest = requestBuilder.build()
            val response = chain.proceed(overridingRequest)

            when (response.code()) {
                HTTP_FORBIDDEN -> {
                    val errorObject: BaseResponseObject = this.getErrorObject(response)
                    val responseString = Gson().toJson(errorObject)
                    val body = ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                    GlobalLiveData.versionRestrictLiveData.postValue(
                        SingleEvent(
                            ShowCommonDialogEvent(
                                errorObject.message,
                                DEPRECATION_DIALOG_TITLE,
                                DEPRECATED_DIALOG_ACTION_BUTTON_TITLE
                            )
                        )
                    )
                    response.newBuilder().body(body).code(HTTP_FORBIDDEN).build()
                }
                HTTP_NOT_ACCEPTED -> {
                    val errorObject = this.getErrorObject(response)
                    val responseString = Gson().toJson(errorObject)
                    val body = ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                    GlobalLiveData.logOutLiveData.postValue(SingleEvent(errorObject.message))
                    response.newBuilder().body(body).code(HTTP_NOT_ACCEPTED).build()
                }
                else -> response
            }
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {

                this.level = HttpLoggingInterceptor.Level.BODY

        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(loggingInterceptor)
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.REST_API)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(this.getGson()))

        this.retrofit = retrofitBuilder.build()
    }

    private fun getErrorObject(response: Response): BaseResponseObject {
        return try {
            val responseBodyObjectConverter = retrofit.responseBodyConverter<BaseResponseObject>(
                BaseResponseObject::class.java,
                arrayOfNulls(0)
            )
            responseBodyObjectConverter.convert(response.body()!!)!!
        } catch (e: IOException) {
            BaseResponseObject(false, response.message())
        }
    }

    protected fun getGson(): Gson {

        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }
}
