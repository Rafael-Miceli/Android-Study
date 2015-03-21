//package com.unitteststudy.rafael.unitteststudy;
//
//import android.app.Activity;
//import android.net.Uri;
//import android.util.Log;
//
//import com.microsoft.windowsazure.mobileservices.MobileServiceUser;
//import com.microsoft.windowsazure.mobileservices.NextServiceFilterCallback;
//import com.microsoft.windowsazure.mobileservices.ServiceFilter;
//import com.microsoft.windowsazure.mobileservices.ServiceFilterRequest;
//import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
//import com.microsoft.windowsazure.mobileservices.ServiceFilterResponseCallback;
//import com.microsoft.windowsazure.mobileservices.UserAuthenticationCallback;
//
//import org.apache.http.StatusLine;
//
//import java.net.URISyntaxException;
//import java.util.concurrent.CountDownLatch;
//
///**
// * Created by Rafael on 19/03/2015.
// */
//private class MyServiceFilter implements ServiceFilter {
//
//    private boolean mShouldRetryAuth;
//    private boolean mIsCustomAuthProvider = false;
//    //A versão do AzureMobileService usada neste App é a 1.1.0
//    //Ao atualizar para nova versão, lembrar que a assinatura da Interface ServiceFilter mudou
//
//
//    @Override
//    public void handleRequest(final ServiceFilterRequest request, final NextServiceFilterCallback nextServiceFilterCallback,
//                              final ServiceFilterResponseCallback responseCallback) {
//
//
//        nextServiceFilterCallback.onNext(request, new ServiceFilterResponseCallback() {
//            @Override
//            public void onResponse(ServiceFilterResponse response, Exception exception) {
//
//                try {
//
//                    if (exception != null) {
//                        //Error begining here : Error while processing request
//
//                        Log.e("MyServiceFilter", "MyServiceFilter onResponse Exception: " + exception.getMessage());
//                    }
//
//
//                    StatusLine status = response.getStatus();
//                    int statusCode = status.getStatusCode();
//                    if (statusCode == 401) {
//                        final CountDownLatch latch = new CountDownLatch(1);
//                        //Log the user out but don't send them to the login page
//                        //If we shouldn't retry (or they've used custom auth),
//                        //we're going to kick them out for now
//                        //If you're doing custom auth, you'd need to show your own
//                        //custom auth popup to login with
//                        if (mShouldRetryAuth && !mIsCustomAuthProvider) {
//                            //Get the current activity for the context so we can show the login dialog
//                            AuthenticationApplication myApp = (AuthenticationApplication) mContext;
//                            Activity currentActivity = myApp.getCurrentActivity();
//                            mClient.setContext(currentActivity);
//
//                            currentActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mClient.login(mProvider, new UserAuthenticationCallback() {
//                                        @Override
//                                        public void onCompleted(MobileServiceUser user, Exception exception,
//                                                                ServiceFilterResponse response) {
//                                            if (exception == null) {
//                                                //Save their updated user data locally
//                                                saveUserData();
//                                                //Update the requests X-ZUMO-AUTH header
//                                                request.removeHeader("X-ZUMO-AUTH");
//                                                request.addHeader("X-ZUMO-AUTH", mClient.getCurrentUser().getAuthenticationToken());
//
//                                                //Add our BYPASS querystring parameter to the URL
//                                                Uri.Builder uriBuilder = Uri.parse(request.getUrl()).buildUpon();
//                                                uriBuilder.appendQueryParameter("bypass", "true");
//                                                try {
//                                                    request.setUrl(uriBuilder.build().toString());
//                                                } catch (URISyntaxException e) {
//                                                    Log.e(TAG, "Couldn't set request's new url: " + e.getMessage());
//                                                    e.printStackTrace();
//                                                }
//                                                latch.countDown();
//
//                                            } else {
//                                                Log.e(TAG, "User did not login successfully after 401");
//                                                //Kick user back to login screen
//                                                logout(true);
//                                            }
//
//                                        }
//                                    });
//                                }
//                            });
//                            try {
//                                latch.await();
//                            } catch (InterruptedException e) {
//                                Log.e(TAG, "Interrupted exception: " + e.getMessage());
//                                return;
//                            }
//
//                            nextServiceFilterCallback.onNext(request, responseCallback);
//                        } else {
//                            //Log them out and proceed with the response
//                            logout(true);
//                            responseCallback.onResponse(response, exception);
//                        }
//                    } else {//
//                        responseCallback.onResponse(response, exception);
//                    }
//                }
//                catch(Exception ex) {
//                    Log.e(TAG, "MyServiceFilter onResponse Exception: " + exception.getMessage());
//                }
//            }
//        });
//    }
