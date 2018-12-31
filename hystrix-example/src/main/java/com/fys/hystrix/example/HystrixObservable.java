package com.fys.hystrix.example;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class HystrixObservable {

  public static void main(String[] args) {

    Observable<String> fs = new HelloWorldCommand("Hystrix").observe();
    fs.subscribe(new Action1<String>() {
      public void call(String s) {
       System.out.println(s);
      }
    });

    fs.subscribe(new Observer<String>() {
      public void onCompleted() {
        System.out.println("Execute omCompleted");
      }

      public void onError(Throwable throwable) {
        System.out.println("OnError" + throwable.getMessage());
      }

      public void onNext(String s) {
        System.out.println("OnNext: " + s);
      }
    });
  }
}
