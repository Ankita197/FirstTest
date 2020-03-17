package com.ankita.firsttestdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvItemShare;
    ArrayList<InvestedShare> itemsInvestedShare=new ArrayList<>();
    ArrayList<InvestedShare> newItemsInvestedShare=new ArrayList<>();
    ArrayList<InvestedShare> itemsResult=new ArrayList<>();
    Button btnCalculate;
    EditText edtAmount;
    Double totalProfit=0.0,totalAmount=0.0;
    TextView tvProfit,tvTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setItemInList();
        setProfit();
        getSortedArrayList();
        getReverseList();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsResult.clear();
                double amount=Double.parseDouble(edtAmount.getText().toString());
                for (InvestedShare data:newItemsInvestedShare){
                    if(amount>data.getBuy()&&data.getProfit()>0){
                        itemsResult.add(data);
                        amount=amount-data.getBuy();
                    }
                }
                setAdapter();
                setTotalAmount();
                setTotalProfit();
            }
        });
    }

    private void getReverseList() {
        for (int i = itemsInvestedShare.size() - 1; i >= 0; i--) {
            newItemsInvestedShare.add(itemsInvestedShare.get(i));
        }
    }

    private void setTotalProfit() {
        totalProfit=0.0;
        for(InvestedShare result:itemsResult){
            totalProfit=totalProfit+result.getProfit();
        }
        Double truncatedDouble = BigDecimal.valueOf(totalProfit)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        tvProfit.setText(String.valueOf(truncatedDouble));
    }

    private void setTotalAmount() {
        totalAmount=0.0;
        for(InvestedShare result:itemsResult){
            totalAmount=totalAmount+result.getBuy();
        }
        Double truncatedDouble = BigDecimal.valueOf(totalAmount)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        tvProfit.setText(String.valueOf(truncatedDouble));
        tvTotalPrice.setText(String.valueOf(totalAmount));
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvItemShare.setLayoutManager(linearLayoutManager);
        ItemShareAdapter itemShareAdapter=new ItemShareAdapter(itemsResult,this);
        rvItemShare.setAdapter(itemShareAdapter);
    }

    private void getSortedArrayList() {

        for(int i=0;i<itemsInvestedShare.size()-1;i++){
            int max=i;
            for (int j=i+1;j<itemsInvestedShare.size();j++){
                if(itemsInvestedShare.get(max).getProfit()>itemsInvestedShare.get(j).getProfit())
                {
                    max=j;
                }
            }
            if(max!=i){
                Collections.swap(itemsInvestedShare, i, max);
            }
        }
    }

    private void setProfit() {
        for (InvestedShare data:itemsInvestedShare){
            data.setProfit(data.getSell()-data.getBuy());
        }
    }

    private void setItemInList() {
        itemsInvestedShare.add(new InvestedShare("L&T",100.00,112.00));
        itemsInvestedShare.add(new InvestedShare("NHPC",25.60,28.80));
        itemsInvestedShare.add(new InvestedShare("SBI CARD",80.00,85.40));
        itemsInvestedShare.add(new InvestedShare("Apollo",250.00,195.00));
        itemsInvestedShare.add(new InvestedShare("Edelweiss ",290.24,62.80));
        itemsInvestedShare.add(new InvestedShare("ITC", 153.95,244.94));
        itemsInvestedShare.add(new InvestedShare("TCS", 456.00,561.00));
        itemsInvestedShare.add(new InvestedShare("CEAT",200.00, 205.44));
        itemsInvestedShare.add(new InvestedShare("HDFC", 806.00  ,1008.50));
        itemsInvestedShare.add(new InvestedShare("PowerGrid",190.00,565.45));
        itemsInvestedShare.add(new InvestedShare("AXISBank ",30.50,80.54));
        itemsInvestedShare.add(new InvestedShare("BajajFinsv", 31.60 ,81.65));
        itemsInvestedShare.add(new InvestedShare("CIPLA  ",140.00 ,157.45));
        itemsInvestedShare.add(new InvestedShare("EKC",80.50, 88.50));
        itemsInvestedShare.add(new InvestedShare("EMCO", 25.60,00.45));
    }

    private void init() {
        rvItemShare=findViewById(R.id.rvItemShare);
        edtAmount=findViewById(R.id.edtAmount);
        btnCalculate=findViewById(R.id.btnCalculate);
        tvProfit=findViewById(R.id.tvProfit);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
    }
}
