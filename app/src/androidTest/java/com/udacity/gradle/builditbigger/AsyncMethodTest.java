package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Krenare Rexhepi on 2/7/2016.
 */
public class AsyncMethodTest extends AndroidTestCase {
    public AsyncMethodTest(Runnable runnable) {

    }

    // create  a signal to let us know when our task is done.
    public void testSomeAsynTask() throws Throwable {
        // create  a signal to let us know when our task is done.

        final CountDownLatch signal = new CountDownLatch(1);


        final AsyncTask<Pair<Context, String>, Void, String> myTask = new AsyncTask<Pair<Context, String>, Void, String>() {

            @Override
            protected String doInBackground(Pair<Context, String>... params) {
                //Do something meaningful.
                return "joke is retrived";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            /* This is the key, normally you would use some type of listener
             * to notify your activity that the async call was finished.
             *
             * In your test method you would subscribe to that and signal
             * from there instead.
             */
                signal.countDown();
            }
        };
        // Execute the async task on the UI thread! THIS IS KEY!
        new AsyncMethodTest(new Runnable() {

            @Override
            public void run() {
                //   myTask.execute("Do something");
                myTask.execute(new Pair<>(getContext(), ""));
            }
        }

        );

    /* The testing thread will wait here until the UI thread releases it
     * above with the countDown() or 30 seconds passes and it times out.
     */
        signal.await(30, TimeUnit.SECONDS);

        // The task is done, and now you can assert some things!
        assertTrue("Happiness", true);
    }
}

