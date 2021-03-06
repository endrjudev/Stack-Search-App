package com.endrjudev.stacksearchapp.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        final Request request = chain.request();

        final long t1 = System.nanoTime();
        Timber.i("Sending request %s on %s%n%s",
                request.url(),
                chain.connection(),
                request.headers());

        final Response response = chain.proceed(request);

        final long t2 = System.nanoTime();
        Timber.i("Received response for %s in %.1fms%n%s",
                response.request().url(),
                (t2 - t1) / 1e6d,
                response.headers());

        return response;
    }
}
