package com.p4r4d0x.punkapibeerviewer

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RetrofitClient {
//    private val client: OkHttpClient
//    /**
//     * Gson for the parser
//     */
//    private val gson: Gson = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        .setLenient()
//        .create()
//
//    /**
//     * Request a random beer from the backend
//     */
//    fun requestRandomBeer(
//        serviceUrl: String,
//        responseCallback: Callback<List<BeerDTO?>?>?
//    ) {
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(client)
//            .baseUrl(serviceUrl)
//            .build()
//        val service = retrofit.create(
//            PunkApiRetrofitService::class.java
//        )
//        service.getRandomBeer()!!.enqueue(responseCallback)
//    }
//
//    /**
//     * Request a beer that matches with the beerName passed
//     */
//    fun requestBeer(
//        serviceUrl: String,
//        beerName: String?,
//        responseCallback: Callback<List<BeerDTO?>?>?
//    ) {
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(client)
//            .baseUrl(serviceUrl)
//            .build()
//        val service = retrofit.create(
//            PunkApiRetrofitService::class.java
//        )
//        service.getBeerByName(beerName)!!.enqueue(responseCallback)
//    }
//
//    /**
//     * Default constructor. Instantiate the core properties for the retrofit client
//     */
//    init {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BASIC
//        logging.level = HttpLoggingInterceptor.Level.HEADERS
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//    }
}