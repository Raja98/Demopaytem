package com.example.su.demopaytem;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.stripe.android.model.Card;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText etcard_number,etexpiredate,et_cvc_number;
    TextView tv_pay_now;
    Spinner expiry_moth,expiry_year;
    String data[] = {" Expire month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String data1[]={"Expire year","2017","2018","2019","2020","2021","2022","2025","2026","2027","2028","2029","2030"};
     Card card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etcard_number=(EditText)findViewById(R.id.etcard_number);
        etexpiredate=(EditText)findViewById(R.id.etcard_number);
        et_cvc_number=(EditText)findViewById(R.id.et_cvc_number);
        tv_pay_now=(TextView)findViewById(R.id.tv_pay_now);
        expiry_moth = (Spinner) findViewById(R.id.expiry_moth);
        expiry_year = (Spinner) findViewById(R.id.expiry_year);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        expiry_moth.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data1);
        expiry_year.setAdapter(adapter1);

        tv_pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int expiremonth= Integer.parseInt(expiry_moth.getSelectedItem().toString());
                 int expireyear=Integer.parseInt(expiry_year.getSelectedItem().toString());

                onClickSomething(etcard_number.getText().toString(),expiremonth,expireyear,et_cvc_number.getText().toString());
            }
        });

//        CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
//
//        Card cardToSave = mCardInputWidget.getCard();
//        if (cardToSave == null) {
//            Toast.makeText(this, "Invalid card details", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Stripe stripe = new Stripe(MainActivity.this, "pk_test_6pRNASCoBOKtIshFeQd4XMUh");
//            stripe.createToken(
//                    cardToSave,
//                    new TokenCallback() {
//                        public void onSuccess(Token token) {
//
//
//                            // Send token to your server
//                        }
//                        public void onError(Exception error) {
//
//
//                            // Show localized error message
//                            Toast.makeText(MainActivity.this,
//                                    error.getLocalizedMessage(),
//                                    Toast.LENGTH_LONG
//                            ).show();
//                        }
//                    }
//            );
//        }



    }
    public void onClickSomething(String cardNumber, int cardExpMonth, int cardExpYear, String cardcvv) {



        Log.d("cardnumber",cardNumber);
        Log.d("cardExpMonth", String.valueOf(cardExpMonth));
        Log.d("cardExpYear", String.valueOf(cardExpYear));
        Log.d("cardcvv",cardcvv);
        card = new Card(cardNumber,cardExpMonth,cardExpYear,cardcvv);

        if (card.validateCard())

        {
            Stripe stripe = new Stripe(MainActivity.this, "pk_test_dBSscHrYswUsYbswbCbuvt1F");
            stripe.createToken(
                    card,
                    new TokenCallback() {
                        public void onSuccess(Token token) {
                            Log.d("id",token.getId());
                            Logger.MSG("@@ TOKEN finger-", token.getCard().getFingerprint() + "");
                            Logger.MSG("@@ TOKEN Brand-", token.getCard().getBrand() + "");
                            Logger.MSG("@@ TOKEN customerId-", token.getCard().getCustomerId() + "");
                            Logger.MSG("@@ TOKEN customerLast4Digits-", token.getCard().getLast4() + "");
                            Logger.MSG("@@ TOKEN ID-", token.getCard().getId() + "");
//                           // Logger.MSG("ADDRESS",token.getCard().getAddressCity());
//                            Logger.MSG("Country",token.getCard().getAddressCountry());
//                            Logger.MSG("State",token.getCard().getAddressState());
//                            Logger.MSG("ADDRESLINE1",token.getCard().getAddressLine1());
//                            Logger.MSG("Zip",token.getCard().getAddressZip());
                           // Log.d("bankaccount", String.valueOf(token.getBankAccount()));

                            // Send token to your server
                        }
                        public void onError(Exception error) {


                            // Show localized error message
                            Toast.makeText(MainActivity.this,
                                    error.getLocalizedMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });

        }
        card.validateNumber();
        card.validateCVC();
    }


}
