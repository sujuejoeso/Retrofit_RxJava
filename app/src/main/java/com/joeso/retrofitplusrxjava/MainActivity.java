package com.joeso.retrofitplusrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joeso.retrofitplusrxjavatranslator.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        result=findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl("http://dummy.restapiexample.com")
                        .build();
                WebApi api= retrofit.create(WebApi.class);
                Observable<ResponseData> mObservable = api.getData();
                mObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseData>() {
                            @Override
                            public void accept(ResponseData responseData) throws Exception {
                                String list="Name                 Age"+"\n";
                                List<Employee> employees= responseData.getData();
                                for(int i=0;i<employees.size();i++) {
                                    Employee employee=employees.get(i);
                                    list += employee.getEmployeeName() +"      " +employee.getEmployeeAge()+"\n";
                                }
                                result.setText(list);
                            }
                        }, new Consumer<Throwable>(){
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                result.setText(throwable.getMessage());
                            }
                        });
            }
        });
    }
}
